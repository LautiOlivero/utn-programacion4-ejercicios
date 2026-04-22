package com.utn.club.controllers;

import com.utn.club.dtos.SocioRequestDTO;
import com.utn.club.dtos.SocioResponseDTO;
import com.utn.club.services.SocioService;
import com.utn.club.dtos.validation.OnCreate;
import com.utn.club.dtos.validation.OnUpdate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {
    private final SocioService socioService;

    @PostMapping
    public ResponseEntity<SocioResponseDTO> crearSocio(
            @Validated(OnCreate.class) @RequestBody SocioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.crearSocio(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> obtenerSocio(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.obtenerSocio(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> actualizarSocio(
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody SocioRequestDTO dto) {
        SocioResponseDTO actualizado = socioService.actualizarSocio(id, dto);
        return ResponseEntity.ok(actualizado);

    }

}