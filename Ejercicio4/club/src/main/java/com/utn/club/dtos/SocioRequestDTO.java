package com.utn.club.dtos;

import com.utn.club.dtos.validation.DniValido;
import com.utn.club.dtos.validation.OnCreate;
import com.utn.club.dtos.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SocioRequestDTO {

    @NotBlank(message = "{socio.nombre.notblank}", groups = {OnCreate.class, OnUpdate.class})
    private String nombre;

    @NotBlank(message = "{socio.apellido.notblank}", groups = {OnCreate.class, OnUpdate.class})
    private String apellido;

    @NotBlank(message = "{socio.dni.notblank}",  groups = OnCreate.class)
    @DniValido(groups = OnCreate.class)
    private String dni;

    @NotBlank(message = "{socio.email.notblank}", groups = OnCreate.class)
    @Email(message = "{socio.email.invalid}", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(message = "{socio.password.notblank}", groups = OnCreate.class)
    @Size(min = 8, message = "{socio.password.min}", groups = OnCreate.class)
    private String password;

    @NotNull(message = "{socio.fechanacimiento.notnull}", groups = OnCreate.class)
    @Past(message = "{socio.fechanacimiento.past}", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate fechaNacimiento;

    @NotNull(message = "{socio.direccion.notnull}", groups = OnCreate.class)
    @Valid
    private DireccionDTO direccion;


}
