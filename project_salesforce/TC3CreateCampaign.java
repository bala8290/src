package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class TC3CreateCampaign {
public TC3CreateCampaign(String testcasename,String BrowserName) throws WriteException, IOException, ParserConfigurationException, TransformerException, BiffException
{
	AppSpecific App = new AppSpecific(testcasename,BrowserName);
	App.ReaddataforLogin(App,false);
	App.EditCampaignView("DeathChest829");
	App.logoutApp();
	App.finishingTouch();
}
}
