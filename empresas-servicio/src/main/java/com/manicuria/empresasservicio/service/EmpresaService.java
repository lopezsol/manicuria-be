package com.manicuria.empresasservicio.service;

import com.manicuria.empresasservicio.dto.EmpresaDTO;
import com.manicuria.empresasservicio.dto.EmpresaRedesDTO;
import com.manicuria.empresasservicio.dto.ImagenDTO;
import com.manicuria.empresasservicio.model.Empresa;
import com.manicuria.empresasservicio.model.Imagen;
import com.manicuria.empresasservicio.repository.IEmpresaRepository;
import com.manicuria.empresasservicio.repository.ImagenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService implements IEmpresaService{
    @Autowired
    private IEmpresaRepository empresaRepository;
    @Autowired
    private ImagenAPI imagenAPI;
    @Override
    public void crearEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> traerEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public List<EmpresaDTO> traerEmpresasDTO() {
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaDTO> empresasDTO = new ArrayList<>();

        for(Empresa e: empresas){
            EmpresaDTO empresaDTO = this.traerEmpresaDTO(e.getId());
            empresasDTO.add(empresaDTO);
        }

        return empresasDTO;
    }

    @Override
    public Empresa traerEmpresa(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public EmpresaDTO traerEmpresaDTO(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        EmpresaDTO empresaDTO = new EmpresaDTO();
        List<ImagenDTO> listaCarrusel = new ArrayList<>();

        //logo en la clase Empresa es un id de tipo Long, que hace referencia a la imagen
        ImagenDTO logo = imagenAPI.traerImagen(empresa.getLogo());
        //recorro los id de las imagenes del carrusel y los asigno en listaCarrusel
        for(Long idImagen : empresa.getListaCarrusel()){
            ImagenDTO carrusel = imagenAPI.traerImagen(idImagen);
            listaCarrusel.add(carrusel);
        }
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setTelefono(empresa.getTelefono());
        empresaDTO.setDireccion(empresa.getDireccion());
        empresaDTO.setHorarios(empresa.getHorarios());
        empresaDTO.setUrlTwitter(empresa.getUrlTwitter());
        empresaDTO.setUrlFacebook(empresa.getUrlFacebook());
        empresaDTO.setUrlInstagram(empresa.getUrlInstagram());
        empresaDTO.setLogo(logo);
        empresaDTO.setListaCarrusel(listaCarrusel);

        return empresaDTO;
    }

    public EmpresaRedesDTO traerRedes(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        return new EmpresaRedesDTO(empresa.getUrlTwitter(),
                empresa.getUrlFacebook(), empresa.getUrlInstagram());
    }

    @Override
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public void editarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public ImagenDTO traerLogo(Long id) {
        Empresa empresa = this.traerEmpresa(id);
        return imagenAPI.traerImagen(empresa.getLogo());
    }

    @Override
    public List<ImagenDTO> traerCarrusel(Long id) {
        Empresa empresa = this.traerEmpresa(id);
        List<ImagenDTO> listaCarrusel = new ArrayList<>();

        for(Long idImagen : empresa.getListaCarrusel()){
            ImagenDTO carrusel = imagenAPI.traerImagen(idImagen);
            listaCarrusel.add(carrusel);
        }

        return listaCarrusel;
    }
}
