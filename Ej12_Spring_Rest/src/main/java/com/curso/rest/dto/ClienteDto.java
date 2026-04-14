package com.curso.rest.dto;

import javax.validation.constraints.NotEmpty;

import com.curso.modelo.entidad.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Cliente", description = "Objeto de transferencia de datos que representa a un Cliente")
public class ClienteDto {

	@Schema(description = "Identificador único del cliente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Integer id;

	@NotEmpty
	@Schema(description = "Nombre completo del cliente", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
	private String nombre;

	@NotEmpty
	@Schema(description = "Dirección postal de residencia", example = "Calle Falsa, 123, Pitiegua", requiredMode = Schema.RequiredMode.REQUIRED)
	private String direccion;

	@NotEmpty
	@Schema(description = "Número de teléfono de contacto", example = "+34 123 456 789", requiredMode = Schema.RequiredMode.REQUIRED)
	private String telefono;

	@Schema(description = "Número de tarjeta de crédito (Opcional)", example = "123456789", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer numeroTC;

	public ClienteDto() {
		super();
	}

	public ClienteDto(Integer id, String nombre, String direccion, String telefono, Integer numeroTC) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numeroTC = numeroTC;
	}
	
	public ClienteDto(Cliente cliente) {
		if(cliente==null) {
			return;
		}
		this.id        = cliente.getId();
		this.nombre    = cliente.getNombre();
		this.direccion = cliente.getDireccion();
		this.telefono  = cliente.getTelefono();
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

	public Integer getNumeroTC() {
		return numeroTC;
	}

	public void setNumeroTC(Integer numeroTC) {
		this.numeroTC = numeroTC;
	}
	
	public Cliente asCliente() {
		return new Cliente(id,nombre,direccion,telefono,numeroTC);
	}

}






















