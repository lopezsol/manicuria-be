package com.manicuria.profesionalesservicio.service;

import com.manicuria.profesionalesservicio.model.Profesional;
import com.manicuria.profesionalesservicio.repository.IProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalService implements IProfesionalService{
    @Autowired
    private IProfesionalRepository profesionalRepository;


    @Override
    public void crearProfesional(Profesional profesional) {
        profesionalRepository.save(profesional);
    }

    @Override
    public List<Profesional> traerProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public Profesional traerProfesional(Long id) {
        return profesionalRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }

    @Override
    public void editarProfesional(Profesional profesional) {
        profesionalRepository.save(profesional);
    }

    @Override
    public List<Profesional> traerProfesionalesXServicio(Long idServicio) {
        System.out.println(profesionalRepository.findAllByListaServiciosOrderByNombreAsc(idServicio));
        return profesionalRepository.findAllByListaServiciosOrderByNombreAsc(idServicio);
    }
}
