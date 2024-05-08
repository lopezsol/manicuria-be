package com.manicuria.turnosservicio.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long id;
    private LocalTime hora;
    private LocalDate fecha;
    private List<Long> listaDisponibles;
    private List<Long> listaReservados;
}
