package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class _08_DataDrivenJUnit {

	private static WebDriver driver;
	private static int contador;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(Constantes.URL);
	}

	//Test que prueba la funcionalidad 'sumar' con un par de n�meros
	//Si necesitamos probar con m�s datos no nos sirve
	@Test
	public void pruebaSuma() throws InterruptedException {
		
		//Cargamos index.html
		driver.get(Constantes.URL);

		//Dados
		String s1  = "20";
		String s2  = "30";
		String res = "50";
		
		WebElement campo1 = driver.findElement(By.id("n1"));
		WebElement campo2 = driver.findElement(By.id("n2"));
		WebElement boton  = driver.findElement(By.id("btn-suma"));
		WebElement span   = driver.findElement(By.id("res"));
		
		//Cuando		
		Thread.sleep(1000);
		campo1.sendKeys(s1);
		Thread.sleep(1000);
		campo2.sendKeys(s2);
		Thread.sleep(1000);
		boton.click();
		
		//Entonces
		assertEquals(span.getText(), res.toString());	
		
	}

	//Juego de datos para el test
	static Stream<Arguments> datosParaElTest() {
	    return Stream.of(
	        Arguments.arguments(2,2,4),
	        Arguments.arguments(4,2,6),
	        Arguments.arguments(6,2,8),
	        Arguments.arguments(8,8,16));
	}	

	@ParameterizedTest
	@MethodSource("datosParaElTest")
	public void testSuma(Integer num1, Integer num2, Integer res) throws InterruptedException {

		Thread.sleep(1000);
		System.out.println("Prueba numero: " + ++contador);
		WebElement campo1 = driver.findElement(By.id("n1"));
		WebElement campo2 = driver.findElement(By.id("n2"));
		//limpiamos los campos para cada prueba
		campo1.clear();
		campo2.clear();
		//escribimos los datos entrados por el constructor
		campo1.sendKeys(num1.toString());
		campo2.sendKeys(num2.toString());
		
		//pulsamos en el boton sumar
		WebElement btnSuma = driver.findElement(By.id("btn-suma"));
		btnSuma.click();
		
		//comprobamos la igualdad
		WebElement campoRes = driver.findElement(By.id("res"));
		assertEquals(res.toString(), campoRes.getText());
		
		
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
