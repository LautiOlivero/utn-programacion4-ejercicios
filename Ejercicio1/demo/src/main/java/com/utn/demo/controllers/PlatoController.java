package com.utn.demo.controllers;

import com.utn.demo.models.Plato;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {

    private List<Plato> menu = new ArrayList<>();

    @PostMapping
    public String agregarPlato(@RequestBody Plato plato) {
        menu.add(plato);
        return "Plato creado exitosamente.";
    }

    @GetMapping
    public List<Plato> obtenerTodosLosPlatos() {
        return menu;
    }

    @GetMapping("/{numero}")
    public Plato obtenerPlatoPorNumero(@PathVariable Integer numero) {
        return menu.stream()
                .filter(p -> p.getNumeroPlato().equals(numero))
                .findFirst()
                .orElse(null);
    }
}