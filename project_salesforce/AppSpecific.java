package project_salesforce;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppSpecific {

	WebDriver driver;
	Select Listbox;
	String FileLocation, ReortExcelLoc, XMLLoc;
	generalMethods met = new generalMethods();
	XMLWriteClass XML;
	reportMethod report;
	String EmailExlPath="/testSuit/Report/email.xls";
	String pwd="Bala@8290";
	String Email="testleaf001@gmail.com";
	WebDriverWait wait;
	int campaignnamemorethn4l=0,typeConfOrWebinar=0,StatusNotComp=0,MaxActualcost=0;
	List<WebElement> table,Cell;
	Actions builder;
	public AppSpecific(String testCaseName,String Browser) throws WriteException, IOException,
			ParserConfigurationException {
		// initialize all variables and class inside constructor.
		XML = new XMLWriteClass();
		XML.createRootNode(testCaseName);
		FileLocation = met.createFolder(testCaseName);
		ReortExcelLoc = FileLocation + "/";
		XMLLoc = FileLocation + "/" + testCaseName + ".xml";
		report = met.start(ReortExcelLoc, XML);
		driver = met.LaunchBrowser(Browser);
	}

	public boolean signup() throws RowsExceededException, WriteException,
			IOException, TransformerException {
		String FirstName="Bala";
		String Lastname="Subra";
		String UserName="testmylife829"+met.randomNumber(1000000)+"@gmail.com";
		String CountryCode = "IN";
		String CompanyName="XXX";
		String pinCode = "600037";
		
		
		boolean status =false;
		
		try {
			
			met.goToURL("https://www.developerforce.com/events/regular/registration.php");
			met.SendValueToObj(met.findElementById("first_name"), FirstName,
					"Sending_first_name");
			met.SendValueToObj(met.findElementById("last_name"), Lastname,
					"Sending_last_name");
			met.SendValueToObj(met.findElementById("email"),
					Email, "Entering_Email_Address");
			met.ListboxByValue(met.findElementById("job_role"),
					"Architect/CTO", "Selecting_Jobrole");
			met.SendValueToObj(met.findElementById("company"), CompanyName,
					"Entering_Company_Name");
			met.ListboxByValue(met.findElementById("country"), CountryCode,
					"Selecting_Country");
			met.SendValueToObj(met.findElementById("postal_code"),pinCode,
					"Sending_Pincode");
			met.SendValueToObj(met.findElementById("username"), UserName,"Entering_UserName");
			met.clickObj(met.findElementById("eula"), "Accepting_Contions");
			met.clickObj(met.findElementById("submit_btn"), "ClickingOnSignUp");
			Thread.sleep(10000);
			String ActivationURL=met.ReadMail(Email, "Testleaf123	",false);
			System.out.println("link:"+ActivationURL);
			if(ActivationURL!=null)
			met.goToURL(ActivationURL);
			met.SendValueToObj(met.findElementById("p5"), pwd, "EnteringPassword");
			met.SendValueToObj(met.findElementById("p6"), pwd, "re_EnteringPassword");
			System.out.println(pwd);
			met.ListboxByIndex(met.findElementById("p2"),1,"Selecting_FirstOption");
			met.SendValueToObj(met.findElementById("p3"), "Bala", "EnteringSecurity_Answer");
			met.clickObj(met.findElementByName("save"), "Clicking_OnSavebutton");
			met.implicitWaitDriver(20);
			logoutApp();
			met.CreateExcel(EmailExlPath, UserName, pwd);
			System.out.println("Registration Successful");
			status =true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			
			}
		return status;
			
	}
	
	public void loginApp (String Username,String Password) throws RowsExceededException, WriteException, IOException
	{
		 //login developer force.com
    	try {
    		met.SendValueToObj(met.findElementById("username"), Username,"Entering_Username");
			met.SendValueToObj(met.findElementById("password"),Password,"Entering_Password");
			met.clickObj(met.findElementById("Login"),"Click_LoginButton");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(true)  // for debugging purpose
			{
				try {
					if(driver.findElement(By.partialLinkText("No thanks. I don't want to use mobile verification")).isDisplayed()){
					met.clickObj(met.findElementByPartLinkText("No thanks. I don't want to use mobile verification"),"No need phone verification");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("Phone number verification is been skipped");}
				} catch (Exception e) {
					System.out.println("Phone number verification is already skipped");
					//e.printStackTrace();
				}
				try
				{
					if(driver.findElement(By.id("save")).isDisplayed()){
						
					met.clickObj(met.findElementById("save"),"Send email verification");
					System.out.println("Doing Email verification");
					String verificationcode=met.ReadMail(Email,"iloverena",true);
					met.SendValueToObj(met.findElementById("code"), verificationcode,"SendingVerificationCode");
					met.clickObj(met.findElementById("save"),"Verify my code and log me in");
					met.implicitWaitDriver(30);
					//met.SendValueToObj(met.findElementById("usrn"),Username, "again entering username");
					//met.SendValueToObj(met.findElementById("password"),Password, "again entering password");
					//met.clickObj(met.findElementById("Login"), "Clicking on login");
					//met.implicitWaitDriver(30);
					}
				}
				catch(Exception e)
				{
					System.out.println("Email verification already done");
				}
			}
			if(met.findElementById("userNavButton").isDisplayed())
			report.addInLine("Login Successful", "Pass", driver);
			else
				report.addInLine("Login Successful", "fail", driver);
		} catch (Exception e) {
			report.addInLine("Login Successful", "fail", driver);
			e.printStackTrace();
		}
	}
	
	public void finishingTouch() throws WriteException, IOException, TransformerException
	{
		report.writeExcel();
		XML.writeDoc(XMLLoc);
		met.closeBrowser();
	}
	
	public void logoutApp() throws RowsExceededException, WriteException, IOException
	{
		try {
			met.clickObj(met.findElementById("userNavButton"), "Clicking_UserName");
			met.clickObj(met.findElementByLinkText("Logout"),"Loggingout");
			report.addInLine("Logout Successful", "Pass", driver);
		} catch (Exception e) {
			
			e.printStackTrace();
			report.addInLine("Logout Successful", "fail", driver);
		}
	}

	public void checkFileExist(AppSpecific App) throws WriteException, IOException, ParserConfigurationException, TransformerException
	{	
		File f = new File(EmailExlPath);
		if(!f.exists())
		{
			report.addInLine("Is file exist for login username and password - execute sign up testcase", "Fail", driver);
			App.signup();
		}	
			
		else
			report.addInLine("Is file exist for login username and password", "Pass", driver);
		
	}
	
	public void forgotPassword(String Username)
	{
		String newpwd="iloveCobra8290";
		try{
			met.clickObj(met.findElementByPartLinkText("Forgot your password?"),"Clicking on forgot password Link");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("un"),Username,"Entering username");
			met.clickObj(met.findElementById("continue"), "Clicking On Continue");
			String URL=met.ReadMail(Email,"iloverena",false);
			met.goToURL(URL);
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("answer"),"Bala", "Entering answer for pet name");
			met.clickObj(met.findElementById("continue"), "Clicking On Continue");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("p5"), newpwd, "Entering new password "+newpwd);
			met.SendValueToObj(met.findElementById("p6"), newpwd, "Re-Entering new password "+newpwd);
			met.clickObj(met.findElementByName("save"), "Clicking on save button");
			met.CreateExcel(EmailExlPath,Username, newpwd);
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	public void ReaddataforLogin(AppSpecific App,boolean ispwdforgotten) throws BiffException, IOException, RowsExceededException, WriteException, TransformerException
	{
		try {
			App.checkFileExist(App);
			int row=report.ReturnRowCount(EmailExlPath, "Email");
			System.out.println(row);
			if(row<=1)
				{report.addInLine("Is file exist for login username and password - execute sign up testcase", "Fail", driver);
				App.signup();
				}
			
			row=report.ReturnRowCount(EmailExlPath, "Email");
			String Username=report.readDataFromCell(EmailExlPath, "Email", 0, row-1);
			String Password=report.readDataFromCell(EmailExlPath, "Email", 1, row-1);
			System.out.println(Username+"           "+Password);
			String URL="http://developer.force.com/";
			met.goToURL(URL);
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByLinkText("DE LOGIN"),"Clicking_on_LoginButton");
			met.implicitWaitDriver(30);
			if(ispwdforgotten)
				App.forgotPassword(Username);
			else
			App.loginApp(Username, Password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}
	
	public void CreateNewCampaignView(String CampaignName) throws RowsExceededException, WriteException, IOException
	{
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByLinkText("Create New View"), "Clicking on Create new view");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("fname"), CampaignName + Keys.TAB, "Entering Campaign name : "+CampaignName);
			met.clickObj(met.findElementById("devname"), "Clicking on auto populate");
			String AutoPopCampName=met.findElementById("devname").getAttribute("value");
			
			
			if(AutoPopCampName.equals(CampaignName))
				report.addInLine("Verify Campaign name auto populated", "pass",driver);
			else
				report.addInLine("Verify Campaign name auto populated", "fail",driver);
			
			List<WebElement> Radiobox=driver.findElements(By.name("fshare"));
			met.clickObj(Radiobox.get(met.randomNumber(Radiobox.size())), "Random selecting Radio button");
			
			//save campaign
			met.clickObj(met.findElementByName("save"), "Clicking on save button");
			
			//wait till browser loaded 
			met.implicitWaitDriver(30); // implicit wait till URL gets loaded.

			Select Listbox = new Select(met.findElementByName("fcf"));
			Listbox.getFirstSelectedOption().getText();
			String DefaultSelected=Listbox.getFirstSelectedOption().getText();
			if(DefaultSelected.equals(CampaignName))
				report.addInLine("Verify Campaign name is auto selected in drop down", "pass",driver);
			else
				report.addInLine("Verify Campaign name is auto selected in drop down", "fail",driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void checkCampaignExist(String CampaignName)
	{
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			if(!met.findElementByName("fcf").getText().contains(CampaignName))
			{
				report.addInLine("Already campaign View exist to delete", "Fail", driver);
				CreateNewCampaignView(CampaignName);
			}
		} catch (RowsExceededException e) {
		
			e.printStackTrace();
		} catch (WriteException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		catch(Exception e4)
		{
			e4.printStackTrace();
		}
	}
	
	public void EditCampaignView(String CampaignName)
	{
		try {
			checkCampaignExist(CampaignName);
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.clickObj(met.findElementByLinkText("Edit"),"Clicking on Edit Campaign");
			met.implicitWaitDriver(30);
			CampaignName="Edited"+CampaignName;
			met.SendValueToObj(met.findElementById("fname"), CampaignName + Keys.TAB, "Entering Campaign name : "+CampaignName);
			met.SendValueToObj(met.findElementById("devname"), CampaignName , "re-Entering Campaign name : "+CampaignName);
			List<WebElement> Radiobox=driver.findElements(By.name("fshare"));
			met.clickObj(Radiobox.get(met.randomNumber(Radiobox.size())), "Random selecting Radio button");
			
			//save campaign
			met.clickObj(met.findElementByName("save"), "Clicking on save button");
			
			//wait till browser loaded 
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // implicit wait till URL gets loaded.

			Select Listbox = new Select(met.findElementByName("fcf"));
			Listbox.getFirstSelectedOption().getText();
			String DefaultSelected=Listbox.getFirstSelectedOption().getText();
			if(DefaultSelected.equals(CampaignName))
				report.addInLine("Verify Campaign name is auto selected in drop down", "pass",driver);
			else
				report.addInLine("Verify Campaign name is auto selected in drop down", "fail",driver);
			
		}  catch (WriteException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void DeleteCampaignView(String CampaignName)
	{
		try {
			checkCampaignExist(CampaignName);
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			//met.clickObj(met.findElementByLinkText("Edit"),"Clicking on Edit Campaign");
			//met.implicitWaitDriver(30);
			met.clickObj(met.findElementByPartLinkText("Edit"), "Clicking on Edit Campaign");
			met.implicitWaitDriver(30);
			//driver.findElement(By.partialLinkText("Edit")).click();
			//met.implicitWaitDriver(30);
			//met.clickObj(met.findElementByName("delID"),"Deleting view");
			//Thread.sleep(5000);
			//met.clickObj(met.findElementByName("delID"),"Deleting view");
			//workaround as taking screen shot automatically closes pop-up
			//driver.findElement(By.name("delID")).click();
			//met.findElementByName().click();
			handleAlertPopUp("delID");
			
			
			
			
			//driver.switchTo().alert().sendKeys(""+Keys.ENTER);
			
			//driver.switchTo().activeElement();
			//Actions builder = new Actions(driver);
			//builder.sendKeys(Keys.ENTER).build().perform();
			//builder.keyDown(Keys.ENTER).build().perform();
			//builder.keyUp(Keys.ENTER).build().perform();
			if(!met.findElementByName("fcf").getText().contains(CampaignName))
				report.addInLine("Verify Campaign view is deleted", "pass",driver);
			else
				report.addInLine("Verify Campaign view is deleted", "fail",driver);
					
		}  catch (WriteException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleAlertPopUp(String objName) throws RowsExceededException, WriteException, IOException
	{
		try {
			driver.findElement(By.name(objName)).click();
			WebDriverWait Wait = new WebDriverWait(driver,5);
			Wait.until(ExpectedConditions.alertIsPresent());
			//System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
			
			report.addInLine("deleted View","pass", driver);
		} catch (Exception e) {
		    e.printStackTrace();
			report.addInLine("deleted View","fail", driver);
		}
	}
	public void cancelCampaignView(String CampaignName)
	{
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByLinkText("Create New View"), "Clicking on Create new view");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("fname"), CampaignName + Keys.TAB, "Entering Campaign name : "+CampaignName);
			met.clickObj(met.findElementById("devname"), "Clicking on auto populate");
			String AutoPopCampName=met.findElementById("devname").getAttribute("value");
			
			
			if(AutoPopCampName.equals(CampaignName))
				report.addInLine("Verify Campaign name auto populated", "pass",driver);
			else
				report.addInLine("Verify Campaign name auto populated", "fail",driver);
			
			List<WebElement> Radiobox=driver.findElements(By.name("fshare"));
			met.clickObj(Radiobox.get(met.randomNumber(Radiobox.size())), "Random selecting Radio button");
			
			//save campaign
			met.clickObj(met.findElementByName("cancel"), "Clicking on save button");
			
			//wait till browser loaded 
			met.implicitWaitDriver(30); // implicit wait till URL gets loaded.

			String context =met.findElementByName("fcf").getText();
			if(context.contains(CampaignName))
				report.addInLine("Cancelling campaign view ", "fail",driver);
			else
				report.addInLine("Cancelling campaign view ", "Pass",driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void createCampaign(String CampaignName)
	{
		String type="Webinar";
		String startingDate="02/08/2014";
		String EndingDate="02/09/2014";
		String CampStatus="Planned";
		String Exprevue="567";
		String BudgetCost="6000";
		String ActualCost="7000";
		String ExpectedResp="55";
		
		
		
		
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByName("new"),"Creating New campaign");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("cpn1"),CampaignName, "Entering CampaignName");
			met.clickObj(met.findElementById("cpn16"),"Making campaign Active");
			met.ListboxByValue(met.findElementById("cpn2"),type,"Selecting type of campaign");
			met.ListboxByValue(met.findElementById("cpn3"),CampStatus,"Selecting Status of campaign");
			met.SendValueToObj(met.findElementById("cpn5"),startingDate, "Entering campaign start date");
			met.SendValueToObj(met.findElementById("cpn6"),EndingDate, "Entering campaign End date");
			met.SendValueToObj(met.findElementById("cpn8"),Exprevue, "Entering Expected Revenue");
			met.SendValueToObj(met.findElementById("cpn9"),BudgetCost, "Entering Budgeted cost");
			met.SendValueToObj(met.findElementById("cpn10"),ActualCost, "Entering Actual Cost");
			met.SendValueToObj(met.findElementById("cpn11"),ExpectedResp, "Entering Expected response");
			met.clickObj(met.findElementByName("save"),"Saving Campaign");
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			if(met.findElementByLinkText(CampaignName).isDisplayed())
				report.addInLine("Creating new campaign", "Pass", driver);
			else
				report.addInLine("Creating new campaign", "fail", driver);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void editCampaign(String CampaignName,String NewCampaignName)
	{
		String type="Webinar";
		String startingDate="02/08/2014";
		String EndingDate="02/10/2014";
		String CampStatus="In Progress";
		String Exprevue="1000";
		String BudgetCost="10000";
		String ActualCost="9000";
		String ExpectedResp="90";
		
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			try
			{
				driver.findElement(By.partialLinkText(CampaignName)).isDisplayed();
			}
			catch(Exception e)
			{
				report.addInLine("Already campaign exist to delete", "Fail", driver);
				createCampaign(CampaignName);
				met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
				met.implicitWaitDriver(30);
			}
			met.clickObj(met.findElementByPartLinkText(CampaignName), "Clicking on campaign to Edit it");
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByName("edit"), "Clicking on Edit button");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("cpn1"),NewCampaignName, "Entering CampaignName");
			met.clickObj(met.findElementById("cpn16"),"Making campaign Active");
			met.ListboxByValue(met.findElementById("cpn2"),type,"Selecting type of campaign");
			met.ListboxByValue(met.findElementById("cpn3"),CampStatus,"Selecting Status of campaign");
			met.SendValueToObj(met.findElementById("cpn5"),startingDate, "Entering campaign start date");
			met.SendValueToObj(met.findElementById("cpn6"),EndingDate, "Entering campaign End date");
			met.SendValueToObj(met.findElementById("cpn8"),Exprevue, "Entering Expected Revenue");
			met.SendValueToObj(met.findElementById("cpn9"),BudgetCost, "Entering Budgeted cost");
			met.SendValueToObj(met.findElementById("cpn10"),ActualCost, "Entering Actual Cost");
			met.SendValueToObj(met.findElementById("cpn11"),ExpectedResp, "Entering Expected response");
			met.clickObj(met.findElementByName("save"),"Saving Campaign");
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			if(met.findElementByLinkText(NewCampaignName).isDisplayed())
				report.addInLine("Editing  campaign", "Pass", driver);
			else
				report.addInLine("Editing campaign", "fail", driver);
			
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void DeleteCampaign(String CampaignName)
	{
		
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			try
			{
				driver.findElement(By.partialLinkText(CampaignName)).isDisplayed();
			}
			catch(Exception e)
			{
				report.addInLine("Already campaign exist to delete", "Fail", driver);
				createCampaign(CampaignName);
				met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
				met.implicitWaitDriver(30);
			}
			met.clickObj(met.findElementByPartLinkText(CampaignName), "Clicking on campaign to Edit it");
			met.implicitWaitDriver(30);
			report.addInLine("Deleting view","Pass", driver);
			//met.clickObj(met.findElementByName("del"), "Clicking on Edit button");
			met.findElementByName("del").click();
			driver.switchTo().alert().accept();
			met.implicitWaitDriver(30);
			try {
				driver.findElement(By.linkText(CampaignName)).isDisplayed();
				report.addInLine("Deleting  campaign", "fail", driver);
			} catch (Exception e) {
				
				report.addInLine("Editing campaign", "pass", driver);
			}
			
				
			
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void cloneCampaign(String CampaignName,String NewCampaignName)
	{
		String type="Webinar";
		String startingDate="02/08/2014";
		String EndingDate="02/10/2014";
		String CampStatus="In Progress";
		String Exprevue="1000";
		String BudgetCost="10000";
		String ActualCost="9000";
		String ExpectedResp="90";
		
		try {
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			try
			{
				driver.findElement(By.partialLinkText(CampaignName)).isDisplayed();
			}
			catch(Exception e)
			{
				report.addInLine("Already campaign exist to exist?(true/false)", "Fail", driver);
				createCampaign(CampaignName);
				met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
				met.implicitWaitDriver(30);
			}
			met.clickObj(met.findElementByPartLinkText(CampaignName), "Clicking on campaign to Edit it");
			met.implicitWaitDriver(30);
			met.clickObj(met.findElementByName("clone"), "Clicking on Edit button");
			met.implicitWaitDriver(30);
			met.SendValueToObj(met.findElementById("cpn1"),NewCampaignName, "Entering CampaignName");
			met.clickObj(met.findElementById("cpn16"),"Making campaign Active");
			met.ListboxByValue(met.findElementById("cpn2"),type,"Selecting type of campaign");
			met.ListboxByValue(met.findElementById("cpn3"),CampStatus,"Selecting Status of campaign");
			met.SendValueToObj(met.findElementById("cpn5"),startingDate, "Entering campaign start date");
			met.SendValueToObj(met.findElementById("cpn6"),EndingDate, "Entering campaign End date");
			met.SendValueToObj(met.findElementById("cpn8"),Exprevue, "Entering Expected Revenue");
			met.SendValueToObj(met.findElementById("cpn9"),BudgetCost, "Entering Budgeted cost");
			met.SendValueToObj(met.findElementById("cpn10"),ActualCost, "Entering Actual Cost");
			met.SendValueToObj(met.findElementById("cpn11"),ExpectedResp, "Entering Expected response");
			met.clickObj(met.findElementByName("save"),"Saving Campaign");
			met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on Campaigns");
			met.implicitWaitDriver(30);
			if(met.findElementByLinkText(NewCampaignName).isDisplayed())
				report.addInLine("Editing  campaign", "Pass", driver);
			else
				report.addInLine("Editing campaign", "fail", driver);
			
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void scenario1()
	{
		try {
			if(met.findElementById("userNavButton").isDisplayed())
			{
				met.clickObj(met.findElementByLinkText("Campaigns"), "Clicking on campaign tab");
				met.implicitWaitDriver(30);
				// select last but one value
				Listbox=new Select(met.findElementByName("fcf"));
				int TotSize=Listbox.getOptions().size();
				if(TotSize>=2)
				{
					Listbox.selectByIndex(TotSize-2);
				}else
				{
					if(TotSize==1)
						Listbox.selectByIndex(0);
				}
				met.clickObj(met.findElementByName("go"), "clicking on go button");
				// print count of campaign present
				met.implicitWaitDriver(30);
				table=driver.findElements(By.className("x-grid3-row-table"));
				//get number of campaign present
				System.out.println("Total campaign present "+table.size());
				System.out.println("-----------------------------------------------------");
				
				System.out.println("Campaign Name more than 4 letters");
				for(int i=0;i<table.size();i++)
				{
					Cell=table.get(i).findElements(By.tagName("td"));
					//campaign name more than four letters.
					if(Cell.get(3).getText().length()>4)
						campaignnamemorethn4l+=1;
					//Campaign type Conference or Webinar
					if(Cell.get(8).getText().equals("Conference")||Cell.get(8).getText().equals("Webinar"))
						typeConfOrWebinar+=1;
					//status - not completed
					if(!(Cell.get(9).getText().equals("Completed")))
						StatusNotComp+=1;
					}
				System.out.println("Number of Campaign Name more than 4 letters is : "+campaignnamemorethn4l);
				System.out.println("Number of Campaign type Conference or Webinar is : "+typeConfOrWebinar);
				System.out.println("Number of status - not completed : "+StatusNotComp);
				System.out.println("------------------------------------------------------");
				
				//Select Edit link of Campaign that has higher actual cost
				System.out.println("Select Edit link of Campaign that has higher actual cost");
				int count=0;
				String TextVal;
				// get max Actual cost
				for(int i=0;i<table.size();i++)
				{
					Cell=table.get(i).findElements(By.tagName("td"));
					TextVal=Cell.get(7).getText();
					//System.out.println(TextVal.length());
					if((TextVal.length()!=1))
							{
								
								if(count==0)
									{
									MaxActualcost=Integer.parseInt(Cell.get(7).getText().substring(1).replace(",",""));
									count+=1;
									}
								
								int currentValue=Integer.parseInt(Cell.get(7).getText().substring(1).replace(",",""));
								if(currentValue > MaxActualcost)
									{
									MaxActualcost=currentValue;
									
									}
									}
							}
						
				for(int i=0;i<table.size();i++)
				{
					Cell=table.get(i).findElements(By.tagName("td"));
					TextVal=Cell.get(7).getText();
					
					if((TextVal.length()!=1))
							{
								int currentValue=Integer.parseInt(Cell.get(7).getText().substring(1).replace(",",""));
								if(currentValue==MaxActualcost)
									{
									Cell.get(2).findElement(By.linkText("Edit")).click();
									break;
									}
							}
				}
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//check whether we are in Edit Campaign
				if(driver.getTitle().contains("Campaign Edit"))
					System.out.println("Campaign Edit field successful");
				else
					System.out.println("Campaign Edit field successful");
				
				// clicking on campaign
				driver.findElement(By.name("cancel")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				// whether campaign is in-ACtive - if its In-Active Make it Active
				WebDriverWait wait = new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("x-grid3-row-table")));
				table=driver.findElements(By.className("x-grid3-row-table"));
				for(int i=0;i<table.size();i++)
				{
					Cell=table.get(i).findElements(By.tagName("td"));
					Cell.get(2).findElement(By.linkText("Edit")).click();
					if(driver.findElement(By.id("cpn16")).isSelected())
					{
						driver.findElement(By.name("cancel")).click();
						
					}else
					{
						driver.findElement(By.id("cpn16")).click();
						driver.findElement(By.id("cpn10")).sendKeys("0");
						driver.findElement(By.name("save")).click();
					}
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.className("x-grid3-row-table")));
					table=driver.findElements(By.className("x-grid3-row-table"));
					Cell=table.get(i).findElements(By.tagName("td"));
					//System.out.println(i);
					}
				//find name of last listed campaign and select first lellter of it.
				try {
					table=driver.findElements(By.className("x-grid3-row-table"));	
					Cell=table.get(table.size()-1).findElements(By.tagName("td"));
					String lastCampaignName=Cell.get(3).getText();
					met.clickObj(met.findElementByLinkText(lastCampaignName.substring(0, 1).toUpperCase()), "Clicking on first letter link");
					Thread.sleep(5000);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.className("x-grid3-row-table")));
					table=driver.findElements(By.className("x-grid3-row-table"));
					System.out.println(lastCampaignName);
					boolean status=true;
					for(int i=0;i<table.size();i++)
					{
						Cell=table.get(i).findElements(By.tagName("td"));
						System.out.println(lastCampaignName);
						String NewString=Cell.get(3).getText();
						if(NewString.contains(lastCampaignName))
							{ System.out.println("last campaign present");
							 Cell.get(2).findElement(By.linkText("Del")).click();
							 wait.until(ExpectedConditions.alertIsPresent());
							 driver.switchTo().alert().accept();
							 status=false;
							 break;
							}
					}
					if(status)
						System.out.println("Last campaign is not present present");
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				// clicking on Opportunities tab
				try {
					met.clickObj(met.findElementByLinkText("Opportunities"),"Clicking on opportunities Tab");
					met.implicitWaitDriver(30);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fcf")));
					//find largest value in opportunities 
					Listbox = new Select(met.findElementByName("fcf"));
					int totSize=Listbox.getOptions().size();
					int length=0;
					String firstselectedopt=Listbox.getFirstSelectedOption().getText();
					for(int i=0;i<totSize;i++)
					{
						if(i==0)
							length=Listbox.getOptions().get(i).getText().length();
						int local=Listbox.getOptions().get(i).getText().length();
							//System.out.println("Length :"+length+"  local "+local);
						if(local>length)
							length=local;
					}
					
					Listbox = new Select(met.findElementByName("fcf"));
					for(int i=0;i<totSize;i++)
					{	String localString=Listbox.getOptions().get(i).getText();
						int local =localString.length();
						if(local==length)
						{
							Listbox.selectByVisibleText(localString);
							//System.out.println(Listbox.getFirstSelectedOption().getText());
							if(localString.equals(firstselectedopt))
								driver.findElement(By.name("go")).click();
							break;
						}
					}
					met.implicitWaitDriver(30);		
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name("newOpp")));					
					
					// enter opp
					met.clickObj(met.findElementByName("newOpp"),"Clicking on new Opportunities button");
					met.implicitWaitDriver(30);
					// entering mandatory field
					met.SendValueToObj(met.findElementById("opp3"), "SamuraiX", "Sending opportunities name");
					met.SendValueToObj(met.findElementById("opp9"), "02/08/2013", "Sending date value");
					met.ListboxByValue(met.findElementById("opp11"), "Qualification", "Selecting qualification from list box");
					met.clickObj(met.findElementByName("save"), "Clicking on save button");
					met.implicitWaitDriver(30);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				//Click report
				met.clickObj(met.findElementByLinkText("Reports"), "Clicking on report tab");
				met.implicitWaitDriver(30);
				
				// mouse over 
				builder = new Actions(driver);
				builder.moveToElement(met.findElementByClass("nodeText")).build().perform();
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void scenario2() throws RowsExceededException, WriteException, IOException, InterruptedException
	{
		//click on Home
		//click on help from that page
		String parentWin;
		try {
			met.clickObj(met.findElementByLinkText("Home"),"Clicking on Home Tab");
			met.implicitWaitDriver(30);
			wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mCalendar")));
			//verify calender present
			if(met.findElementByClass("mCalendar").isDisplayed())
				System.out.println("Calender present");
			else
				System.out.println("Calender not present");
			// select date exactly 25 days from now
			clickCalender(25);
			report.addInLine("Clicking on calender date and verify calender is present", "pass", driver);
		} catch (Exception e) {
			report.addInLine("Clicking on calender date and verify calender is present", "fail", driver);
			e.printStackTrace();
		}
		try {
			//clicking on help link
			parentWin = driver.getWindowHandle();
			met.clickObj(met.findElementByLinkText("Help"), "Clicking on Help Link");
			driver=met.switchtolastWindow(driver, "Switching to help Window");
			
			System.out.println("Inside help window "+ driver.getTitle());
			// close help Window
			met.closeBrowser();
			// switch control back to parent window
			met.SwitchToParentWindow(driver, parentWin);
			report.addInLine("Clicking on help window and closing it", "pass", driver);
		} catch (Exception e) {
			report.addInLine("Clicking on help window and closing it", "fail", driver);
			e.printStackTrace();
		}
		
		//click on contact tab
		try {
			met.clickObj(met.findElementByLinkText("Contacts"), "Clicking on Contact tab");
			met.implicitWaitDriver(30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fcf")));
			// selecting all contacts from list box
			Listbox=new Select(met.findElementByName("fcf"));
			String FirstselectOpt=Listbox.getFirstSelectedOption().getText();
			Listbox.selectByVisibleText("All Contacts");
			if(FirstselectOpt.equals("All Contacts"))
				driver.findElement(By.name("go")).click();
			met.implicitWaitDriver(30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("x-grid3-row-table")));
			// select phone text and double click phone text
			table=driver.findElements(By.className("x-grid3-row-table"));
			for(int i=0;i<table.size();i++)
			{
				Cell=table.get(i).findElements(By.tagName("td"));
				String context=Cell.get(6).getText();
				if(context.length()!=1)
				{
					Cell.get(6).findElement(By.tagName("div")).click();
					builder = new Actions(driver);
					builder.doubleClick(Cell.get(6).findElement(By.tagName("div"))).build().perform();
					//builder.sendKeys(Keys.ENTER).build().perform();
					met.findElementById("Phone").sendKeys("(719)545-3456");
					met.findElementById("saveButton").click();
					Thread.sleep(2000);
					//met.clickObj(met.findElementById("saveButton"), "Clicking on save button");
					met.implicitWaitDriver(30);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.className("x-grid3-row-table")));
					break;
				}
			}
			
			// selecting odd checkbox and checking nos of box selected
			table=driver.findElements(By.className("x-grid3-row-table"));
			int odd=0,Selected=0;
			for(int i=0;i<table.size();i++)
			{ Cell=table.get(i).findElements(By.tagName("td"));
				odd+=1;
				if((odd%2)==1)
				{
					if(!(Cell.get(0).findElement(By.className("checkbox")).isSelected()))
					{
						System.out.println();
						Cell.get(0).findElement(By.className("checkbox")).click();
					}
					Selected+=1;
				}
					
				
			}
			
			
			String Value=driver.getCurrentUrl();
			Value=Value.substring(Value.indexOf("=")+1);
			WebElement Element = met.findElementById(Value+"_paginator_selection_target");
			// can't use class name so try to use xpath
			if(Element.getText().contains(""+Selected))
				{
					System.out.println("Selected shows as per selected  - nos selected : "+ Element.getText());
					report.addInLine("Checking text box selected and displayed value", "Pass", driver);
				}
				
			else
			{
				System.out.println("Selected is Fault Selected shows "+ Element.getText()+" But Actual selected "+Selected);
				report.addInLine("Checking text box selected and displayed value", "fail", driver);
			}
		} catch (Exception e) {
			report.addInLine("Checking text box selected and displayed value", "fail", driver);
			e.printStackTrace();
		}
		
	}
	
	public void clickCalender(int maxcount) throws InterruptedException, RowsExceededException, WriteException, IOException
	{
		boolean start=false,Isbreak=false;
		int count=0;
		int nos = 0;
		String tex="0";
		try {
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
			report.addInLine("Clicking on calender at "+maxcount+" from today " , "pass", driver);
		} catch (Exception e) {
			report.addInLine("Clicking on calender at "+maxcount+" from today " , "fail", driver);
			e.printStackTrace();
		}
	}


}
