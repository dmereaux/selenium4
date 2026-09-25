package selenium4.common;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	final static Logger logger = Logger.getLogger(selenium4.prestashopTest.class);
	public static Logger getLogger()
	{
		 return logger;
	}
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
