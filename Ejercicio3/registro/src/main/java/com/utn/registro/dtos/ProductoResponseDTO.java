package com.utn.registro.dtos;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private String codigoSKU;
    private LocalDateTime fechaRegistro;
    private String estadoStock;
}

