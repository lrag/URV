package selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class _01_FirefoxTest {
	
	private static WebDriver driver;
	
	//Crear el driver y abrir el navegador consume mucho tiempo
	//Es conveniente crearlo antes de todos los test (o usar selenium grid)
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void goToGoogle() {
		driver.get("http://www.google.com");	
		//driver.get("http://localhost:8080/Ej06_Selenium/pagina.html");	
	}
	
	@AfterAll
	public static void tearDown() {
		//Comentamos esta línea para que no se cierre el navegador 
		//despues del test, pero es necesaria:		
		driver.quit();
	}
	
}
