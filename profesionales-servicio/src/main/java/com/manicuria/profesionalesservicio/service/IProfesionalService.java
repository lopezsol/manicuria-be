package com.manicuria.profesionalesservicio.service;

import com.manicuria.profesionalesservicio.model.Profesional;

import java.util.List;

public interface IProfesionalService {
    public void crearProfesional(Profesional profesional);
    public List<Profesional> traerProfesionales();
    public Profesional traerProfesional(Long id);
    public void eliminarProfesional(Long id);

    public void editarProfesional(Profesional profesional);
}
