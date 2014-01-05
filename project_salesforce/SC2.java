package project_salesforce;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SC2 {

	/**
	 * @param args
	 */
	
	static List<WebElement> table;
	static List<WebElement> Cell;
	static WebDriver driver;
	static WebDriverWait wait;
	public static void main(String[] args) {
		Select Listbox;
		driver = new FirefoxDriver();
		int campaignnamemorethn4l=0,typeConfOrWebinar=0,StatusNotComp=0,MaxActualcost=0;
		List<WebElement> table,Cell;
		 try {
			 
			 // launch and login
			 
				driver.get("http://developer.force.com/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.partialLinkText("DE LOGIN")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.id("username")).sendKeys("testmylife829227085@gmail.com");
				driver.findElement(By.id("password")).sendKeys("iloveCobra8290");
				driver.findElement(By.id("Login")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				
				//click on Home
				driver.findElement(By.linkText("Home")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mCalendar")));
				//verify calender present
				if(driver.findElement(By.className("mCalendar")).isDisplayed())
					System.out.println("Calender present");
				else
					System.out.println("Calender not present");
				// select date exactly 25 days from now
				
				clickCalender(25);
				
				
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		 finally
		 {
			 //closing browser
			// driver.close();
		 }
	}
	
	
	public static void clickCalender(int maxcount) throws InterruptedException
	{
		boolean start=false,Isbreak=false;
		int count=0;
		int nos = 0;
		String tex="0";
		WebElement Element = driver.findElement(By.className("mCalendar"));
		table=Element.findElements(By.tagName("tr"));
		for(int i=0;i<table.size();i++)
		{
			if(Isbreak)
				break;
			Cell=table.get(i).findElements(By.tagName("td"));
			for(int j=0;j<Cell.size();j++)
			{
				
				if(Cell.get(j).getAttribute("class").contains("calToday"))
					start=true;
				if(start)
					{
						count+=1;
						System.out.println(count);
						if(count==maxcount)
							{
							    Isbreak=true;
							    Cell.get(j).findElement(By.linkText(Cell.get(j).getText())).click();
								break;
							}
							
					}
				tex=Cell.get(j).getText();
						}
				}
		
				nos=Integer.parseInt(tex);
				System.out.println(+nos);
		
		
		while (count<maxcount)
		{
			driver.findElement(By.className("nextCalArrow")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mCalendar")));
			Element = driver.findElement(By.className("mCalendar"));
			table=Element.findElements(By.className("days"));
			int i=0;
			if(nos<=7)
				i=1;
			else
				i=0;
			for(;i<table.size();i++)
			{
				if(Isbreak)
					break;
				Cell=table.get(i).findElements(By.tagName("td"));
				for(int j=0;j<Cell.size();j++)
				{
					if(start)
					{
						count+=1;
						System.out.println(count);
						if(count==maxcount)
							{
								Isbreak=true;
								Cell.get(j).findElement(By.linkText(Cell.get(j).getText())).click();
								break;
							}
					}
					tex=Cell.get(j).getText();
					if(tex.length()==1)
						nos=Integer.parseInt(tex);
				}
			
			 
			}
		}
	}
}


