package com.manicuria.turnosservicio.repository;

import com.manicuria.turnosservicio.dto.CitaDTO;
import com.manicuria.turnosservicio.dto.ProfesionalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "citas-servicio")
public interface CitaAPI {
    @GetMapping("/citas/traer/{id}")
    public CitaDTO traerCita(@PathVariable Long id);

    @PutMapping("/citas/agregar/profesional-disponible/{idProfesional}/{idCita}")
    public ResponseEntity<Object> agregarProfesionalDisponible(@PathVariable Long idProfesional,
                                                               @PathVariable Long idCita);

    @PutMapping("/citas/agregar/profesional-reservado/{idProfesional}/{idCita}")
    public ResponseEntity<Object> agregarProfesionalReservado(@PathVariable Long idProfesional,
                                                              @PathVariable Long idCita);

    @PutMapping("/citas/eliminar/profesional-disponible/{idProfesional}/{idCita}")
    public ResponseEntity<Object> eliminarProfesionalDisponible(@PathVariable Long idProfesional,
                                                                @PathVariable Long idCita);

    @PutMapping("/citas/eliminar/profesional-reservado/{idProfesional}/{idCita}")
    public ResponseEntity<Object> eliminarProfesionalReservado(@PathVariable Long idProfesional,
                                                               @PathVariable Long idCita);
}
