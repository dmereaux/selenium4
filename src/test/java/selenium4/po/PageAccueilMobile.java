package selenium4.po;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import selenium4.common.Util;

public class PageAccueilMobile extends PageAccueil {
	
	private Logger logger; 

	public PageAccueilMobile(WebDriver driver) {
		super(driver);
		logger = Util.getLogger();
	}

	@Override
	public PageRecherche rechercher(String mot) {
		logger.info("lancer recherche sur mobile");
		chercher(mot);
		   return PageFactory.initElements(driverAccueil, PageRechercheMobile.class);
	}

}
