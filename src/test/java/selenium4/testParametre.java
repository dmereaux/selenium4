package selenium4;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//@RunWith(Parameterized.class)
public class testParametre{
	
  private WebDriver driver;
 

  @BeforeEach
  public void setUp() throws Exception {
//	System.setProperty("webdriver.gecko.driver", "geckodriver");
    driver = new FirefoxDriver();
    driver.get("http://prestashop.qualifiez.fr/");
  } 
  @ParameterizedTest
  @CsvFileSource(resources = "donnees.csv", numLinesToSkip = 1)
  public void testPrestashop(String input, String expected) throws Exception {
	    driver.findElement(By.name("s")).sendKeys(input);
	    driver.findElement(By.cssSelector("#search_widget > form > button > i")).click();
	    assertEquals(driver.findElement(By.xpath("//*[@id='js-product-list-top']/div[1]/p")).getText(),expected);	    
  } 
  @AfterEach
  public void tearDown() throws Exception {
    driver.quit();
  }
}
