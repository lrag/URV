package com.curso.modelo.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CalificacionProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String calificacion;
	@ManyToOne
	@JoinColumn(name="fk_id_producto")
	private Producto producto;

	public CalificacionProducto() {
		super();
	}

	public CalificacionProducto(Integer id, String usuario, String calificacion, Producto producto) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.calificacion = calificacion;
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificación(String calificacion) {
		this.calificacion = calificacion;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "CalificacionProducto [id=" + id + ", usuario=" + usuario + ", calificación=" + calificacion + "]";
	}

}
