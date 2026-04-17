package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class _00_Selenium {

	public static void main(String[] args) {

		//Navegadores soportados:
		//Firefox
		//Internet Explorer (11, se requiere configuración extra)
		//Safari
		//Opera
		//Chrome
		//Edge		

		System.setProperty("webdriver.gecko.driver",
				           "src/test/resources/drivers/geckodriver/geckodriver.exe");
		//El objeto driver representa al navegador
		WebDriver driver = new FirefoxDriver();
		
		/*
		System.setProperty("webdriver.chrome.driver", 
				           "src/test/resources/drivers/chromedriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		*/	
		
		driver.get("http://www.google.com");
		
		//Para que de tiempo a quitar el popup con la aceptación de términos
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element = driver.findElement(By.name("q"));

		element.sendKeys("Spring security 4");

		element.submit();


		//driver.quit();
	}

}
