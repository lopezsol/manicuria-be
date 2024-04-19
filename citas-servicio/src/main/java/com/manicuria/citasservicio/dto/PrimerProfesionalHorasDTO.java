package com.manicuria.citasservicio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrimerProfesionalHorasDTO {
    private Long id;
    private LocalTime hora;
    private List<Long> listaProfesionales;
}
