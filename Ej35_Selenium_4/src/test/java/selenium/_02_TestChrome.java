package selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class _02_TestChrome {
	
	//PARA DESCARGAR EL CHROME DRIVER:
	//
	//https://googlechromelabs.github.io/chrome-for-testing/#stable
	
	private static WebDriver driver;
	
	//Crear el driver y abrir el navegador consume mucho tiempo
	//Es conveniente crearlo antes de todos los test (o usar selenium grid)
	//
	//En este ejemplo hay dos test y los dos usarán la misma instancia del navegador
	//
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", 
				"src/test/resources/drivers/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void goToGoogle() throws InterruptedException {
		driver.get("http://www.google.com");	
		Thread.sleep(4000);		
	}

	@Test
	public void goToStackOverflow() throws InterruptedException {
		driver.get("http://www.stackoverflow.com");		
		Thread.sleep(4000);
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
