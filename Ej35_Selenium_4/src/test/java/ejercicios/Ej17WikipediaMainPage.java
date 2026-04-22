package ejercicios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ej17WikipediaMainPage {
	public static String url = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
	private WebDriver driver;
	
	@FindBy(id="searchInput")
	private WebElement searchBox;

	@FindBy(id="searchButton")
	private WebElement searchButton;

	@FindBy(id="pt-createaccount")
	private WebElement createAccountLink;
	
	@FindBy(id="pt-anoncontribs")
	private WebElement contributionsLink;
	
	public Ej17WikipediaMainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void setSearchText(String text) {
		this.searchBox.clear();
		this.searchBox.sendKeys(text);
	}
	
	public void search() {
		this.searchButton.click();
	}
	
	public void goToContributions() {
		this.contributionsLink.click();
	}
	
	public void goToCreateAccount() {
		this.createAccountLink.click();
	}
	
	public String getPlaceholderText() {
		return searchBox.getAttribute("placeholder");
	}
}
