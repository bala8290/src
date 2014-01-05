package project_salesforce;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String StarterPath="/testSuit/Starter/Starter.xls";
		String SheetName="Starter";
		try {
			excelMethods exm= new excelMethods();
			int rowcount=exm.ReturnRowCount(StarterPath, SheetName);
			System.out.println(rowcount);
			for(int i=1;i<rowcount;i++)
			{
				if(exm.readDataFromCell(StarterPath, SheetName, 3,i).equals("yes"))
				{
					String Browsername=exm.readDataFromCell(StarterPath, SheetName, 2, i);
					String Testcasename=exm.readDataFromCell(StarterPath, SheetName, 1, i);
					String spliter =Testcasename.substring(0,Testcasename.indexOf('_'));
					System.out.println(spliter);
					switch(spliter)
					{
					case "TC1": new TC1SignUp(Testcasename,Browsername); 
								Thread.sleep(300000);
								break;
					case "TC2": new TC2Login(Testcasename,Browsername); break;
					case "TC3": new TC3CreateCampaign(Testcasename,Browsername); break;
					case "TC4":  new TC4EditCampaignView(Testcasename,Browsername);break;
					case "TC5":  new TC5DeleteCampaignView(Testcasename,Browsername); break;
					case "TC6": new TC6CreateCampaign(Testcasename,Browsername); break;
					case "TC7": new TC7EditCampaign(Testcasename,Browsername); break;
					case "TC8":  new TC8DeleteCampaign(Testcasename,Browsername); break;
					case "TC9": new TC9CloneCampaign(Testcasename,Browsername); break;
					case "TC10": new TC10ForgotPassword(Testcasename,Browsername); break;
					case "TC11": new TC11CancelCampaignView(Testcasename,Browsername); break;
					case "SC1":System.out.println("Scenario 1");
								new Scenario1(Testcasename,Browsername); 
							    break;
					case "SC2":	System.out.println("Scenario 2");
								new Scenario2(Testcasename,Browsername);
								 break;
						
							
					}
					//System.out.println(Testcasename+" Will be executed on Browser "+Browsername);
				}
			}
		} catch (BiffException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
