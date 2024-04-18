package com.manicuria.serviciosservicio.controller;

import com.manicuria.serviciosservicio.model.Servicio;
import com.manicuria.serviciosservicio.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
@CrossOrigin
public class ServicioController {
    @Autowired
    private IServicioService servicioService;

    @PostMapping("/crear")
    public String crearServicio(@RequestBody Servicio servicio) {
        servicioService.crearServicio(servicio);
        return "El servicio se creó correctamente";
    }

    @GetMapping("/traer")
    public List<Servicio> traerServicios() {
        return servicioService.traerServicios();
    }

    @GetMapping("/traer/{id}")
    public Servicio traerServicio(@PathVariable Long id) {
        return servicioService.traerServicio(id);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return "El servicio se eliminó correctamente";
    }

    @PutMapping("/editar")
    public String editarServicio(@RequestBody Servicio servicio) {
        servicioService.editarServicio(servicio);
        return "Servicio editado correctamente";
    }
}
