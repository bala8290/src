package project_salesforce;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

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
import jxl.write.biff.RowsExceededException;

public class reportMethod {

	WritableWorkbook wwb;
	Workbook wb;
	Sheet sh;
	WritableSheet wws;
	WritableFont wf;
	WritableCellFormat wcf;
	Label l1;
	XMLWriteClass XML;
	String ExcelPath, Screenshotpath;

	public reportMethod(String FileLoc, XMLWriteClass xml) throws IOException,
			WriteException {
		XML = xml;
		ExcelPath = FileLoc;
		wwb = Workbook.createWorkbook(new File(FileLoc + "Report.xls"));
		createSSFolder(ExcelPath); // create screenshot folder
		wws = wwb.createSheet("Report", 0);
		createHeader();

	}

	public void createHeader() throws WriteException {
		wf = new WritableFont(WritableFont.TIMES, 16);
		wcf = new WritableCellFormat(wf);
		wcf.setBackground(Colour.YELLOW);
		l1 = new Label(0, 0, "Step_ID", wcf); // giving step id
		wws.addCell(l1);
		l1 = new Label(1, 0, "Description", wcf); // Heading description
		wws.addCell(l1);
		l1 = new Label(2, 0, "Status", wcf); // giving Status
		wws.addCell(l1);
		l1 = new Label(3, 0, "SnapShot", wcf); // giving step id
		wws.addCell(l1);
	}

	public void addInLine(String Descr, String Status, WebDriver Driver)
			throws RowsExceededException, WriteException, IOException {
		wcf = new WritableCellFormat();
		int Rows = wws.getRows();

		l1 = new Label(0, Rows, "" + Rows); // giving step id
		wws.addCell(l1);
		l1 = new Label(1, Rows, Descr); // Heading description
		wws.addCell(l1);
		if (Status.equalsIgnoreCase("pass"))
			wcf.setBackground(Colour.GREEN);
		else
			wcf.setBackground(Colour.RED);

		l1 = new Label(2, Rows, Status, wcf); // giving Status
		wws.addCell(l1);

		// String str="HYPERLINK(\"C://MyFirstCreation//Screenshot//" + Rows
		// +".jpg\",\"View Snap\")";
		XML.createNode("TestStep" + Rows, "TestName", Descr, "Status", Status);
		Descr=Descr.substring(0,10).replace(" ","_");
		String sSFileLoc = Screenshotpath + "/" + Rows + Descr + ".bmp";
		// System.out.println(sSFileLoc);
		String str = "HYPERLINK(\"" + sSFileLoc + "\",\"View Snap\")";
		// giving step id
		wws.addCell(new Formula(3, Rows, str));
		TakeScreenShotSaveToFile(Driver, sSFileLoc);
	}

	public void writeExcel() throws IOException, WriteException {
		wwb.write();
		wwb.close();

	}

	public String readDataFromCell(String SourceFile, String SheetName,
			int Col, int Row) throws BiffException, IOException {
		wb = Workbook.getWorkbook(new File(SourceFile));
		sh = wb.getSheet(SheetName);
		int Trows = sh.getRows();
		int TCol = sh.getColumns();
		if ((Trows >= Row) & (TCol >= Col)) {
			System.out.println("reading data from excel");
			return (sh.getCell(Col, Row).getContents());

		} else {
			System.out
					.println("Read operation failed column or Row size mentioned is more compare to specified sheet");
			return "";
		}

	}

	public int ReturnRowCount(String SourceFile, String sheetName)
			throws BiffException, IOException {
		wb = Workbook.getWorkbook(new File(SourceFile));
		sh = wb.getSheet(sheetName);
		int Trows = sh.getRows();
		return Trows;

	}

	public void TakeScreenShotSaveToFile(WebDriver driver, String FileName)
			throws IOException {
		try {
			File srcFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(FileName));
		} catch (WebDriverException e) {

			e.printStackTrace();
		}
	}

	public void createSSFolder(String folderPath) {

		try {

			Screenshotpath = folderPath + "Screenshot";

			File dir = new File(Screenshotpath);
			dir.mkdir();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
