package com.manicuria.serviciosservicio.service;

import com.manicuria.serviciosservicio.model.Servicio;
import com.manicuria.serviciosservicio.repository.IServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService implements IServicioService {
    @Autowired
    private IServicioRepository servicioRepository;
    @Override
    public void crearServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public List<Servicio> traerServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio traerServicio(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public void editarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }
}
