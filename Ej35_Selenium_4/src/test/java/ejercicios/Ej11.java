package ejercicios;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 11 NO FUNCIONA TODO
 * Navegar a 'http://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced'
 * Obtener el primer checkbox seleccionado
 * Comprobar que el está seleccionado
 * Obtener todos los checkboxes
 * Seleccionar todos los checkboxes que no lo esten
 * Comprobar que todos los checkboxes están seleccionados
 */
public class Ej11 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced");
	}
	
	@Test
	public void testEjercicio11() {
		WebElement mainCheckbox = driver.findElement(By.id("mw-search-ns0"));
		assertTrue(mainCheckbox.isSelected());
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("#mw-searchoptions input[name^='ns']"));
		
		for (WebElement check : checkboxes) {
			if (!check.isSelected()) {
				check.click();
			}
		}
		
		for (WebElement check : checkboxes) {
			assertTrue(check.isSelected());
		}
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
