package ejercicios;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 *  Ejercicio 12
 * Navegar a 'http://es.wikipedia.org/w/index.php?title=Especial:Crear_una_cuenta'
 * Comprobamos que un elemento no está presente y mostramos un mensaje
 * Comprobar que un elemento no está presente y mostramos una excepción con error
	personalizado
 *Comprobamos que un elemento está presente y se está mostrando
 */
public class Ej12 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/w/index.php?title=Especial:Crear_una_cuenta");
	}
	
	@Test
	public void testEjercicio12() {
		if (!isElementPresent(By.id("noExist"))) {
			System.out.println("Lo sabia!!!");
		}
		
		if (!isElementPresent(By.id("wpEditToken"))) {
			WebElement editToken = driver.findElement(By.id("wpEditToken"));
			assertFalse(editToken.isDisplayed());
		}
		
		if (!isElementPresent(By.id("noExist"))) {
			fail("El elemento #userLoginForm no se encuentra en la página");
		}
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
