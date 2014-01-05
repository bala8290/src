package project_salesforce;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SC1 {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		Select Listbox;
		WebDriver driver = new FirefoxDriver();
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
			
				// clicking on campaign
				driver.findElement(By.linkText("Campaigns")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//Select last but one value
				Listbox = new Select(driver.findElement(By.name("fcf")));
				System.out.println(Listbox.getOptions().size());
				Listbox.selectByIndex(Listbox.getOptions().size()-2);
				driver.findElement(By.name("go")).click();
				// print all campaign names.
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				table=driver.findElements(By.className("x-grid3-row-table"));
				// get number of campaign present
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
					//System.out.println(TextVal.length());
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
					table=driver.findElements(By.className("x-grid3-row-table"));	
					Cell=table.get(table.size()-1).findElements(By.tagName("td"));
					String lastCampaignName=Cell.get(3).getText();
					driver.findElement(By.linkText(lastCampaignName.substring(0, 1).toUpperCase())).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
					
					// clicking on Opportunities tab
					driver.findElement(By.linkText("Opportunities")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fcf")));
					//find largest value in opportunities 
					Listbox = new Select(driver.findElement(By.name("fcf")));
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
					
					Listbox = new Select(driver.findElement(By.name("fcf")));
					for(int i=0;i<totSize;i++)
					{	String localString=Listbox.getOptions().get(i).getText();
						int local =localString.length();
						if(local==length)
						{
							Listbox.selectByVisibleText(localString);
							System.out.println(Listbox.getFirstSelectedOption().getText());
							if(localString.equals(firstselectedopt))
								driver.findElement(By.name("go")).click();
							break;
						}
					}
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name("newOpp")));					
					
					// enter opp
					driver.findElement(By.name("newOpp")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					// entering mandatory field
					driver.findElement(By.id("opp3")).sendKeys("SamuraiX");
					driver.findElement(By.id("opp9")).sendKeys("02/08/2013");
					Listbox=new Select(driver.findElement(By.id("opp11")));
					Listbox.selectByValue("Qualification");
					driver.findElement(By.name("save")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					
					
					//Click report
					driver.findElement(By.linkText("Reports")).click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					// mouse over 
					Actions builder = new Actions(driver);
					builder.moveToElement(driver.findElement(By.className("nodeText")));
					
				driver.quit();
				//driver.findElement(By.linkText("Edit")).click();
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				//Actions builder = new Actions(driver);
				//builder.click(driver.findElement(By.name("delID"))).build().perform();
				//builder.moveToElement(driver.findElement(By.name("delID"))).build().perform();
				//builder.sendKeys(Keys.ENTER).build().perform();
				//Thread.sleep(1000);
				//builder.sendKeys(Keys.ENTER).build().perform();
				//builder.keyDown(Keys.ENTER).build().perform();
				//builder.keyUp(Keys.ENTER).build().perform();
				//driver.findElement(By.name("delID")).click();
				//WebDriverWait wait = new WebDriverWait(driver, 5);
				//wait.until(ExpectedConditions.alertIsPresent());
				//driver.switchTo().alert().accept();
			} catch(Exception e1)
		    {
				driver.quit();
		    	e1.printStackTrace();
		    }
	}

}
