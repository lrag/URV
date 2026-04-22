package ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.curso.modelo.negocio.Calculadora;


//@Disabled("No")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)	
public class _03_Ciclo_de_vida {

	//Se creará una instancia de la clase por cada @Test
	//A no ser que marquemos cla clase con @TestInstance, que entonces reutilizará una instancia por clase!
	//Se creará tambien un objeto de la clase calculadora por cada test
	private Calculadora calculadora = new Calculadora();

	//Si declaramos estático el atributo que guarda el objeto a probar
	//no se creará uno por cada test, pero hay que estar muy seguro de 
	//que es imposible que los test se acoplen entre si al reutilizar la calculadora
	//No es recomendable
	//private static Calculadora calculadora = new Calculadora();
	
	public _03_Ciclo_de_vida() {
		super();
		//calculadora = new Calculadora();
		System.out.println("Creando CicloDeVidaTest");
	}

	@Test
	public void primerTest() {	

		//Dados
		double n1 = 5;
		double n2 = 10;
		
		//Cuando
		double n3 = calculadora.sumar(n1,n2);
		
		//Entonces
		assertEquals(15, n3);		
	}

	@Test
	//@Disabled("Este no")
	public void segundoTest() {

		double n1 = 5;
		
		double n2 = calculadora.cuadrado(n1);
		assertEquals(25, n2);
	}

	@Test
	//@Disabled("Este tampoco")
	public void tercerTest() throws Exception {
		double n1 = 30;
		double n2 = 2;
		
		double n3 = calculadora.dividir(n1, n2);
		assertEquals(15, n3);
	}
	

	/*
	@Test
	public void insertarCliente() throws Exception {

		Cliente c = new Cliente(1l,"N","D","T");
		RepositorioClientes rc = new RepositorioClientes();
		
		rc.addCliente(c);
		
		//entonces...
	}	
	
	@Test
	public void listarClientes() throws Exception {

		RepositorioClientes rc = new RepositorioClientes();
		
		rc.listar();
		
		//entonces...
	}
	*/
	
	
		
}

/*
class RepositorioClientes {
	
	List<Cliente> clientes = new ArrayList<>();
	
	public void addCliente(Cliente c) {
		clientes.add(c);
	}
	
	public List<Cliente> listar() {
		return clientes;
	}	
	
}
*/





