package com.manicuria.empresasservicio.repository;

import com.manicuria.empresasservicio.dto.ImagenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "imagenes-servicio")
public interface ImagenAPI {
    @GetMapping("/imagenes/traer/{id}")
    public ImagenDTO traerImagen(@PathVariable Long id);
}
