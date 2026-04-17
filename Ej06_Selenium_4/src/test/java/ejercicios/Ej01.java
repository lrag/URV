package ejercicios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 *  Ejercicio 1
 * Navegar a 'http://es.wikipedia.org
 * Obtener los enlaces que hay dentro del elemento con id 'p-navigation'
 * Comprobar que los enlaces son los correctos
 * 
 * Son los enlaces que hay arriba a la izquierda (Portada, Portal de la comunidad, etc.)
 */

public class Ej01 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/");
	}
	
	@Test
	public void testEjercicio1() {
		WebElement portada = driver.findElement(By.id("n-mainpage-description"));
		assertEquals(portada.getText(), "Portada");
		
		WebElement portal = driver.findElement(By.id("n-portal"));
		assertEquals(portal.getText(), "Portal de la comunidad");
		
		WebElement ayuda = driver.findElement(By.id("n-help"));
		assertEquals(ayuda.getText(), "Ayuda");
		
		WebElement donaciones = driver.findElement(By.id("n-sitesupport"));
		assertEquals(donaciones.getText(), "Donaciones");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
