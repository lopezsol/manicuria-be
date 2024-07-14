package com.manicuria.turnosservicio.controller;

import com.manicuria.turnosservicio.dto.ErrorResponse;
import com.manicuria.turnosservicio.dto.TurnoDTO;
import com.manicuria.turnosservicio.model.Turno;
import com.manicuria.turnosservicio.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turnos")
public class TurnoController {
    @Autowired
    private ITurnoService turnoService;

    final ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor", 500);

    @PostMapping("/crear")
    public ResponseEntity<Object> crearTurno(@RequestBody Turno turno) {
        if (turno == null || turno.getIdCita() == null || turno.getIdProfesional() == null
                || turno.getIdServicio() == null
                || turno.getDni() == null
                || turno.getDni().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos del" +
                    " turno son incorrectos o faltan", 400);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            ErrorResponse response = turnoService.crearTurno(turno);

            if (response.getStatus() == 500) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerTurnos() {
        try {
            return ResponseEntity.ok(turnoService.traerTurnosDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerTurno(@PathVariable Long id) {
        try {
            TurnoDTO turnoBuscado = turnoService.traerTurnoDTO(id);
            if (turnoBuscado == null) {
                //este error funciona cuando el turno no existe o cuando
                // ocurre un error en la comunicacion de los servidores
                ErrorResponse errorResponse = new ErrorResponse("Error al intentar" +
                        " obtener el turno con id " + id, 404);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(turnoBuscado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarTurno(@PathVariable Long id) {
        try {
            ErrorResponse response = turnoService.eliminarTurno(id);

            if (response.getStatus() == 404) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if (response.getStatus() == 500) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarTurno(@RequestBody Turno turno) {
        if (turno == null || turno.getIdCita() == null || turno.getIdProfesional() == null
                || turno.getIdServicio() == null
                || turno.getDni() == null
                || turno.getDni().isBlank() || turno.getId() == null
        ) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos del" +
                    " turno son incorrectos o faltan", 400);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            turnoService.editarTurno(turno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/dni/{dni}")
    public ResponseEntity<Object> traerTurnosPorDni(@PathVariable String dni) {
        try {
            return ResponseEntity.ok(turnoService.traerTurnosPorDniDTO(dni.trim()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }

    }
}
