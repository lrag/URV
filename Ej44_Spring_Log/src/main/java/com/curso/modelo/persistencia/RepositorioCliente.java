package com.curso.modelo.persistencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class RepositorioCliente {
	
	// Definición manual del logger
    private static final Logger logger = LoggerFactory.getLogger(RepositorioCliente.class);

	public void insertar() {
		logger.debug("DEBUG en RC.insertar");
		logger.info("INFO en RC.insertar");
	}
	
	public void modificar() {
		logger.debug("DEBUG en RC.modificar");
		logger.info("INFO en RC.modificar");
	}
	
	public void borrar() {
		logger.debug("DEBUG en RC.borrar");
		logger.info("INFO en RC.borrar");
	}
	
}
