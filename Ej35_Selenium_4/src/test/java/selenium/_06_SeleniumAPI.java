package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

@Execution(ExecutionMode.SAME_THREAD)
public class _06_SeleniumAPI {
	private static WebDriver driver;
	
	/*
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", 
				"src/test/resources/drivers/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	*/
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();		
	}	
	
	//@Test
	public void testDoubleClick() throws InterruptedException {
		//driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium_4/DoubleClickDemo.html");
		WebElement mensaje = driver.findElement(By.id("message"));
		
		Thread.sleep(5000);
		//La clase Actions realizar acciones más complejas
		Actions actions = new Actions(driver);
		actions.doubleClick(mensaje).perform();
		assertEquals("rgb(255, 255, 0)", mensaje.getCssValue("background-color"));

		Thread.sleep(2000);
	}
	
	//@Test
	public void testDragAndDrop() throws InterruptedException {
		//driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium_4/DragAndDropDemo.html");
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		Thread.sleep(5000);
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
		assertEquals(target.getText(), "Dropped!");
	
		Thread.sleep(2000);
	}
	
	//@Test
	public void testJavascript() throws InterruptedException {
		driver.get(Constantes.URL);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("saludar()");
		//Podemos enviar a ejecutar cualquier JS
		//js.executeScript("alert('HABER KE PASA')");
		
		
		//seleccionamos el alert que se nos muestra en pantalla
		//con switchTo indicamos el elemento al que queremos acceder
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "Hola mundo!!!");
		
		Thread.sleep(2000);
		
		alert.accept();
	}

	//Este no tiene que fallar, generamos las capturas
	//beforeDragAndDrop.png
	//afterDragAndDrop.png
	@Test
	public void testScreenshots() throws IOException {
		//driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium_4/DragAndDropDemo.html");
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		//Le hacemos cast al driver a 'TakeScreenshot'
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		//Tomamos una captura de la pantalla
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		//Copiamos la captura de pantalla de la memoria a un directorio fisico
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/beforeDragAndDrop.png"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
		assertEquals(target.getText(), "Dropped!");
		
		srcFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/afterDragAndDrop.png"));
	}

	//Eventos
	//Este SI tiene que fallar para que cree una captura de pantalla, en la carpeta de resources
	//onException.png
	@Test
	public void testEventosWebDriver() {
		
		//Esta clase la hemos creado nosostros y será la que reciba los eventos
		//Esta clase guarda los métodos de retro llamada o callback		
		WebDriverListener oyente = new _06_WebDriverListener(driver);
		
		//Con esta clase se nos permite escuchar eventos lanzados por el driver
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(oyente);
		
		//A partir de ahora utilizaremos el webDriver que ha sido decorado con el oyente
		WebDriver decorated = decorator.decorate(driver);

		//Cargamos Wikipedia
		decorated.get("https://es.wikipedia.org");

		//Dispara los eventos 'beforeFindBy' y 'afterFindBy'
		WebElement input = decorated.findElement(By.id("searchInput"));
		input.sendKeys("Selenium");
		input.sendKeys(Keys.RETURN);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Buscamos algo que no existe para que se produzca una excepción y se
		//lance el evento 'onError'
		WebElement input2 = decorated.findElement(By.id("searchInput_TROLOLO"));
		input2.sendKeys("Selenium");
		
		/*
		//Por si no hay internet 
		eventDriver.get(Constantes.URL);
		eventDriver
			.findElement(
				By.id("userTrololo")).getTagName();*/
		
	}
	
	//Podemos hacer test sobre las cookies a traves del metodo manage()
	//@Test
	public void testCookies() {
		driver.get(Constantes.URL);
		Cookie cookie = new Cookie("cookie_selenium", "Cookie creada por nosostros");
		driver.manage().addCookie(cookie);
		
		//Comprobamos que la cookie creada por nososotros está
		Cookie cookie2 = driver.manage().getCookieNamed("cookie_selenium");
		assertEquals("Cookie creada por nosostros", cookie2.getValue());
		
		//Borramos la cookie y comprobamos
		driver.manage().deleteCookieNamed("cookie_selenium");
		cookie2 = driver.manage().getCookieNamed("cookie_selenium");
		assertNull(cookie2);
	}	
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
