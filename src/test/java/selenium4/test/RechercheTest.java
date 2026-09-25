package selenium4.test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.time.Duration;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.*;

import selenium4.common.DriverFactory;
import selenium4.common.DriverFactory.BrowserType;
import selenium4.common.Util;
import selenium4.po.PageAccueilMobile;

import selenium4.po.PageAccueil;
import selenium4.po.PageAccueilDesktop;
import selenium4.po.PageRecherche;


import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;


public class RechercheTest {
	private static WebDriver driver;
	private static String baseUrl;
	private PageAccueil accueil;
	private static Logger logger;
	private static String plateforme;
	private static String type;
	
	@BeforeAll
	public static void initializeSuite()
	{
		try {
			logger=Util.getLogger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// extractions infos pour lancer les tests

	        InputStream inputStream;
			try {
				inputStream = new FileInputStream("config.properties");
		        Properties properties = new Properties();
		        properties.load(inputStream);
		        System.out.println(properties.getProperty("plateforme"));
				plateforme=properties.getProperty("plateforme");
				type=properties.getProperty("type");
		        System.out.println(type);
			} catch (IOException e) {

				e.printStackTrace();
			}
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		// creation du driver
		logger.info("création du driver");
		driver=DriverFactory.makeBrowser(plateforme);
		baseUrl = "http://www.qualifiez.fr/monPrestashop2/prestashop/index.php";
		// configuration du driver
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(baseUrl);
		logger.info("affichage" + type);	
		if ("mobile".equals(type))
		{
			logger.info("création page mobile");
			accueil =  PageFactory.initElements(driver, PageAccueilMobile.class);
		}
		else
		{
			logger.info("création page desktop");
			accueil =  PageFactory.initElements(driver, PageAccueilDesktop.class);
			
		}
	};

	// Create a test using SignInPage and PageProject to check successful login  
	@Test
	public void testRecherche() throws Exception {
		PageRecherche res = accueil.rechercher("MUG");
		assertEquals(res.getResultat(),res.nbElementTrouve());
	}
	@Test
	public void testRechercheAvecTri() throws Exception {
		
		
		accueil.allerPageCompte();
		PageRecherche res = accueil.rechercher("MUG");
		res.trier("Prix, croissant");		
		assertEquals(res.getResultat(),res.nbElementTrouve());
		assertTrue(res.verifierTri());
		System.out.print(res.recupererPremierPrix());
	}
	@Test
	public void testRechercheInfructueuse() throws Exception {
		PageRecherche res = accueil.rechercher("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		assertEquals("Aucun produit ne correspond à votre recherche",res.PasDeResultat());
		assertEquals("xxxxxxxxxxxxxxxxxxxxxxxxxxxx",res.valeurRecherchee());
	}
	@Test
	public void testRechercheInfructueuseVide() throws Exception {
		PageRecherche res = accueil.rechercher("");
		assertEquals("Aucun produit ne correspond à votre recherche",res.PasDeResultat());
	}
	@AfterEach
	public  void tearDown() throws Exception {
		driver.quit();
	}




}



