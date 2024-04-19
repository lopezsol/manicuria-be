package com.manicuria.citasservicio.service;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.model.Cita;
import com.manicuria.citasservicio.repository.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService implements ICitaService {
    @Autowired
    private ICitaRepository citaRepository;

    @Override
    public void crearCita(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> traerCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Cita traerCita(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public void editarCita(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> traerCitasDisponiblesProfesional(Long id) {
        return citaRepository.findAllByListaDisponibles(id);
    }

    @Override
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(Long id) {
        return List.of();
    }

    @Override
    public CitaHoraDTO traerHorasDisponiblesProfesionalFecha(Long id, LocalDate fecha) {
        List<Cita> citas = citaRepository.findAllByListaDisponiblesAndFecha(id, fecha);
        CitaHoraDTO horasDTO = new CitaHoraDTO();
        List<LocalTime> horas = new ArrayList<>();

        for (Cita c : citas) {
            horas.add(c.getHora());
        }
        horasDTO.setHoras(horas);
        return horasDTO;
    }
}
