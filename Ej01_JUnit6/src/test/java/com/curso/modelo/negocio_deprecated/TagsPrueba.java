package com.curso.modelo.negocio_deprecated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.curso.modelo.negocio.Calculadora;

public class TagsPrueba {

	private Calculadora calculadora;

	public TagsPrueba() {
		super();
		System.out.println("Instanciando TagsTest");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("Before each");
		calculadora = new Calculadora();
	}

	@Test
	public void ejemplo()  {
		assertEquals(new Double(4), calculadora.sumar(2d, 2d));
	}

	/*
	 * Tags
	 * 
	 */
	@Test
	@Tag("development")
	@Tag("production")
	public void prueba1() {
		System.out.println("PRUEBA 1");
		assertTrue(1 == 1);
	}

	@Test
	@Tag("production")
	public void prueba2() {
		System.out.println("PRUEBA 2");
		assertTrue(2 == 2);
	}

}
