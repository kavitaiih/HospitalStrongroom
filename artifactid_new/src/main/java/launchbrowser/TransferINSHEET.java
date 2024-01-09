package launchbrowser;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferINSHEET
{

	static WebDriver driver = new ChromeDriver();
	static int searchCount = 1;
	static int searchCount1 = -1;
	static int searchCoun2 = 1;

	static int rowIndex = 1;

	public static List<String> drugNames;
	public static List<String> Innumbers;
	public static List<String> location;

	public static void topmethod() throws InterruptedException {

		// Maximize the Chrome window
		driver.manage().window().maximize();

		// Set implicit wait to 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Initialize WebDriverWait with a longer duration	

		// Open the webpage
		driver.get("https://hospital-staging.strongroom.ai/login");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		// Entering Location
		WebElement locationInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Location']")));
		locationInput.sendKeys("Orange Hospital");
		Thread.sleep(2000);
		
		WebElement clickonLOCATION = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='drug-search-result']")));
		clickonLOCATION.click();
		Thread.sleep(2000);

		
		WebElement clickElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Username/email']")));
		clickElement.click();
		clickElement.sendKeys("qa@strongroom.ai");
		Thread.sleep(2000);


		// Entering Password
		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput.sendKeys("stew-dazzling-washtub!");

		// Clicking on Login Button
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		loginButton.click();
		Thread.sleep(2000);

		// Selecting location on the second page
		WebElement selectLocation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		selectLocation.click();

		// Choosing a location from the dropdown
		WebElement dropdownOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		dropdownOption.click();
		Thread.sleep(2000);
		
		WebElement CLICKonselectlocationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		CLICKonselectlocationbtn.click();
		Thread.sleep(2000);

		// Explicit wait for the "blue-button" with a 90-second duration
		//WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(60));

	//	try {
		//	Thread.sleep(5000);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}

		//WebElement Stockbtn = extendedWait
		//		.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));

		//Stockbtn.click();

	}

	public void run() throws InterruptedException {

		List<String> drugNames = readDrugNamesFromExcel("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx");
		// List<String> location = readlocationFromExcel("output.xlsx");

		for (; searchCount < drugNames.size();) {
			secondmthod();
			searchCount++;
			searchCoun2++;

		}

	}

	public static void main(String[] args) throws InterruptedException {
	    // TODO Auto-generated method stub

	    // Close the WebDriver
	    // driver.quit();
	    topmethod();

	    TransferINSHEET myObject = new TransferINSHEET();
	    myObject.run();

	    driver.quit();
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void secondmthod() throws InterruptedException {

		List<String> DrugName = readDrugNamesFromExcel("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx");

		List<String> Quantity = readInnumbersFromExcel("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx");
		List<String> Location = readlocationFromExcel("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx");

		Thread.sleep(4000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		WebElement Stock = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();
		Thread.sleep(2000);

		WebElement Stocktake = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
		Stocktake.click();
		Thread.sleep(2000);

		WebElement Medication = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));

		 drugNames = readDrugNamesFromExcel("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx");

		String drugname = drugNames.get(searchCount);

		Medication.sendKeys(drugname);

		Thread.sleep(2000);

		WebElement Displayinstock = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//p[@class='active-select-filter select-filter-item']")));
		Displayinstock.click();

		WebElement searching = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
		searching.click();

		WebElement Displayimprest = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		Displayimprest.click();

		Thread.sleep(3000);

		WebElement Expected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//td)[4]")));

		String OpenB = Expected.getText().trim();
		System.out.println("(Expected): " + OpenB);

		// Extract numeric part from the string (remove non-numeric characters)
		String numericPart = OpenB.replaceAll("[^0-9]", "");

		// Parse the numeric part into an integer
		int valueToCompare = Integer.parseInt(numericPart);

		System.out.println("(Stock): " + valueToCompare);

		Thread.sleep(3000);

		// Clicking on the Transfer in
		WebElement transferIn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
		transferIn.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']")));

		// Explicit wait for the location input field
		WebElement enterLocation = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));
		enterLocation.click();

		// Send keys for all locations
		for (String loc : location) {
			enterLocation.sendKeys(loc);

			// Add a small delay (adjust as needed)
			Thread.sleep(1000);

			// You may clear the input field after sending keys, depending on your use case
			enterLocation.clear();
		}

		/*
		 * // Send keys only for the first location if (!location.isEmpty()) {
		 * enterLocation.sendKeys(location.get(0)); }
		 */

		Thread.sleep(3000);
		// Locate the location options
		List<WebElement> dropdownlocation = driver.findElements(By.xpath("//li[contains(@class, 'p-dropdown-item')]"));

		// Iterate through the options to find a match with the drop down location
		for (WebElement option : dropdownlocation) {

			String loc = location.get(searchCoun2);

			if (option.getText().trim().equals(loc)) {
				// Found a match, click on the option
				Thread.sleep(3000);

				searchCoun2++;

				option.click();
				break;
			}
		}

		// Assuming you have navigated to the page and located the textarea element
		WebElement noteTextArea = driver.findElement(By.xpath("//textarea[@name='notes' and @id='note-modal']"));

		// Write "Transferrin" in the note box
		noteTextArea.sendKeys("Transferr in");

		// Click the Imprest/Emergency Meds/Ward Stock button
		WebElement button = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		button.click();

		WebElement medicationInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
		medicationInput.click();

		String drugname1 = drugNames.get(searchCount);

		medicationInput.sendKeys(drugname1);

		Thread.sleep(3000);
		// Locate the dropdown options
		List<WebElement> dropdownOptions1 = driver.findElements(By.xpath("//li[contains(@class, 'p-dropdown-item')]"));

		// Iterate through the options to find a match with the entered drug name
		for (WebElement option : dropdownOptions1) {
			if (option.getText().trim().equals(drugname1)) {
				// Found a match, click on the option
				Thread.sleep(3000);
				option.click();
				break;
			}
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));

		// Click on the quantity field with the specified placeholder

		WebElement quantityInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter qty']")));
		quantityInput.click();

		// searchCount++;
		searchCount1++;

		// If this is the second name, perform the search
		// Locate the search field and enter the drug name

		String drugqty = Innumbers.get(searchCount1);

		quantityInput.sendKeys(drugqty);

		WebElement addButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addButton.click();

		Thread.sleep(3000);

//		column = 9
//		our_name = Soflax (AN) tablet
//		dynamic_name[1] = Soflax (ANC) tablet
//		dynamic_name[2] = Soflax (AN) tablet
//		initial_balance = Value on 9th Column & 2nd Row 

		// Get the text content of the element and print it

		// Assuming you want to print the content of the element with XPath
		WebElement elementWithText1 = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

		// Get the text content of the element and print it
		String add = elementWithText1.getText().trim();
		System.out.println("(Transfer): " + add);

		// Convert the text content to an integer
		int valueToCompare1 = Integer.parseInt(add);
		// Perform addition
		Integer sum = valueToCompare + valueToCompare1;
//
//		// Print the result
		System.out.println("Balance: " + sum);

		OpeningBalance("/home/user/Documents/artifactid (copy)/files/MedicationData.xlsx", "MediData", 7, 10, String.valueOf(valueToCompare),
				String.valueOf(sum));

		// OpeningBalance("/home/user/Documents/myExcelFile.xlsx",
		// "TableData",10,String.valueOf(sum));

		// Click on the button with the specified class
		WebElement completeButton = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		completeButton.click();

		// Check that the signature check modal pops up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']")));

		// Find the input field with the specified placeholder
		WebElement usernameInput1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));

		usernameInput1.click();

		// Clear the text from the input field
		usernameInput1.clear();

		// Writing text into the input field

		WebElement usernameInput11 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput11.sendKeys("valeshan.naidoo@strongroom.ai");

		// Clicking on the input field
		WebElement passwordInput1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='PIN/Password']")));
		passwordInput1.click();

		// Writing text into the input field
		passwordInput1.sendKeys("1111");

		// Clicking on the field
		WebElement greenButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		greenButton.click();

		// Wait for 5 seconds (5000 milliseconds)
		Thread.sleep(2000);

	}

	// Method to read drug names from the Excel file

	private static List<String> readDrugNamesFromExcel(String filePath) {
		List<String> drugNames = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {

			Sheet sheet = workbook.getSheet("MediData");

			for (Row row : sheet) {
				Cell cell = row.getCell(2); // Assuming drug namesare in the second column (index 1)

				if (cell != null) {

					drugNames.add(cell.getStringCellValue());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return drugNames;

	}

	public static void OpeningBalance(String filePath, String sheetName, int columnIndex7, int columnindex10,
			String beforestockcount, String sum) {
		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheet(sheetName);

			Row row = sheet.getRow(rowIndex);

			// Check if the row exists before trying to create a cell
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}

			// Create a cell only for the specified columnIndex
			processCell(rowIndex, columnIndex7, row, beforestockcount);
			processCell(rowIndex, columnindex10, row, sum);

			// Create a new temporary file
			File tempFile = new File(filePath + ".tmp");

			try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
				workbook.write(outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Rename the temporary file to the original file
			if (tempFile.renameTo(new File(filePath))) {
				System.out.println("File updated successfully.");
			} else {
				System.err.println("Error updating file.");
			}

			rowIndex++;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void processCell(int rowIndex, int columnIndex, Row row, String value) {
		Cell cell = row.createCell(columnIndex);
		cell.setCellValue(value);
	}

	// Method to qty from the Excel file (7) for all rows
	public static List<String> readInnumbersFromExcel(String filePath) {
		List<String> Innumbers = new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheet("MediData"); // Replace with your sheet name
			int cellIndexQuantities = 7; // Assuming quantities are in the seventh column (index 6)
			int rowIndex = 0; // Initialize row index counter
			for (Row row : sheet) {
				Cell cell = row.getCell(cellIndexQuantities);
				if (cell != null) {
					if (rowIndex > 0) {
						if (cell.getCellType() == CellType.STRING) {
							Innumbers.add(cell.getStringCellValue());
						} else if (cell.getCellType() == CellType.NUMERIC) {
							// Use a formatter if you want to format the numeric value as a string
							Innumbers.add(String.valueOf(cell.getNumericCellValue()));

							// System.out.println(Innumbers);
						}
					}
					rowIndex++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Innumbers;
	}

	// Method to qty from the Excel file (7) for all rows
	public static List<String> readlocationFromExcel(String filePath) {
		List<String> location = new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheet("MediData"); // Replace with your sheet name
			int cellIndexQuantities = 4; // Assuming quantities are in the seventh column (index 6)
			int rowIndex = 0; // Initialize row index counter
			for (Row row : sheet) {
				Cell cell = row.getCell(cellIndexQuantities);
				if (cell != null) {
					if (rowIndex > 0) {
						if (cell.getCellType() == CellType.STRING) {
							location.add(cell.getStringCellValue());
						} else if (cell.getCellType() == CellType.NUMERIC) {
							// Use a formatter if you want to format the numeric value as a string
							location.add(String.valueOf(cell.getNumericCellValue()));

							// System.out.println(Innumbers);
						}
					}
					rowIndex++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return location;
	}

}