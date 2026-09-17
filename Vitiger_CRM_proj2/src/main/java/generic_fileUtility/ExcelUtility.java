package generic_fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	FileInputStream fis;

	public String readDataFromExcel(String sheetName, int rownum, int colnum)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rownum).getCell(colnum).getStringCellValue();
		return data;
	}

	public List<String> readMultipleDataFromExcel(String sheetName, int colnum)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		List<String> list = new LinkedList<String>();
		for (int i = 0; i <= lastRow; i++) {
			String values = sh.getRow(i).getCell(colnum).toString();
			list.add(values);
		}
		return list;
	}

	public Object[][] readCompleteExcel(String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		//System.out.println(lastRow);
		int lastCol = sh.getRow(0).getLastCellNum();
		//System.out.println(lastCol);
		Object[][] obj = new Object[lastRow][lastCol];
		for (int i = 1; i <= lastRow; i++) {
			for (int j = 0; j < lastCol; j++) {
				obj[i-1][j] = sh.getRow(i).getCell(j).toString();
				
			}
		}
		wb.close();
		return obj;
	}

	public void writeDataToExistingSheetNewCell(String sheetName, int rownum, int colnum, String value)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rownum).createCell(colnum).setCellValue(value);
		FileOutputStream fos = new FileOutputStream("./test-data/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}

	public void writeDataToNewSheet(String sheetName, int rownum, int colnum, String value)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).createRow(rownum).createCell(colnum).setCellValue(value);
		FileOutputStream fos = new FileOutputStream("./test-data/TestScriptData.xlxs");
		wb.write(fos);
		wb.close();
	}

	public void modifyDataToExistingCell(String Sheetname, int row, int cell, String value) throws IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(Sheetname).getRow(row).getCell(cell).setCellValue(value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/Ninza.xlsx");
		wb.write(fos);
		wb.close();

	}

	public List<String> readDataBasedOnACondition(String sheetName, int colnum, String expectedData)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./test-data/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);

		int lastRow = sh.getLastRowNum();
		int lastcol = sh.getRow(0).getLastCellNum();
		List<String> list = new LinkedList<String>();
		for (int i = 0; i <= lastRow; i++) {
			String values = sh.getRow(i).getCell(colnum).toString();
			if (expectedData.equalsIgnoreCase(values)) {
				for (int j = 0; j <= lastcol; j++) {
					String value = sh.getRow(i).getCell(j).toString();
					list.add(value);
				}
			}
		}
		return list;
	}
}
