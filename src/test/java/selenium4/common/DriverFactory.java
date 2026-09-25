package selenium4.common;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	public enum BrowserType{
		Firefox,
		Chrome,
		Edge,
		Safari,
		Android16
	};
	
	public static WebDriver  makeBrowser(String type) throws MalformedURLException
	{
		switch (type) {
		case "Firefox": return new FirefoxDriver();
		case "Chrome": return new ChromeDriver(new ChromeOptions().addArguments("--disable-search-engine-choice-screen"));
		case "Edge": return new EdgeDriver(new EdgeOptions().addArguments("--disable-search-engine-choice-screen"));
		case "Safari": return new SafariDriver();
		case "Android16":
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "android"); 
			capabilities.setCapability("browserName", "chrome"); 
			capabilities.setCapability("appium:automationName", "UIAutomator2");
			capabilities.setCapability("appium:platformVersion", "16");
			capabilities.setCapability("appium:chromedriverExecutable", "/Users/dominiquemereaux/code/appium/chromedriver");
			return new RemoteWebDriver(new URL("http://127.0.0.1:4723/"), capabilities);
		}
		}
		return new ChromeDriver(new ChromeOptions().addArguments("--disable-search-engine-choice-screen"));


	}



}