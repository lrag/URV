package ejemplos;


import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.Arguments.ArgumentSet;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraImpuestos;
import com.curso.modelo.negocio.ServicioClientes;
import com.curso.modelo.negocio.InvalidIngresoException;


public class _07_TestParametrizado {

	//En esta test probamos tres metodos diferentes de tres clases distintas
	//Esto está mal
	private CalculadoraImpuestos calculadoraImpuestos;
	private Calculadora calculadora;
	private ServicioClientes gestorClientes;
	
	public _07_TestParametrizado() {
		super();
		System.out.println("Instanciando TestParametrizado");
	}
	
	@BeforeEach
	void inicializar() {
		calculadoraImpuestos = new CalculadoraImpuestos();
		calculadora = new Calculadora();
		gestorClientes = new ServicioClientes();
	}

	
	/*
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-params</artifactId>
		<version>5.5.2</version>
		<scope>test</scope>
	</dependency>	
	*/
	
	@ParameterizedTest
	@ValueSource( doubles = { 5, 25, 50, 75 })
	@DisplayName("Prueba de test parametrizado")
	public void ejemplo(Double numero) {
		//Dado este número
		System.out.println("Parámetro:"+numero);
		//Cuando 
		double resultado = calculadora.cuadrado(numero);
		
		//Entonces
		Assertions.assertTrue(resultado == numero*numero); //Trampa
		//Lo mismo pero con equals
		Assertions.assertEquals(resultado, numero*numero);	
	}
	
	@ParameterizedTest(name = "CalculadoraImpuestos {index}: El impuesto de {0} debe ser {1}")
	@DisplayName("Calculo de impuestos por ingreso")
	@MethodSource("datosParaProbarCalculadoraImpuestos")
	void testWithMultiArgMethodSource(Double ingreso, Double impuestoEsperado) throws InvalidIngresoException {
		//Dado
		System.out.println(ingreso+","+impuestoEsperado);
		//Cuando
		double resultado = calculadoraImpuestos.calcularImpuestosSobreIngreso(ingreso);
	    //Entonces
		Assertions.assertEquals(impuestoEsperado, resultado);
	}
	
	@ParameterizedTest
	@MethodSource("datosParaProbarInsertarCliente")
	void pruebaInsertarClientes(Cliente cliente) {
		//Dado el cliente recibido
		System.out.println(cliente);
		//Cuando
		Cliente cAux = gestorClientes.insertarCliente(cliente);
		//Entonces
		Assertions.assertNotNull(cAux.getId());		
	}	

	//Es obligatorio que sea estático
	static Stream<Arguments> datosParaProbarCalculadoraImpuestos() {
		return Stream.of(
	        Arguments.arguments(5000d,0d),
	        Arguments.arguments(10000d,800d),
	        Arguments.arguments(17000d,1700d),
	        Arguments.arguments(22000d,3300d),
	        Arguments.arguments(35000d,6825d)
	    );	 
	}	
	
	//Es obligatorio que sea estático
	static Stream<Arguments> datosParaProbarInsertarCliente() {
		return Stream.of(
			Arguments.arguments(new Cliente(null,"N1","D1","T1")),
			Arguments.arguments(new Cliente(null,"N2","D2","T2")),
			Arguments.arguments(new Cliente(null,"N3","D3","T3")),
			Arguments.arguments(new Cliente(null,"N4","D4","T4")),
			Arguments.arguments(new Cliente(null,"N5","D5","T5"))
		);	 
	}	

}






