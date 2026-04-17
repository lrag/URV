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
 * Ejercicio 7
 * Navegar a 'http://es.wikipedia.org/wiki/Wikipedia:Portada'
 * Encuentra el elemento input
 * Vacía su contenido y busca 'Selenium'
 * Envia el formulario y pulsa el botón
 * Comprobar que vamos a la página correcta
 */
public class Ej07 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");
	}
	
	@Test
	public void testEjercicio7() {
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		searchBox.clear();
		searchBox.sendKeys("Selenium");
		
		WebElement sendButton = driver.findElement(By.id("searchButton"));
		sendButton.click();
		
		assertEquals(driver.getTitle(), "Selenium - Wikipedia, la enciclopedia libre");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

}
