package generic_dataproviderUtility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

import generic_fileUtility.ExcelUtility;

public class DataProviderUtility {
	
	
	ExcelUtility excel = new ExcelUtility();

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		
		return excel.readCompleteExcel("Sheet1");
		

	}
}
