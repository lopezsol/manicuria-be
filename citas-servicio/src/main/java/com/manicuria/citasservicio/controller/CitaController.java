package com.manicuria.citasservicio.controller;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.dto.CitaHoraPrimerProfesionalDTO;
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

    @GetMapping("/traer/disponible/profesional/{idProfesional}")
    public List<Cita> traerCitasDisponiblesProfesional(@PathVariable Long idProfesional) {
        return citaService.traerCitasDisponiblesProfesional(idProfesional);
    }

    @GetMapping("/traer/filtradas/disponible/profesional/{idProfesional}")
    public List<Cita> traerCitasDisponiblesProfesionalFiltradas(@PathVariable Long idProfesional) {
        return citaService.traerCitasDisponiblesProfesionalFiltradas(idProfesional);
    }

    @GetMapping("/traer/disponible/profesional/horas/{idProfesional}/{fecha}")
    public List<CitaHoraDTO> traerHorasDisponiblesProfesionalFecha(@PathVariable Long idProfesional,
                                                             @PathVariable LocalDate fecha) {
        return citaService.traerHorasDisponiblesProfesionalFecha(idProfesional, fecha);
    }

    @GetMapping("/traer/primer-profesional")
    public List<Cita> traerPrimerProfesionalDisponible(@RequestParam("idProfesional") List<Long> listaProfesionales) {
        return citaService.traerPrimerProfesionalDisponible(listaProfesionales);
    }

    @GetMapping("/traer/primer-profesional/horas/{fecha}")
    public List<CitaHoraPrimerProfesionalDTO> traerHorasPrimerProfesionalDisponible(@PathVariable LocalDate fecha,
                                                                                    @RequestParam("idProfesional")
                                                                                 List<Long> listaProfesionales) {
        System.out.println("listaProfesionales: " + listaProfesionales);
        return citaService.traerHorasDisponiblesPrimerProfesional(listaProfesionales, fecha);
    }
}
