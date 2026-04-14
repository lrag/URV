package com.curso.modelo.negocio.excepcion;

import com.curso.modelo.entidad.Cliente;

public class ClienteException extends NegocioException {

	private Cliente cliente;

	public ClienteException(Cliente cliente, String mensaje) {
		super(mensaje);
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
