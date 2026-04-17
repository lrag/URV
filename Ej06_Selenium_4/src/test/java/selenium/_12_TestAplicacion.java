package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class _12_TestAplicacion {
	
	private static WebDriver driver;

	//@BeforeEach
	//public void setUp() throws Exception{
	@BeforeAll
	public static void setUp() throws Exception{
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/login.html");
		Thread.sleep(2000);
	
		WebElement login     = driver.findElement(By.name("login"));
		WebElement pw        = driver.findElement(By.name("pw"));
		WebElement btnEntrar = driver.findElement(By.id("btnEntrar"));
		login.clear();
		pw.clear();
		
		login.sendKeys("aaa");
		Thread.sleep(750);
		pw.sendKeys("bbb");
		Thread.sleep(750);
		
		btnEntrar.sendKeys(Keys.SHIFT);
		Thread.sleep(1500);		
		
		btnEntrar.click();	
		
		//Comprobamos que nos envían un redirect y la cookie JSESSIONID (no formaría parte de este test) 
		Cookie token = driver.manage().getCookieNamed("JSESSIONID");
		System.out.println("JSESSIONID:"+token.getValue());
		assertNotNull(token);	
		
	}
	
	@Test
	@DisplayName("Prueba funcional: insertar cliente")
	public void insertarCliente() throws InterruptedException {

		//Podemos pedir la pantalla del listado y pulsar nuevo
		driver.get("http://localhost:8080/Ej06_Selenium_4/seguro/SVClientes");
		driver.findElement(By.id("btnNuevo")).click();
		
		//Podemos pedir la pantalla del formulario directamente
		//driver.get("http://localhost:8080/Ej06_Selenium_4/seguro/SVClientes?accion=verFormulario");
		
		//Esperamos el formulario como respuesta
		WebElement h1 = driver.findElement(By.xpath("//h1[@class='titulo']"));
		assertEquals(h1.getText(), "Formulario de clientes");			
		
		WebElement hiddenId     = driver.findElement(By.name("idCliente"));
		WebElement txtNombre    = driver.findElement(By.name("nombre"));
		WebElement txtDireccion = driver.findElement(By.name("direccion"));
		WebElement txtTelefono  = driver.findElement(By.name("telefono"));
		
		//Esperamos que las cajas de texto estén vacías
		assertEquals("", hiddenId.getDomAttribute("value").trim());
		assertEquals("", txtNombre.getDomAttribute("value").trim());
		assertEquals("", txtDireccion.getDomAttribute("value").trim());
		assertEquals("", txtTelefono.getDomAttribute("value").trim());		
		
		//Definimos unos valores
		String nombre    = "Ringo Starr";
		String direccion = "C/Su calle";
		String telefono  = "555123456";
		
		//Rellenamos el formulario
		Thread.sleep(1500);
		txtNombre.sendKeys(nombre);
		Thread.sleep(750);
		txtDireccion.sendKeys(direccion);
		Thread.sleep(750);
		txtTelefono.sendKeys(telefono);
		Thread.sleep(750);
		
		//Buscamos el botón para insertar
		WebElement btnInsertar = driver.findElement(By.id("btnInsertar"));
		btnInsertar.sendKeys(Keys.SHIFT);	
		Thread.sleep(1500);
		
		//Pulsamos el botón
		//Al pulsarlo se hace un submit del formulario y el navegador pide otra página
		btnInsertar.click();
		
		//No hace falta colocar ningun retardo ni espera para estar seguros de que se
		//ha cargado la siguiente página. Lo hace selenium de manera automática
				
		//Esperamos el listado como respuesta
		h1 = driver.findElement(By.xpath("//h1[@class='titulo']"));
		assertEquals(h1.getText(), "Listado de clientes");
		
		//Esperamos que la última fila de la tabla contenga al cliente insertado

		WebElement tablaClientes = driver.findElement(By.id("tablaClientes"));
		//driver.findElements(By.tagName("tr")); //Todos los tr de la página
		List<WebElement> filas = tablaClientes.findElements(By.tagName("tr")); //Todos los tr que 'cuelguen' de 'tablaClientes'
		WebElement ultimaFila = filas.get(filas.size()-1);
		
		List<WebElement> columnas = ultimaFila.findElements(By.tagName("td")); //Los td del último tr
		String tdNombre    = columnas.get(0).getText();
		String tdDireccion = columnas.get(1).getText();
		String tdTelefono  = columnas.get(2).getText();	
		
		//Comprobamos que los valores coinciden con los insertados:
		Assertions.assertAll(
					() -> assertEquals(nombre, tdNombre),
					() -> assertEquals(direccion, tdDireccion),
					() -> assertEquals(telefono, tdTelefono)
				);
		//assertEquals(nombre, tdNombre);
		//assertEquals(direccion, tdDireccion);
		//assertEquals(telefono, tdTelefono);
		
		System.out.println("YEPA");
		Thread.sleep(10_000);
		
	}
	
	@Test
	@DisplayName("Prueba funcional: modificar un cliente")
	public void modificarCliente() throws InterruptedException {
		
		//Pedimos la pantalla del listado de clientes
		driver.get("http://localhost:8080/Ej06_Selenium_4/seguro/SVClientes");
		
		//Esperamos el formulario como respuesta
		WebElement h1 = driver.findElement(By.xpath("//h1[@class='titulo']"));
		assertEquals(h1.getText(), "Listado de clientes");			
		
		WebElement tablaClientes = driver.findElement(By.id("tablaClientes"));
		//driver.findElements(By.tagName("tr")); //Todos los tr de la página
		List<WebElement> filas = tablaClientes.findElements(By.tagName("tr")); //Todos los tr que 'cuelguen' de 'tablaClientes'
		WebElement primeraFila = filas.get(0);
		
		List<WebElement> columnas = primeraFila.findElements(By.tagName("td")); //Los td del último tr
		columnas.get(0).findElement(By.tagName("a")).click();
		
		//Comprobar que en el formulario efectivamente están los datos de la fila seleccionada...
		
		//Definimos unos valores
		String nombre    = "Félix Selenium";
		String direccion = "C/nueva direccion";
		String telefono  = "555-FELIX";
		
		WebElement hiddenId     = driver.findElement(By.name("idCliente"));
		WebElement txtNombre    = driver.findElement(By.name("nombre"));
		WebElement txtDireccion = driver.findElement(By.name("direccion"));
		WebElement txtTelefono  = driver.findElement(By.name("telefono"));		
		
		//Rellenamos el formulario
		Thread.sleep(1500);
		txtNombre.clear();
		txtNombre.sendKeys(nombre);
		Thread.sleep(750);
		txtDireccion.clear();
		txtDireccion.sendKeys(direccion);
		Thread.sleep(750);
		txtTelefono.clear();
		txtTelefono.sendKeys(telefono);
		Thread.sleep(750);
		
		//Buscamos el botón para modificar
		WebElement btnModificar = driver.findElement(By.id("btnModificar"));
		btnModificar.sendKeys(Keys.SHIFT);	
		Thread.sleep(1500);
		
		//Pulsamos el botón
		//Al pulsarlo se hace un submit del formulario y el navegador pide otra p�gina
		btnModificar.click();
		
		//No hace falta colocar ningun retardo ni espera para estar seguros de que se
		//ha cargado la siguiente página. Lo hace selenium de manera automática
				
		//Esperamos el listado como respuesta
		h1 = driver.findElement(By.xpath("//h1[@class='titulo']"));
		assertEquals(h1.getText(), "Listado de clientes");
		
		//Esperamos que la primera fila de la tabla contenga los nuevos datos del cñiente
		//reutilizar variables es mi pasión
		tablaClientes = driver.findElement(By.id("tablaClientes"));
		//driver.findElements(By.tagName("tr")); //Todos los tr de la p�gina
		filas = tablaClientes.findElements(By.tagName("tr")); //Todos los tr que 'cuelguen' de 'tablaClientes'
		primeraFila = filas.get(0);
		
		columnas = primeraFila.findElements(By.tagName("td")); //Los td del último tr
		String tdNombre    = columnas.get(0).getText();
		String tdDireccion = columnas.get(1).getText();
		String tdTelefono  = columnas.get(2).getText();	
		
		//Comprobamos que los valores coinciden con los insertados:
		Assertions.assertAll(
				() -> assertEquals(nombre, tdNombre),
				() -> assertEquals(direccion, tdDireccion),
				() -> assertEquals(telefono, tdTelefono)	
			);
	}
	
	
	//@AfterEach
	//public void tearDown() {
	@AfterAll
	public static void tearDown() {
		//Comentamos esta línea para que no se cierre el navegador
		//driver.quit();
	}	
	
}
