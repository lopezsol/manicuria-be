package com.manicuria.profesionalesservicio.repository;

import com.manicuria.profesionalesservicio.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfesionalRepository extends JpaRepository<Profesional, Long> {
    public List<Profesional> findAllByListaServiciosOrderByNombreAsc(Long idServicio);
}
