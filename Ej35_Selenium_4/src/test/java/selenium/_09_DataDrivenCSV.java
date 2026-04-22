package selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.opencsv.CSVReader;

@Execution(ExecutionMode.SAME_THREAD)
public class _09_DataDrivenCSV {

	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/registerData.csv")
	public void testSignUp(String username, String password, String confirmPassword, String email,	String captcha) throws InterruptedException { 

		//Cargamos la página de alta de usuarios de www.wikipedia.org
		driver.get("https://es.wikipedia.org/w/index.php?title=Especial:Crear_una_cuenta");
		
		WebElement usernameElement = driver.findElement(By.cssSelector("#wpName2"));
		usernameElement.clear();
		usernameElement.sendKeys(username);

		WebElement passElement = driver.findElement(By.cssSelector("#wpPassword2"));
		passElement.clear();
		passElement.sendKeys(password);

		WebElement confirmPassElement = driver.findElement(By.cssSelector("#wpRetype"));
		confirmPassElement.clear();
		confirmPassElement.sendKeys(confirmPassword);

		WebElement emailElement = driver.findElement(By.cssSelector("#wpEmail"));
		emailElement.clear();
		emailElement.sendKeys(email);

		WebElement captchaElement = driver.findElement(By.cssSelector("#mw-input-captchaWord"));
		captchaElement.clear();
		captchaElement.sendKeys(captcha);

		WebElement submitBtn = driver.findElement(By.cssSelector("#wpCreateaccount"));
		submitBtn.click();

		//Usamos un fluent wait para experar la respuesta, experaremos max 10 seg
		//y comprobaremos que ha llegado la respuesta cada 2 seg
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2));

		//Esperaremos hasta que aparezca el mesaje de error, ya que los captcha son 
		//inventados
		WebElement error = fluentWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver arg0) {
				WebElement elem = arg0.findElement(By.cssSelector(".errorbox"));
				if (elem.isDisplayed()) {
					return elem;
				} else {
					return null;
				}	
			}
		});
		//debería llegar el error
		assertNotNull(error);
		
		Thread.sleep(2000);
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}

}
