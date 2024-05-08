package com.manicuria.turnosservicio.service;

import com.manicuria.turnosservicio.dto.*;
import com.manicuria.turnosservicio.model.Turno;
import com.manicuria.turnosservicio.repository.CitaAPI;
import com.manicuria.turnosservicio.repository.ITurnoRepository;
import com.manicuria.turnosservicio.repository.ProfesionalAPI;
import com.manicuria.turnosservicio.repository.ServicioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private ProfesionalAPI profesionalAPI;
    @Autowired
    private CitaAPI citaAPI;
    @Autowired
    private ServicioAPI servicioAPI;

    @Override
    public ErrorResponse crearTurno(Turno turno) {
        try {
            citaAPI.eliminarProfesionalDisponible(turno.getIdProfesional(), turno.getIdCita());
            citaAPI.agregarProfesionalReservado(turno.getIdProfesional(), turno.getIdCita());

            turnoRepository.save(turno);
            return new ErrorResponse("Turno creado correctamente", 201);


        } catch (Exception e) {
            System.out.println("error: " + e);
            return new ErrorResponse(e.getMessage(), 500);
        }
    }

    @Override
    public List<Turno> traerTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public List<TurnoDTO> traerTurnosDTO() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();

        for (Turno turno : turnos) {
            TurnoDTO turnoDTO = this.traerTurnoDTO(turno.getId());
            if (turnoDTO != null) turnosDTO.add(turnoDTO);
        }
        return turnosDTO;
    }

    @Override
    public Turno traerTurno(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    @Override
    public TurnoDTO traerTurnoDTO(Long id) {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno == null) return null;

        TurnoDTO turnoDTO = new TurnoDTO();

        try {
            ServicioDTO servicioDTO = servicioAPI.traerServicio(turno.getIdServicio());
            CitaDTO citaDTO = citaAPI.traerCita(turno.getIdCita());
            ProfesionalDTO profesionalDTO = profesionalAPI.traerProfesional(turno.getIdProfesional());

            turnoDTO.setId(turno.getId());
            turnoDTO.setNombreCliente(turno.getNombreCliente());

            turnoDTO.setFechaCita(citaDTO.getFecha());
            turnoDTO.setHoraCita(citaDTO.getHora());

            turnoDTO.setNombreServicio(servicioDTO.getNombre());
            turnoDTO.setDuracionServicio(servicioDTO.getDuracion());
            turnoDTO.setPrecioServicio(servicioDTO.getPrecio());

            turnoDTO.setNombreProfesional(profesionalDTO.getNombre());
        } catch (Exception e) {
            return null;
        }

        return turnoDTO;
    }

    @Override
    public ErrorResponse eliminarTurno(Long id) {
        Turno turno = this.traerTurno(id);

        if (turno == null) {
            return new ErrorResponse("El turno con ID " + id + " no existe.", 404);
        }
        //todo - cambiar
        //cambiar en Cita, el idProfesional de listaDisponible a listaReservado
        try {
            citaAPI.eliminarProfesionalReservado(turno.getIdProfesional(), turno.getIdCita());
            citaAPI.agregarProfesionalDisponible(turno.getIdProfesional(), turno.getIdCita());

            turnoRepository.deleteById(id);
            return new ErrorResponse("Turno eliminado correctamente", 204);

        } catch (Exception e) {
            System.out.println("error: " + e);
            return new ErrorResponse(e.getMessage(), 500);

        }
    }

    @Override
    public void editarTurno(Turno turno) {
        turnoRepository.save(turno);
    }
}
