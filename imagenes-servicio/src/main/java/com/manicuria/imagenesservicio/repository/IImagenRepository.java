package com.manicuria.imagenesservicio.repository;

import com.manicuria.imagenesservicio.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Long> {
    Imagen findByUrl(String url);
}
