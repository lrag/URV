package com.curso.modelo.negocio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.curso.modelo.negocio.excepcion.DatosBancariosEx;

@Service
public class GestorBancos {

	public void comprobarTC(Integer numeroTC) throws Exception{
		
		System.out.print("comprobando TC...");
		
		if(numeroTC<5000) {
			System.out.println("MAL");

			//Con setRollbackOnly solcitamos que hagan rollback AL FINAL
			//Es definitivo y no podemos retractarnos
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw new DatosBancariosEx("Datos bancarios incorrectos");
		}
		
		System.out.println("OK");
	}	
	
}






