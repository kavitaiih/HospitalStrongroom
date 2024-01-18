package launchbrowser;

import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class StockZero {

	public static void main(String[] args) throws InterruptedException {

		String excelFilePath = "/home/user/Desktop/artifactid/files/LoginCred.xlsx";
		String sheetName = "TransferInimprest";

		try {
			// Sting for location,id,pass
			String[] loginData = readLoginDataFromExcel(excelFilePath, sheetName);

			System.out.println("URL: " + loginData[0]);
			System.out.println("Location: " + loginData[1]);
			System.out.println("Username: " + loginData[2]);
			System.out.println("Password: " + loginData[3]);
			System.out.println("-------------------------------");

			String loc = loginData[1];
			String UserN = loginData[2];
			String PassWD = loginData[3];

			// Read medication data into ArrayList
			ArrayList<String[]> ArrayMedication = readArrayMedicationFromExcel(excelFilePath, sheetName);

			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.get(loginData[0]);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

			WebElement locationInput = driver.findElement(By.xpath("//input[@placeholder='Location']"));
			locationInput.sendKeys(loc); // Location from Excel

			WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//p[@class='drug-search-result' and text()='" + loginData[1] + "']")));
			locationOption.click();

			WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
			usernameField.sendKeys(UserN); // Username from Excel

			WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
			passwordField.sendKeys(PassWD); // Password from Excel

			WebElement loginBtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
			loginBtn.click();

			// Continue with the rest of your test...
			WebElement dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
			dropdown.click();

			WebElement dropdown1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'Pharmacy')]")));
			dropdown1.click();

			Thread.sleep(2000);

			WebElement selectlocationbtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
			selectlocationbtn.click();

			Thread.sleep(2000);
			
			

			for (String[] medicationData : ArrayMedication)
				
				
				
			{
				// Assuming medication name is in the first column (index 0)
				String MedicationName = medicationData[0];

				// Assuming quantity is in the second column (index 1)
				String Quantity = medicationData[1];
				
				if (MedicationName == null || MedicationName.isEmpty()) {
			        System.out.println("Medication name not available. Skipping further processing.");
			        continue; // Skip to the next iteration of the loop
			    }

// Print the Present available balance of that Medicaiton on the Stocktake 

				WebElement Stock = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
				Stock.click();

				Thread.sleep(2000);

				WebElement StockTake = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
				StockTake.click();

				Thread.sleep(2000);

				// New code to read medication name from Excel
				WebElement medicationNameInput = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
				medicationNameInput.click();
				
				medicationNameInput.sendKeys(medicationData[0]); // Medication name from Excel
				
				

				Thread.sleep(2000);

				WebElement clickonDisplyStock = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
				clickonDisplyStock.click();

				Thread.sleep(1000);

				WebElement clickonIncluseS8 = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
				clickonIncluseS8.click();

				Thread.sleep(1000);

				WebElement Imprestonlybtn = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
				Imprestonlybtn.click();

				Thread.sleep(1000);

				WebElement SearchBTN = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
				SearchBTN.click();

				Thread.sleep(2000);

//Print the Available Balance and selected Medication
				
				String MedicationName1 = "0"; // Default value in case element not found
			    String stock = "0"; // Default value in case element not found

				
			    try {
			        WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
			        MedicationName1 = SelectedMedication.getText();
			        System.out.println("Medication Name = " + MedicationName1);
			    } catch (Exception e) {
			        System.out.println("Medication Name Not found: 0");
			    }

				Thread.sleep(2000);
				

				try {
			        WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			        stock = AvailableBalance.getText();
			        System.out.println("Current Stock = " + stock);
			    } catch (Exception e) {
			        System.out.println("Current Stock not found: 0");
			    }

				Thread.sleep(2000);

// Transfer In Process

				WebElement transferInBtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
				transferInBtn.click();

				Thread.sleep(2000);

				WebElement locationToReceiveFromInput = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));
				locationToReceiveFromInput.sendKeys("Emergency Ward");

				WebElement locationDropdown = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
				locationDropdown.click();

				Thread.sleep(2000);

				WebElement locationSelect = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
				locationSelect.click();

				WebElement notesInput = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
				notesInput.sendKeys("Notes Will be here");

				WebElement imprestBtn = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
				imprestBtn.click();

				Thread.sleep(2000);

				WebElement medicationInput = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
				medicationInput.sendKeys(medicationData[0]);
				Thread.sleep(2000);

				WebElement selectMedication = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")));
				selectMedication.click();

				Thread.sleep(2000);

				WebElement quantityInput = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));
				quantityInput.sendKeys(medicationData[1]);

				WebElement addBtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
				addBtn.click();

				Thread.sleep(2000);

// Print the entered qty

				WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

				String add = AddedBalance.getText().trim();

				System.out.println("Added qty =  " + add);

				Thread.sleep(1000);

			

				String numericAdd = add.replaceAll("[^0-9]", "");
				String numericStock = stock.replaceAll("[^0-9]", "");

				int intValue = Integer.parseInt(numericStock);
				int abc = Integer.parseInt(numericAdd);
				int sum = intValue + abc;
				

				System.out.println("Total Balance = " + sum);

				System.out.println("-------------------------------");

				Thread.sleep(2000);

				// Continue the process of transfer in

				WebElement receiveTransferBtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Receive Transfer']")));
				receiveTransferBtn.click();

				Thread.sleep(2000);

				WebElement passwordInput = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
				passwordInput.sendKeys("1111");

				Thread.sleep(2000);

				WebElement signInBtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				signInBtn.click();

				Thread.sleep(2000);

				WebElement completeBtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
				completeBtn.click();

				Thread.sleep(2000);

				// Update Excel file with current stock and sum
				updateExcelWithStockAndSum(excelFilePath, sheetName, medicationData[0], stock, sum);
				
				

			}
				
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void updateExcelWithStockAndSum(String excelFilePath, String sheetName, String MedicationName,
			String stock, int sum) throws IOException {
		FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(excelFile);
		Sheet sheet = workbook.getSheet(sheetName);

		int stockColumnIndex = 6;
		int sumColumnIndex = 7;

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);

			if (row == null) {
				row = sheet.createRow(i);
			}

			// Assuming medication name is in the fifth column (index 4)
			Cell MedicationNameCell = row.getCell(4);
			String cellValue = getCellValueAsString(MedicationNameCell);

			if (cellValue.equals(MedicationName)) {
				// Set stock value in the stock column
				Cell stockCell = row.createCell(stockColumnIndex);
				stockCell.setCellValue(stock);

				// Set sum value in the sum column
				Cell sumCell = row.createCell(sumColumnIndex);
				sumCell.setCellValue(sum);

				break; // Break the loop once the matching row is found and updated
			}
		}

		// Write changes to the Excel file
		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		workbook.close();
		excelFile.close();
	}

	private static ArrayList<String[]> readArrayMedicationFromExcel(String excelFilePath, String sheetName)
			throws IOException {
		ArrayList<String[]> ArrayMedication = new ArrayList<>();

		FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(excelFile);
		Sheet sheet = workbook.getSheet(sheetName);

		// Assuming medication data starts from the second row (index 1)
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			String[] medicationData = new String[2];

			// Assuming medication name is in the first column (index 0)
			Cell MedicationNameCell = row.getCell(4);
			medicationData[0] = getCellValueAsString(MedicationNameCell);

			// Assuming quantity is in the second column (index 1)
			Cell quantityCell = row.getCell(5);
			medicationData[1] = getCellValueAsString(quantityCell);

			ArrayMedication.add(medicationData);
		}

		workbook.close();
		excelFile.close();

		return ArrayMedication;
	}

	private static String[] readLoginDataFromExcel(String filePath, String sheetName) throws IOException {
		String[] loginData = new String[4];

		FileInputStream excelFile = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(excelFile);
		Sheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(1);
		for (int i = 0; i < 4; i++) {
			Cell cell = row.getCell(i);
			loginData[i] = getCellValueAsString(cell);
		}

		workbook.close();
		excelFile.close();

		return loginData;
	}

	private static String getCellValueAsString(Cell cell) {
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(cell);
	}
}