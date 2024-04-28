package com.manicuria.empresasservicio.controller;

import com.manicuria.empresasservicio.dto.EmpresaDTO;
import com.manicuria.empresasservicio.dto.EmpresaRedesDTO;
import com.manicuria.empresasservicio.dto.ErrorResponse;
import com.manicuria.empresasservicio.dto.ImagenDTO;
import com.manicuria.empresasservicio.model.Empresa;
import com.manicuria.empresasservicio.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/empresas")
@CrossOrigin

public class EmpresaController {
    @Autowired
    private IEmpresaService empresaService;

    final ErrorResponse errorServidor = new ErrorResponse("Se produjo un error " +
            "interno en el servidor");

    @PostMapping("/crear")
    public ResponseEntity<Object> crearEmpresa(@RequestBody Empresa empresa) {
        if (empresa == null || empresa.getNombre() == null
                || empresa.getNombre().isBlank()) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " empresa son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            empresaService.crearEmpresa(empresa);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/todas")
    public ResponseEntity<Object> traerEmpresas() {
        try {
            return ResponseEntity.ok(empresaService.traerEmpresas());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer")
    public ResponseEntity<Object> traerEmpresasDTO() {
        try {
            return ResponseEntity.ok(empresaService.traerEmpresasDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/id/{id}")
    public ResponseEntity<Object> traerEmpresa(@PathVariable Long id) {
        try {
            Empresa empresaBuscada = empresaService.traerEmpresa(id);
            if (empresaBuscada == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la empresa con id " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(empresaBuscada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Object> traerEmpresaDTO(@PathVariable Long id) {
        try {
            EmpresaDTO empresaBuscada = empresaService.traerEmpresaDTO(id);
            if (empresaBuscada == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la empresa con id " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(empresaBuscada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @GetMapping("/traer/redes/{id}")
    public ResponseEntity<Object> traerRedes(@PathVariable Long id) {
        try {
            EmpresaRedesDTO redesBuscadas = empresaService.traerRedes(id);
            if (redesBuscadas == null) {
                ErrorResponse errorResponse = new ErrorResponse("No se encontró" +
                        " la empresa con id " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(redesBuscadas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Object> borrarEmpresa(@PathVariable Long id) {
        try {
            empresaService.eliminarEmpresa(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarEmpresa(@RequestBody Empresa empresa) {
        if (empresa == null || empresa.getNombre() == null ||
                empresa.getId() == null || empresa.getNombre().isBlank()
        ) {
            ErrorResponse errorResponse = new ErrorResponse("Los datos de la" +
                    " empresa son incorrectos o faltan");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            empresaService.editarEmpresa(empresa);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    //este metodo se usa si estoy trabajando con una Empresa, no EmpresaDTO,
    // dado que EmpresaDTO ya tiene la info de las imagenes
    @GetMapping("/traer/logo/{id}")
    public ResponseEntity<Object> traerLogo(@PathVariable Long id) {
        try {
            ImagenDTO logoBuscado = empresaService.traerLogo(id);
            if (logoBuscado == null) {
                //este error funciona cuando la imagen no existe o cuando
                // ocurre un error en la comunicacion de los servidores
                ErrorResponse errorResponse = new ErrorResponse("Error al intentar " +
                        "obtener el logo");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(logoBuscado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }

    //este metodo se usa si estoy trabajando con una Empresa, no EmpresaDTO,
    // dado que EmpresaDTO ya tiene la info de las imagenes
    @GetMapping("/traer/carrusel/{id}")
    public ResponseEntity<Object> traerCarrusel(@PathVariable Long id) {
        try {
            List<ImagenDTO> imagenesCarrusel = empresaService.traerCarrusel(id);
            if (imagenesCarrusel == null) {
                //este error funciona cuando la imagen no existe o cuando
                // ocurre un error en la comunicacion de los servidores
                ErrorResponse errorResponse = new ErrorResponse("Error al intentar" +
                        " obtener las imagenes del carrusel");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(imagenesCarrusel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(errorServidor);
        }
    }
}
