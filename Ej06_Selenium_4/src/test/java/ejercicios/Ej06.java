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

/* NO FUNCIONA TODO
 * Navegar a
	'http://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced'
 * Encuentra los checkboxes de la página
 * Comprueba que hay 28 checkboxes
 * Encuentra el campo de texto para realizar búsquedas y el botón de buscar
 * Comprueba que existen tanto el campo de búsqueda como el botón
 * Selectores CSS para búsquedas de hijos
 */
public class Ej06 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced");
	}
	
	@Test
	public void testEjercicio6() {
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("#mw-searchoptions input[name^='ns']"));
		
		assertEquals(checkboxes.size(), 28);
		
		WebElement searchBox = driver.findElement(By.cssSelector("#searchText .oo-ui-inputWidget-input"));
		WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
		
		assertNotNull(searchBox);
		assertNotNull(searchButton);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
