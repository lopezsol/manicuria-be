package com.manicuria.profesionalesservicio.repository;

import com.manicuria.profesionalesservicio.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesionalRepository extends JpaRepository<Profesional, Long> {
}
