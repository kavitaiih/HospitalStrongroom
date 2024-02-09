package Testcase;

import java.util.ArrayList;
import org.openqa.selenium.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Util.TestUtil;

public class TRansferINImprest extends Base {

	private static int rownum = 2;

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtil.TRansferINImprest();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void traansferInPrescriber(String Medication_name, String Quantity, String Current_Stock,
			String Total_Balance, String pin, String note, String location, String Status) throws InterruptedException {

		// Set the log level for PoolingHttpClientConnectionManager to a higher level
		// (e.g., INFO or higher)

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
		// String PatientName1 = "0"; //Default value in case element not found

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

		driver.findElement(By.xpath("//input[@placeholder='Select Medication']")).sendKeys(Medication_name);
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Qty...']")).sendKeys(Quantity);

		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver
				.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
		String add = AddedBalance.getText().trim();
		System.out.println("Out qty =  " + add);
		Thread.sleep(1000);

		String numericAdd = add.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int sum = intValue + abc;
		String sumasString = String.valueOf(sum);
		System.out.println("Total Balance = " + sum);
		Thread.sleep(2000);
		// Continue the process of transfer in

		WebElement receiveTransferBtn = driver.findElement(By.xpath("//p[normalize-space()='Receive Transfer']"));
		receiveTransferBtn.click();
		Thread.sleep(2000);

		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput.sendKeys("1111");
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

// Print the final stock

		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);

		if (sumasString.equals(numericStock1)) {
		    TestUtil.writeDataTRansferINImprest(rownum++, stock, sumasString, "Pass");
		} else {
		    TestUtil.writeDataTRansferINImprest(rownum++, stock, sumasString, "Fail");
		}
	}
}
