package com.curso.rest.dto;

public class Data {

	private String descripcion;
	private Object data;

	public Data() {
		super();
	}

	public Data(String descripcion, Object data) {
		super();
		this.descripcion = descripcion;
		this.data = data;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
