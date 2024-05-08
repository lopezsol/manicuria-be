package com.manicuria.turnosservicio.repository;

import com.manicuria.turnosservicio.dto.ServicioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicios-servicio")
public interface ServicioAPI {
    @GetMapping("/servicios/traer/{id}")
    public ServicioDTO traerServicio(@PathVariable Long id);
}
