package com.manicuria.serviciosservicio.controller;

import com.manicuria.serviciosservicio.dto.ErrorResponse;
import com.manicuria.serviciosservicio.model.Servicio;
import com.manicuria.serviciosservicio.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicios")
@CrossOrigin
public class ServicioController {
    @Autowired
    private IServicioService servicioService;
    final ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor");

    @PostMapping("/crear")
    public ResponseEntity<Object> crearServicio(@RequestBody Servicio servicio) {
        if (servicio == null || servicio.getNombre() == null
                || servicio.getNombre().isBlank() || servicio.getPrecio() < 0) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos del" +
                    " servicio son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            servicioService.crearServicio(servicio);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerServicios() {
        try {
            return ResponseEntity.ok(servicioService.traerServicios());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerServicio(@PathVariable Long id) {
        try {
            Servicio servicioBuscado = servicioService.traerServicio(id);
            if (servicioBuscado == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " el servicio");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(servicioBuscado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarServicio(@PathVariable Long id) {
        try {
            servicioService.eliminarServicio(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarServicio(@RequestBody Servicio servicio) {
        if (servicio == null || servicio.getNombre() == null ||
                servicio.getId() == null || servicio.getNombre().isBlank()
        ) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos del" +
                    " servicio son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            servicioService.editarServicio(servicio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }
}
