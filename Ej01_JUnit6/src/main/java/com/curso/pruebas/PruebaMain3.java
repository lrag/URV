package com.curso.pruebas;

import com.curso.modelo.negocio.Calculadora;

public class PruebaMain3 {

	public static void main(String[] args) throws Exception {
		
		Calculadora c = new Calculadora();
		
		double resultado = c.dividir(20d,10d);
		
		if(resultado == 2d) {
			System.out.println("calculadora.dividir OK");
		} else {
			System.out.println("calculadora.dividir ZASCA!");
		}		
		
	}	
	
}
