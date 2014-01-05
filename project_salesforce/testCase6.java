package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

class testCase6 {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws TransformerException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws WriteException, IOException, ParserConfigurationException, BiffException, TransformerException {
		AppSpecific App = new AppSpecific("CreateCampaign","firefox");
		App.ReaddataforLogin(App,false);
		App.createCampaign("mybabyLove");
		App.logoutApp();
		App.finishingTouch();

	}

}
