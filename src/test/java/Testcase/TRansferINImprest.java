package Testcase;

import java.time.Duration;

import java.util.ArrayList;

import org.openqa.selenium.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import objects.NotificationPage;

import test.Util.TestUtil;
public class TRansferINImprest extends Base {
private static NotificationPage notificationPage;

//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtil.TRansferINImprest();
//		return testData.iterator();
//	}
//
//	@Test(dataProvider = "getTestData") traansferInIpmrest
	public static void transferInIpmrest(String action, String location, String note, String Medication_name,  String Quantity, String username,  String pin ) 
			throws InterruptedException {

		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		TaskName = action ;


		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);
		
		// Check if Medication_name is empty, exit the loop
        if (Medication_name == null || Medication_name.isEmpty()) {
            System.out.println("No more data to process. Exiting the test.");
            return;
        }

		// New code to read medication name from Excel
		try {
            WebElement medicationInput = driver.findElement(By.xpath("//input[@placeholder='Medication...']"));
            medicationInput.sendKeys(Medication_name);
            Thread.sleep(2000); // Adjust the sleep time as needed
        } catch (NoSuchElementException e) {
            System.out.println("Medication input element not found. Exiting the test.");
            return; // Exit the test method
        }   
			
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Display In Stock Only']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Include S8']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Display Imprest Only']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@class='button submit-button']")).click();
		Thread.sleep(2000);

		// Print the Available Balance and selected Medication, and patient name

		String MedicationName1 = "0"; // Default value in case element not found
		String stock = "0"; // Default value in case element not found
		try {
			WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
			MedicationName1 = SelectedMedication.getText();
			System.out.println("Medication Name = " + MedicationName1);
		} catch (Exception e) {
			System.out.println("Entry for this medication is not found");
			
		}
		Thread.sleep(2000);

		try {
			WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			stock = AvailableBalance.getText();
			String numericStock = stock.replaceAll("[^0-9]", "");
			int intValue = Integer.parseInt(numericStock);
		
			System.out.println("Current Stock = " + stock);
		} catch (Exception e) {
			e.printStackTrace(); // print the stack trace for debugging
			
		}

		Thread.sleep(2000);

//Transfer In Process

		driver.findElement(By.xpath("//button[normalize-space()='Transfer In']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Type in location to receive from']")).sendKeys(location);

		driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']")).click();

		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note);

		driver.findElement(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")).click();
		Thread.sleep(2000);

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(5000);

		
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); // SELECT
			clickonmedication.click(); // SELECT THE MEDICATION
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Qty...']")).sendKeys(Quantity);

		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver
				.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
		String add = AddedBalance.getText().trim();
		System.out.println("Transfer In  qty =  " + add);
		Thread.sleep(1000);

		String numericAdd = add.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int sum = intValue + abc;
		String sumasString = String.valueOf(sum);
		System.out.println("Total Balance = " + sumasString);
		Thread.sleep(2000);
		// Continue the process of transfer in

		WebElement receiveTransferBtn = driver.findElement(By.xpath("//p[normalize-space()='Receive Transfer']"));
		receiveTransferBtn.click();
		Thread.sleep(2000);
		
		WebElement usernameInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput.click();
		usernameInput.clear();
		usernameInput.sendKeys(username);
		Thread.sleep(2000);

		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput.sendKeys(pin);
		Thread.sleep(2000);

		WebElement signInBtn = driver.findElement(By.xpath("//div[@class='green-button']"));
		signInBtn.click();
		Thread.sleep(2000);

		WebElement completeBtn = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
		completeBtn.click();
		Thread.sleep(3000);

		WebElement againclickonsearchbtn = driver.findElement(By.xpath("//button[@class='button submit-button']"));
		againclickonsearchbtn.click();
		Thread.sleep(3000);
		
		String MedicationName2 = "0"; // Default value in case element not found
		
		
		try {
			WebElement SelectedMedication1 = driver.findElement(By.xpath("//td[1]"));
			MedicationName2 = SelectedMedication1.getText();
		} catch (Exception e) {
		}
		
		Thread.sleep(3000);

// Print the final stock

		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);
		
		inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer In Imprest Location: " + location + "\n" + "Transferin Imprest Drug Name: "
				+ Medication_name + "\n" + "Transferin Imprest in quantity:  " + abc
				+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + sumasString + "\n";;
				
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, sumasString, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "	Medication Name mismatch");
				softAssert.assertAll();

	
	}
}
