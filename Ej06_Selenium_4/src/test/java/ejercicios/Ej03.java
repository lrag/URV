package ejercicios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 *  Ejercicio 3
 * Navegar a 'http://es.wikipedia.org
 * Encuentra todos los links que hay en el div con id 'p-personal' y comprueba que existen
 * Encuentra algún link que contenga texto dinámico y comprueba que existe
 */
public class Ej03 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/");
	}
	
	@Test
	public void testEjercicio3() {
		//El div con id p-personal
		WebElement discussion = driver.findElement(By.linkText("Discusión"));
		WebElement contributions = driver.findElement(By.linkText("Contribuciones"));
		WebElement account = driver.findElement(By.linkText("Crear una cuenta"));
		WebElement access = driver.findElement(By.linkText("Acceder"));
		
		assertNotNull(discussion);
		assertNotNull(contributions);
		assertNotNull(account);
		assertNotNull(access);
		
		//tenemos que poner el mes en el que estamos
		WebElement mes = driver.findElement(By.partialLinkText("enero"));
		assertNotNull(mes);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
