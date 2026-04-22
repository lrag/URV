package com.curso.pruebas;

import com.curso.modelo.negocio.Calculadora;

public class Prueba2 implements Prueba {

	public void test() {
		
		Calculadora c = new Calculadora();
		
		double resultado = c.cuadrado(5d);
		
		if(resultado == 25d) {
			System.out.println("calculadora.cuadrado OK");
		} else {
			System.out.println("calculadora.cuadrado ZASCA!");
		}
		
	}
	
	
}
