package com.curso.pruebas;

import com.curso.modelo.negocio.Calculadora;

public class Prueba3 implements Prueba {

	//TODO: Este tendría que probar 'dividir'
	public void test() {
		
		Calculadora c = new Calculadora();
		
		try {
			double resultado = c.dividir(20d,10d);
			
			if(resultado == 2d) {
				System.out.println("calculadora.dividir OK");
			} else {
				System.out.println("calculadora.dividir ZASCA!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}	
	
}
