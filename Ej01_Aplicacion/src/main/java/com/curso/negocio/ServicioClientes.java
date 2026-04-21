package com.curso.negocio;

import org.springframework.stereotype.Service;

import com.curso.util.Despedidor;
import com.curso.util.Saludador;

@Service
public class ServicioClientes {

	private Saludador saludador;
	private Despedidor despedidor;
	
	public ServicioClientes(Saludador saludador, Despedidor despedidor) {
		super();
		this.saludador = saludador;
		this.despedidor = despedidor;
	}

	public void altacliente(String cliente) {
		System.out.println(saludador.saludar(cliente));
		//LN...
		System.out.println("Insertando el cliente...");
		System.out.println(despedidor.despedir(cliente));
	}
	
}
