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
    private int minAnterioridadCita = 15;

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
        return citaRepository.findAllByListaDisponiblesOrderByFechaAscHoraAsc(id);
    }

    @Override
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(Long id) {

        LocalDate fecha = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        LocalTime hora = horaActual.plusMinutes(minAnterioridadCita);
        //cambiar, hay que ver la hora de apertura de la empresa
        //no se si va a necesitar
        //LocalTime horaApertura = LocalTime.of(10, 0);
        //horaApertura = horaApertura.minusMinutes(60);
        /*if (hora.equals(horaCierre) || hora.isAfter(horaCierre)) {
            fecha = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth() + 1);
        }*/
        System.out.println("hora actual: " + horaActual);
        System.out.println("hora: " + hora);
        //System.out.println("hora apertura: " + horaApertura);
        System.out.println("fecha: " + fecha);

        return citaRepository.findAllByListaDisponiblesAndFechaGreaterThanEqualAndHoraGreaterThanEqualOrderByFechaAsc(
                id, fecha, hora);
    }

    @Override
    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(Long id, LocalDate fecha) {
        LocalTime horaActual = LocalTime.now();
        LocalTime hora = horaActual.plusMinutes(minAnterioridadCita);

        List<Cita> citas = citaRepository.findAllByListaDisponiblesAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
                id, fecha, hora);
        List<CitaHoraDTO> citasHorasDTO = new ArrayList<>();

        for (Cita c : citas) {
            CitaHoraDTO cita = new CitaHoraDTO(c.getId(),c.getHora());
            citasHorasDTO.add(cita);
        }
        return citasHorasDTO;
    }
}
