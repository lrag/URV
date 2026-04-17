package com.curso.modelo.entidad;

import java.util.List;

public class Cliente {

	public Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	private Sucursal sucursal;
	private List<Comercial> comerciales;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nombre, String direccion, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Cliente(Integer id, String nombre, String direccion, String telefono, Sucursal sucursal,
			List<Comercial> comerciales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.sucursal = sucursal;
		this.comerciales = comerciales;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<Comercial> getComerciales() {
		return comerciales;
	}

	public void setComerciales(List<Comercial> comerciales) {
		this.comerciales = comerciales;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", sucursal=" + sucursal + ", comerciales=" + comerciales + "]";
	}

}
