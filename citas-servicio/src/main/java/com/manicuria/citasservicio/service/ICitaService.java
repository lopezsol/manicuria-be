package com.manicuria.citasservicio.service;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.dto.PrimerProfesionalHorasDTO;
import com.manicuria.citasservicio.model.Cita;

import java.time.LocalDate;
import java.util.List;

public interface ICitaService {
    public void crearCita(Cita cita);
    public List<Cita> traerCitas();
    public Cita traerCita(Long id);
    public void eliminarCita(Long id);
    public void editarCita(Cita cita);

    public List<Cita> traerCitasDisponiblesProfesional(Long idProfesional);
    //trae las citas disponibles de un profesional a partir de la fecha actual
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(Long idProfesional);

    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(Long idProfesional, LocalDate fecha);

    //cambiar parametro entrada
    public List<Cita> traerPrimerProfesionalDisponible();
    //cambiar parametro entrada
    public List<PrimerProfesionalHorasDTO> traerHorasDisponiblesPrimerProfesional(LocalDate fecha);

}
