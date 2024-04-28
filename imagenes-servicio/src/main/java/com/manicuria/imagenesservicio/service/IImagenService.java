package com.manicuria.imagenesservicio.service;

import com.manicuria.imagenesservicio.model.Imagen;

import java.util.List;

public interface IImagenService {
    public void crearImagen(Imagen imagen);
    public List<Imagen> traerImagenes();
    public Imagen traerImagen(Long id);
    public void eliminarImagen(Long id);
    public void editarImagen(Imagen imagen);

    public Imagen traerImagenXUrl(String url);
}
