package com.utn.club.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "socios")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String dni;

    private String email;
    private String password;

    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaVencimientoMembresia;

    @Embedded
    private Direccion direccion;
}
