package com.movida.curso.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.curso.saludador")
public class SaludadorProperties {
	
	//En el fichero application.properties/yml:
	//
	//com.curso.saludador.mensaje=AAA
	//com.curso.saludador.mensajeDespedida=BBB

	private String mensaje;
	private String mensajeDespedida;
	
	public SaludadorProperties() {
		super();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		System.out.println("SET MENSAJE");
		this.mensaje = mensaje;
	}

	public String getMensajeDespedida() {
		return mensajeDespedida;
	}

	public void setMensajeDespedida(String mensajeDespedida) {
		this.mensajeDespedida = mensajeDespedida;
	}

}