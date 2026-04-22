package selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//Esta clase utilizará _12_POM para hacer las pruebas
public class _10_POMTest {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	//@Test
	public void testSinObjetoPom() {
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		WebElement titulo       = driver.findElement(By.name("titulo"));
		WebElement director     = driver.findElement(By.name("director"));
		WebElement genero       = driver.findElement(By.name("genero"));
		WebElement fechaEstreno = driver.findElement(By.name("fechaEstreno"));
		
		WebElement btnInsertar  = driver.findElement(By.id("btnInsertar"));

		titulo.clear();
		director.clear();
		genero.clear();
		fechaEstreno.clear();
		
		titulo.sendKeys("El último grán heroe");
		director.sendKeys("John McTiernan");
		genero.sendKeys("Accion");
		fechaEstreno.sendKeys("1993");	
		
		btnInsertar.click();
		
		//Asertos...
		//...
	}
	
	@Test
	public void testSearchInsertar() throws InterruptedException {

		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		_10_FormularioPeliculaPOM page = new _10_FormularioPeliculaPOM(driver);
		
		Thread.sleep(750);
		page.getTitulo().sendKeys("El último grán heroe");
		Thread.sleep(750);
		page.getDirector().sendKeys("John McTiernan");
		Thread.sleep(750);
		page.getGenero().sendKeys("Accion");
		Thread.sleep(750);
		page.getFechaEstreno().sendKeys("1993");
		Thread.sleep(1000);
		
		page.getBtnInsertar().click();
		
		//asertos necesarios
	}
	
	@Test
	public void testSearchModificar() throws InterruptedException {
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		_10_FormularioPeliculaPOM page = new _10_FormularioPeliculaPOM(driver);
		
		Thread.sleep(750);
		page.getTitulo().sendKeys("El último grán heroe");
		Thread.sleep(750);
		page.getDirector().sendKeys("John McTiernan");
		Thread.sleep(750);
		page.getGenero().sendKeys("Accion");
		Thread.sleep(750);
		page.getFechaEstreno().sendKeys("1993");
		Thread.sleep(1000);
		
		page.getBtnModificar().click();
		
		//asertos necesarios
	}

	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
