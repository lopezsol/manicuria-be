package com.manicuria.empresasservicio.dto;

import com.manicuria.empresasservicio.model.Imagen;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private List<String> horarios;
    private String urlTwitter;
    private String urlFacebook;
    private String urlInstagram;
    private ImagenDTO logo;
    private List<ImagenDTO> listaCarrusel;
}
