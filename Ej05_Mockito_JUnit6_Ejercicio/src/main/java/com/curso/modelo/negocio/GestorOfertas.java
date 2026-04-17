package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class GestorOfertas {

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String obtenerPerritoPiloto(boolean hayPerritoPiloto) throws Exception{
		
		System.out.print("Obteniendo perrito piloto...");
		
		if(!hayPerritoPiloto) {
			System.out.println("MAL");
			
			
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw new Exception("No hay perrito piloto");
		}
		System.out.println("OK");
		return "Perrito piloto";
		
	}
	
	
}
