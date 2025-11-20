package selenium4;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class pageRecherche {

	private WebDriver driverRecherche;
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[1]/p")
    private WebElement resRecherche;
	@FindBy(how = How.XPATH, using = "//*[@id=\"product-search-no-matches\"]")
	private WebElement rienTrouve;
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[2]/div/div/button")
	private WebElement listeTri;	
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[2]/div/div/div/a")
	private List<WebElement> items;
	@FindBy(how = How.XPATH, using = "//*[@id=\"search_widget\"]/form/input[2]")
	private WebElement valeur;
	@FindBy(how = How.XPATH, using = "//article//*[@class='price']")
	private List<WebElement> prices;
	@FindBy(how=How.XPATH, using ="//*[@id=\"js-product-list\"]/div[1]/div[1]/article/div/div[2]/div[1]/span")
	private WebElement premierPrix;
	@FindBy(how=How.XPATH, using ="//*[@id=\"js-product-list-top\"]/div[2]/div/div/button")
	private WebElement dropdown;

	WebDriverWait wait;


   public pageRecherche(WebDriver driverAccueil) throws InterruptedException {
		driverRecherche = driverAccueil;
		wait = new WebDriverWait(driverRecherche, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Rechercher"));
	}
   public String nbElementTrouve()
   {
	   return resRecherche.getText();
   }
   public String PasDeResultat()
   
   {
	   return rienTrouve.getText();
   }
   public pageRecherche trier(String item) throws InterruptedException
   {
	   listeTri.click();
	   for (WebElement elt :items )
	   {
		   if (elt.getText().contains(item))
			   elt.click();
	   }
       wait.until(ExpectedConditions.textToBePresentInElement(dropdown, item));
	   return this;
   }
   public String valeurRecherchee()
   {
	   return valeur.getAttribute("value");
   }
   public boolean verifierTri()
   {
 	  double initialVal=0.0;
 	  System.out.println(prices.size());
 	   for (WebElement elt : prices )
 	   {
 		   String prixS = elt.getText();
 		   System.out.println(prixS);
 		   String prix2 = prixS.substring(0, prixS.length()-1);
 		   prix2 = prix2.replace(',', '.');
 		   System.out.println(prix2);
 		   float prix = Float.parseFloat(prix2);
 		   if (prix < initialVal)
 			   return false;
 		   initialVal=prix;
 	   }

 	  return true;
   }

   public boolean verifierPertinence()
   {

	for ( WebElement elt : items) {
		if(!elt.getText().contains("Mug"))
			return false;
	}
	return true;
   }

public String recupererPremierPrix() {

    return premierPrix.getText();
}
}
