package com.curso.modelo.entidad;

public class Comercial {

	private Integer id;
	private String codigoEmpleado;
	private String nombre;

	public Comercial() {
		super();
	}

	public Comercial(Integer id, String codigoEmpleado, String nombre) {
		super();
		this.id = id;
		this.codigoEmpleado = codigoEmpleado;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Comercial [id=" + id + ", codigoEmpleado=" + codigoEmpleado + ", nombre=" + nombre + "]";
	}

}
