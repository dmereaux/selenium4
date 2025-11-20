package selenium4;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
	void checkLaBox(WebElement elt)
	{
	}
	void deCheckLaBox(WebElement elt)
	{
	}
	public void monClick(WebDriver driver, String chemin) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(chemin)));
	    button.click();
		
	}


}
