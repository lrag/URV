package ejercicios;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/*
 *  Ejercicio 15 NO FUNCIONA TODO
 * Navegar a 'http://es.wikipedia.org/wiki/Wikipedia:Portada'
 * Pulsar el botón de buscar
 * Espera esplícita hasta que el title de la página contenga 'Buscar'
 * Obtener el input y buscar 'Selenium'
 * Crea una espera con timeout de 10s y que realice la búsqueda del elemento cada 200ms,
   ignorando las excepciones
 * Espera hasta que el elemento buscado se haya mostrado
 * Pulsar en el primer resultado
 * Comprobar que el title de la página acaba en '- Wikipedia, la enciclopedia libre'
 */
public class Ej15 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://es.wikipedia.org/wiki/Wikipedia:Portada");
	}
	
	@Test
	public void testEjercicio15() {
		WebElement searchButton = driver.findElement(By.id("searchButton"));
		searchButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Buscar"));
	
		WebElement searchBox = driver.findElement(By.name("search"));
		searchBox.clear();
		searchBox.sendKeys("selenium");
		searchBox.submit();
		
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(1000))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(Exception.class);
		
		WebElement resultsElem = fWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				
				//cualquier input cuyo atributo type sea submit Y su atributo value sea login
				//WebElement button1 = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
				
				WebElement elem = driver.findElement(By.xpath("//a[@data-serp-pos='0']"));
				
				if (elem != null) {
					return elem;
				} else {
					return null;
				}
			}
		});
		
		assertTrue(resultsElem.isDisplayed());
		resultsElem.click();
		
		assertTrue(driver.getTitle().endsWith("- Wikipedia, la enciclopedia libre"));
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
