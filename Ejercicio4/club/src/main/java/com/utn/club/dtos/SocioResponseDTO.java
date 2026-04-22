package com.utn.club.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SocioResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDateTime fechaRegistro;
    private String estadoMembresia;
    private DireccionDTO direccion;
}
