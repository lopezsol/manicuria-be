package com.manicuria.turnosservicio.service;

import com.manicuria.turnosservicio.dto.ErrorResponse;
import com.manicuria.turnosservicio.dto.TurnoDTO;
import com.manicuria.turnosservicio.model.Turno;

import java.util.List;

public interface ITurnoService {
    public ErrorResponse crearTurno(Turno turno);

    public List<Turno> traerTurnos();
    public List<TurnoDTO> traerTurnosDTO();

    public Turno traerTurno(Long id);
    public TurnoDTO traerTurnoDTO(Long id);

    public ErrorResponse eliminarTurno(Long id);

    public void editarTurno(Turno turno);

    public List<TurnoDTO> traerTurnosPorDniDTO(String dni);
}
