package selenium4;
import java.util.regex.Pattern;
import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.*;

import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import selenium4.common.DriverFactory;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class prestashopTest {
	private static String baseUrl;
	private WebDriver driver;  
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private HashMap<String, Object> vars;
	//  private Util monUtil;
	final static Logger logger = Logger.getLogger(selenium4.prestashopTest.class);
	  JavascriptExecutor js;




	@BeforeEach
	public void setUp() throws Exception {
//		FirefoxOptions optionF = new FirefoxOptions();
//		optionF.addArguments("--headless");
//		driver= new FirefoxDriver(optionF);	
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless=new");
		options.addArguments("--disable-search-engine-choice-screen");
		driver= new ChromeDriver(options);
//		driver=DriverFactory.makeBrowser(DriverFactory.BrowserType.Chrome);
//		driver= new FirefoxDriver();
//		driver = new ChromeDriver(new ChromeOptions().addArguments("--disable-search-engine-choice-screen"));
		baseUrl = "http://www.qualifiez.fr/monPrestashop2/prestashop/index.php";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(baseUrl);
		

        // commandes pour lancer un profile firefox spécifique
//		FirefoxOptions options = new FirefoxOptions();
//		FirefoxProfile monProfil = new FirefoxProfile(new File("/Users/dominiquemereaux/Library/Application Support/Firefox/Profiles/rfqfjl41.monProfil"));
//		options.setProfile(monProfil);
		//	driver = new FirefoxDriver(options);
//		DesiredCapabilities capabilities = new DesiredCapabilities().edge();
//		driver = new RemoteWebDriver(new URL("http://192.168.1.34:4444/wd/hub"),capabilities); ou
//		driver = new RemoteWebDriver(new URL("http://192.168.1.34:4444"),capabilities);
		

	}
	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "donnees.csv", numLinesToSkip = 1)
	//test variabillisé avec Junit5
	public void testPrestashopParam(String input, String expected) throws Exception {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search_widget']/form/input[2]")));
		
		driver.findElement(By.xpath("//*[@id='search_widget']/form/input[2]")).sendKeys(input);
		driver.findElement(By.xpath("//*[@id='search_widget']/form/input[2]")).sendKeys(Keys.ENTER);
		assertEquals(driver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]/p")).getText(),expected);	    
	} 
	// Utilisation des fonctions de base + attente explicite
	@Test 
	public void chercherLeMugAttenteCliquable() throws InterruptedException, MalformedURLException {
		logger.debug("creation de l'objet de type  WebDriverWait");
		// creation de l'objet de type  WebDriverWait
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// utilisation de l'objet pour créer une attente par polling sur le champ de recherche
		logger.info("Etape saisie recherche");
		WebElement saisie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search_widget']/form/input[2]")));
		saisie.clear();
		saisie.sendKeys("MUG");
		saisie.sendKeys(Keys.ENTER);
		// utilisation de l'objet pour créer une attente par polling sur le résultat
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#js-product-list-top > div.col-lg-5.hidden-sm-down.total-products > p")));
		assertEquals ( "Rechercher",driver.getTitle());
		assertEquals("Il y a 5 produits.",driver.findElement(By.cssSelector("#js-product-list-top > div.col-lg-5.hidden-sm-down.total-products > p")).getText());
		List<WebElement> mesArticlesLiens = driver.findElements(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[2]/h2/a"));
		assertEquals(mesArticlesLiens.size(),5);
		for ( WebElement elt : mesArticlesLiens) {
		
			assertTrue(elt.getText().contains("Mug"),"texte affiché: " + elt.getText());
		}
	}

//	@Test
	public void testChercherOptions()
	{
		driver.get("https://www.qualifiez.fr/monPrestashop2/prestashop/index.php?id_product=1&id_product_attribute=1&rewrite=hummingbird-printed-t-shirt&controller=product#/1-taille-s/8-couleur-blanc");
		List<WebElement> list = driver.findElements(By.tagName("option"));
		for (WebElement option : list)
		{
			System.out.println(option.getAttribute("title"));
		}
	}

//	@Test 
	public void chercherLeMugJavascript() throws InterruptedException, MalformedURLException {
//		driver.quit();
//		ChromeOptions option = new ChromeOptions();
		// commandes pour lancer un chrome particulier
		//	 	option.addArguments("--profile-directory='Guest Profile'");
		//	 	option.addArguments("user-data-dir=/Users/dominiquemereaux/Library/Application Support/Google/Chrome"); 

//		option.addArguments("--headless");

//		driver= new ChromeDriver(option);
//		driver.get(PrestaShopURL);
		// creation du JavascriptExecutor
		JavascriptExecutor js =(JavascriptExecutor)driver;
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saisie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("s")));
		saisie.clear();
		//saisie.sendKeys("MUG");
		// utilisation du JavascriptExecutor, 1er commande recherche du champ de saisie, 2ème commande mise à jour du champ
		js.executeScript("const input = document.querySelector(`input[name=\"s\"]`);input.value=\"MUG\"");
		saisie.sendKeys(Keys.ENTER);
		//assertEquals ( "Rechercher",driver.getTitle());
		assertEquals("Il y a 5 produits.",driver.findElement(By.cssSelector("#js-product-list-top > div.col-lg-5.hidden-sm-down.total-products > p")).getText());
		List<WebElement> mesArticlesLiens = driver.findElements(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[2]/h2/a"));
		assertEquals(mesArticlesLiens.size(),5,"pb");
		for ( WebElement elt : mesArticlesLiens) {
		
			assertTrue(elt.getText().contains("Mug"),"message" + elt.getText());
		}
	}
	// checker la Box et vérifier qu'elle est checkée
	@Test 
	public void checkerLaBOX() throws InterruptedException {
		driver.get("http://www.qualifiez.fr/monPrestashop2/prestashop/index.php?id_product=1&id_product_attribute=3&rewrite=hummingbird-printed-t-shirt&controller=product#/2-taille-m/8-couleur-blanc");
		assertTrue(driver.findElement(By.xpath("//*[@id=\"group_2\"]/li[1]/label/input")).isSelected());
		driver.findElement(By.xpath("//*[@id=\"group_2\"]/li[2]/label/input")).click();
		assertFalse(driver.findElement(By.xpath("//*[@id=\"group_2\"]/li[1]/label/input")).isSelected());

	}

	//tester la frame apres avoir accepter la modale
//	@Test 
	public void testLaFrame() throws InterruptedException, IOException {

		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_option");
		File monFichier = driver.findElement(By.id("accept-choices")).getScreenshotAs(OutputType.FILE);
		File toto = new File("toto.png");
		//fenêtre modale type RGPD, acceptée
		driver.findElement(By.id("accept-choices")).click();
		// switch sur la frame 
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.id("cars"));


	}
	// exemple mobiles
	@Test 
	
	public void chercherMobile() throws MalformedURLException {
		driver.quit();
		// choix du téléphone, navigateur et lien vers de driver consistant avec la version sur le téléphone mobile
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "android"); 
		capabilities.setCapability("browserName", "chrome"); 
		capabilities.setCapability("appium:automationName", "UIAutomator2");
		capabilities.setCapability("appium:platformVersion", "16");
		capabilities.setCapability("appium:chromedriverExecutable", "/Users/dominiquemereaux/code/appium/chromedriver");
		

		// Création d'un remote driver pour se connecter au serveur APPIUM
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/"), capabilities);
		
		driver.get("http://www.qualifiez.fr/monPrestashop2/prestashop/index.php");
		driver.findElement(By.name("s")).sendKeys("MUG");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
		assertEquals(driver.getTitle(),"Rechercher");	   
		driver.quit();
	}

	// compter les fenêtres
//	@Test
	public void compterLesFenetres() {

		driver.get("http://www.qualifiez.fr/examples/Selenium/project-list.php");
		driver.findElement(By.id("btnNewWindow")).click();
		assertEquals ("Projets",driver.getTitle());
		// récupération de la liste des handles
		Set<String> set = driver.getWindowHandles();
		assertEquals(2,set.size());
		// switch sur la fenêtre ouverte avec le nom de la fenêtre (dans le code javascript
		driver.switchTo().window("toto");
		assertEquals ("My Window",driver.getTitle());
		System.out.print("Handle: " + driver.getWindowHandle());


	}
	// Utiliser la classe action
//	@Test
	public void testActions() throws Exception {
		driver.manage().window().maximize();
		// création de l'objet Actions
		Actions builder = new Actions(driver);
		//ajout des différentes actions --> doit se terminer par build()
		Action mouseOverClothes = builder.moveToElement(driver.findElement(By.cssSelector("#category-3 > a"))).build();
		// exécution de l'action
		mouseOverClothes.perform();
		assertTrue(driver.findElement(By.xpath("//*[@id='category-4']/a")).isDisplayed());

	} 


//	@Test
	// Traitement des alertes
	public void testPrestashopALerte() throws Exception {
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		driver.findElement(By.id("accept-choices")).click();
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//*[@onclick='myFunction()']")).click();
     
		Wait<WebDriver> MonAttente = new WebDriverWait(driver, Duration.ofSeconds(2));
        MonAttente.until(ExpectedConditions.alertIsPresent());
        // switch sur l'alerte et récupération d'un objet de type Alert
		Alert alert = driver.switchTo().alert();
		// Utilisation de cet objet pour récupérer le texte contenu dans l'alerte puis pour l'accepter
		assertEquals("Hello! I am an alert box!", alert.getText());
		alert.accept();

	}
//	@Test
	// Traitement des alertes
	public void testPrestashopALerteAccepteA() throws Exception {
		driver.quit();
		// utilisation des options pour fermer automatiquement les alertes
		ChromeOptions option = new ChromeOptions();
		option.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
		driver= new ChromeDriver(option);
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
		driver.findElement(By.id("accept-choices")).click();
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//*[@onclick='myFunction()']")).click();
		Thread.sleep(2);
		// on peut cliquer sans fermer l'alerte
		driver.findElement(By.xpath("//*[@onclick='myFunction()']")).click();
		driver.quit();

	}



//	@Test
	//exemple avec une application mobile
	public void testPrestashopAPK() throws Exception {
		Date jour = new Date();

		// Definition du mobile sur lequel on va exécuter les tests
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("sessionName", "Automation test session on android web");
		capabilities.setCapability("sessionDescription", "This is example android web testing"); 
		capabilities.setCapability("deviceOrientation", "portrait"); 
		capabilities.setCapability("platformName", "android"); 
		capabilities.setCapability("app", "/Users/dominiquemereaux/AndroidStudioProjects/Dice/app/build/outputs/apk/app-debug.apk"); 
		capabilities.setCapability("deviceName", "M10");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("appPackage", "com.example.dominiquemereaux.dice");	  
		// Création d'un remote driver pour se connecter au serveur APPIUM
//		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		URL remoteUrl = new URL("http://127.0.0.1:4723//wd/hub");
//		driver = new AndroidDriver(remoteUrl, capabilities);





	}
	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {


				alert.accept();

			} else {

				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
