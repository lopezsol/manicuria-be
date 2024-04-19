package com.manicuria.citasservicio.repository;

import com.manicuria.citasservicio.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findAllByListaDisponiblesOrderByFechaAscHoraAsc(Long id);
    public  List<Cita> findAllByListaDisponiblesAndFecha(Long id, LocalDate fecha);
    public  List<Cita> findAllByListaDisponiblesAndFechaAndHoraGreaterThanEqual(
            Long id, LocalDate fecha, LocalTime hora);
    public  List<Cita> findAllByListaDisponiblesAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
            Long id, LocalDate fecha, LocalTime hora);
    List<Cita> findAllByListaDisponiblesAndFechaGreaterThanEqual(Long id, LocalDate fecha);
    List<Cita> findAllByListaDisponiblesAndFechaGreaterThanEqualAndHoraGreaterThanEqual(
            Long id, LocalDate fecha, LocalTime hora);
    List<Cita> findAllByListaDisponiblesAndFechaGreaterThanEqualAndHoraGreaterThanEqualOrderByFechaAsc(
            Long id, LocalDate fecha, LocalTime hora);

    List<Cita> findAllByListaDisponiblesInAndFechaGreaterThanEqualAndHoraGreaterThanEqualOrderByFechaAsc(
            List<Long> listaProfesionales,LocalDate fecha, LocalTime hora);

    public  List<Cita> findAllByListaDisponiblesInAndFechaAndHoraGreaterThanEqualOrderByHoraAsc(
            List<Long> listaProfesionales, LocalDate fecha, LocalTime hora);

}
