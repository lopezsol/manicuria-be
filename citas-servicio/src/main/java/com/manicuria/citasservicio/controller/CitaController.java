package com.manicuria.citasservicio.controller;

import com.manicuria.citasservicio.dto.CitaHoraDTO;
import com.manicuria.citasservicio.dto.CitaHoraPrimerProfesionalDTO;
import com.manicuria.citasservicio.dto.ErrorResponse;
import com.manicuria.citasservicio.model.Cita;
import com.manicuria.citasservicio.service.ICitaService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin
public class CitaController {
    @Autowired
    private ICitaService citaService;
    final ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor");

    @PostMapping("/crear")
    public ResponseEntity<Object> crearCita(@RequestBody Cita cita) {
        if (cita == null || cita.getFecha() == null
                || cita.getHora() == null) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " cita son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            citaService.crearCita(cita);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerCitas() {
        try {
            return ResponseEntity.ok(citaService.traerCitas());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }

    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerCita(@PathVariable Long id) {
        try {
            Cita citaBuscada = citaService.traerCita(id);
            if (citaBuscada == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la cita con id " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citaBuscada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarCita(@RequestBody Cita cita) {
        if (cita == null || cita.getFecha() == null
                || cita.getHora() == null || cita.getId() == null

        ) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " cita son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            citaService.editarCita(cita);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/disponible/profesional/{idProfesional}")
    public ResponseEntity<Object> traerCitasDisponiblesProfesional(@PathVariable Long idProfesional) {
        try {
            List<Cita> citas = citaService.traerCitasDisponiblesProfesional(idProfesional);
            if (citas.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " citas disponibles para el profesional " + idProfesional);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/filtradas/disponible/profesional/{idProfesional}")
    public ResponseEntity<Object> traerCitasDisponiblesProfesionalFiltradas(
            @PathVariable Long idProfesional) {
        try {
            List<Cita> citas = citaService.traerCitasDisponiblesProfesionalFiltradas(idProfesional);
            if (citas.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " citas disponibles para el profesional " + idProfesional);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/disponible/profesional/horas/{idProfesional}/{fecha}")
    public ResponseEntity<Object> traerHorasDisponiblesProfesionalFecha(@PathVariable Long idProfesional,
                                                                        @PathVariable LocalDate fecha) {
        try {
            List<CitaHoraDTO> citasHoras = citaService.
                    traerHorasDisponiblesProfesionalFecha(idProfesional, fecha);
            if (citasHoras.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " horas disponibles para la fecha " + fecha + " para profesional "
                        + idProfesional);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citasHoras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/primer-profesional")
    public ResponseEntity<Object> traerPrimerProfesionalDisponible(
            @RequestParam("idProfesional") List<Long> listaProfesionales) {
        try {
            List<Cita> citas = citaService.
                    traerPrimerProfesionalDisponible(listaProfesionales);
            if (citas.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " citas para los profesionales");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/primer-profesional/horas/{fecha}")
    public ResponseEntity<Object> traerHorasPrimerProfesionalDisponible(@PathVariable LocalDate fecha,
                                                                        @RequestParam("idProfesional")
                                                                        List<Long> listaProfesionales) {
        try {
            List<CitaHoraPrimerProfesionalDTO> citasHoras = citaService.
                    traerHorasDisponiblesPrimerProfesional(listaProfesionales, fecha);
            if (citasHoras.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " horas disponibles para la fecha " + fecha);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citasHoras);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/agregar/profesional-disponible/{idProfesional}/{idCita}")
    public ResponseEntity<Object> agregarProfesionalDisponible(@PathVariable Long idProfesional,
                                             @PathVariable Long idCita) {
        try {
            citaService.agregarProfesionalDisponible(idProfesional, idCita);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/agregar/profesional-reservado/{idProfesional}/{idCita}")
    public ResponseEntity<Object> agregarProfesionalReservado(@PathVariable Long idProfesional,
                                             @PathVariable Long idCita) {
        try {
            citaService.agregarProfesionalReservado(idProfesional, idCita);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/eliminar/profesional-disponible/{idProfesional}/{idCita}")
    public ResponseEntity<Object> eliminarProfesionalDisponible(@PathVariable Long idProfesional,
                                                              @PathVariable Long idCita) {
        try {
            citaService.eliminarProfesionalDisponible(idProfesional, idCita);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/eliminar/profesional-reservado/{idProfesional}/{idCita}")
    public ResponseEntity<Object> eliminarProfesionalReservado(@PathVariable Long idProfesional,
                                                              @PathVariable Long idCita) {
        try {
            citaService.eliminarProfesionalReservado(idProfesional, idCita);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/filtrar/profesional-fecha-hora")
    public ResponseEntity<Object> traerCitaPorProfesionalFechaHora(
            @RequestParam("idProfesional") Long idProfesional,
            @RequestParam("fecha") LocalDate fecha,
            @RequestParam("hora")LocalTime hora) {
        try {
            Cita citaBuscada = citaService.
                    traerCitaPorProfesionalFechaHora(idProfesional, fecha, hora);

            if (citaBuscada==null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " una cita para los filtros solicitados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(citaBuscada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }
}
