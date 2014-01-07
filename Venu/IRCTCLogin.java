package Venu;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IRCTCLogin {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	static generalMethods Met=new generalMethods();
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		WebDriver driver;
		String Username="Bala8290";
		String Password="Bala@8290";
		String boardingStation="MS";
		String ToStation="MDU";
		String BookType="General";
		String Date="February_15";
		String TrainName="	PANDIAN EXP";
		String TrainNos="12631";
		String Class="2A";
		driver=Met.LaunchBrowser("chrome");
		// Launch IRCTC 
		Met.goToURL("https://www.irctc.co.in/");
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		Met.implicitWaitDriver(30);
		//Login
		Met.SendValueToObj(Met.findElementByName("userName"), Username, "Sending Username");
		Met.SendValueToObj(Met.findElementByName("password"), Password, "Sending password");
		Met.clickObj(Met.findElementById("button"), "Clicking on login");
		Met.implicitWaitDriver(30);
		// Entering To and fo station
		Met.SendValueToObj(Met.findElementByName("stationFrom"),boardingStation , "Boarding Station");
		Met.SendValueToObj(Met.findElementByName("stationTo"),ToStation , "Boarding Station");
		Met.ListboxByVisibleText(Met.findElementByName("quota"),BookType,"Selecting type");
		Met.clickObj(Met.findElementById("JDatee"), "Click on date");
		WebDriverWait Wait=new WebDriverWait(driver,30);
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CalendarControl")));
		SelectDate(Date);
		Met.clickObj(Met.findElementByName("Submit"),"Click on Find Trains");
		Wait.until(ExpectedConditions.presenceOfElementLocated(By.id("avlAndFareForm:trainbtwnstns:th")));
		List<WebElement> Headers= driver.findElement(By.id("avlAndFareForm:trainbtwnstns:th")).findElements(By.tagName("th")); 
		List<WebElement> Rows=driver.findElement(By.id("avlAndFareForm:trainbtwnstns:tb")).findElements(By.tagName("tr"));
		List<WebElement> Cells;
		Hashtable<String,Integer> Header1 = new Hashtable<String,Integer>();
		for (int i=0;i<Headers.size();i++)
		{
			Header1.put(Headers.get(i).getText(), i);
			//System.out.println(i+"  "+Headers.get(i).getText());
		}
		
		
		
		
		for(int i=0;i<Rows.size();i++)
		{
			Cells=Rows.get(i).findElements(By.tagName("td"));
			
				if((Cells.get(Header1.get("Train No.")).getText().equals(TrainNos))||(Cells.get(Header1.get("Train Name")).getText().equals(TrainName)))
				{
					if(Cells.get(Header1.get("Class")).getText().contains(Class))
					{
						Cells.get(Header1.get("Class")).findElement(By.linkText(Class)).click();
						break;
					}
				}
			
		}
	}

	public static void SelectDate(String Date)
	{
		String a[]=Date.split("_");
		System.out.println(a[0]);
		System.out.println(a[1]);
		Boolean Status=false;
		WebElement Element=Met.findElementById("CalendarControl");
		List<WebElement> Cell = Element.findElements(By.tagName("td"));
		for(int i=0;i<Cell.size();i++)
		{ 		
				String Tex=Cell.get(i).getText();
				if(Tex.contains(a[0]))
					Status=true;
				if(Status)
				{
					if(Cell.get(i).getText().equals(a[1]))
						{ Cell.get(i).findElement(By.linkText(a[1])).click();
							break;
						}
				}
				
			
		}
	}
}
