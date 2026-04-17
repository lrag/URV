package ejercicios;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 17
 * Navegar a la página https://es.wikipedia.org/wiki/Wikipedia:Portada
 * Crear instancia de WikipediaMainPage
 * Comprobar que el placeholder es correcto
 * Buscar 'Selenium'
 * Comprobar que estamos en la página correcta
 * Crear clase WikipediaMainPage
 * Crear el constructor
 * Crear método para obtener el campo de búsqueda
 * Crear método para obtener el botón de búsqueda
 * Crear método para obtener el link de crear cuenta
 * Crear método para obtener el link con el id 'pt-anoncontribs'
 * Crear método para establecer el texto a buscar
 * Crear método para realizar la búsqueda
 * Crear método para navegar al link de contributors
 * Crear método para crear una cuenta
 * Crear método para obtener el texto del placeholder
 */
public class Ej17 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(Ej17WikipediaMainPage.url);
	}

	@Test
	public void testSearch() {
		Ej17WikipediaMainPage page = new Ej17WikipediaMainPage(driver);
		assertEquals(page.getPlaceholderText(), "Buscar en Wikipedia");
		
		page.setSearchText("Selenium");
		page.search();
		
		assertEquals(driver.getTitle(), "Selenium - Wikipedia, la enciclopedia libre");
	}

	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
