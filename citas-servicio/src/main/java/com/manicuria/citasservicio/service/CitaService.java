package com.manicuria.citasservicio.service;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.dto.PrimerProfesionalHorasDTO;
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
    public List<Cita> traerCitasDisponiblesProfesional(Long idProfesional) {
        return citaRepository.findAllByListaDisponiblesOrderByFechaAscHoraAsc(idProfesional);
    }

    @Override
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(Long idProfesional) {
        LocalDate fecha = obtenerFecha();
        LocalTime hora = obtenerHora();

        return citaRepository.findAllByListaDisponiblesAndFechaGreaterThanEqualAndHoraGreaterThanEqualOrderByFechaAsc(
                idProfesional, fecha, hora);
    }

    @Override
    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(Long idProfesional, LocalDate fecha) {
        LocalTime hora = obtenerHora();

        List<Cita> citas = citaRepository.findAllByListaDisponiblesAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
                idProfesional, fecha, hora);
        List<CitaHoraDTO> citasHorasDTO = new ArrayList<>();

        for (Cita c : citas) {
            CitaHoraDTO cita = new CitaHoraDTO(c.getId(),c.getHora());
            citasHorasDTO.add(cita);
        }
        return citasHorasDTO;
    }

    @Override
    public List<Cita> traerPrimerProfesionalDisponible() {
        LocalDate fecha = obtenerFecha();
        LocalTime hora = obtenerHora();
        //cambiar la forma en la que se obtiene este array, minimo deberia estar conectado
        //a la api de profesionales para traer cuales hacen ese servicio
        List<Long> listaProfesionales = new ArrayList<>();
        listaProfesionales.add(1L);
        listaProfesionales.add(4L);
        return citaRepository.findAllByListaDisponiblesInAndFechaGreaterThanEqualAndHoraGreaterThanEqualOrderByFechaAsc(
                listaProfesionales, fecha, hora);
    }

    @Override
    public List<PrimerProfesionalHorasDTO> traerHorasDisponiblesPrimerProfesional(LocalDate fecha) {
        LocalTime hora = obtenerHora();
        //cambiar la forma en la que se obtiene este array, minimo deberia estar conectado
        //a la api de profesionales para traer cuales hacen ese servicio
        List<Long> listaProfesionales = new ArrayList<>();
        listaProfesionales.add(1L);
        listaProfesionales.add(4L);
        List<Cita> citas = citaRepository.findAllByListaDisponiblesInAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
                listaProfesionales, fecha, hora);
        List<PrimerProfesionalHorasDTO> citasHorasDTO = new ArrayList<>();

        for (Cita c : citas) {
            List<Long> listaProfesionalesDTO = new ArrayList<>();
            for(Long profesional: c.getListaDisponibles()) {
                if(listaProfesionales.contains(profesional) ){
                    listaProfesionalesDTO.add(profesional);
                }
            }
            PrimerProfesionalHorasDTO cita = new PrimerProfesionalHorasDTO(
                    c.getId(),c.getHora(), listaProfesionalesDTO);
            citasHorasDTO.add(cita);
        }
        return citasHorasDTO;
    }

    public LocalDate obtenerFecha() {
        System.out.println("fecha: " + LocalDate.now());
        return LocalDate.now();
    }

    public LocalTime obtenerHora() {
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

        return hora;
    }
}
