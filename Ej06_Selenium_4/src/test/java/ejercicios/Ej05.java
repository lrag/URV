package ejercicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 5
 * Navegar a 'http://www.w3schools.com/html/html_tables.asp'
 * Encontrar la primera tabla de la página
 * Comprueba que la tabla existe
 * Comprueba que tiene el número de filas correcto
 * Comprueba que la última fila tiene el número de celdas correcto
 * Comprueba que después de la quinta fila, hay dos filas más
 * Comprueba que todas las celdas tienen contenido
 */
public class Ej05 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
	}
	
	@Test
	public void testEjercicio5() {
		//Encontrar la primera tabla de la página
		WebElement table = driver.findElement(By.xpath("//*[@id='customers']"));
		//la tabla existe
		assertNotNull(table);
		
		//comprbar que tiene el número de filas correcto
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		assertEquals(rows.size(), 7);
		
//		List<WebElement> lastRowCells = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr[last()]/td"));
		//Comprobar que la ultima fila tiene el numero de celdas correcto
		List<WebElement> lastRowCells = rows.get(rows.size() - 1).findElements(By.xpath("./td"));
		assertEquals(lastRowCells.size(), 3);
		
//		List<WebElement> last2Rows = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr[position()>5]"));
		//Comprobar que despues de la quinta fila hay dos filas más
		List<WebElement> last2Rows = table.findElements(By.xpath("./tbody/tr[position()>5]"));
		assertEquals(last2Rows.size(), 2);

//		List<WebElement> cells = table.findElements(By.tagName("td"));
		//comprobar que todas las celdas tienen contenido
		List<WebElement> cells = table.findElements(By.xpath(".//td"));
		assertEquals(cells.size(), 18);
		for (WebElement cell : cells) {
			assertFalse(cell.getText().length() == 0);
		}
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
