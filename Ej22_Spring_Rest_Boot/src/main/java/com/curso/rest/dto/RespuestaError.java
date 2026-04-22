package com.curso.rest.dto;

public class RespuestaError implements Respuesta {

	private String codigo;
	private String status;
	private Zasca error;

	public RespuestaError() {
		super();
	}

	public RespuestaError(String codigo, String status, Zasca error) {
		super();
		this.codigo = codigo;
		this.status = status;
		this.error = error;
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

	public Zasca getError() {
		return error;
	}

	public void setError(Zasca error) {
		this.error = error;
	}

}
