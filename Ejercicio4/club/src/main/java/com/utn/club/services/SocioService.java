package com.utn.club.services;

import com.utn.club.dtos.DireccionDTO;
import com.utn.club.dtos.SocioRequestDTO;
import com.utn.club.dtos.SocioResponseDTO;
import com.utn.club.exceptions.DniDuplicadoException;
import com.utn.club.models.Direccion;
import com.utn.club.models.Socio;
import com.utn.club.repositories.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioResponseDTO crearSocio(SocioRequestDTO dto) {
        validarUnicidadDni(dto.getDni());

        Socio nuevoSocio = mapearAEntidad(dto);
        Socio socioGuardado = socioRepository.save(nuevoSocio);

        return mapearAResponse(socioGuardado);
    }

    public SocioResponseDTO obtenerSocio(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado con ID:" + id));
        return mapearAResponse(socio);
    }

    public SocioResponseDTO actualizarSocio(Long id, SocioRequestDTO dto){
        Socio socioExistente = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el socio con ID:" + id));

        socioExistente.setNombre(dto.getNombre());
        socioExistente.setApellido(dto.getApellido());

        socioExistente.setEmail(dto.getEmail());
        socioExistente.setFechaNacimiento(dto.getFechaNacimiento());

        if (dto.getDireccion() != null) {
            Direccion direccion = new Direccion();
            direccion.setCalle(dto.getDireccion().getCalle());
            direccion.setCiudad(dto.getDireccion().getCiudad());
            direccion.setProvincia(dto.getDireccion().getProvincia());
            direccion.setCodigoPostal(dto.getDireccion().getCodigoPostal());
        } 


        Socio socioActualizado = socioRepository.save(socioExistente);
        return mapearAResponse(socioActualizado);
    }

    private void validarUnicidadDni(String dni) {
        if (socioRepository.existsByDni(dni)) {
            throw new DniDuplicadoException("El DNI ingresado ya se encuentra registrado en el sistema.");
        }
    }

    private Socio mapearAEntidad(SocioRequestDTO dto) {
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setDni(dto.getDni());
        socio.setEmail(dto.getEmail());
        socio.setPassword(dto.getPassword());
        socio.setFechaNacimiento(dto.getFechaNacimiento());
        socio.setFechaRegistro(LocalDateTime.now());

        if (dto.getDireccion() != null) {
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getDireccion().getCalle());
        direccion.setCiudad(dto.getDireccion().getCiudad());
        direccion.setProvincia(dto.getDireccion().getProvincia());
        direccion.setCodigoPostal(dto.getDireccion().getCodigoPostal());
        socio.setDireccion(direccion);
    }
    return socio;
    }

    private SocioResponseDTO mapearAResponse(Socio socio) {
        SocioResponseDTO response = new SocioResponseDTO();
        response.setId(socio.getId());
        response.setNombre(socio.getNombre());
        response.setApellido(socio.getApellido());
        response.setEmail(socio.getEmail());
        response.setFechaRegistro(socio.getFechaRegistro());

        response.setEstadoMembresia(calcularEstadoMembresia(socio.getFechaVencimientoMembresia()));

        if (socio.getDireccion() != null) {
            DireccionDTO dirDTO = new DireccionDTO();
            dirDTO.setCalle(socio.getDireccion().getCalle());
            dirDTO.setCiudad(socio.getDireccion().getCiudad());
            dirDTO.setProvincia(socio.getDireccion().getProvincia());
            dirDTO.setCodigoPostal(socio.getDireccion().getCodigoPostal());
            response.setDireccion(dirDTO);
        }
        return response;
    }

    private String calcularEstadoMembresia(LocalDateTime fechaVencimiento) {
        if (fechaVencimiento == null) {
            return "SIN MEMBRESIA";
        }
        return fechaVencimiento.isAfter(LocalDateTime.now()) ? "ACTIVO" : "VENCIDA";
    }

}
