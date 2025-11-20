package selenium4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium4.pageRecherche;


public class pageAccueil {
	
	private WebDriver driverAccueil;

	@FindBy(how = How.XPATH, using = "//*[@id='search_widget']/form/input[2]")
    private WebElement champRecherche;
	private WebDriverWait wait;
	public pageAccueil(WebDriver driver){
		driverAccueil = driver;
		wait = new WebDriverWait(driverAccueil, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("monPrestashop"));
	}
//	@Step("Rechercher les MUG")
	public pageRecherche rechercher(String mot)
	{
		   wait = new WebDriverWait(driverAccueil, Duration.ofSeconds(10));
		   wait.until(ExpectedConditions.visibilityOf(champRecherche));

		   champRecherche.click();
		   champRecherche.clear();
		   champRecherche.sendKeys(mot);
		   champRecherche.sendKeys(Keys.ENTER);
		   
		   return PageFactory.initElements(driverAccueil, pageRecherche.class);
	}


}

	


