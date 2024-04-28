package com.manicuria.imagenesservicio.controller;

import com.manicuria.imagenesservicio.dto.ErrorResponse;
import com.manicuria.imagenesservicio.model.Imagen;
import com.manicuria.imagenesservicio.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin
public class ImagenController {

    @Autowired
    private IImagenService imagenService;

    ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor");

    @PostMapping("/crear")
    public ResponseEntity<Object> crearImagen(@RequestBody Imagen imagen) {
        // Validar los datos de la imagen
        if (imagen == null || imagen.getUrl() == null || imagen.getUrl().isBlank()) {
            // Si faltan datos o son incorrectos, devolver un ResponseEntity con el
            // código de estado 400 Bad Request y un mensaje personalizado
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " imagen son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            // Crear la imagen
            imagenService.crearImagen(imagen);
            // Buscar la imagen creada por URL
            Imagen imagenCreada = imagenService.traerImagenXUrl(imagen.getUrl());
            // Si la imagen no se encuentra, devolver un ResponseEntity con el código
            // de estado 404 Not Found y un mensaje personalizado
            if (imagenCreada == null) {
                ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                        " imagen son incorrectos o faltan");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            // Devolver la imagen creada con el código de estado 200 OK
            //return ResponseEntity.ok(imagenCreada);
            return ResponseEntity.status(HttpStatus.CREATED).body(imagenCreada);

        } catch (Exception e) {
            // Manejar errores del servidor devolviendo un ResponseEntity con el
            // código de estado 500 Internal Server Error y un mensaje personalizado
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerImagenes() {
        try {
            return ResponseEntity.ok(imagenService.traerImagenes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerImagen(@PathVariable Long id) {
        try {
            Imagen imagenBuscada = imagenService.traerImagen(id);
            if (imagenBuscada == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la imagen");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(imagenBuscada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarImagen(@PathVariable Long id) {
        try {
            imagenService.eliminarImagen(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }


    @PutMapping("/editar")
    public ResponseEntity<Object> editarImagen(@RequestBody Imagen imagen) {
        if (imagen == null || imagen.getUrl() == null || imagen.getId() == null
                || imagen.getUrl().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " imagen son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            imagenService.editarImagen(imagen);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    //ver si se puede hacer de otra forma
    @GetMapping("/traer/url")
    public ResponseEntity<Object> traerImagenXUrl(@RequestBody Imagen imagen) {
        if (imagen == null || imagen.getUrl() == null || imagen.getUrl().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " imagen son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            Imagen imagenBuscada = imagenService.traerImagenXUrl(imagen.getUrl());
            if (imagenBuscada == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la imagen");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(imagenBuscada);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

}