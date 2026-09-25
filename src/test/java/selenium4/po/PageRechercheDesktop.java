package selenium4.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageRechercheDesktop  extends PageRecherche{
	// Résultats attendus 
	public final String resultat = "Il y a 5 produits.";
	//Locators
	@FindBy(how = How.XPATH, using = "//*[@id='js-product-list-top']/div[1]/p")
    private WebElement resRecherche;
	// constructeur
	public PageRechercheDesktop(WebDriver driver) throws InterruptedException {
		super(driver);
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
