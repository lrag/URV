package ejemplos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(_10_CustomExtension.class)
public class _10_Test_Extension {

	public _10_Test_Extension() {
		super();
		System.out.println("Constructor de _10_CustomExtension");
	}

	@BeforeAll
	public static void beforeAll() {
		System.out.println("BEFORE_ALL DE _10_");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println("BEFORE_EACH DE _10_");
	}
	
	
	@Test
	public void test1() { 
		System.out.println("Ejecutando un test que no hace nada");
	}
	
	
	@Test
	public void test2() { 
		System.out.println("ZASCA");
		//Assertions.fail("MUERTE Y DESTRUCCI�N");
	}
	
	
}

