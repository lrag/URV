package ejercicios;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 14 NO FUNCIONA PROBLEMAS DE CONEXION TODO
 * Navegar a 'http://es.wikipedia.org/wiki/Wikipedia:Portada'
 * Comprobar que hay una cookie con el nombre 'GeoIP'
 * Obtener todas las cookies
 * Comprobar que hay cookies
 * Eliminar todas las cookies
 * Comprobar que no hay cookies
 */
public class Ej14 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");
	}
	
	@Test
	public void testEjercicio14() {
		Cookie geoIPCookie = driver.manage().getCookieNamed("GeoIP");
		assertNotNull(geoIPCookie);
		
		Set<Cookie> cookies = driver.manage().getCookies();
		assertEquals(cookies.size() > 0, true);
		
		driver.manage().deleteAllCookies();
		
		cookies = driver.manage().getCookies();
		assertEquals(cookies.size(), 0);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
