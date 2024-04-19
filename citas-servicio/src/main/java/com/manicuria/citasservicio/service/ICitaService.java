package com.manicuria.citasservicio.service;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.model.Cita;

import java.time.LocalDate;
import java.util.List;

public interface ICitaService {
    public void crearCita(Cita cita);
    public List<Cita> traerCitas();
    public Cita traerCita(Long id);
    public void eliminarCita(Long id);
    public void editarCita(Cita cita);

    public List<Cita> traerCitasDisponiblesProfesional(Long id);
    //trae las citas disponibles de un profesional a partir de la fecha actual
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(Long id);

    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(Long id, LocalDate fecha);
}
