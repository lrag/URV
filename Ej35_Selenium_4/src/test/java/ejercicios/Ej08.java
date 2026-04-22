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
 *  Ejercicio 8
 * Navegar a 'http://es.wikipedia.org/wiki/Wikipedia:Portada'
 * Encuentra el elemento input
 * Comprobar que el placeholder del input es correcto
 * Comprobar que el tamaño de la letra del input es la correcta
 * Buscar 'Selenium' en la Wikipedia
 * Comprobar que vamos a la página correcta
 */
public class Ej08 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");
	}
	
	@Test
	public void testEjercicio8() {
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		WebElement sendButton = driver.findElement(By.id("searchButton"));
		
		String placeholderSearch = searchBox.getAttribute("placeholder");
		assertEquals(placeholderSearch, "Buscar en Wikipedia");
		
		String fontSizeSearch = searchBox.getCssValue("font-size");
		assertEquals(fontSizeSearch, "13px");		
		
		searchBox.clear();
		searchBox.sendKeys("Selenium");
		sendButton.click();
		
		//comprobamos que vamos a la pagina correcta por el titulo
		assertEquals(driver.getTitle(), "Selenium - Wikipedia, la enciclopedia libre");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
