package com.manicuria.empresasservicio.service;

import com.manicuria.empresasservicio.dto.EmpresaDTO;
import com.manicuria.empresasservicio.dto.EmpresaRedesDTO;
import com.manicuria.empresasservicio.dto.ImagenDTO;
import com.manicuria.empresasservicio.model.Empresa;
import com.manicuria.empresasservicio.model.Imagen;

import java.util.List;

public interface IEmpresaService {
    public void crearEmpresa(Empresa empresa);
    public List<Empresa> traerEmpresas();
    public List<EmpresaDTO> traerEmpresasDTO();

    public Empresa traerEmpresa(Long id);
    public EmpresaDTO traerEmpresaDTO(Long id);

    public EmpresaRedesDTO traerRedes(Long id);

    public void eliminarEmpresa(Long id);

    public void editarEmpresa(Empresa empresa);
    public ImagenDTO traerLogo(Long id);
    public List<ImagenDTO> traerCarrusel(Long id);
}
