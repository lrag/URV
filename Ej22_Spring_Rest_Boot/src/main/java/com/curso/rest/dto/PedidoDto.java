package com.curso.rest.dto;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Pedido;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PedidoDto {
	
	private Integer id;
	@Size(min=1, max=10)
	private String codigo;
	private String fecha;
	@NotEmpty
	private String estado;
	private ClienteDto cliente;

	public PedidoDto() {
		super();
	}

	public PedidoDto(Integer id, String codigo, String fecha, String estado, Cliente cliente) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.fecha = fecha;
		this.estado = estado;
		this.cliente = new ClienteDto(cliente);
	}

	public PedidoDto(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.codigo = pedido.getCodigo();
		this.fecha = pedido.getFecha().toString();
		this.estado = pedido.getEstado();
		this.cliente = new ClienteDto(pedido.getCliente());
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public Pedido asPedido() {
		//La fecha se queda a nulo
		//Falta un formateador de fechas
		return new Pedido(id,codigo,null,estado,cliente.asCliente(), null);
	}
	
}
