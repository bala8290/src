package project_salesforce;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;


public class excelMethods {
	
	Workbook wb;
	Sheet sh;
	WritableSheet wws;
	WritableFont wf;
	WritableCellFormat wcf;
	
	
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

}
