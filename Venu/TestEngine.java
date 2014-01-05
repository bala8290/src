package Venu;

import static org.junit.Assert.fail;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TestEngine {
	
	
	// Method to click on the Verify link in Gmail
	public static void verify_mail_link(WebDriver wd,String mailFrom, String linkVerify, String strTCNum, String strStep, int ScenarioNum) throws RowsExceededException, BiffException, WriteException, IOException{
			try {
				List<WebElement> mails = wd.findElements(By.className("zF"));
				int count = mails.size();
				for(int i=0;i<count;i++){
					String mail = mails.get(i).getText();
					if(mail.equalsIgnoreCase(mailFrom)){
						mails.get(i).click();
						Thread.sleep(45000);
						break;
					}
					
				}
				
				List<WebElement> links = wd.findElements(By.tagName("a"));
				int linkCount = links.size();
					
				
		
				for(int i=0;i<linkCount;i++){
					String mail = links.get(i).getText();
					if(mail.startsWith(linkVerify)){
						links.get(i).click();
						Thread.sleep(45000);
						break;
					}
					
				}
				
				
				
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
			}catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			}
	}
	
	// To Close all driver instances
	
	public static void quit_Driver(WebDriver driver, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.quit();
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			//dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			
		}

	}

	// Launch the Application using the URL passed
	
	public static void launch_App(WebDriver driver,String url, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			driver.manage().window().maximize();
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(driver, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}

	}
	
	// Entering value in Text box using locater ID
	
	public static void enter_ID(WebDriver wd, String pr, String dt, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		try {
			
			wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wd.findElement(By.id(pr)).click();
			wd.findElement(By.id(pr)).clear();
			wd.findElement(By.id(pr)).sendKeys(dt);
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);

		}
	}
	
	
	// Selecting from List box using Index
	public static void select_ID_Index(WebDriver wd, String pr, String dt, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		      try {
		    	  
		    	  Select se = new Select(wd.findElement(By.id(pr)));
				  int d=Integer.parseInt(dt);
				  se.selectByIndex(d);
				  Driver dc = new Driver();
				  dc.screenshot(wd, ScenarioNum, strTCNum);
					dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
					
			}catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			}
		      catch (Exception e) {
				// TODO Auto-generated catch block
		    	  Driver dc = new Driver();
		    	  dc.screenshot(wd, ScenarioNum, strTCNum);
					dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			}
		          
		  
		  
	}
	
	
	// Selecting from List box using Visible Text
	public static void select_ID_Value(WebDriver wd, String pr, String dt, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		      try {
		    	  wd.findElement(By.id(pr)).click();
		    	  Select se = new Select(wd.findElement(By.id(pr)));
				se.selectByVisibleText(dt);
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			}catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			}
		      catch (Exception e) {
				// TODO Auto-generated catch block
		    	  Driver dc = new Driver();
		    	  dc.screenshot(wd, ScenarioNum, strTCNum);
					dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
					
			}
		          
		  
		  
	}
	
	
	// Accept the Alert for Delete
	
	public static void Accept_Alert(WebDriver wd, String strTCNum, String strStep, int ScenarioNum) throws RowsExceededException, BiffException, WriteException, IOException{
		
		try {
			wd.switchTo().alert().accept();
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}
		
	}

	// Verify the Text of the Link
	
	public static void Verify_linkText(WebDriver wd, String pr, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		 wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			if(wd.findElement(By.linkText(pr)).isDisplayed()){
				System.out.println("Yes");
				Thread.sleep(120000);
				Driver dc = new Driver();
				dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			}
				
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 Driver dc = new Driver();
			 dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
				
			}
	}
	
	public static void Switch_window(WebDriver wd, String strTCNum, String strStep, int ScenarioNum) throws RowsExceededException, BiffException, WriteException, IOException{
		
		try {
			Set<String> win = wd.getWindowHandles();
			int i=0;
			for(String w:win){
				if(i==1){
					wd.switchTo().window(w);
					break;
				}
				
				if(i==0){
					wd.switchTo().window(w);
					//String parent=w;
					i=i+1;
				}
				
			}
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			
		}

		
	}

	
	// Click method using locater Name
	
	public static void Click_Name(WebDriver wd, String pr, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		 wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			wd.findElement(By.name(pr)).click();
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 Driver dc = new Driver();
			 dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
				
			}
	}
	
	// Click method using locater ID
	
	public static void Click_ID(WebDriver wd, String pr, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		 wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			wd.findElement(By.id(pr)).click();
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 Driver dc = new Driver();
			 dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
				
			}
	}

	public static void Click_Xpath(WebDriver wd, String pr, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		 wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			wd.findElement(By.xpath(pr)).click();
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 Driver dc = new Driver();
			 dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
				
			}
	}
	
	
	// Click on Link using LinkText
	public static void Click_Link(WebDriver wd, String pr, String strTCNum, String strStep, int ScenarioNum) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException{
		 wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
			wd.findElement(By.linkText(pr)).click();
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Pass",ScenarioNum);
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			Driver dc = new Driver();
			dc.screenshot(wd, ScenarioNum, strTCNum);
			dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 Driver dc = new Driver();
			 dc.screenshot(wd, ScenarioNum, strTCNum);
				dc.writeResult("C:\\SalesForce\\Execution\\TestResult.xls",strTCNum ,strStep,"Fail",ScenarioNum);
			 
			}
	}
	
}
	//**********************************************************************************
