package project_salesforce;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class testCase1 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void main(String[] args) throws RowsExceededException,
			WriteException, IOException, ParserConfigurationException,
			TransformerException {
		// TODO Auto-generated method stub
		AppSpecific App = new AppSpecific("SignUp","firefox");
		App.signup();
		App.finishingTouch();

	}

}
