package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class testcase7 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException, ParserConfigurationException, BiffException, TransformerException {
		AppSpecific App = new AppSpecific("EditCampaign","firefox");
		App.ReaddataforLogin(App,false);
		App.editCampaign("mybabyLove","RockStar");
		App.logoutApp();
		App.finishingTouch();


	}

}
