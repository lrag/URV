package com.curso.rest.dto;

public class RespuestaOk implements Respuesta{

	private String codigo;
	private String status;
	private Data data;

	public RespuestaOk() {
		super();
	}

	public RespuestaOk(String codigo, String status, Data data) {
		super();
		this.codigo = codigo;
		this.status = status;
		this.data = data;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
