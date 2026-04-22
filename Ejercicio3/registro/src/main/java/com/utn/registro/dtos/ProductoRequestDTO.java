package com.utn.registro.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductoRequestDTO {
    @NotBlank(message = "{producto.nombre.obligatorio}")
    @Size (min = 3, max = 100 ,message = "{producto.nombre.longitud}")
    private String nombre;

    @Size(max = 500, message = "{producto.descripcion.maximo}")
    private String descripcion;

    @NotNull(message = "{producto.precio.obligatorio}")
    @Positive(message = "{producto.precio.positivo}")
    @DecimalMax(value = "99999.99",message = "{producto.precio.maximo}")
    private BigDecimal precio;

    @NotNull(message = "{producto.stock.obligatorio}")
    @Min(value = 0, message = "{producto.stock.rango}")
    @Max(value = 9999, message = "{producto.stock.rango}")
    private Integer stock;

    @NotBlank(message = "{producto.emailProveedor.obligatorio}")
    @Email(message =  "{producto.emailProveedor.formato}")
    private String emailProveedor;

    @Future(message = "{producto.fechaVencimiento.future}")
    private LocalDate fechaVencimiento;

    @Pattern(regexp= "^[A-Z]{3}-\\d{4}$", message = "{producto.codigoSKU.formato}")
    private String codigoSKU;
}
