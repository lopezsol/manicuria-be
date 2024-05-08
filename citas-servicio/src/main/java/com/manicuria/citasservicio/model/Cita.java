package com.manicuria.citasservicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIME)
    private LocalTime hora;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @ElementCollection
    private List<Long> profesionalesDisponibles;
    @ElementCollection
    private List<Long> profesionalesReservados;
}
