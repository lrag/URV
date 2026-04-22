package selenium;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

//Para capturar enventos en Selenium
public class _06_WebDriverListener implements WebDriverListener  {
	
	private WebDriver webDriver;
	
	public _06_WebDriverListener(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		System.out.println("ANTES DE BUSCAR: "+locator);
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
		System.out.print("Se ha buscado por el id:"+locator);
		if(result == null) {
			System.out.println(". NO EXISTE");
		} else {
			System.out.println(". EXISTE");
		}		
	}

	@Override
	public void beforeGet(WebDriver driver, String url) {
		// TODO Auto-generated method stub
		System.out.println("NAVEGANDO A: "+url);
	}
	
	@Override
	public void afterGet(WebDriver driver, String url) {
		System.out.println("ESTAMOS EN: "+url);
	}

	@Override
	public void beforeClick( WebElement element) {
		System.out.println("ANTES DE HACER CLICK: "+element);
	}
	
	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		StringBuilder sb = new StringBuilder();
		for(int a=0; a<keysToSend.length; a++) {
			sb.append(keysToSend[a]);
		}
		System.out.println("ANTES DE ESCRIBIR: "+sb.toString());
	}

	@Override
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		System.out.println("ZASCA");
		File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/onError.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}	
	}
	
}


