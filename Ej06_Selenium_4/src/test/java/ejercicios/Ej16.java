package ejercicios;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

/*
 *  Ejercicio 16
 * Obtener el campo de usuario y rellenarlo
 * Obtener el campo de password y rellenarlo
 * Obtener el campo de repetir password y rellenarlo
 * Obtener el campo de email y rellenarlo
 * Obtener el campo de captcha y rellenarlo
 * Obtener el botón y enviar el formulario
 * Esperar hasta que el elemento de error se muestre
 */

@Execution(ExecutionMode.SAME_THREAD)
public class Ej16 {
	
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://es.wikipedia.org/w/index.php?title=Especial:Crear_una_cuenta");
	}

	//Estos van a ser los parametros que van a entrar por el 
	//constructor. Como tiene 4 filas, se ejecutara 4 veces y cada
	//vez entrará por el constructor todas las columnas
	static Stream<Arguments> datosParaElTest() {
	    return Stream.of(
	        Arguments.arguments("robb", "1234", "1234", "robb.st@gmail.com", "yuasd"),
	        Arguments.arguments("rickon", "3333", "3333", "rickon.st@gmail.com", "jsdks"),
	        Arguments.arguments("arya", "1010", "1010", "arya.st@gmail.com", "sdsko"),
	        Arguments.arguments("sansa", "1777", "1777", "sansa.st@gmail.com", "lpijd"),
	        Arguments.arguments("bran", "0001", "0001", "bran.st@gmail.com", "tsdun"));
	}	


	@ParameterizedTest
	@MethodSource("datosParaElTest")	
	public void testSignUp(String username, String pass, String confirmPass, String email,	String captcha) { 		
		WebElement usernameElem = driver.findElement(By.id("wpName2"));
		WebElement passElem = driver.findElement(By.id("wpPassword2"));
		WebElement confirmPassElem = driver.findElement(By.id("wpRetype"));
		WebElement emailElem = driver.findElement(By.id("wpEmail"));
		WebElement captchaElem = driver.findElement(By.id("mw-input-captchaWord"));
		
		usernameElem.clear();
		usernameElem.sendKeys(username);
		passElem.clear();
		passElem.sendKeys(pass);
		confirmPassElem.clear();
		confirmPassElem.sendKeys(confirmPass);
		emailElem.clear();
		emailElem.sendKeys(email);
		captchaElem.clear();
		captchaElem.sendKeys(captcha);
		
		WebElement submitButton = driver.findElement(By.id("wpCreateaccount"));
		submitButton.click();
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(Exception.class);
		
		WebElement errorElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("errorbox")));
		assertTrue(errorElem.isDisplayed());
		
//		WebElement errorElem = wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver arg0) {
//				WebElement elem = arg0.findElement(By.className("error"));
//				if (elem != null) {
//					return elem;
//				} else {
//					return null;
//				}
//			}
//		});
		
		assertNotNull(errorElem);
		
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}
