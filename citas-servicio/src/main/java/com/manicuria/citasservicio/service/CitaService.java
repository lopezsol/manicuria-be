package com.manicuria.citasservicio.service;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.dto.CitaHoraPrimerProfesionalDTO;
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
        LocalDate fecha = obtenerFechaActual();
        LocalTime hora = obtenerHora();
        List<Cita> citas = citaRepository.findAllByListaDisponiblesAndFechaGreaterThanEqualOrderByFechaAsc(
                idProfesional, fecha);
        return filtrarCitaFechaHora(citas,fecha,hora);
    }

    @Override
    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(Long idProfesional, LocalDate fecha) {
        LocalTime hora = obtenerHora();
        LocalDate fechaActual = obtenerFechaActual();
        List<Cita> citas;
        /*
            si la fecha pasada por parametro es igual que la fecha actual, filtro por la hora
            actual, caso contrario, no filtro por la hora actual
         */
        if (fechaActual.equals(fecha)) {
            citas = citaRepository.findAllByListaDisponiblesAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
                    idProfesional, fecha, hora);
        } else {
            citas = citaRepository.findAllByListaDisponiblesAndFechaOrderByHoraAsc(
                    idProfesional, fecha);
        }

        List<CitaHoraDTO> citasHorasDTO = new ArrayList<>();

        for (Cita c : citas) {
            CitaHoraDTO cita = new CitaHoraDTO(c.getId(), c.getHora());
            citasHorasDTO.add(cita);
        }
        return citasHorasDTO;
    }

    @Override
    public List<Cita> traerPrimerProfesionalDisponible(List<Long> listaProfesionales) {
        LocalDate fechaActual = obtenerFechaActual();
        LocalDate fechaFin = fechaActual.plusDays(31);
        LocalTime hora = obtenerHora();
        List<Cita> citas = citaRepository.findByFechaBetweenAndListaDisponiblesInOrderByFechaAsc(fechaActual, fechaFin, listaProfesionales);
        return filtrarCitaFechaHora(citas, fechaActual, hora);
    }

    @Override
    public List<CitaHoraPrimerProfesionalDTO> traerHorasDisponiblesPrimerProfesional(
            List<Long> listaProfesionales,
            LocalDate fecha) {

        LocalTime hora = obtenerHora();
        LocalDate fechaActual = obtenerFechaActual();
        List<Cita> citas;
        /*
            si la fecha pasada por parametro es igual que la fecha actual, filtro por la hora
            actual, caso contrario, no filtro por la hora actual
         */
        if (fechaActual.equals(fecha)) {
            citas = citaRepository.findAllByListaDisponiblesInAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
                    listaProfesionales, fecha, hora);
        } else {
            citas = citaRepository.findAllByListaDisponiblesInAndFechaOrderByHoraAsc(
                    listaProfesionales, fecha);
        }

        return getCitaHoraPrimerProfesionalDTOS(listaProfesionales, citas);
    }

    private static List<CitaHoraPrimerProfesionalDTO> getCitaHoraPrimerProfesionalDTOS(
            List<Long> listaProfesionales, List<Cita> citas) {
        List<CitaHoraPrimerProfesionalDTO> citasHorasDTO = new ArrayList<>();
        /*
            crea una lista de objetos tipo CitaHoraPrimerProfesionalDTO,
            en donde se devuelve el id de la cita, la hora y UNICAMENTE la lista de profesionales
            que pueden realizar el servicio solicitado
         */
        for (Cita c : citas) {
            List<Long> listaProfesionalesDTO = new ArrayList<>();
            for (Long profesional : c.getListaDisponibles()) {
                if (listaProfesionales.contains(profesional)) {
                    listaProfesionalesDTO.add(profesional);
                }
            }
            CitaHoraPrimerProfesionalDTO cita = new CitaHoraPrimerProfesionalDTO(
                    c.getId(), c.getHora(), listaProfesionalesDTO);
            citasHorasDTO.add(cita);
        }
        return citasHorasDTO;
    }

    //Obtiene la fecha actual
    private LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

    /*
        Adiciona a la hora actual los minutos indicados con que se tiene
        que pedir con anterioridad una cita
     */
    private LocalTime obtenerHora() {
        LocalTime horaActual = LocalTime.now();
        return horaActual.plusMinutes(minAnterioridadCita);
    }

    /*
        Remueve las citas que tienen un horario anterior que la hora actual, para una fecha
        indicada como parametro
     */
    private List<Cita> filtrarCitaFechaHora(List<Cita> listaCitas, LocalDate fecha,
                                           LocalTime hora) {
        listaCitas.removeIf(c -> c.getFecha().equals(fecha) && c.getHora().isBefore(hora));
        return listaCitas;

    }
}
