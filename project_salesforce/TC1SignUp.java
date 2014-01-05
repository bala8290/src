package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.write.WriteException;

public class TC1SignUp {

	public TC1SignUp(String testcaseName,String BrowserName) throws WriteException, IOException, ParserConfigurationException, TransformerException, InterruptedException
	{
		AppSpecific App = new AppSpecific(testcaseName,BrowserName);
		App.signup();
		//Thread.sleep(100000);
		App.finishingTouch();
	}
	
}
