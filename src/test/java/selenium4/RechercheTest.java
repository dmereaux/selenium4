package selenium4;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.support.PageFactory;


public class RechercheTest {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private pageAccueil accueil;
	
	@BeforeEach
	public void setUp() throws Exception {
		// creation du driver
		driver = new ChromeDriver(new ChromeOptions().addArguments("--disable-search-engine-choice-screen"));
		baseUrl = "http://www.qualifiez.fr/monPrestashop2/prestashop/index.php";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(baseUrl);
		accueil =  PageFactory.initElements(driver, pageAccueil.class);
	};

	// Create a test using SignInPage and PageProject to check successful login  
	@Test
	public void testRecherche() throws Exception {
		
		
		
		pageRecherche res = accueil.rechercher("MUG");
		res.trier("Prix, croissant");		
		assertEquals("Il y a 5 produits.",res.nbElementTrouve());
		assertTrue(res.verifierTri());
		System.out.print(res.recupererPremierPrix());
	}
	@Test
	public void testRechercheInfructueuse() throws Exception {
		pageRecherche res = accueil.rechercher("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		assertEquals("Aucun produit ne correspond à votre recherche",res.PasDeResultat());
		assertEquals("xxxxxxxxxxxxxxxxxxxxxxxxxxxx",res.valeurRecherchee());
	}
	@Test
	public void testRechercheInfructueuseVide() throws Exception {
		pageRecherche res = accueil.rechercher("");
		assertEquals("Aucun produit ne correspond à votre recherche",res.PasDeResultat());
	}
	@AfterEach
	public  void tearDown() throws Exception {
		driver.quit();
	}




}



