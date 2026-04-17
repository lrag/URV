package com.curso.modelo.entidad;

public class Avion {

	private Integer id;
	private String fabricante;
	private String modelo;
	private Integer year;

	public Avion() {
		super();
	}

	public Avion(Integer id, String fabricante, String modelo, Integer year) {
		super();
		this.id = id;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Avion [id=" + id + ", fabriccante=" + fabricante + ", modelo=" + modelo + ", year=" + year + "]";
	}

}
