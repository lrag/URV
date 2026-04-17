package ejercicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 2
 * Navegar a 'http://es.wikipedia.org
 * Obtener los enlaces que hay dentro del elemento con id 'p-navigation'
 * Comprobar que se obtiene el número correcto de enlaces
 * 
 * Basicamente contar el numero de enlaces (etiquetas li) dentro de ese id
 */
public class Ej02 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/");
	}
	
	@Test
	public void testEjercicio2() {
		List<WebElement> enlaces = driver.findElement(By.id("p-navigation")).findElement(By.className("body")).findElements(By.tagName("li"));
		for (WebElement enlace: enlaces) {
			assertNotNull(enlace);
		}
		//hay 8 visibles pero hay uno que no se muestra
		assertEquals(enlaces.size(), 9);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
