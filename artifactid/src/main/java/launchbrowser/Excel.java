package launchbrowser;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel {

	private static final String[] MedicationData = new String[2];


	public static void main(String[] args) throws InterruptedException {
        String excelFilePath = "/home/user/Documents/artifactid/files/LoginCred.xlsx";
        String sheetName = "Credentials";

        try {
        	String[] loginData = readLoginDataFromExcel(excelFilePath, sheetName);
            readMedicationDataFromExcel(excelFilePath, sheetName);
            
            System.out.println("URL: " + loginData[0]);
            System.out.println("Location: " + loginData[1]);
            System.out.println("Username: " + loginData[2]);
            System.out.println("Password: " + loginData[3]);
            System.out.println("medication name: " + MedicationData[0]);
            System.out.println("quantity: " + MedicationData[1]);

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            // Use the URL read from Excel
            driver.get(loginData[0]);
            
           

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

            WebElement locationInput = driver.findElement(By.xpath("//input[@placeholder='Location']"));
            locationInput.sendKeys(loginData[1]); // Location from Excel
            
            

            WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='" + loginData[1] + "']")));
            locationOption.click();

            WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
            usernameField.sendKeys(loginData[2]); // Username from Excel

            WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
            passwordField.sendKeys(loginData[3]); // Password from Excel

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
    		
    		

// Transfer In Process
    		
            WebElement transferInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
            transferInBtn.click();

            Thread.sleep(2000);

            WebElement locationToReceiveFromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));
            locationToReceiveFromInput.sendKeys("Emergency Ward");

            WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
            locationDropdown.click();

            Thread.sleep(2000);

            WebElement locationSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
            locationSelect.click();

            WebElement notesInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
            notesInput.sendKeys("Notes Will be here");

            WebElement imprestBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
            imprestBtn.click();

            Thread.sleep(2000);

            WebElement medicationInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
            medicationInput.sendKeys(MedicationData[0]);
            Thread.sleep(2000);
            

            WebElement selectMedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")));
            selectMedication.click();

            Thread.sleep(2000);

            WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));
            quantityInput.sendKeys(MedicationData[1]);

            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
            addBtn.click();

            Thread.sleep(2000);

            WebElement receiveTransferBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Receive Transfer']")));
            receiveTransferBtn.click();

            Thread.sleep(2000);

            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
            passwordInput.sendKeys("1111");

            Thread.sleep(2000);

            WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
            signInBtn.click();

            Thread.sleep(2000);

            WebElement completeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
            completeBtn.click();

            Thread.sleep(2000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        
private static String[] readLoginDataFromExcel(String filePath, String sheetName) throws IOException {
    String[] loginData = new String[4];

    FileInputStream excelFile = new FileInputStream(new File(filePath));
    Workbook workbook = WorkbookFactory.create(excelFile);
    Sheet sheet = workbook.getSheet(sheetName);

    Row row = sheet.getRow(1); // Assuming login data is in the second row (index 1)
    for (int i = 0; i < 4; i++) {
        Cell cell = row.getCell(i);
        loginData[i] = cell.getStringCellValue();
    }

    workbook.close();
    excelFile.close();

    return loginData;
}


private static void readMedicationDataFromExcel(String filePath, String sheetName) throws IOException {
    FileInputStream excelFile = new FileInputStream(new File(filePath));
    Workbook workbook = WorkbookFactory.create(excelFile);
    Sheet sheet = workbook.getSheet(sheetName);

    Row row = sheet.getRow(1); // Assuming medication data is in the second row (index 1)

    // Assuming medication name is in column 4 (index 3)
    Cell medicationNameCell = row.getCell(4);
    MedicationData[0] = getCellValueAsString(medicationNameCell);

    // Assuming quantity is in column 5 (index 4)
    Cell quantityCell = row.getCell(5);
    MedicationData[1] = getCellValueAsString(quantityCell);

    workbook.close();
    excelFile.close();
}

private static String getCellValueAsString(Cell cell) {
    if (cell == null) {
        return null;
    }

    switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            // Check if the cell is formatted as a date
            if (DateUtil.isCellDateFormatted(cell)) {
                // Convert date to string based on your format
                return cell.getLocalDateTimeCellValue().toString();
            } else {
                // Convert numeric value to string
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            }
        default:
            return null;
    }
}


}





