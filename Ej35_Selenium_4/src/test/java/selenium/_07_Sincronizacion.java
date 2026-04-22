package selenium;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@Execution(ExecutionMode.SAME_THREAD)
public class _07_Sincronizacion {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	//WAIT IMPLICITO
	//Se indica al driver y este lo aplica a todas las búsquedas
	@Test
	public void testSincronoImplicito() throws InterruptedException {
		driver.get(Constantes.URL);
		
		//Podemos hacer una ineficiente espera activa (MAL):
		//Thread.sleep(8000);		
		//WebElement button2 = driver.findElement(By.cssSelector("#btn-1"));
		
		//Le indicamos al driver que si le pedimos un webElement y no está que espere
		//un tiempo antes de lanzar la excepción por si aparece
		//podemos ir cambiando el tiempo, el boton aparece en 4000 milisegundos
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));

		//Usamos unos System.out.println por comodidad...
		System.out.println("========================");
		System.out.println("Antes del findElement");
		long inicio = System.currentTimeMillis();
		//El driver esperará un máximo de ocho segundos si el botón no está
		WebElement button = driver.findElement(By.cssSelector("#btn-1"));
		long fin = System.currentTimeMillis();
		System.out.println("Despues del findElement: " + (fin-inicio));
		//Al pulsar el botón aparecerá un mensaje EN LA CONSOLA
		button.click();
		System.out.println("Saliendo del test:");
	}
	
	//WAIT EXPLICITO
	//Detiene el test hasta que esté disponible un elemento
	//@Test
	public void testWebDriverWait() {
		System.out.println("DOS");
		driver.get(Constantes.URL);
		
		//como mucho espero 8 segundos a que aparezca, si aparece antes no espero mas
		System.out.println("========================");
		long inicio = System.currentTimeMillis();
		
		System.out.println("Esperando al botón...");		
		//Creamos un wait, pero aún no lo estamos utilizando
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));		
		//No pasará de esta línea hasta que esté disponible el elemento
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-1")));

		long fin = System.currentTimeMillis();
		System.out.println("Despues del wait.until: " + (fin-inicio));//aqui no se para, se para en el findElement
		WebElement button = driver.findElement(By.cssSelector("#btn-1"));

		//Al pulsar el botón aparecerá un mensaje EN LA CONSOLA
		button.click();
		
		System.out.println("Saliendo del test");
	}
	
	/* FLUENT WAIT (también es explícito)
	 * Nos permite indicar al sistema una cantidad de tiempo a esperar y la 
	 * frecuencia con la que va a buscar un elemento en el DOM de la página web. 
	 * También podemos indicarle que ignore cierto tipo de excepciones como 
	 * NoSuchElementException.
	 */

	//@Test
	public void testFluentWait() {
		driver.get(Constantes.URL);
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(60000))
				.pollingEvery(Duration.ofMillis(250))
				.ignoring(Exception.class);
		
		/*
		WebElement button = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver drive) {
				return drive.findElement(By.id("btn-1"));
			}
		});
		*/
		
		//Con una expresion lambda
		System.out.println("Está ya el botón?");
		WebElement button = wait.until(driver -> {
				System.out.println("y ahora?");
				return driver.findElement(By.id("btn-1"));
			});		
		button.click();
	}

	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}

}

