package com.curso.modelo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.modelo.entidad.Oferta;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Integer>{
}
