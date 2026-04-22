package ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.modelo.negocio.Calculadora;

public class _04_Ciclo_de_vida_2 {

	private Calculadora calculadora;

	//CalculadoraTest se instanciará por cada método marcado con @Test
	public _04_Ciclo_de_vida_2() {
		super();
		System.out.println("Instanciando 04_Ciclo_de_vida_2");
	}

	@BeforeAll //Puede haber más de un método con @BeforeAll, @AfterAll...
	public static void beforeAll() {
		System.out.println("Before all");
		//Si calculadora es estático se utilizará la misma instancia para todas las pruebas
		//No es recomendable puesto que debemos asegurarnos de que ningún test está acoplado a otro
		//calculadora = new Calculadora();
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("After all");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("Before each");
		//Es más ortodoxo inicializar todo antes de cada test
		//si hicieramos esto 'calculadora' no sería estática
		calculadora = new Calculadora();
	}

	@AfterEach
	public void afterEach() {
		System.out.println("After each");
	}

	@Test
	public void laSumaDebeSerCorrecta() {	
		
		System.out.println("Test 1");
		
		//DADO
		double s1 = 2d;
		double s2 = 2d;
		//CUANDO
		double res = calculadora.sumar(s1, s2);
		//ENTONCES		
		assertEquals(Double.valueOf(4), res);
	}
	
	@Test
	public void dividirFuncionaCorrectamente() throws Exception {
		System.out.println("Test 2");
		assertEquals(5d, calculadora.dividir(10d, 2d));
	}
	
}

