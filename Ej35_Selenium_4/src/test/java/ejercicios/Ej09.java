package ejercicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/*
 *  Ejercicio 9
 * Navegar a 'http://es.wikipedia.org/wiki/Especial:Contribuciones' 
 * Encuentra el desplegable
 * Comprobar si permite seleccionar varias opciones o no
 * Seleccionar del desplegable la opción de 'Usuario' y comprobar que está seleccionada
 * Seleccionar del desplegable la opción de 'MediaWiki' y comprobar que está seleccionada
 * Seleccionar del desplegable la opción de 'Ayuda' y comprobar que está seleccionada
 */
public class Ej09 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/wiki/Especial:Contribuciones");
	}
	
	@Test
	public void testEjercicio9() {
		Select namespace = new Select(driver.findElement(By.id("namespace")));
		
		assertFalse(namespace.isMultiple());
		assertEquals(namespace.getOptions().size(), 31);
		
		namespace.selectByVisibleText("Usuario");
		assertEquals(namespace.getFirstSelectedOption().getText(), "Usuario");
		
		namespace.selectByValue("8");
		assertEquals(namespace.getFirstSelectedOption().getText(), "MediaWiki");
		
		namespace.selectByIndex(13);
		assertEquals(namespace.getFirstSelectedOption().getText(), "Ayuda");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
