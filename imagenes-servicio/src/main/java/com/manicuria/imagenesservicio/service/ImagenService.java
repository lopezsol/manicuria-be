package com.manicuria.imagenesservicio.service;

import com.manicuria.imagenesservicio.model.Imagen;
import com.manicuria.imagenesservicio.repository.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenService implements IImagenService{
    @Autowired
    private IImagenRepository imagenRepository;
    @Override
    public void crearImagen(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    @Override
    public List<Imagen> traerImagenes() {
        return imagenRepository.findAll();
    }

    @Override
    public Imagen traerImagen(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

    @Override
    public void editarImagen(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    @Override
    public Imagen traerImagenXUrl(String url) {
        return imagenRepository.findByUrl(url);
    }
}
