package com.manicuria.empresasservicio.repository;

import com.manicuria.empresasservicio.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
}
