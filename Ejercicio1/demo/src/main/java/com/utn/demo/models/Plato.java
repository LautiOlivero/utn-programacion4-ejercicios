package com.utn.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plato {
    private Integer numeroPlato;
    private String nombre;
    private Double precio;
    private String descripcion;
}