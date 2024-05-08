package com.manicuria.citasservicio.repository;

import com.manicuria.citasservicio.model.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByProfesionalesDisponiblesOrderByFechaAscHoraAsc(Long id);

    public List<Cita> findAllByProfesionalesDisponiblesAndFechaOrderByHoraAsc(
            Long id, LocalDate fecha);

    public List<Cita> findAllByProfesionalesDisponiblesAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
            Long id, LocalDate fecha, LocalTime hora);

    List<Cita> findAllByProfesionalesDisponiblesAndFechaGreaterThanEqualOrderByFechaAsc(
            Long id, LocalDate fecha);

    List<Cita> findAllByProfesionalesDisponiblesInAndFechaOrderByHoraAsc(
            List<Long> listaProfesionales, LocalDate fecha);

    List<Cita> findAllByProfesionalesDisponiblesInAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
            List<Long> listaProfesionales, LocalDate fecha, LocalTime hora);

    List<Cita> findByFechaBetweenAndProfesionalesDisponiblesInOrderByFechaAsc(
            LocalDate fechaInicio, LocalDate fechaFin, List<Long> idProfesionales);

}
