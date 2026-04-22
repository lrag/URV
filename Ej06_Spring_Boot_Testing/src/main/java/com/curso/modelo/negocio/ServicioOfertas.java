package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;

import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.persistencia.OfertaRepositorio;

@Service
public class ServicioOfertas {

	private OfertaRepositorio ofertaRepositorio;
	
	public ServicioOfertas(OfertaRepositorio ofertaRepositorio) {
		super();
		System.out.println("INSTANCIANDO SERVICIO_OFERTAS");
		this.ofertaRepositorio = ofertaRepositorio;
	}

	public void calcularOferta(Pedido pedido) {
		System.out.println("CALCULANDO LA OFERTA DEL PEDIDO: "+pedido);
		ofertaRepositorio.findAll();
	}
	
}
