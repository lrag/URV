package ejercicios;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 13 NO FUNCIONA TODO
 * Navegar a 'http://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced'
 * Obtener el checkbox principal y comprobar que está seleccionado
 * Obtener todos los checkboxes
 * Capturar pantalla y guardar la imagen
 * Seleccionar todos los checkboxes que no lo estén
 * Capturar pantalla y guardar la imagen
 */
public class Ej13 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/w/index.php?title=Especial:Buscar&search=&fulltext=Buscar&profile=advanced");
	}
	
	@Test
	public void testEjercicio13() throws IOException {
		WebElement mainCheckbox = driver.findElement(By.id("mw-search-ns0"));
		assertTrue(mainCheckbox.isSelected());
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("#mw-searchoptions input[name^='ns']"));
		
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/checkboxes_before.png"));
		
		for (WebElement check : checkboxes) {
			if (!check.isSelected()) {
				check.click();
			}
		}
		
		srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/checkboxes_after.png"));
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
