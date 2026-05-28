package com.unidad5.ejercicio8.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.unidad5.ejercicio8.exception.ConflictException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        
        private final Map<String, UserDetails> usuariosEnMemoria = new ConcurrentHashMap<>();

        private final PasswordEncoder passwordEncoder;

        public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

       
        usuariosEnMemoria.put("lector", User.builder()
            .username("lector")
            .password(this.passwordEncoder.encode("password"))
            .roles("LECTOR")
            .build());

        usuariosEnMemoria.put("bibliotecario", User.builder()
            .username("bibliotecario")
            .password(this.passwordEncoder.encode("password"))
            .roles("BIBLIOTECARIO")
            .build());

        usuariosEnMemoria.put("admin", User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .roles("ADMIN")
            .build());
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = usuariosEnMemoria.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado en memoria: " + username);
        }
        return user;
    }

    public void registrarUsuario(String username, String password, String role) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username inválido");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password inválido");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role inválido");
        }

        if (usuariosEnMemoria.containsKey(username)) {
            throw new ConflictException("El usuario ya existe");
        }

        UserDetails nuevoUsuario = User.builder()
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .roles(role)
                .build();

        usuariosEnMemoria.put(username, nuevoUsuario);
    }
}