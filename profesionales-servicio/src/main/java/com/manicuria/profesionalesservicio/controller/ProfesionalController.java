package com.manicuria.profesionalesservicio.controller;

import com.manicuria.profesionalesservicio.dto.ErrorResponse;
import com.manicuria.profesionalesservicio.model.Profesional;
import com.manicuria.profesionalesservicio.service.IProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin
public class ProfesionalController {
    @Autowired
    private IProfesionalService profesionalService;
    final ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor");

    @PostMapping("/crear")
    public ResponseEntity<Object> crearProfesional(@RequestBody Profesional profesional) {
        if (profesional == null || profesional.getNombre() == null
                || profesional.getNombre().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos del" +
                    " profesional son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            profesionalService.crearProfesional(profesional);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerProfesionales() {
        try {
            return ResponseEntity.ok(profesionalService.traerProfesionales());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerProfesional(@PathVariable Long id) {
        try {
            Profesional profesionalBuscado = profesionalService.traerProfesional(id);
            if (profesionalBuscado == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " el profesional");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(profesionalBuscado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarProfesional(@PathVariable Long id) {
        try {
            profesionalService.eliminarProfesional(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarProfesional(@RequestBody Profesional profesional) {
        if (profesional == null || profesional.getNombre() == null ||
                profesional.getId() == null || profesional.getNombre().isBlank()
        ) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " profesional son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            profesionalService.editarProfesional(profesional);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/servicio/{idServicio}")
    public ResponseEntity<Object> traerProfesionalesXServicio(@PathVariable Long idServicio) {

        try {
            List<Profesional> profesionalesBuscados =
                    profesionalService.traerProfesionalesXServicio(idServicio);
            if (profesionalesBuscados.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontraron" +
                        " profesionales para el servicio " + idServicio);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(profesionalesBuscados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }
}
