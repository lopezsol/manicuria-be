package com.manicuria.imagenesservicio.controller;

import com.manicuria.imagenesservicio.model.Imagen;
import com.manicuria.imagenesservicio.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin
public class ImagenController {

    @Autowired
    private IImagenService imagenService;

    @PostMapping("/crear")
    public String crearImagen(@RequestBody Imagen imagen) {
        imagenService.crearImagen(imagen);
        return "Imagen creada correctamente";
    }

    @GetMapping("/traer")
    public List<Imagen> traerImagenes() {
        return imagenService.traerImagenes();
    }

    @GetMapping("/traer/{id}")
    public Imagen traerImagen(@PathVariable Long id) {

        return imagenService.traerImagen(id);
    }
    @DeleteMapping("/borrar/{id}")
    public String borrarImagen(@PathVariable Long id) {
        imagenService.eliminarImagen(id);
        return "Imagen borrada correctamente";
    }

    @PutMapping("/editar")
    public String editarImagen(@RequestBody Imagen imagen) {
        imagenService.editarImagen(imagen);
        return "Imagen editada correctamente";
    }

    @GetMapping("/traer/url")
    public Imagen traerImagenXUrl(@RequestBody Imagen imagen) {
        return imagenService.traerImagenXUrl(imagen.getUrl());
    }
}
