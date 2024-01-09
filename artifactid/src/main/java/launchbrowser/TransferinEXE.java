package launchbrowser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;                                         
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;


public class TransferinEXE {
	static WebDriver driver = new ChromeDriver();
	static int searchCount = 1;
	static int searchCount1 = 1;
	static int searchCount2 = 1;
	static int rowIndex = 1;

	static int valueToCompare = 0;
	
	public static List<String> drugNames;
	public static List<String> Innumbers;
	public static List<String> location;
	
	

	public static void topMethod() throws InterruptedException {
		
			// Maximize the Chrome window
			driver.manage().window().maximize();

			// Set implicit wait to 10 seconds

			// Process of Log in
			driver.get("https://hospital-staging.strongroom.ai/login");

			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");

			Thread.sleep(2000);

			
			WebElement clickElement = driver.findElement(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']"));
			clickElement.click();

			Thread.sleep(2000);

			
			WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
			field2.sendKeys("qa@strongroom.ai");

			Thread.sleep(2000);

			
			WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
			field3.sendKeys("stew-dazzling-washtub!");

			Thread.sleep(2000);

			
			WebElement loginBtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
			loginBtn.click();
			
			    

			Thread.sleep(2000);

			WebElement dropdown = driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
			dropdown.click();

			Thread.sleep(2000);

			
			WebElement dropdown1 =driver.findElement(By.xpath("//li[contains(@aria-label,'Pharmacy')]"));
			dropdown1.click();

			Thread.sleep(2000);

			WebElement selectLocationBtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
			selectLocationBtn.click();

		Thread.sleep(2000);

	}

	

	public static  void run() throws InterruptedException {
		List<String> drugNames = readDrugNamesFromExcel(
				"/home/user/Documents/artifactid/files/MedicationData.xlsx");

		
		System.out.println("--------");

		System.out.println(drugNames.size());
		
		System.out.println("--------");

		Thread.sleep(2000);

		
		//for (; searchCount < 4;) {
			secondMethod();
			searchCount++;
			searchCount2++;
		//}
	}



	

	public static void main(String[] args) throws InterruptedException {
		topMethod();
		//TransferinEXE myObject = new TransferinEXE();
		Thread.sleep(2000);

		run();
		//driver.quit();
	}


	
	public static void secondMethod() throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println(searchCount);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		

if (searchCount == 1)
{
	WebElement stock = driver.findElement( By.xpath("//p[normalize-space()='Stock']"));
	stock.click();
	Thread.sleep(2000);

	WebElement stockTake = driver.findElement((By.xpath("//p[normalize-space()='Stocktake']")));
	stockTake.click();
	Thread.sleep(2000);

	WebElement medication = driver.findElement((By.xpath("//input[@placeholder='Medication...']")));

	drugNames = readDrugNamesFromExcel(
			"/home/user/Documents/artifactid/files/MedicationData.xlsx");

	String drugName = drugNames.get(searchCount);

	medication.sendKeys(drugName);

	Thread.sleep(2000);

	WebElement displayInStock = driver.findElement((By.xpath("//p[@class='active-select-filter select-filter-item']")));
	displayInStock.click();

	WebElement searching = driver.findElement((By.xpath("//button[@class='button submit-button']")));
	searching.click();

	WebElement displayImprest = driver.findElement((By.xpath("//p[normalize-space()='Display Imprest Only']")));
	displayImprest.click();

	Thread.sleep(3000);

	WebElement expected = driver.findElement((By.xpath("(//td)[4]")));

	String openBalance = expected.getText().trim();
	System.out.println("(Expected): " + openBalance);

	// Extract numeric part from the string (remove non-numeric characters)
	String numericPart = openBalance.replaceAll("[^0-9]", "");

	// Parse the numeric part into an integer
	int valueToCompare = Integer.parseInt(numericPart);

	System.out.println("(Stock): " + valueToCompare);

	Thread.sleep(2000);


}


	

	Thread.sleep(3000);
			// Clicking on the Transfer in
			WebElement transferIn = driver.findElement(By.xpath("//button[normalize-space()='Transfer In']"));
			transferIn.click();
			Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']")));
			//wait.until(ExpectedConditions
					//.visibilityOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']")));

			// Explicit wait for the location input field
			WebElement enterLocation = driver.findElement((By.xpath("//input[@placeholder='Type in location to receive from']")));

			enterLocation.click();
			enterLocation.sendKeys(Keys.SPACE);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='p-dropdown-items-wrapper']")));

			WebElement selectlocaion = driver.findElement((By.xpath("//li[@aria-label='Orange Hospital']")));
			selectlocaion.click();

			Thread.sleep(1000);

			// Locate the location options
//			List<WebElement> dropdownlocation = driver.findElements(By.xpath("//li[contains(@class, 'p-dropdown-item')]"));
	//
//			// Iterate through the options to find a match with the drop down location
//			for (WebElement option : dropdownlocation) {
	//
//				int searchCoun2 = 0;
//				String loc = location.get(searchCoun2);
	//
//				if (option.getText().trim().equals(loc)) {
//					// Found a match, click on the option
//					Thread.sleep(3000);
	//
//					searchCoun2++;
	//
//					option.click();
//					break;
//				}
//			}

			WebElement noteTextArea = driver.findElement(By.xpath("//textarea[@name='notes' and @id='note-modal']"));

			// Write "Transferrin" in the note box
			noteTextArea.sendKeys("Notes will be here");

			// Click the Imprest/Emergency Meds/Ward Stock button
			WebElement button = driver.findElement((By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
			button.click();

			WebElement medicationInput = driver.findElement((By.xpath("//input[@placeholder='Select Medication']")));
			medicationInput.click();

			String drugname1 = drugNames.get(searchCount);

			medicationInput.sendKeys(drugname1);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));

			

			// Click on the quantity field with the specified placeholder

			WebElement quantityInput =driver.findElement((By.xpath("//input[@placeholder='Qty...']")));
			quantityInput.click();

			List<String> Quantity = readInnumbers1FromExcel(
						"/home/user/Documents/artifactid/files/MedicationData.xlsx");
			
			 searchCount++;
			searchCount1++;

			Thread.sleep(2000);
			String drugqty = Quantity.get(searchCount1);



			String substringToRemove = ".0";
			String modifiedString = drugqty.replace(substringToRemove, "");

			
			quantityInput.sendKeys(modifiedString);
			Thread.sleep(2000);

			WebElement addButton = driver.findElement((By.xpath("//p[@class='blue-button']")));
			addButton.click();
			Thread.sleep(2000);

			//System.out.println("Successfully navigated to the Stock tab.");
			
			WebElement AddedBalance = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

			String add = AddedBalance.getText().trim();

			System.out.println("Out qty =  " + add);


				
				Thread.sleep(2000);
				
				
				int valueToCompare1 = Integer.parseInt(modifiedString);
				Integer sum = valueToCompare + valueToCompare1;
				
				System.out.println("this is one: " + String.valueOf(sum));
				System.out.println("this is 2nd: " + String.valueOf(valueToCompare));
				
				
				Thread.sleep(2000);
				
				run();
	
}

	
	

//	private static void updateValueToCompareInExcel(String filePath, String sheetName, int columnIndex, String value) {
//		{
//			try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
//				Sheet sheet = workbook.getSheet(sheetName);
//
//				// Assuming you want to update the value in the first row
//				Row row = sheet.getRow(0);
//
//				// Check if the row exists before trying to create a cell
//				if (row == null) {
//					row = sheet.createRow(0);
//				}
//
//				// Create a cell only for th
//				//e specified columnIndex
//				processCell(0, columnIndex, row, value);
//
//				// Create a new temporary file
//				File tempFile = new File(filePath + ".tmp");
//
//				try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
//					workbook.write(outputStream);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//				// Rename the temporary file to the original file
//				if (tempFile.renameTo(new File(filePath))) {
//					System.out.println("File updated successfully.");
//				} else {
//					System.err.println("Error updating file.");
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		      
//		
		
		
		  //disconnect kardo aur likhna ki sheet me write karne ka code kar rahi hu baki kal me tume samjh dunga ki kese karna hai? ok? yes ok perfect chalo done bye bye bye tata tata

//		char[] valueToCompare;
//		char[] sum;   ///ye kya kiya hai??? are value to sum kraya tha fir but ye i think glt h mujhe dekhna pdega pura 
//		OpeningBalance(
//				"C:\\Users\\jatav\\Downloads\\artifactid-20231226T175227Z-001\\artifactid\\files\\MedicationData.xlsx",
//				"MediData", 7, 10, String.valueOf(valueToCompare), String.valueOf(sum));
	//}
 
	

//	catch(IOException e1)
//	{
//		e1.printStackTrace();
//		// Handle the exception appropriately (logging, error messages, etc.)
//
//		System.out.println("Successfully navigated to the Stock tab");
//
//		// Click on the button with the specified class
//		FluentWait<WebDriver> wait;
//		WebElement completeButton = wait.until(
//				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
//		completeButton.click();
//
//		// WebElement loader =
//		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//your-loader-xpath")));
//		Thread.sleep(5000); // Wait for 5 seconds after clicking the "Add" button
//
//		// Check that the signature check modal pops up
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By
//				.xpath("//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']")));
//
//		// Find the input field with the specified placeholder
//		WebElement usernameInput1 = wait
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
//
//		usernameInput1.click();
//
//		// Clear the text from the input field
//		usernameInput1.clear();
//
//		// Writing text into the input field
//
//		WebElement usernameInput11 = wait
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
//		usernameInput11.sendKeys("valeshan.naidoo@strongroom.ai");
//
//		// Clicking on the input field
//		WebElement passwordInput1 = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='PIN/Password']")));
//		passwordInput1.click();
//
//		// Writing text into the input field
//		passwordInput1.sendKeys("1111");
//
//		// Clicking on the field
//		WebElement greenButton = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
//		greenButton.click();
//
//		// Wait for 5 seconds (5000 milliseconds)
//		Thread.sleep(2000);
//
//	}
//
//private static void  List<String> Object  java.lang.Object locationFromExcel(String string)

	// TODO Auto-generated method stub
	// return null;

  public static List<String> readInnumbers1FromExcel(String filePath) {
    List<String> Innumbers = new ArrayList<>();
    Workbook workbook = null;
    try {
        workbook = WorkbookFactory.create(new File(filePath));
        Sheet sheet = workbook.getSheet("MediData"); // Replace with your sheet name
        int cellIndexQuantities = 1; // Assuming quantities are in the seventh column (index 6)
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
                        
                        
                    }
                }
                rowIndex++;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return Innumbers;
}

// Method to read drug names from the Excel file

	private static List<String> readDrugNamesFromExcel(String filePath) {
		List<String> drugNames = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {

			Sheet sheet = workbook.getSheet("MediData");

			for (Row row : sheet) {
				Cell cell = row.getCell(0); // Assuming drug namesare in the second column (index 1)

				if (cell != null) {

					drugNames.add(cell.getStringCellValue());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return drugNames;

	}

	public static void OpeningBalance(String filePath, String sheetName, int columnIndex7, int columnindex10, String beforestockcount, String sum) {
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
	            System.err.println("Error updating file/////");
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

//	// Method to qty from the Excel file (7) for all rows
//	public static List<String> readInnumbersFromExcel(String filePath) {
//		List<String> Innumbers = new ArrayList<>();
//		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
//			Sheet sheet = workbook.getSheet("MediData"); // Replace with your sheet name
//			int cellIndexQuantities = 1; // Assuming quantities are in the seventh column (index 6)
//			int rowIndex = 0; // Initialize row index counter
//			for (Row row : sheet) {
//				Cell cell = row.getCell(cellIndexQuantities);
//				if (cell != null) {
//					if (rowIndex > 0) {
//						if (cell.getCellType() == CellType.STRING) {
//							Innumbers.add(cell.getStringCellValue());
//						} else if (cell.getCellType() == CellType.NUMERIC) {
//							// Use a formatter if you want to format the numeric value as a string
//							Innumbers.add(String.valueOf(cell.getNumericCellValue()));
//
//						}
//					}
//					rowIndex++;
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return Innumbers;
//	}

	// Method to qty from the Excel file (7) for all rows
	public static List<String> readlocationFromExcel(String filePath) {
		List<String> location = new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
			Sheet sheet = workbook.getSheet("MediData"); // Replace with your sheet name
			int cellIndexQuantities = 1; // Assuming quantities are in the seventh column (index 6)
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