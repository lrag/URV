package com.curso.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.persistencia.ComercialDao;

public class ServicioComerciales {

	private ComercialDao comercialDao;

	public void setComercialDao(ComercialDao comercialDao) {
		this.comercialDao = comercialDao;
	}
	
	public void insertar(Comercial comercial) {
		//LN
	}
	
	public void modificar(Comercial comercial) {
		//LN
	}
	
	public List<Comercial> encontrarComerciales(){
		
		//
		//Lógica de negocio para encontrar comerciales
		//
		
		
		Comercial com1 = new Comercial(1, "EMP-1", "Venancio");
		Comercial com2 = new Comercial(2, "EMP-2", "Venancia");
		List<Comercial> comerciales = new ArrayList<>();
		comerciales.add(com1);
		comerciales.add(com2);
		return comerciales;
	}
	
}

/*
class GestoComerciales_MOCK extends GestorComerciales{

	@Override
	public void setComercialDao(ComercialDao comercialDao) {
	}
	
	@Override
	public void insertar(Comercial comercial) {
	}
	
	@Override
	public void modificar(Comercial comercial) {
	}	

	@Override
	public List<Comercial> encontrarComerciales() {
		return null;
	}
	
}
*/