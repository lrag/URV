package com.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.modelo.negocio.ServicioClientes;

@SpringBootApplication
public class Aplicacion implements CommandLineRunner{

	@Autowired private ServicioClientes servicioClientes;
	
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		servicioClientes.insertar();
		servicioClientes.modificar();
		servicioClientes.borrar();
		
	}

}
