package Testcase;

import org.testng.annotations.Test;


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
import org.testng.asserts.SoftAssert;
import objects.NotificationPage;

import test.Util.TestUtil;


public class TransferinPatient extends Base {
	private static NotificationPage notificationPage;

	//private static int rownum = 2;

//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtil1.DataTransferInWithPatient();
//		return testData.iterator();
//
//	}

	//@Test(dataProvider = "getTestData")
	public static void transferwithpatient(String action, String location, String note, String Medication_name, String Patient_Name, String Prescriber_Name,
			String Quantity,  String username, String pin) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		SoftAssert softAssert = new SoftAssert();
		TaskName = action ;
		// Print the Present available balance of that Medicaiton on the Stocktake
		// Opening
			//	notificationPage = new NotificationPage(driver, wait);

				//notificationPage.clickNotificationIcon();

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

		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).sendKeys(Patient_Name);
		Thread.sleep(1000);

		WebElement clickonDisplyStock = driver.findElement(By.xpath("//p[normalize-space()='Display In Stock Only']"));
		clickonDisplyStock.click();
		Thread.sleep(1000);

		WebElement clickonIncluseS8 = driver.findElement(By.xpath("//p[normalize-space()='Include S8']"));
		clickonIncluseS8.click();
		Thread.sleep(1000);

		WebElement SearchBTN = driver.findElement(By.xpath("//button[@class='button submit-button']"));
		SearchBTN.click();
		Thread.sleep(2000);

		// Print the Available Balance and selected Medication, and patient name

		String MedicationName1 = "0"; // Default value in case element not found
		String stock = "0"; // Default value in case element not found
		String PatientName1 = "0"; //Default value in case element not found
		
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
		
		try {
		    WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
		    MedicationName1 = SelectedMedication.getText();
		    System.out.println("Medication Name = " + MedicationName1);
		} catch (Exception e) {
		    System.out.println("Entry for this medication is not found");
		    // Handle the case when medication information is not found in the current row
		    MedicationName1 = "-";
		}
		Thread.sleep(2000);

		try {
			WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
			PatientName1 = SelectedPatient.getText();
			System.out.println("Patient Name = " + PatientName1);
		} catch (Exception e) {
			// System.out.println("Patient Name not found: 0");
			PatientName1 = "-"; // Set default value for PatientName1 if not found
		}

		Thread.sleep(2000);
		
		// Transfer In Process

		WebElement transferInBtn = driver.findElement(By.xpath("//button[normalize-space()='Transfer In']"));
		transferInBtn.click();
		Thread.sleep(2000);

		WebElement locationToReceiveFromInput = driver
				.findElement(By.xpath("//input[@placeholder='Type in location to receive from']"));
		locationToReceiveFromInput.sendKeys(location);

		WebElement locationDropdown = driver
				.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
		locationDropdown.click();
		Thread.sleep(2000);

		WebElement locationSelect = driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']"));
		locationSelect.click();

		WebElement notesInput = driver.findElement(By.xpath("//textarea[@id='note-modal']"));
		notesInput.sendKeys(note);

		WebElement patientbtn = driver.findElement(By.xpath("//p[normalize-space()='Patient Medication']"));
		patientbtn.click();

		WebElement patientname = driver
				.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']"));
		patientname.click();
		patientname.sendKeys(Patient_Name);
		Thread.sleep(2000);

		WebElement ClickonsearchBTN = driver.findElement(By.xpath("//p[normalize-space()='Search']"));
		ClickonsearchBTN.click();
		Thread.sleep(2000);

		WebElement ClickonPatient = driver.findElement(By.xpath("//div[@class='patient-result-info']"));
		ClickonPatient.click();
		Thread.sleep(2000);

		WebElement PrescRIBERINPUT = driver
				.findElement(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']"));
		PrescRIBERINPUT.sendKeys(Prescriber_Name);
		Thread.sleep(2000);

		WebElement Selectprescriber = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[3]"));
		Selectprescriber.click();
		Thread.sleep(2000);

		WebElement Prescriber = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p[1]"));
		String PrisName = Prescriber.getText();
		System.out.println(PrisName);
		Thread.sleep(2000);

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(5000);

		
		
//		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]/li[1]")); // NO
//		Thread.sleep(5000);
//		// MEDICATION
//		// FOUND
//		String NoMedication = nomedicationnamefound.getText();
//
//		if ("No results found".equals(NoMedication)) {
//			System.out.println("Medication not selected: " + NoMedication);
//			Thread.sleep(2000);
//			inputdata = "\n"+ "Transaction Type: " +action + "\n" +"Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
//					+ "Patient Name" + Patient_Name + "\n"
//					+ "Entry for this medication for selected patient is not found: " + NoMedication + "\n";
//			;
//
//			softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
//			softAssert.assertAll();
//			return;
//		} else {
			// System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); // SELECT
			Thread.sleep(2000);
			clickonmedication.click(); // SELECT THE MEDICATION

		//}
						
			
		Thread.sleep(2000);

		WebElement quantityInput = driver.findElement(By.xpath("//input[@placeholder='Qty...']"));
		quantityInput.sendKeys(Quantity);

		WebElement addBtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
		addBtn.click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver
				.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
		String add = AddedBalance.getText().trim();
		System.out.println("Added qty =  " + add);
		Thread.sleep(1000);

		String numericAdd = add.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		
		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int sum = intValue + abc;
		String sumAsString = String.valueOf(sum);

		System.out.println("Total Balance = " + sum);
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
		Thread.sleep(2000);
		
		WebElement againclickonsearchbtn = driver.findElement(By.xpath("//button[@class='button submit-button']"));
		againclickonsearchbtn.click();
		Thread.sleep(3000);
	
		String MedicationName2 = "0"; // Default value in case element not found
		//String stock1 = "0"; // Default value in case element not found
		String PatientName2 = "0";
		
		try {
			WebElement SelectedMedication1 = driver.findElement(By.xpath("//td[1]"));
			MedicationName2 = SelectedMedication1.getText();
		} catch (Exception e) {
			//System.out.println("Entry for this medication is not found");
		}
		
		Thread.sleep(3000);

		try {
			WebElement SelectedPatient1 = driver.findElement(By.xpath("//td[2]"));
			PatientName2 = SelectedPatient1.getText();
		} catch (Exception e) {
			//System.out.println("Patient Name not found: 0");
		}
		Thread.sleep(3000);


		// Print the final stock

		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);
		
		inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer In Imprest Location: " + location + "\n" + "Medication Name: "
				+ Medication_name + "\n" + "Patient Name:  " + Patient_Name + "\n" + "Transfer in quantity:  " + abc
				+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + sumAsString + "\n";;
				
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, sumAsString, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Medication Name mismatch");
				softAssert.assertEquals(Patient_Name, PatientName2, "Patient Name mismatch");
				softAssert.assertAll();



	}
}
