package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

//Arrancamos el servidor:
//java -jar selenium-server-<version>.jar hub
//Damos de alta un nodo 
//java -jar selenium-server-<version>.jar node --detect-drivers true
//http://localhost:4444/ui/

public class _11_SeleniumGrid {
	
	private static WebDriver driver;
	private static String nodeUrl;
	
	@BeforeAll
	public static void setUp() throws MalformedURLException {
		
		nodeUrl = "http://localhost:4444";
		
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver/chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		
		//le decimos que es una instancia de windows
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		//ahora utilizamos la clase RemoteWebDriver
		driver = new RemoteWebDriver(new URL(nodeUrl), options);
	}
	
	@Test
	public void searchReact() {
		driver.get("http://www.google.com");
		assertEquals(driver.getTitle(), "Google");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
