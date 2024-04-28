package com.manicuria.empresasservicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    @ElementCollection
    private List<String> horarios;
    private String urlTwitter;
    private String urlFacebook;
    private String urlInstagram;
    private Long logo;
    @ElementCollection
    private List<Long> listaCarrusel;
}
