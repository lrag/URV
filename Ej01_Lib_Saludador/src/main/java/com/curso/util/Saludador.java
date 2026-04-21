package com.curso.util;

public class Saludador {

	private String mensaje;

	public Saludador() {
		super();
	}

	public Saludador(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String saludar(String nombre) {
		return mensaje+" "+nombre;
	}
	
}
