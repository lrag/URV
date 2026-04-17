package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional(propagation=Propagation.REQUIRED) 
public class GestorTransportes {
	
	public String obtenerCamion(boolean hayCamion) throws Exception{
		
		System.out.print("Reservando un camión...");
		if(!hayCamion) {
			System.out.println("MAL");
			throw new Exception("No hay camión");
		}
		
		System.out.println("OK");
		return "MOOOOC MOOOOOOC!";
		
	}
	
}
