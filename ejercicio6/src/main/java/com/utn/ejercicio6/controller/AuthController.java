package com.utn.ejercicio6.controller;

import com.utn.ejercicio6.dto.AuthResponseDTO;
import com.utn.ejercicio6.dto.LoginRequestDTO;
import com.utn.ejercicio6.dto.RegisterRequestDTO;
import com.utn.ejercicio6.model.Usuario;
import com.utn.ejercicio6.security.JwtUtils;
import com.utn.ejercicio6.service.AuthService;
import com.utn.ejercicio6.store.UsuarioStore;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioStore usuarioStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    // ENDPOINT 1: POST /api/auth/register
    // Público. Rol asignado por defecto: ROLE_PACIENTE.

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        // Verificar que username y email no estén en uso
        if (usuarioStore.existeUsername(request.getUsername())) {
            return ResponseEntity.status(409).body("El username ya está en uso");
        }
        if (usuarioStore.existeEmail(request.getEmail())) {
            return ResponseEntity.status(409).body("El email ya está registrado");
        }
        authService.registrar(request);
        return ResponseEntity.status(201).body("Usuario registrado correctamente");
    }

    // ENDPOINT 2: POST /api/auth/login
    // Público. Devuelve JWT con expiración de 1 hora.

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        // Spring Security verifica username y password internamente
        // Si son incorrectos lanza BadCredentialsException → 401 automático


        //Verificacion
        System.out.println("Intentando loguear usuario: " + request.getUsername());
        System.out.println("Contraseña recibida en DTO: " + request.getPassword());
        Usuario userEnMemoria = usuarioStore.buscarPorUsername(request.getUsername());
        if(userEnMemoria != null) {
            System.out.println("Contraseña guardada en memoria (debería ser un hash largo): " + userEnMemoria.getPassword());
        } else {
            System.out.println("El usuario no existe en la memoria.");
        }


        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername
                        (), request.getPassword())
        );

        // Obtener el usuario autenticado
        Usuario usuario = (Usuario) auth.getPrincipal();
        // Armar la lista de roles para incluir en el JWT
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : usuario.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        // Generar el JWT
        String token = jwtUtils.generarToken(usuario.getUsername(), roles);
        AuthResponseDTO response = new AuthResponseDTO(token, 3600, usuario
                .getUsername());
        return ResponseEntity.ok(response);
    }
}

