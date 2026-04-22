package selenium;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@Execution(ExecutionMode.SAME_THREAD)
public class _05_Interactions {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	//Escribimos algo en google y hacemos submit
	@Test
	public void textBoxSubmit() throws InterruptedException {
		driver.get("http://www.google.es");

		//Para aceptar los términos 
		WebElement button = driver.findElement(By.id("L2AGLb"));
		button.click();		
		
		WebElement input = driver.findElement(By.name("q"));
		input.sendKeys("React");
		Thread.sleep(1000);
		input.clear();
		Thread.sleep(1000);
		input.sendKeys("Angular");
		Thread.sleep(1000);
		input.submit();
	}
	
	
	//Escribimos algo en wikipedia y hacemos click en el boton
	@Test
	public void buttonClick() {
		driver.get("https://es.wikipedia.org");
		WebElement input = driver.findElement(By.id("searchInput"));
		input.sendKeys("React");
		input.clear();
		input.sendKeys("Angular");
		
		WebElement button = driver.findElement(By.id("searchButton"));
		button.click();
	}

	
	//Obtener texto
	@Test
	public void getTextFromElement() {
		driver.get(Constantes.URL);
		WebElement label = driver.findElement(By.id("lb-mensaje"));
		assertEquals(label.getText(), "Mensaje:");
	}
	
	@Test
	public void getCssValueAndAttribute() {
		driver.get(Constantes.URL);
		WebElement mensaje = driver.findElement(By.id("mensaje"));
		assertEquals(mensaje.getDomAttribute("value"), "Hola Mundo!!!");
		assertEquals(mensaje.getCssValue("width"), "200px");
	}
	
	@Test
	public void testDropdown() throws InterruptedException {
		driver.get(Constantes.URL);
		//para trabajar con los atributos especificos de los select
		//creamos un objeto Select a partir de su web element
		WebElement select = driver.findElement(By.id("carOptions"));
		Select coches = new Select(select);
		//comprobamos que no se puede seleccionar multiples
		assertFalse(coches.isMultiple());
		//el numero de opciones es 4
		assertEquals(coches.getOptions().size(), 4);
		
		Thread.sleep(1000);
		
		//seleccionamos por texto visible al coche
		coches.selectByVisibleText("Tesla");
		//podemos comprobar la opcion seleccionada, por el paso anterior
		assertEquals(coches.getFirstSelectedOption().getText(), "Tesla");
		
		Thread.sleep(1000);

		//ahora seleccionamos por value
		coches.selectByValue("ford");
		//ahora la opcion seleccionada su atributo value es 'ford'
		assertEquals(coches.getFirstSelectedOption().getDomAttribute("value"), "ford");
		
		Thread.sleep(1000);
		
		//ahora seleccionamos la opción 0
		coches.selectByIndex(0);
		assertEquals(coches.getFirstSelectedOption().getText(), "BMW");
		
		Thread.sleep(1000);
	}
	
	@Test
	public void testDropdownMultiple() {
		driver.get(Constantes.URL);
		Select colores = new Select(driver.findElement(By.id("colorOptions")));
		
		assertTrue(colores.isMultiple());
		assertEquals(colores.getOptions().size(), 4);
		
		//seleccionamos dos opciones, la primera por texto visible y la segunda por valor
		colores.selectByVisibleText("Yellow");
		colores.selectByValue("red");
		assertEquals(colores.getAllSelectedOptions().size(), 2);
		
		//ahora deseleccionamos  por valor
		colores.deselectByValue("yellow");
		assertEquals(colores.getAllSelectedOptions().size(), 1);
		
		//ahora deseleccionamos por indice
		colores.deselectByIndex(0);
		assertEquals(colores.getAllSelectedOptions().size(), 0);
		
		//ahora seleccionamos por indice las posiciones 0 y 3
		colores.selectByIndex(0);
		colores.selectByIndex(3);
		assertEquals(colores.getAllSelectedOptions().size(), 2);
		
		//deseleccionamos por texto visible Black
		colores.deselectByVisibleText("Black");
		assertEquals(colores.getAllSelectedOptions().size(), 1);
		
		//deseleccionamos todo
		colores.deselectAll();
		assertEquals(colores.getAllSelectedOptions().size(), 0);
	}

	@Test
	public void testCorrectOptions() {
		driver.get(Constantes.URL);
		
		ArrayList<String> listaMeses = new ArrayList<String>();
		listaMeses.add("Enero");
		listaMeses.add("Febrero");
		listaMeses.add("Marzo");
		listaMeses.add("Abril");
		listaMeses.add("Mayo");
		
		//seleccionamos 
		Select meses = new Select(driver.findElement(By.id("meses")));
		List<String> valoresSelect = new ArrayList<String>();
		for (WebElement option : meses.getOptions()) {
			valoresSelect.add(option.getText());
		}
		//mismos elementos y en la misma posición
		assertArrayEquals(listaMeses.toArray(), valoresSelect.toArray());
	}
	
	@Test
	public void testRadioButtons() {
		driver.get(Constantes.URL);
		WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='Yes']"));
		//comprobamos que no esta seleccionado
		assertFalse(radioButton.isSelected());
		
		//si no esta seleccionamos lo clickeamos
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
		//ahora si debería de estar seleccionado
		assertTrue(radioButton.isSelected());
	}

	@Test
	public void testCheckBoxes() throws InterruptedException {
		driver.get(Constantes.URL);
		
		Thread.sleep(5000);
		
		//Seleccionamos el checkbox con id musica
		WebElement checkMusica = driver.findElement(By.xpath("//input[@id='musica']"));
		assertFalse(checkMusica.isSelected());
		
		if (!checkMusica.isSelected()) {
			checkMusica.click();
		}
		assertTrue(checkMusica.isSelected());
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
	
}
