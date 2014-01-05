package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class testCase5 {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws TransformerException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws WriteException, IOException, ParserConfigurationException, BiffException, TransformerException {
		AppSpecific App = new AppSpecific("DeleteCampaignView","chrome");
		App.ReaddataforLogin(App,false);
		App.DeleteCampaignView("DeathChest8290");
		App.logoutApp();
		App.finishingTouch();

	}

}
