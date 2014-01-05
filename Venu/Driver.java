package Venu;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




import org.openqa.selenium.*;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		moveTheFile();
		
		String driverPath = "C:/SalesForce/Execution/Driver.xls";
		int driverSheet = 0;
		
		try {
            Workbook wb = Workbook.getWorkbook(new File(driverPath));
            Sheet sheet = wb.getSheet(driverSheet);
                int driverRows = sheet.getRows();
                int scCount = 0;
                
                
                
                
                
                 String SNo, scenario, brow, exec;
                   for(int row = 0;row < driverRows;row++) {
                   		  SNo = sheet.getCell(0, row).getContents();
                   		scenario = sheet.getCell(1, row).getContents();
                   		brow = sheet.getCell(2, row).getContents();
                   		exec = sheet.getCell(3, row).getContents();
                       if(exec.equalsIgnoreCase("YES")){
                    	   
                    	   scCount = scCount+1;
                   	   switch (brow) {
                       case "Firefox":	
                    	   				DesiredCapabilities capabilities = new DesiredCapabilities();
                       					capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
                       					capabilities.setPlatform(Platform.WINDOWS);
                       					WebDriver d = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), capabilities);
//WebDriver d	= new FirefoxDriver();
                    	   				d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                       					readExcelSheet(d,driverPath,row,scCount);
                    	   				break;
                       case "Chrome":   
                    	   DesiredCapabilities capabilities1 = new DesiredCapabilities();
          					capabilities1.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
          					capabilities1.setPlatform(Platform.WINDOWS);
          					WebDriver driver1 = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), capabilities1);
          					readExcelSheet(driver1,driverPath,row,scCount);
                                               break;
                      
                 	  default:            break;
          }  	  
                       	  
                       	
                       }	  
                       	  
                     
                       }
                 } catch(Exception ioe) {
                      ioe.printStackTrace();
                 }
    archiveFile();
	}

	//********************************************************************************************************
	
	
	public static void writeResult(String strPath,String strTS ,String strScName) throws BiffException, IOException, RowsExceededException, WriteException{
		//Initiating the instance of the file Workbook and Writable Workbook
			
				 Workbook wb=Workbook.getWorkbook(new File(strPath));
				 WritableWorkbook workbook = Workbook.createWorkbook(new File(strPath),wb);   
		// Defining the fonts and formats
				   WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
				   WritableFont cellFont1 = new WritableFont(WritableFont.TIMES, 12);
		           WritableCellFormat cellformat=new WritableCellFormat(cellFont);
		          
		           WritableCellFormat cellformat1=new WritableCellFormat(cellFont);
		           cellformat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                   jxl.format.Colour.BLACK);
		           cellformat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                   jxl.format.Colour.BLACK);
		          
		           WritableFont cellFont2 = new WritableFont(WritableFont.TIMES, 12);
		           cellFont2.setColour(Colour.GREEN);		   
		           	WritableCellFormat cellFormat2 = new WritableCellFormat(cellFont2);
		           	cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                    jxl.format.Colour.BLACK);
		           	WritableFont cellFont3 = new WritableFont(WritableFont.TIMES, 12);
		            cellFont3.setColour(Colour.RED);		   
		            	WritableCellFormat cellFormat3 = new WritableCellFormat(cellFont3);
		            	cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                        jxl.format.Colour.BLACK);
		            	//Select the sheet number of the Workbook      
		            	WritableSheet sheet= workbook.getSheet(0);
				 int rows=0;
				 rows=sheet.getRows();
				 //System.out.println(rows);
				 
				 Label label = new Label(1,rows,strTS,cellformat);
				 Label label1 = new Label(2,rows,strScName,cellformat);
				 Label label2 = new Label(3,rows,"",cellformat);
				 Label label3 = new Label(4,rows,"",cellformat);
				 Label label4 = new Label(5,rows,"",cellformat);
				 
				 sheet.addCell(label);
				 sheet.addCell(label1);
				 sheet.addCell(label2);
				 sheet.addCell(label3);
				 sheet.addCell(label4);
				 workbook.write();
				 workbook.close();
			}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void moveTheFile () {
        try {
        	
            File ExeDir = new File("C:\\SalesForce\\Execution");
            if(!ExeDir.exists()){
            	ExeDir.mkdir();
            }
            File SnapDir = new File("C:\\SalesForce\\Execution\\Snapshots");
            if(!SnapDir.exists()){
            	SnapDir.mkdir();
            }
            
            
            File srcTD = new File("C:\\SalesForce\\Driver.xls");
            File srcTR = new File("C:\\SalesForce\\TestResult.xls");
            File dstTD = new File("C:\\SalesForce\\Execution\\Driver.xls");
            if(!dstTD.exists()){
            	FileUtils.copyFileToDirectory(srcTD, ExeDir);
            }
            File dstTR = new File("C:\\SalesForce\\Execution\\TestResult.xls");
            if(!dstTR.exists()){
            	FileUtils.copyFileToDirectory(srcTR, ExeDir);
            }
            } catch(Exception e) {}
    }
	
// Archiving the Files and Folders to Backup folder with Date and Timestamp
    public static void archiveFile () {
    	try {
        	File srcDir = new File("C:\\SalesForce\\Execution");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
    		Date date = new Date();
    	    String[] dt=dateFormat.format(date).split("-");
    		File dtbk=new File("C:\\SalesForce\\Archived\\back up("+dt[0]+"-"+dt[1]+"-"+dt[2]+"_h"+dt[3]+"-m"+dt[4]+"-s"+dt[5]+")");
    		FileUtils.copyDirectory(srcDir,dtbk);
        	} catch(Exception e) {}
    }
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void writeResult(String strPath,String strTCNum ,String strStep,String strResult,int TS) throws BiffException, IOException, RowsExceededException, WriteException{
		//Initiating the instance of the file Workbook and Writable Workbook
			
				 Workbook wb=Workbook.getWorkbook(new File(strPath));
				 WritableWorkbook workbook = Workbook.createWorkbook(new File(strPath),wb);   
		// Defining the fonts and formats
				   WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
				   WritableFont cellFont1 = new WritableFont(WritableFont.TIMES, 12);
		           WritableCellFormat cellformat=new WritableCellFormat(cellFont);
		          
		           WritableCellFormat cellformat1=new WritableCellFormat(cellFont);
		           cellformat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                   jxl.format.Colour.BLACK);
		           cellformat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                   jxl.format.Colour.BLACK);
		          
		           WritableFont cellFont2 = new WritableFont(WritableFont.TIMES, 12);
		           cellFont2.setColour(Colour.GREEN);		   
		           	WritableCellFormat cellFormat2 = new WritableCellFormat(cellFont2);
		           	cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                    jxl.format.Colour.BLACK);
		           	WritableFont cellFont3 = new WritableFont(WritableFont.TIMES, 12);
		            cellFont3.setColour(Colour.RED);		   
		            	WritableCellFormat cellFormat3 = new WritableCellFormat(cellFont3);
		            	cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,
		                        jxl.format.Colour.BLACK);
		            	//Select the sheet number of the Workbook      
		            	WritableSheet sheet= workbook.getSheet(0);
				 int rows=0;
				 rows=sheet.getRows();
				 //System.out.println(rows);
				 Label label1 = new Label(1,rows,"",cellformat);
				 Label label2 = new Label(2,rows,strTCNum,cellformat);
				 Label label3 = new Label(3,rows,strStep,cellformat);
				 Label label4;
				 if(strResult.equalsIgnoreCase("Pass")){
					 label4 = new Label(4,rows,strResult,cellFormat2);
				 }
				 else{
					 label4 = new Label(4,rows,strResult,cellFormat3);
				 }
				 //Label label5 = new Label(5,rows,"",cellformat);
				 Formula label5 = new Formula(5, rows,"HYPERLINK(\"C:\\SNAPSHOTS\\SnapShot"+TS+"_"+strTCNum+".jpg\",\"View Snap\")",cellformat);
				 sheet.addCell(label1);
				 sheet.addCell(label2);
				 sheet.addCell(label3);
				 sheet.addCell(label4);
				 sheet.addCell(label5);
				 workbook.write();
				 workbook.close();
			}
	
	
	
	
	 public static void readExcelSheet(WebDriver wda, String destFile, int sheetNum, int SN){ 
	      
         
	        try {
	             Workbook wb = Workbook.getWorkbook(new File(destFile));
	             Sheet sheet = wb.getSheet(sheetNum);
	                 int rows = sheet.getRows();
	                  String met, prop, data, stepNo, stepDesc;
	                    for(int row = 0;row < rows;row++) {
	                    		  stepNo = sheet.getCell(0, row).getContents();
	                    		  stepDesc = sheet.getCell(1, row).getContents();
	                              met = sheet.getCell(2, row).getContents();
	                        	  prop = sheet.getCell(3, row).getContents();
	                        	  data = sheet.getCell(4, row).getContents();
	                              switch (met) {
	                                  case "launch_APP":      TestEngine.launch_App(wda, prop,stepNo,stepDesc,SN);
	                                                          break;
	                                  case "enter_ID":        TestEngine.enter_ID(wda,prop,data,stepNo,stepDesc,SN);
	                                                          break;
	                                  case "select_ID_Index": TestEngine.select_ID_Index(wda,prop,data,stepNo,stepDesc,SN);
	                                  						  break;
	                                  case "Click_ID":        TestEngine.Click_ID(wda,prop,stepNo,stepDesc,SN);
	                     		                              break;
	                                  case "Click_Name":        TestEngine.Click_Name(wda,prop,stepNo,stepDesc,SN);
 		                              						  break;
	                                  case "Click_Xpath":        TestEngine.Click_Xpath(wda,prop,stepNo,stepDesc,SN);
               						  break;
	                                  case "Verify_linkText":        TestEngine.Verify_linkText(wda,prop,stepNo,stepDesc,SN);
 		                              						  break;
	                                  case "Click_Link":        TestEngine.Click_Link(wda,prop,stepNo,stepDesc,SN);
 		                              						  break;
	                                  case "select_ID_Value": TestEngine.select_ID_Value(wda,prop,data,stepNo,stepDesc,SN);
	 		                          						   break;
	                                  case "Switch_window": TestEngine.Switch_window(wda,stepNo,stepDesc,SN);
              						   break;
	                                  case "Accept_Alert": TestEngine.Accept_Alert(wda,stepNo,stepDesc,SN);
             						   break;
	                                  case "verify_mail_link": TestEngine.verify_mail_link(wda,prop,data,stepNo,stepDesc,SN);
                 											break;
	                                  case "TS": String strTS = "TS"+SN;
	                                	  writeResult("C:/SalesForce/Execution/TestResult.xls",strTS ,prop) ;
										break;
	                            	  default:            break;
	                            	  
	                            	  
	                            	  
	                          }
	                        }
	                  } catch(Exception ioe) {
	                       ioe.printStackTrace();
	                  }
	             
	          }
	

	 public void screenshot(WebDriver wd,int TS,String TestStepID) throws BiffException, IOException
	      {
	      
	       File scrFile2 = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
	          FileUtils.copyFile(scrFile2, new File("C:\\SalesForce\\Execution\\Snapshots\\SnapShot"+TS+"_"+TestStepID+".jpg"));
	       }
	 
	 
}
