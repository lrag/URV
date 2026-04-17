package ejercicios;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Navegar a 'http://es.wikipedia.org
 * Encuentra los títulos de todos los 'mw-panel' usando un XPATH absoluto
 * Encuentra los títulos de todos los 'mw-panel' usando un XPATH relativo
 */
public class Ej04 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/");
	}
	
	@Test
	public void testEjercicio4() {
		//absoluto
		WebElement print = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/h3"));
		//WebElement otherProjects = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[4]/h3"));
		WebElement languages = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/h3"));
		
		assertNotNull(print);
		//assertNotNull(otherProjects);
		assertNotNull(languages);
		
		//relativo
		print = driver.findElement(By.xpath("//*[@id='p-coll-print_export-label']"));
		//otherProjects = driver.findElement(By.xpath("//*[@id='p-wikibase-otherprojects-label']"));
		languages = driver.findElement(By.xpath("//*[@id='p-lang-label']"));
		
		assertNotNull(print);
		//assertNotNull(otherProjects);
		assertNotNull(languages);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

}
