package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class testcase3 {

	/**
	 * @param args
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws BiffException 
	 * @throws RowsExceededException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException, TransformerException, ParserConfigurationException {
		AppSpecific App = new AppSpecific("CreateCampaignView","firefox");
		App.ReaddataforLogin(App,false);
		App.EditCampaignView("DeathChest829");
		App.logoutApp();
		App.finishingTouch();
	}

}
