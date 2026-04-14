package com.curso.rest.dto;

import com.curso.modelo.entidad.CalificacionProducto;
import com.curso.modelo.entidad.Producto;

public class CalificacionProductoDto {

	private Integer id;
	private String usuario;
	private String calificacion;
	private Integer idProducto;

	public CalificacionProductoDto() {
		super();
	}

	public CalificacionProductoDto(Integer id, String usuario, String calificacion, Integer idProducto) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.calificacion = calificacion;
		this.idProducto = idProducto;
	}
	
	public CalificacionProductoDto(CalificacionProducto cp) {
		super();
		this.id = cp.getId();
		this.usuario = cp.getUsuario();
		this.calificacion = cp.getCalificacion();
		this.idProducto = cp.getProducto().getId();
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

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public CalificacionProducto asCalificacionProducto() {
		Producto producto = new Producto();
		producto.setId(idProducto);
		return new CalificacionProducto(id, usuario, calificacion, producto);
	}

}
