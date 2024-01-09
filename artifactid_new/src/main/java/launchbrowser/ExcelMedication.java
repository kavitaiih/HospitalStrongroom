package launchbrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMedication {
    String xlFilePath;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    HashMap<String, Integer> colNums = null;

    public ExcelMedication(String filePath) {
        this.xlFilePath = filePath;
        try {
            FileInputStream fis = new FileInputStream(new File(this.xlFilePath));
            System.out.println("File Input Stream Created successfully..");
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSheet(String sheetName) {
        sheet = wb.getSheet(sheetName);
        if (sheet != null) {
            populateColumnNums();
        } else {
            System.out.println("Sheet is null. Please check if the sheet name is correct.");
        }
    }

    private void populateColumnNums() {
        colNums = new HashMap<>();
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

    public int getColNumber(String colName) {
        return colNums.get(colName);
    }

    public String getCellData(int rowNum, String colName) {
        int colNum = getColNumber(colName);
        return getCellData1(rowNum, colNum);
    }

    public String getCellData1(int rowNum, int colNum) {
        String ret = "";
        try {
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            if (cell != null) {
                if (cell.getCellType() == CellType.STRING) {
                    ret = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    ret = String.valueOf(cell.getNumericCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void readSheetData() {
        Iterator<Row> rows = sheet.iterator();

        while (rows.hasNext()) {
            Row currRow = rows.next();
            Iterator<Cell> cells = currRow.cellIterator();

            while (cells.hasNext()) {
                Cell currCell = cells.next();
                CellType ctype = currCell.getCellType();

                String value = "";
                if (ctype == CellType.STRING) {
                    value = currCell.getStringCellValue();
                } else if (ctype == CellType.NUMERIC) {
                    value = String.valueOf(currCell.getNumericCellValue());
                }

                System.out.println("Value for cell: " + value);

                // Assuming here you can perform the transaction for each medication
                // Modify this part based on your specific transaction logic
                performTransaction(value);
            }
        }
    }

    private void performTransaction(String medicationName) {
        // Your transaction logic here
        // Use the medicationName variable to perform the transaction for each medication
        System.out.println("Performing transaction for Medication: " + medicationName);
        // Add your transaction code here...
    }

    public static void main(String[] args) {
        // Provide the absolute path of your Excel file
        String filePath = "/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx";

        // Create an instance of ExcelMedication class with the file path
        ExcelMedication xl = new ExcelMedication(filePath);

        // Set the sheet name (assuming it's "MedicationSheet")
        xl.setSheet("MediData");

        // Read and print data from the Excel file if the sheet is not null
        if (xl.sheet != null) {
            xl.readSheetData();
        }
    }
}