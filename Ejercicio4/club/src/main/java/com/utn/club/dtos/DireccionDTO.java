package com.utn.club.dtos;

import com.utn.club.dtos.validation.OnCreate;
import com.utn.club.dtos.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DireccionDTO {
    @NotBlank(message = "{direccion.calle.notblank}", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 5, max = 150, message = "{direccion.calle.size}",groups = {OnCreate.class, OnUpdate.class} )
    private String calle;

    @NotBlank(message = "{direccion.ciudad.notblank}",groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 80, message = "{direccion.ciudad.size}", groups = {OnCreate.class, OnUpdate.class})
    private String ciudad;

    @NotBlank(message = "{direccion.provincia.notblank}", groups = {OnCreate.class, OnUpdate.class})
    private String provincia;

    @NotBlank(message = "{direccion.cp.notblank}", groups = {OnCreate.class, OnUpdate.class})
    @Pattern(regexp = "\\d{4}", message = "{direccion.cp.pattern}", groups = {OnCreate.class, OnUpdate.class})
    private String codigoPostal;

}
