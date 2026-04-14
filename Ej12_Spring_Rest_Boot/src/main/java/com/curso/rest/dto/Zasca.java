package com.curso.rest.dto;

public class Zasca {

	private String codigo;
	private String mensaje;
	private Object data;

	public Zasca() {
		super();
	}

	public Zasca(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public Zasca(String codigo, String mensaje, Object data) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
