package com.manicuria.empresasservicio.controller;

import com.manicuria.empresasservicio.dto.EmpresaDTO;
import com.manicuria.empresasservicio.dto.EmpresaRedesDTO;
import com.manicuria.empresasservicio.dto.ImagenDTO;
import com.manicuria.empresasservicio.model.Empresa;
import com.manicuria.empresasservicio.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin

public class EmpresaController {
    @Autowired
    private IEmpresaService empresaService;

    @PostMapping("/crear")
    public String crearEmpresa(@RequestBody Empresa empresa) {
        empresaService.crearEmpresa(empresa);
        return "Empresa creada correctamente";
    }

    @GetMapping("/traer/todas")
    public List<Empresa> traerEmpresas() {
        return empresaService.traerEmpresas();
    }

    @GetMapping("/traer")
    public List<EmpresaDTO> traerEmpresasDTO() {
        return empresaService.traerEmpresasDTO();
    }

    @GetMapping("/traer/id/{id}")
    public Empresa traerEmpresa(@PathVariable Long id) {
        return empresaService.traerEmpresa(id);
    }

    @GetMapping("/traer/{id}")
    public EmpresaDTO traerEmpresaDTO(@PathVariable Long id) {

        return empresaService.traerEmpresaDTO(id);
    }

    @GetMapping("/traer/redes/{id}")
    public EmpresaRedesDTO traerRedes(@PathVariable Long id) {

        return empresaService.traerRedes(id);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return "Empresa borrada correctamente";
    }

    @PutMapping("/editar")
    public String editarEmpresa(@RequestBody Empresa empresa) {
        empresaService.editarEmpresa(empresa);
        return "Empresa editada correctamente";
    }

    @GetMapping("/traer/logo/{id}")
    public ImagenDTO traerLogo(@PathVariable Long id) {
        return empresaService.traerLogo(id);
    }

    @GetMapping("/traer/carrusel/{id}")
    public List<ImagenDTO> traerCarrusel(@PathVariable Long id) {
        return empresaService.traerCarrusel(id);
    }
}
