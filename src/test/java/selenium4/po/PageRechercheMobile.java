package selenium4.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageRechercheMobile extends PageRecherche{
	public final String resultat = "Affichage 1-5 de 5 article(s)";
	@FindBy(how = How.XPATH, using = "//*[@id=\"js-product-list-top\"]/div[3]")
    private WebElement resRecherche;

	public PageRechercheMobile(WebDriver driverAccueil) throws InterruptedException {
		super(driverAccueil);
	}
	   public String nbElementTrouve()
	   {
		   return resRecherche.getText();
	   }
		@Override
		public String getResultat() {

			return resultat;
		}

}
