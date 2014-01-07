package project_salesforce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class IRCTCLogin {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		WebDriver driver;
		generalMethods Met=new generalMethods();
		Met.LaunchBrowser("Chrome");
		Met.goToURL("https://www.irctc.co.in/");
		Met.implicitWaitDriver(30);
		Met.SendValueToObj(Met.findElementByName("userName"), "Bala8290", "Sending Username");
		Met.SendValueToObj(Met.findElementByName("password"), "Bala@8290", "Sending password");
		Met.clickObj(Met.findElementById("button"), "Clicking on login");
		Met.implicitWaitDriver(30);

	}

}
