package com.manicuria.citasservicio.controller;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.model.Cita;
import com.manicuria.citasservicio.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin
public class CitaController {
    @Autowired
    private ICitaService citaService;

    @PostMapping("/crear")
    public String crearCita(@RequestBody Cita cita) {
        citaService.crearCita(cita);
        return "Cita creada correctamente";
    }

    @GetMapping("/traer")
    public List<Cita> traerCitas() {
        return citaService.traerCitas();
    }

    @GetMapping("/traer/{id}")
    public Cita traerCita(@PathVariable Long id) {
        return citaService.traerCita(id);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "Cita borrada correctamente";
    }

    @PutMapping("/editar")
    public String editarCita(@RequestBody Cita cita) {
        citaService.editarCita(cita);
        return "Cita editada correctamente";
    }

    @GetMapping("/traer/disponible/profesional/{id}")
    public List<Cita> traerCitasDisponiblesProfesional(@PathVariable Long id) {
        return citaService.traerCitasDisponiblesProfesional(id);
    }

    @GetMapping("/traer/filtradas/disponible/profesional/{id}")
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(@PathVariable Long id) {
        return citaService.traerCitasDisponiblesProfesionalFiltradas(id);
    }

    @GetMapping("/traer/disponible/profesional/{id}/{fecha}")
    public CitaHoraDTO traerCitasDisponiblesProfesionalFecha(@PathVariable Long id,
                                                             @PathVariable LocalDate fecha) {
        return citaService.traerHorasDisponiblesProfesionalFecha(id, fecha);
    }
}
