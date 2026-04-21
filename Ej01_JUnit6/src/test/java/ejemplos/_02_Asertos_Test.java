package ejemplos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.curso.modelo.negocio.Calculadora;

public class _02_Asertos_Test {
	
	@Test
	public void test1() {			

		System.out.println("Test 1");
		
		//dados
		int n1 = 100;
		int n2 = 200;
		
		//cuando
		int n3 = n1 + n2;

		//entonces
		
		//Si no existieran los asertos:
		/*
		if(n3 != 301) {
			System.out.println("Vamos listos porque el + de java no funciona");
			throw new RuntimeException("La suma no es correcta");
		}
		*/
			
		//Utiliza equals, no el '=='
		
		//Con import estático
		//assertEquals(300, n3);
	
		//Lo mismo, pero sin import estático:
		Assertions.assertEquals(301, n3);
	
	}
	
	@Test
	public void test2() {	
		System.out.println("Test 2");
		int n1 = 100;
		int n2 = 200;
		
		//Utiliza equals, no el '=='
		assertNotEquals(n1, n2, "Son iguales y no tendrían que serlo!");	
	}
	
	@Test
	public void test3() {
		System.out.println("Test 3");		
		int n1 = 100;
		int n2 = 200;	
		//Recibe una expresión que se resuelve en un boolean
		//Con assertTrue podríamos hacer cualquier comprobación
		assertTrue(n2 > n1);		
	}

	@Test
	public void test4() {
		System.out.println("Test 4");		
		int n1 = 100;
		int n2 = 200;		
		assertFalse(n2 < n1);		
	}

	@Test
	public void test5() throws Exception {
		System.out.println("Test 5");	
		String txt = null;
		
		assertNull(txt);	
	}

	@Test
	public void test6() {
		System.out.println("Test 6");	
		String txt = new String("TXT");
		
		assertNotNull(txt);	
	}
	
	@Test
	public void test7() {
		System.out.println("Test 7");	

		//Cuidado con el autoboxing 
		Integer i1 = 127; 
		Integer i2 = 127;
		
		Integer i3 = i1 + i2;
		
		Calculadora c1 = new Calculadora();
		Calculadora c2 = c1;
			
		//AssertEquals compara utilizando el  método 'equals'		
		//AssertSame compara utilizando '==', compara LAS REFERENCIAS
		assertSame(i1, i2);	
		assertSame(c1, c2);	
	}
	
	@Test
	public void test8() {
		System.out.println("Test 8");	
		Integer i1 = 128;
		Integer i2 = 128;
		
		Calculadora c1 = new Calculadora();
		Calculadora c2 = new Calculadora();		
		
		assertNotSame(i1, i2);	
		assertNotSame(c1, c2);			
	}

	@Test
	public void test9() {
		System.out.println("Test 9");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		
		/*
		for(int a=0; a<palabras1.length; a++) {
			if(!palabras1[a].equals(palabras2[a])) {
				throw new RuntimeException("No son iguales!!!!");
			}
		}
		*/
		
		assertArrayEquals(palabras2, palabras1);	
	}

	@Test
	public void test10() {
		System.out.println("Test 10");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		
		List<String> lista1 = Arrays.asList(palabras1);
		List<String> lista2 = Arrays.asList(palabras2);
		
		//Para comparar Iterables. 
		assertIterableEquals(lista1, lista2);		
	}
	
	@Test
	public void test11() {
		System.out.println("Test 11");
		
		boolean condicionDificilDeExpresarConUnAserto = false;
		if(!condicionDificilDeExpresarConUnAserto) {
			//throw new RuntimeException("La liamos parda"); //Ñapa
			Assertions.fail("Test fallido");
		}
	}
	
	@Test
	@DisplayName("Prueba de assertAll (test12)")
	void test12() {
		
		System.out.println("Test 12");		
		
		//Dados
		Calculadora calculadora = new Calculadora();
		double s1 = 5d;
		double s2 = 4d;
			
		//Cuando
		final Double sumResult = calculadora.sumar(s1, s2);

		
		//Podríamos hacer esto, pero al primer aserto que falle se sale del método por la excepción que se lanza
		//System.out.println("I");
		//assertNotNull(sumResult, "El resultado no es nulo");
		//System.out.println("II");
		//assertTrue(sumResult > 10, "El resultado es mayor que 10"); //A partir de aqui no se ejecuta (se lanza una excepción)
		//System.out.println("III");
		//assertTrue(sumResult < 10, "El resultado es menor que 10");
		
		//Esto exige un trabajo inasumible:
		//Comprobador1 c1 = new Comprobador1();
		//c1.sumResult = sumResult;
		//Comprobador2 c2 = new Comprobador2();
		//c2.sumResult = sumResult;
		//Comprobador3 c3 = new Comprobador3();
		//c3.sumResult = sumResult;
		//Assertions.assertAll(c1, c2, c3);		
		

		//Con clases internas anónimas

		Assertions.assertAll( 
				new Executable() {
					public void execute() throws Throwable {
						System.out.println("I");
						assertNotNull(sumResult);
					}
				},
				new Executable() {
					public void execute() throws Throwable {
						System.out.println("II");
						assertTrue(sumResult > 10, "No es mayor que 10");
					}
				},
				new Executable() {
					public void execute() throws Throwable {
						System.out.println("III");
						assertTrue(sumResult < 10, "No es menor que 10");
					}
				});	
		
		//Con expresiones lambda
		Assertions.assertAll(
				() -> assertTrue(sumResult != null, "La suma es nula!"),
				() -> assertTrue(sumResult < 10, "La suma es mayor que 10!"),
				() -> assertTrue(sumResult > 10, "La suma es menor que 10!")
			);
		
		/*
		Assertions.assertAll(
			() -> { System.out.println("-I"); assertTrue(sumResult == null, "La suma es nula!"); },
			() -> { System.out.println("-II"); assertTrue(sumResult < 10, "La suma es mayor que 10!"); },
			() -> { System.out.println("-III"); assertTrue(sumResult > 10, "La suma es menor que 10!"); }
		);
		*/
	}
	
	
}



class Comprobador1 implements Executable {
	public Double sumResult;	
	
	@Override
	public void execute() throws Throwable {
		System.out.println("I");
		Assertions.assertNotNull(sumResult);
	}
}

class Comprobador2 implements Executable {
	public Double sumResult;	
	
	@Override
	public void execute() throws Throwable {
		System.out.println("II");
		Assertions.assertTrue(sumResult > 10);
	}
}

class Comprobador3 implements Executable {
	public Double sumResult;	
	
	@Override
	public void execute() throws Throwable {
		System.out.println("III");
		Assertions.assertTrue(sumResult < 10);
	}
}


