package com.curso.modelo.negocio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.curso.modelo.persistencia.RepositorioCliente;

@Service
public class ServicioClientes {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicioClientes.class);
	
	private RepositorioCliente repoCliente;
	
	public ServicioClientes(RepositorioCliente repoCliente) {
		super();
		this.repoCliente = repoCliente;
	}
	
	public void insertar() {
		logger.debug("DEBUG en SC.insertar");
		logger.info("INFO en SC.insertar");
		repoCliente.insertar();
	}
	
	public void modificar() {
		logger.debug("DEBUG en SC.modificar");
		logger.info("INFO en SC.modificar");
		repoCliente.modificar();
	}
	
	public void borrar() {
		logger.debug("DEBUG en SC.borrar");
		logger.info("INFO en SC.borrar");
		repoCliente.borrar();
	}
	
}
