package com.manicuria.turnosservicio.repository;

import com.manicuria.turnosservicio.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findAllByDni(String dni);
}
