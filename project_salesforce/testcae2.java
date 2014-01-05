package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class testcae2 {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws TransformerException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws WriteException, IOException, ParserConfigurationException, TransformerException, BiffException {
		AppSpecific App = new AppSpecific("Login","firefox");
		App.ReaddataforLogin(App,false);
		App.logoutApp();
		App.finishingTouch();
		
	}

}
