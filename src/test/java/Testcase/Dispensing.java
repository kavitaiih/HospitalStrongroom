package Testcase;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Util.TestUtil;

public class Dispensing extends Base {

	private static int rownum = 2;

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtil.dipensing();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void Dispensingmedication(String Medication_name, String Patient_Name, String note, String Prescriber_Name,
			String Quantity, String PIN) throws InterruptedException {

		// Check the stock on the medidcation

		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
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

		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).sendKeys(Patient_Name); // Patient name from
																									// excel

		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Display In Stock Only']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Include S8']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@class='button submit-button']")).click();
		Thread.sleep(2000);

//Print the Available Balance and selected Medication, and patient name

		String MedicationName1 = "0"; // Default value in case element not found
		String stock = "0"; // Default value in case element not found
		String PatientName1 = "0"; // Default value in case element not found

		try {
			WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
			MedicationName1 = SelectedMedication.getText();
			System.out.println("Medication Name = " + MedicationName1);
		} catch (Exception e) {
			System.out.println("Medication Name Not found: 0");
			System.out.println("Entry for this medication is not found");
			
			// Set default values for MedicationName1, stock, and RemainingasString
			MedicationName1 = "-";
			stock = "-";
			String RemainingasString = "-";

			// Write default values to Excel or perform any necessary action
			TestUtil.writeDatadipensing(rownum++, stock, RemainingasString, "Fail");
			// This will exit the current method, allowing the program to continue

		}

		Thread.sleep(2000);

		try {
			WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			stock = AvailableBalance.getText();
			System.out.println("Current Stock = " + stock);
		} catch (Exception e) {
			System.out.println("Current Stock not found: 0");
			stock = "-"; // Set default value for stock if not found
		}

		Thread.sleep(2000);

		try {
			WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
			PatientName1 = SelectedPatient.getText();
			System.out.println("Patient Name = " + PatientName1);
		} catch (Exception e) {
			System.out.println("Patient Name not found: 0");
		}
		Thread.sleep(2000);

//Start Dispensing process	

		driver.findElement(By.xpath("//button[normalize-space()='Dispensing']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='refnum']")).sendKeys("12345");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note); // Take not from excel
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[@class='pom-imprest-choice-button']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']"))
				.sendKeys(Patient_Name); // Patient name from Excel
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Search']")).click(); // click on search btn
		Thread.sleep(2000);

		WebElement ClickonPatient = driver.findElement(By.xpath("//div[@class='patient-result-info']"));
		ClickonPatient.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")).sendKeys(Prescriber_Name); // Prescriber
																														// NAME
																														// Excel
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[@class='drug-search-result']")).click(); // SELECT PRESCRIBER
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Type medication name here']")).sendKeys(Medication_name); // ENTER// MEDICATION
																																																										
		Thread.sleep(2000);

		WebElement Nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]"));/// html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]
		String NoMedication = Nomedicationnamefound.getText();
		//System.out.println(NoMedication);

		Thread.sleep(2000);


		if ("No available options".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
		} else {
			System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver
					.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/p[1]")); // SELECT
																													// MEDICATION
			Thread.sleep(2000);
			clickonmedication.click(); // SELECT THE MEDICATION

		}

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Enter quantity']")).sendKeys(Quantity); // qty from excel fle

		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[@class='submit-button blue-button']")).click(); //
		Thread.sleep(2000);

//Print Entered qty		

		WebElement AddedBalance = driver
				.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
		String add = AddedBalance.getText().trim();
		System.out.println("Out qty =  " + add);
		Thread.sleep(1000);

		String numericAdd = add.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int Remaining = intValue - abc;
		String RemainingasString = String.valueOf(Remaining);
		System.out.println("Remaining Balance = " + Remaining);
		Thread.sleep(2000);

//Continue the despensing process		
		driver.findElement(By.xpath("//p[normalize-space()='Dispense']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1111"); // enter password from excel
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='green-button']")).click(); // sign in btn
		Thread.sleep(2000);

		driver.findElement(By.xpath("//h3[normalize-space()='Complete']")).click(); // complete btn
		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[@class='button submit-button']")).click(); // Again search the same drug
																							// on the stocktake screen

		Thread.sleep(3000);

//Print New Balance

		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);

		if (RemainingasString.equals(numericStock1)) {
			TestUtil.writeDatadipensing(rownum++, stock, RemainingasString, "Pass");
		} else {
			TestUtil.writeDatadipensing(rownum++, stock, RemainingasString, "Fail");
			Thread.sleep(3000);
		}

	}
}
