package project_salesforce;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Folder;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.mail.pop3.POP3Store;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;



public class generalMethods {

	Select Listbox;
	WebDriver driver;
	XMLWriteClass XML;
	reportMethod report;
	WebElement Element = null;
	String path;
	POP3Store emailStore = null;
	Folder emailFolder;
	WritableWorkbook wwb;
	Workbook wb;
	Sheet sh;
	WritableSheet wws;
	WritableFont wf;
	WritableCellFormat wcf;
	Label l1;
	String StartingPath="/testSuit/Report/";
	WebDriverWait wait;
	public reportMethod start(String fileLocation, XMLWriteClass XML)
			throws WriteException, IOException {
		report = new reportMethod(fileLocation, XML);
		return report;
	}

	public WebDriver LaunchBrowser(String BrowserName) throws RowsExceededException, WriteException, IOException     //launch browser
	{
		driver=null;
		try {
			if(BrowserName.toLowerCase().equals("ie"))
			{
				System.setProperty("webdriver.ie.driver",
						"/testSuit/lib/IEDriverServer.exe");
				driver = new InternetExplorerDriver(); // launching IE	
			}
			else 
				if(BrowserName.toLowerCase().equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						"/testSuit/lib/chromedriver.exe");
				driver = new ChromeDriver(); // Launch chrome
			}
			else 
				if(BrowserName.toLowerCase().equals("firefox"))
			{
				driver = new FirefoxDriver();
			}else
			{
				System.out.println("Browser string doesn't match"+BrowserName);
			}
			
			System.out.println("Launching browser "+BrowserName);
			report.addInLine("Launching browser "+BrowserName, "pass", driver);
		} catch (Exception e) {
			report.addInLine("Launching browser "+BrowserName, "fail", driver);
			e.printStackTrace();
		}
		
			return driver;
		
		
	}
	
	public void SendValueToObj(WebElement Element, String SendValue, String Desc)
			throws RowsExceededException, WriteException, IOException {
		Desc=Desc+"  "+SendValue;
		try {

			Element.clear();
			Element.sendKeys(SendValue);
			report.addInLine(Desc, "pass", driver);

		} catch (NoSuchElementException NSEE) {

			NSEE.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		} catch (Exception e) {

			e.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		}

	}

	public String printTitle() {
		String title = null;

		try {
			title = driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return title;
	}

	public void clickObj(WebElement Element, String Desc)
			throws RowsExceededException, WriteException, IOException {
		
		try {
			Element.click();
			report.addInLine(Desc, "pass", driver);
		} catch (NoSuchElementException NSEE) {

			report.addInLine(Desc, "fail", driver);

			NSEE.printStackTrace();

		} catch (Exception e) {
			report.addInLine(Desc, "fail", driver);
			e.printStackTrace();
		}

	}

	public void DropdownSelect(WebElement Element,String SelectBy,String Selector,
			String Desc) throws RowsExceededException, WriteException, IOException
	{
		/*
		 * VT- select by VisibleText
		 * VAL-Select by Value
		 * INDEX-Select by index 
		 */
		boolean status=true;
		SelectBy=SelectBy.toUpperCase();
		try {
			Desc =Desc+" Select by locator :  "+SelectBy+" seleting value: "+Selector;
			Listbox = new Select(Element);
			if(SelectBy.equals("VT"))
				Listbox.selectByVisibleText(Selector);
			else if(SelectBy.equals("VAL"))
					Listbox.selectByValue(Selector);
			else if(SelectBy.equals("INDEX"))
				Listbox.selectByIndex(Integer.parseInt(Selector));
			else
				{
					System.out.println("Wrong locator type please verify");
					status=false;
					//report.addInLine(Desc, "fail", driver);
				}
				
				if(status)
				{
					//report.addInLine(Desc, "pass", driver);
				}
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			e.printStackTrace();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}

	public WebElement findElementby(String Locator,String LocatorVal,String Desc) throws RowsExceededException, WriteException, IOException
	{ 
		Locator=Locator.toLowerCase();
		boolean status=true;
		Desc=Desc + "Locator : "+Locator+" Locator value :"+LocatorVal;
		try {
			switch(Locator)
			{
			
			case "id": Element=driver.findElement(By.id(LocatorVal));
						break;
			case "name":Element=driver.findElement(By.name(LocatorVal));
						break;
			case "css":Element=driver.findElement(By.cssSelector(LocatorVal));
						break;
			case "lt":Element=driver.findElement(By.linkText(LocatorVal));
						break;
			case "plt":Element=driver.findElement(By.partialLinkText(LocatorVal));
						break;
			case "xpath":Element=driver.findElement(By.xpath(LocatorVal));
						break;
			case "class":Element=driver.findElement(By.className(LocatorVal));
						break;
			default: {
						System.out.println("Wrong locator "+Locator);
						status=false;
						report.addInLine(Desc, "fail", driver);
						}	
			}
			//if(status)
		//	report.addInLine(Desc, "pass", driver);
		} catch (Exception e) {
			//report.addInLine(Desc, "fail", driver);
			e.printStackTrace();
		}
		return Element;
	}
	
	public void ListboxByVisibleText(WebElement Element, String VisibleText,
			String Desc) throws RowsExceededException, WriteException,
			IOException {
		Desc =Desc+" Select Visible Text: "+VisibleText;
		try {
			Listbox = new Select(Element);
			Listbox.selectByVisibleText(VisibleText);
			report.addInLine(Desc, "pass", driver);
		} catch (NoSuchElementException NSEE) {

			NSEE.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		} catch (Exception e) {

			e.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		}

	}

	public void ListboxByIndex(WebElement Element, int idValue, String Desc)
			throws RowsExceededException, WriteException, IOException {
		Desc =Desc+" Select Value: "+idValue;
		try {
			Listbox = new Select(Element);
			Listbox.selectByIndex(idValue);
			report.addInLine(Desc, "pass", driver);
		}

		catch (NoSuchElementException NSEE) {

			NSEE.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		} catch (Exception e) {

			e.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		}

	}

	// select a option in Listbox using value
	public void ListboxByValue(WebElement Element, String Value, String Desc)
			throws RowsExceededException, WriteException, IOException {
		Desc =Desc+" Select value: "+Value;
		try {
			Listbox = new Select(Element);
			Listbox.selectByValue(Value);
			report.addInLine(Desc, "pass", driver);
		}

		catch (NoSuchElementException NSEE) {

			NSEE.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		} catch (Exception e) {

			e.printStackTrace();
			report.addInLine(Desc, "fail", driver);
		}
	}

	// select a option by visible text

	public WebDriver LaunchChromeBrowserReturnIt() {
		try {
			System.setProperty("webdriver.chrome.driver",
					"/TestSuit/lib/chromedriver.exe");
			driver = new ChromeDriver(); // Launch chrome

		} catch (Exception e) {

			e.printStackTrace();
		}
		return driver;

	}

	public WebDriver LaunchFirefoxBrowserReturnIt() {
		try {
			driver = new FirefoxDriver(); // launching Firefox

		} catch (Exception e) {

			e.printStackTrace();
		}
		return driver;

	}

	public WebDriver LaunchIEBrowserReturnIt() {
		try {
			System.setProperty("webdriver.ie.driver",
					"/TestSuit/lib/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); // launching IE

		} catch (Exception e) {

			e.printStackTrace();
		}
		return driver;

	}

	public void goToURL(String url) {
		try {
			driver.get(url); // Go to given URL
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			report.addInLine("navigate_to_URL"+url, "pass", driver);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// close browser
	public void closeBrowser() {
		driver.close();

	}

	public WebElement findElementById(String idValue) {
		try {
			Element = driver.findElement(By.id(idValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;
	}

	// find element by Name
	public WebElement findElementByName(String NameValue) {
		try {
			Element = driver.findElement(By.name(NameValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;

	}

	// find element by LinkText
	public WebElement findElementByLinkText(String LinkValue) {
		try {
			Element = driver.findElement(By.linkText(LinkValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;

	}

	// find by partial Link Text

	public WebElement findElementByPartLinkText(String PartLinkValue) {
		try {
			Element = driver.findElement(By.partialLinkText(PartLinkValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;

	}

	// find element by CSS
	public WebElement findElementByCSS(String CSSValue) {
		try {
			Element = driver.findElement(By.cssSelector(CSSValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;

	}

	// find element by Xpath
	public WebElement findElementByXPath(String XPathValue) {
		try {
			Element = driver.findElement(By.xpath(XPathValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;

	}

	// find element by class
	public WebElement findElementByClass(String ClassValue) {

		try {
			Element = driver.findElement(By.className(ClassValue));

		} catch (Exception e) {

			e.printStackTrace();

		}
		return Element;
	}

	// creates a Folder

	public String createFolder(String folderName) {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			path = StartingPath
					+ folderName
					+ dateFormat.format(date).replace(':', '_')
							.replace(' ', '_');
			File dir = new File(path);
			dir.mkdir();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;

	}

	// taking screen shot using method

	public int randomNumber(int maxValue) {
		Random rndNumbers = new Random();
		int rndNumber = 0;
		rndNumber = rndNumbers.nextInt(maxValue);
		return rndNumber;

	}
	
	
	public String ReadMail(String Username,String Password,boolean ReadCode) throws MessagingException, IOException, RowsExceededException, WriteException {
		
				
		String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";	
			
		String URL="";
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", "pop.gmail.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.socketFactory.class" , socketFactoryClass);
			Session emailSession = Session.getDefaultInstance(properties);

			emailStore = (POP3Store) emailSession.getStore("pop3");
			emailStore.connect(Username,Password);			
			//System.out.println(emailStore);
			
			emailFolder =  emailStore.getFolder("Inbox");
			//System.out.println(emailFolder.getName());
			emailFolder.open(Folder.READ_ONLY);
			
			//System.out.println(emailFolder.getMessageCount());
			//System.out.println(emailFolder.getNewMessageCount());
			
			Message[] messages = emailFolder.getMessages();
			Object msgContent = messages[messages.length-1].getContent();
			if (msgContent instanceof Multipart){
			    Multipart multipart = (Multipart) messages[messages.length-1].getContent();
			    @SuppressWarnings("unused")
				BodyPart part = multipart.getBodyPart(0);
			   // System.out.println(part.getContent().toString());
			 }else
			    System.out.println(messages[messages.length-1].getContent());
			String MailContain = messages[messages.length-1].getContent().toString();
			if(ReadCode)
			{
				if(MailContain.contains("Verification Code"))
				{
					int startingindex=MailContain.indexOf("Code: ")+"Code: ".length();
					int Endingindex=MailContain.indexOf('\r', startingindex);
					URL=MailContain.substring(startingindex, Endingindex);
					return URL;
				}
				else
				{
					URL=this.ReadMail(Username, Password, ReadCode);
				}
			
			}else
			{
				
				if(MailContain.contains("https")){
						int startindex = MailContain.indexOf("https");
						int EndingIndex = MailContain.indexOf(" ",startindex);
						URL = MailContain.substring(startindex,EndingIndex);
						return URL;
						//System.out.println(URL);
						//report.addInLine("GetActivationURL", "Pass", driver);
					}
			else 
				{
				URL=this.ReadMail(Username, Password, ReadCode);
				}
			}
		} catch (Exception e) {
			//report.addInLine("GetActivationURL", "fail", driver);
			e.printStackTrace();
		}
		
			return URL;
		
		}
	
	public void CreateExcel(String FileLoc,String Email,String password) throws IOException, RowsExceededException, WriteException {
		try {
			wwb = Workbook.createWorkbook(new File(FileLoc));
			wws = wwb.createSheet("Email", 0);
			wf = new WritableFont(WritableFont.TIMES, 16);
			wcf = new WritableCellFormat(wf);
			wcf.setBackground(Colour.YELLOW);
			l1 = new Label(0, 0, "Email", wcf); // giving Email
			wws.addCell(l1);
			l1 = new Label(1, 0, "Password", wcf); // giving password
			wws.addCell(l1);
			int Rows = wws.getRows();
			l1 = new Label(0, Rows, Email); // giving Email
			wws.addCell(l1);
			l1 = new Label(1, Rows, password); // giving password
			wws.addCell(l1);	
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
}
	
	public void implicitWaitDriver(int sec)
	{
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}
	
	public WebDriver switchtolastWindow(WebDriver driver1,String Desc) throws RowsExceededException, WriteException, IOException
	{
		try {
			driver = driver1;
			Set<String> Win = driver.getWindowHandles();
			for(String S : Win)
			{
				driver.switchTo().window(S);
			}
			report.addInLine(Desc, "pass", driver);
		} catch (Exception e) {
			
			report.addInLine(Desc, "Fail", driver);
			e.printStackTrace();
		}
		return driver;
	}
	
	public WebDriver SwitchToParentWindow(WebDriver driver1,String Parent)
	{
		driver = driver1;
		driver.switchTo().window(Parent);
		return driver;
		
	}
	
	
	
}
