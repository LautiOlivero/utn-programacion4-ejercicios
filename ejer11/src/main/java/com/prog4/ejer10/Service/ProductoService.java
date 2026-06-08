package com.prog4.ejer10.Service;

import java.util.List;

import com.prog4.ejer10.Model.Producto;
import com.prog4.ejer10.Repository.ProductoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService (ProductoRepository repo){
        this.repo = repo; //inyeccion por construcctor
    }

    public Producto guardar(Producto producto){
        return repo.save(producto);
    }

    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id){
        return repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public List<Producto> listarTodos(){
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Producto> listarPaginado(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Producto actualizar(Long id, Producto datos){
        Producto existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        return existente;
    }

    public void eliminar (Long id){
        buscarPorId(id);
        repo.deleteById(id);
    }

}
