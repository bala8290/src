package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class TC10ForgotPassword {
	public TC10ForgotPassword(String testcasename,String BrowserName) throws WriteException, IOException, ParserConfigurationException, TransformerException, BiffException
	{
		AppSpecific App = new AppSpecific(testcasename,BrowserName);
		App.ReaddataforLogin(App,true);
		App.logoutApp();
		App.finishingTouch();
	}
}
