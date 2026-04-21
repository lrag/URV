package com.curso.modelo.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.persistencia.OfertaRepositorio;

@Service
public class ServicioOfertas {

	@Autowired OfertaRepositorio ofertaRepositorio;
	
	public void calcularOferta(Pedido pedido) {
		System.out.println("CALCULANDO LA OFERTA DEL PEDIDO: "+pedido);
		ofertaRepositorio.findAll();
	}
	
}
