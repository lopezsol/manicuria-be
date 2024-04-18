package com.manicuria.citasservicio.repository;

import com.manicuria.citasservicio.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Long> {
    public List<Cita> findAllByListaDisponibles(Long id);
    public  List<Cita> findAllByListaDisponiblesAndFecha(Long id, LocalDate fecha);
    List<Cita> findAllByListaDisponiblesAndFechaGreaterThanEqual(Long id, LocalDate fecha);
}
