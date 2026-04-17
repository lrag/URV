package selenium;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

//junit-platform.properties en src/test/resources:
//
//junit.jupiter.execution.parallel.enabled = true
//junit.jupiter.execution.parallel.mode.default = same-thread
//
@Execution(ExecutionMode.CONCURRENT)
public class _11_SeleniumGridParalelos {
	
	private WebDriver getWebDriver(String browser) {
		
		try {
			WebDriver driver = null;		
			String nodeUrl = "http://localhost:4444";
			
			switch (browser) {
				case "Firefox" :
					FirefoxOptions fOptions = new FirefoxOptions();
					fOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
					//ahora utilizamos la clase RemoteWebDriver
					driver = new RemoteWebDriver(new URL(nodeUrl), fOptions);
					break;
				case "Edge" :
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(nodeUrl), edgeOptions);
					break;
				case "Chrome" :
					ChromeOptions cOptions = new ChromeOptions();
					cOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(nodeUrl), cOptions);
					break;
			}
			
			return driver;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Estos van a ser los parametros que van a entrar por el 
	//constructor. Como tiene 4 filas, se ejecutara 4 veces y cada
	//vez entrará por el constructor todas las columnas
	public static Stream<Arguments> listaNavegadores() {
	    return Stream.of(
	        Arguments.arguments("Firefox"),
	        Arguments.arguments("Chrome"),
	        Arguments.arguments("Edge"));
	}	

	@ParameterizedTest
	@MethodSource("listaNavegadores")	
	public void Test1(String navegador) {
		
		WebDriver driver = getWebDriver(navegador);
		System.out.println("Test1: "+driver+" - "+Thread.currentThread().getName());
		driver.get("http://www.google.es");
		assertEquals(driver.getTitle(), "Google");
		System.out.println("Test1 (fin): "+driver+" - "+Thread.currentThread().getName());
		driver.quit();
	
	}

	@ParameterizedTest
	@MethodSource("listaNavegadores")	
	public void Test2(String navegador) {
		
		WebDriver driver = getWebDriver(navegador);
		System.out.println("Test2: "+driver+" - "+Thread.currentThread().getName());
		driver.get(Constantes.URL);
		assertEquals(driver.getTitle(), "Index");
		System.out.println("Test2 (fin): "+driver+" - "+Thread.currentThread().getName());
		driver.quit();
	
	}	

	@ParameterizedTest
	@MethodSource("listaNavegadores")	
	public void Test3(String navegador) {
		
		WebDriver driver = getWebDriver(navegador);
		System.out.println("Test3: "+driver+" - "+Thread.currentThread().getName());
		driver.get(Constantes.URL);
		assertEquals(driver.getTitle(), "Index");
		System.out.println("Test3 (fin): "+driver+" - "+Thread.currentThread().getName());
		driver.quit();
		
	}	
	
}



