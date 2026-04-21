package com.curso.util;

public class Despedidor {

	private String mensajeDespedida;

	public Despedidor() {
		super();
	}

	public Despedidor(String mensajeDespedida) {
		super();
		this.mensajeDespedida = mensajeDespedida;
	}

	public String getMensajeDespedida() {
		return mensajeDespedida;
	}

	public void setMensajeDespedida(String mensajeDespedida) {
		this.mensajeDespedida = mensajeDespedida;
	}

	public String despedir(String nombre) {
		return mensajeDespedida+" "+nombre;
	}	
	
}
