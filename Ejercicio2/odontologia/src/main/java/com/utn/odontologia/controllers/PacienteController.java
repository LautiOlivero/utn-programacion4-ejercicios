package com.utn.odontologia.controllers;

import com.utn.odontologia.models.Paciente;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private List<Paciente> pacientesRegistrados = new ArrayList<>();

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        pacientesRegistrados.add(paciente);
        return paciente;
    }

    @GetMapping
    public List<Paciente> obtenerTodosLosPacientes() {
        return pacientesRegistrados;
    }

    @GetMapping("/menores")
    public List<Paciente> obtenerPacientesMenores() {
        LocalDate fechaActual = LocalDate.now();

        return pacientesRegistrados.stream()
                .filter(p -> Period.between(p.getFechaNacimiento(), fechaActual).getYears() < 18)
                .collect(Collectors.toList());
    }
}
