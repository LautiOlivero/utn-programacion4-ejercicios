package com.utn.registro.Service;

import com.utn.registro.dtos.ProductoRequestDTO;
import com.utn.registro.dtos.ProductoResponseDTO;
import com.utn.registro.models.Producto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductoService {
    public ProductoResponseDTO crear(ProductoRequestDTO request){
    Producto producto = new Producto();
    producto.setNombre(request.getNombre());
    producto.setPrecio(request.getPrecio());
    producto.setStock(request.getStock());
    producto.setCodigoSKU(request.getCodigoSKU());
    producto.setId(1L);
    producto.setFechaRegistro(LocalDateTime.now());

    ProductoResponseDTO response = new ProductoResponseDTO();
    response.setId(producto.getId());
    response.setNombre(producto.getNombre());
    response.setPrecio(producto.getPrecio());
    response.setStock(producto.getStock());
    response.setCodigoSKU(producto.getCodigoSKU());
    response.setFechaRegistro(producto.getFechaRegistro());

        response.setEstadoStock(calcularEstado(producto.getStock()));

        return response;
    }

    private String calcularEstado (Integer stock) {
        if (stock == 0) {
            return "SIN STOCK";
        } else if (stock < 10) {
            return "STOCK BAJO";
        } else {
            return "Disponible";
        }
    }
}


