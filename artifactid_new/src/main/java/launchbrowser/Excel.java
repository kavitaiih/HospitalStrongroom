package launchbrowser;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.CellType;

public class Excel {
	String xlFilePath;

	XSSFWorkbook wb;
	XSSFSheet sheet;
	HashMap<String, Integer> colNums = null;
	FileInputStream fis = null;

	public Excel(String filePath) {
		this.xlFilePath = filePath;
		try {
			fis = new FileInputStream(new File(this.xlFilePath));
			System.out.println("File Input Stream Created successfully..");
			wb = new XSSFWorkbook(fis);

		}

		catch (IOException e) {
			e.printStackTrace();

		}
	}

//Provide sheet name

	public void setSheet(String sheetName) {
		sheet = wb.getSheet(sheetName);
		populateColumnNums(); // Call populateColumnNums to initialize colNums
	}

//Populates the column and rows number

	public void populateColumnNums() {
		colNums = new HashMap<String, Integer>(); // Initialize the HashMap

		int colIndex = 0;
		Row row = sheet.getRow(0);

		if (row != null) {
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				String colName = cell.getStringCellValue();
				colNums.put(colName, colIndex);
				colIndex++;
			}
		}
	}

//get column name

	public int getColNumber(String ColName) {
		return colNums.get(ColName);

	}

//get cell data
	public String getCellData(int rowNum, String colName) {
		String ret = "";
		int colNum = getColNumber(colName);
		ret = getCellData(rowNum, colNum);
		return ret;

	}

//Another method for particular rows and columns data

	public String getCellData(int rowNum, int colNUm)

	{
		String ret = "";

		try {
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNUm);

			if (cell.getCellType() == CellType.STRING)

			{
				ret = cell.getStringCellValue();

			}
		}

		catch (Exception e)

		{
			e.printStackTrace();

		}

		return ret;

	}

	public void readSheetData() {
		// if (sheet != null) {
		java.util.Iterator<Row> rows = sheet.iterator();

		while (rows.hasNext()) {
			Row currRow = rows.next();

			Iterator<Cell> cells = currRow.cellIterator();

			while (cells.hasNext()) {
				Cell currCell = cells.next();
				CellType ctype = currCell.getCellType();

				String value = "";
				if (ctype == CellType.STRING)

				{
					value = currCell.getStringCellValue();

				}

				else if (ctype == CellType.NUMERIC) {

					value = "" + currCell.getDateCellValue();
				}

				System.out.println("Value for cell: " + value);

			}

		}

	}

	private void next() {
		// TODO Auto-generated method stub

	}

	public static void main(String rs[]) {

		// Provide the absolute path of your Excel file
		String filePath = "/home/user/Documents/artifactid (copy)/files/AutomationData.xlsx";

		// Print the absolute path to the console
		System.out.println("Absolute Path: " + new File(filePath).getAbsolutePath());
		System.out.println("Working Directory: " + System.getProperty("user.dir"));
		System.out.println("File path length: " + filePath.length());

		// Create an instance of Excel class with the file path
		Excel xl = new Excel(filePath);
		// xl.readSheetData();

		// Set the sheet name (assuming it's "Data")
		xl.setSheet("Data");

		// Read and print data from the Excel file if the sheet is not null
		if (xl.sheet != null) {
			xl.readSheetData();

		}
	}


public void close()
{
	try {
		
		if (fis!= null)
		{
			fis.close();
			wb.close();
			
		}
	}
	catch(Exception e)
	{
		
	}
}

}










