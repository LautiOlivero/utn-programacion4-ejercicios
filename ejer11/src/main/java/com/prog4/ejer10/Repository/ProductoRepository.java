package com.prog4.ejer10.Repository;

import java.math.BigDecimal;
import java.util.List;

import com.prog4.ejer10.Model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String parte);

    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);

}
