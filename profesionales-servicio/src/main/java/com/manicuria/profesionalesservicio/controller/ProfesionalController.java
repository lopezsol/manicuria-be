package com.manicuria.profesionalesservicio.controller;

import com.manicuria.profesionalesservicio.model.Profesional;
import com.manicuria.profesionalesservicio.service.IProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin
public class ProfesionalController {
    @Autowired
    private IProfesionalService profesionalService;

    @PostMapping("/crear")
    public String crearProfesional(@RequestBody Profesional profesional) {
        profesionalService.crearProfesional(profesional);
        return "Profesional creado correctamente";
    }

    @GetMapping("/traer")
    public List<Profesional> traerEmpresas() {
        return profesionalService.traerProfesionales();
    }

    @GetMapping("/traer/{id}")
    public Profesional traerEmpresa(@PathVariable Long id) {

        return profesionalService.traerProfesional(id);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarEmpresa(@PathVariable Long id) {
        profesionalService.eliminarProfesional(id);
        return "Profesional borrado correctamente";
    }

    @PutMapping("/editar")
    public String editarProfesional(@RequestBody Profesional profesional) {
        profesionalService.editarProfesional(profesional);
        return "Profesional editado correctamente";
    }

    @GetMapping("/traer/servicio/{idServicio}")
    public List<Profesional> traerProfesionalesXServicio(@PathVariable Long idServicio) {

        return profesionalService.traerProfesionalesXServicio(idServicio);
    }
}
