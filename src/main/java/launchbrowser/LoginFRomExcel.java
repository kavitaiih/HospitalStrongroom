package launchbrowser;

import org.apache.poi.ss.usermodel.*;


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

public class LoginFRomExcel {

    public static void main(String[] args) throws InterruptedException {
        String excelFilePath = "/home/user/Desktop/artifactid/files/LoginCred.xlsx";
        String sheetName = "Credentials";

        try {
            String[] loginData = readLoginDataFromExcel(excelFilePath, sheetName);
            
            System.out.println("URL: " + loginData[0]);
            System.out.println("Location: " + loginData[1]);
            System.out.println("Username: " + loginData[2]);
            System.out.println("Password: " + loginData[3]);

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

        } 
    
        catch (IOException e) {
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
}

            
        
        

	

	
	
	
	
	
	