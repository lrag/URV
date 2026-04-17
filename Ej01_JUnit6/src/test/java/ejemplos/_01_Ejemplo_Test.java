package ejemplos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Nombres de clases para que SureFire las encuentre:
//**/Test*.java
//**/*Test.java
//**/*Tests.java
//**/*TestCase.java
public class _01_Ejemplo_Test {

	//Tiene que tener constuctor por defecto
	//public _01_Ejemplo_Test(int x) {}
	
	//Los métodos marcados con @Test son invocados por jUnit
	//En orden albabético
	@Test
	//DisplayName es opcional
	//El nombre del método debe ser descriptivo
	//Si queda un nombre horrible podemos utilizar la anotacion displayName
	@DisplayName("Primer test")
	//Estos métodos no reciben parámetros
	//Son void
	public void primerTest() { 
		System.out.println("Ejecutando un test que no hace nada");
	}
		
}

