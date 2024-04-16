package com.manicuria.serviciosservicio.repository;

import com.manicuria.serviciosservicio.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio,Long> {
}
