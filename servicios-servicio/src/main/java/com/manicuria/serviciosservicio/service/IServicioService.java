package com.manicuria.serviciosservicio.service;

import com.manicuria.serviciosservicio.model.Servicio;

import java.util.List;

public interface IServicioService {
    public void crearServicio(Servicio servicio);
    public List<Servicio> traerServicios();
    public Servicio traerServicio(Long id);
    public void eliminarServicio(Long id);

    public void editarServicio(Servicio servicio);
}
