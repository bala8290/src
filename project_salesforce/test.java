package project_salesforce;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class test {

	/**
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException{
		
		generalMethods met = new generalMethods();
		WebDriver driver = met.LaunchBrowser("firefox");
		driver.get("https://www.developerforce.com/events/regular/registration.php");
		
		met.findElementby("id", "first_name", "Sending value by Id").sendKeys("bala");
		met.findElementby("name","user[last_name]", "Send by name").sendKeys("subra");
		met.findElementby("class","textField","Send by class").sendKeys("Rena");
		met.findElementby("css","//image[type='input']","Send value by CSS path").sendKeys("XXX");
		met.findElementby("xpath","//input[@id='username']", "xpath ").sendKeys("bala@cyon.com");
		met.DropdownSelect(driver.findElement(By.id("job_role")),"VT", "Developer", "Selecting VT");
		met.DropdownSelect(driver.findElement(By.id("country")),"VAL","IN", "Select by Value");
		met.DropdownSelect(driver.findElement(By.id("job_role")), "INDEX", "2", "Select by index");
		
		WebElement element = null;

		try{ 
		
		
		driver.switchTo().frame(driver.findElement(By.id("desktop")));
		System.out.println("Switching to Desktop Success");

		driver.switchTo().frame(driver.findElement(By.id("app_iframe")));
		System.out.println("Switching to app_iframe");

		driver.findElement(By.id("inputUsername")).click();//this is as it was recorded by the Selenium IDE
		System.out.println("Clicking On username");
		driver.findElement(By.id("inputUsername")).clear();
		System.out.println("Clicking On username");
		driver.findElement(By.id("inputUsername")).sendKeys("admin");

		driver.findElement(By.id("inputPassword")).click();
		System.out.println("inputPassword");
		driver.findElement(By.id("inputPassword")).clear();

		driver.findElement(By.id("inputPassword")).sendKeys("123");

		driver.findElement(By.id("buttonOK")).click();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//String Valuivere = "DM Campaign to Top Customers - Nov 12-23, 2001";
		//System.out.println(Value.substring(0, 1).toUpperCase());
		//Value="https://ap1.salesforce.com/003?fcf=00B90000006PuwI";
		//System.out.println(Value.substring(Value.indexOf("=")+1));
		//nos=Integer.parseInt(Value.substring(1).replace(",", ""));
		//System.out.println(""+nos);
		//String tex="25";
		  //int nos=Integer.parseInt(tex);
		  //System.out.println(nos);
	}
}
		// TODO Auto-generated method stub
		//WebDriver driver;
		/*System.setProperty("webdriver.chrome.driver",
				"/test/lib/chromedriver.exe");
		driver = new ChromeDriver();
	    try {
			driver.get("http://developer.force.com/");
			driver.manage().window().maximize();
			driver.findElement(By.partialLinkText("DE LOGIN")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.id("username")).sendKeys("testmylife829686130@gmail.com");
			driver.findElement(By.id("password")).sendKeys("iloveCobra8290");
			driver.findElement(By.id("Login")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Campaigns")).click();
			driver.findElement(By.linkText("Edit")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions builder = new Actions(driver);
			builder.click(driver.findElement(By.name("delID"))).build().perform();
			//builder.moveToElement(driver.findElement(By.name("delID"))).build().perform();
			//builder.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			builder.sendKeys(Keys.ENTER).build().perform();
			//builder.keyDown(Keys.ENTER).build().perform();
			//builder.keyUp(Keys.ENTER).build().perform();
			//driver.findElement(By.name("delID")).click();
			//WebDriverWait wait = new WebDriverWait(driver, 5);
			//wait.until(ExpectedConditions.alertIsPresent());
			//driver.switchTo().alert().accept();
		} catch(Exception e1)
	    {
	    	e1.printStackTrace();
	    }
	  
	}

}
*/
