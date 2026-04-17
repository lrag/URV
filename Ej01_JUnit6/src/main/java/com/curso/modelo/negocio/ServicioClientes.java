package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Cliente;

public class ServicioClientes {

	public Cliente insertarCliente(Cliente cliente) {
		//LN para insertar el cliente
		//...
		
		if(cliente.getDireccion() == null) {
			throw new RuntimeException("El cliente no tiene direccion");
		}
		
		cliente.setId(System.currentTimeMillis());
		return cliente;
	}
	
}
