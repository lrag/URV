package com.curso.modelo.negocio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.modelo.entidad.Avion;

public class ServicioAvionesTest {

	private ServicioAviones gestorAviones;

	@BeforeEach
	void setUp() throws Exception {
		gestorAviones = new ServicioAviones();
	}

	@Test
	void despuesDeHaberInserrtadoElAvionEsteTendraId() throws Exception {		
		
		Avion a = new Avion(null, "Messerschmitt", "BF 109", 1936);
		//ESTE TRY HACE QUE EL TEST ESTÉ MAL DISEÑADO:
		//No esperamos que falle con ese avión
		//try {
			gestorAviones.insertar(a);
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
	
		//JUnit                 
		//Assertions.assertNotNull(a.getId());		
		//Hamcrest
		//assertThat(a.getId(), is(not(nullValue())));	
		//
		assertThat(a.getId()).isNotNull();
	}

	@Test
	void insertarTest() {
		//Esto no es una prueba unitaria ni de lejos
		
		Integer antes = gestorAviones.listar().size();
		Avion a = new Avion(null, "Supermarine", "Spitfire", 1938);
		try {
			gestorAviones.insertar(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer despues = gestorAviones.listar().size();
						
		//JUnit
		//assertEquals(despues, new Integer(antes+1));
		//Hamcrest
		//assertThat(despues, is(Integer.valueOf(antes+1)));
		//
		assertThat(despues).isEqualTo(Integer.valueOf(antes+1));
	}

	@Test
	void buscarTest() {
		
		final String FABRICANTE = "fabricante";
		final String MODELO = "modelo";
		final Integer YEAR = 2020;
		
		Avion a1 = new Avion(null, FABRICANTE, MODELO, YEAR);
		try {
			gestorAviones.insertar(a1); //Este no es el m�todo que estamos probando
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Avion a2 = gestorAviones.buscar(a1.getId()); //Este si es el método que estamos probando
		
		//JUnit 5
		//assertAll( () -> assertEquals(a2.getId(), a1.getId()),
		//		     () -> assertEquals(a2.getFabricante(), FABRICANTE),
		//		     () -> assertEquals(a2.getModelo(), MODELO),
		//		     () -> assertEquals(a2.getYear(), YEAR) );
		//
		//Hamcrest
		//assertThat(a2, allOf( hasProperty("id", is(a1.getId())),
		//					  hasProperty("fabricante", is(FABRICANTE)),
		//					  hasProperty("modelo", is(MODELO)),
		//					  hasProperty("year", is(YEAR))
		//					)
		//		  );
		//
		
		assertThat(a2)
			.hasFieldOrPropertyWithValue("id", a1.getId())
			.hasFieldOrPropertyWithValue("fabricante", a1.getFabricante())
			.hasFieldOrPropertyWithValue("modelo", a1.getModelo())
			.hasFieldOrPropertyWithValue("year", a1.getYear());		
		
	}

	@Test
	void testListar() {
		List<Avion> aviones = gestorAviones.listar();
		
		//JUnit
		//Assertions.assertTrue(aviones.size()>0);
		//Hamcrest
		//assertThat(aviones,  is(not(empty())));
		//
		assertThat(aviones).isNotEmpty();		
	}

	@Test
	public void listarPorFabricanteTest() {		
		final String FABRICANTE = "McDonell Douglas";
		List<Avion> aviones = gestorAviones.listarPorFabricante(FABRICANTE);
		
		//Hamcrest
		//assertThat(aviones, everyItem( hasProperty("fabricante", is(FABRICANTE) )));
		//
		assertThat(aviones)
			.extracting(Avion::getFabricante).allMatch( f -> f.equals(FABRICANTE));
	}

}

