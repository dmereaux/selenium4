package selenium4.po;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import selenium4.common.Util;

public class PageAccueilDesktop extends PageAccueil {


	private Logger logger;

	public PageAccueilDesktop(WebDriver driver) {
		super(driver);
		logger=Util.getLogger();

	}
	
	public PageRechercheDesktop rechercher(String mot)
	{
		   logger.info("lancer une recherche");
		   chercher(mot);
		   
		   return PageFactory.initElements(driverAccueil, PageRechercheDesktop.class);
	}

}
