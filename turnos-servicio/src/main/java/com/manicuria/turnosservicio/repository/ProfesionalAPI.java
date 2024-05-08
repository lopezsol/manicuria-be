package com.manicuria.turnosservicio.repository;

import com.manicuria.turnosservicio.dto.ProfesionalDTO;
import com.manicuria.turnosservicio.dto.ServicioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "profesionales-servicio")
public interface ProfesionalAPI {
    @GetMapping("/profesionales/traer/{id}")
    public ProfesionalDTO traerProfesional(@PathVariable Long id);
}

