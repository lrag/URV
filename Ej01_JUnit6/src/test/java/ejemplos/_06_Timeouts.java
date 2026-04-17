package ejemplos;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.modelo.negocio.Calculadora;

public class _06_Timeouts {

	private Calculadora calculadora;

	public _06_Timeouts() {
		super();
		System.out.println("Instanciando 06_Timeouts");
	}

	@BeforeEach
	public void beforeEach() {
		calculadora = new Calculadora();
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("Fin");
	}

	@Test
	public void calculoExtremadamenteComplejoDebeRealizarseRapido() throws InterruptedException {
		
		//Si no existiera el assertTimeout
		/*
		long inicio = System.currentTimeMillis();
		calculadora.calculoExtremadamenteComplejo();
		long fin = System.currentTimeMillis();
		Assertions.assertTrue(fin-inicio < 2000, "El metodo no se ha ejecutado en el tiempo estipulado");
		*/
		
		/*
		Assertions.assertTimeout(
				Duration.ofMillis(2000), 
			    () -> calculadora.calculoExtremadamenteComplejo()
			);
		*/
		
		//Detiene la prueba si se excede el tiempo
		Assertions.assertTimeoutPreemptively(
				Duration.ofMillis(2000), 
				() -> calculadora.calculoExtremadamenteComplejo()
			);
		
	}

}


