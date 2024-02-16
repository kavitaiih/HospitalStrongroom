package Testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.NotificationPage;
import test.Util.TestUtil;

public class TrnasferOutpatient extends Base {
	private static NotificationPage notificationPage;

//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtilOld.TRnasferoutPATIENT();
//		return testData.iterator();
//	}
//
//	@Test(dataProvider = "getTestData")
	public static void transferoutpatient(String action, String location, String note, String Patient_Name, String Prescriber_Name, String Medication_name, 
			String Quantity, String username, String pin )
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		SoftAssert softAssert = new SoftAssert();
		TaskName = action ;
		Thread.sleep(3000);

		WebElement Draftmsg;
		try {
		    Draftmsg = driver.findElement(By.xpath("//p[@class='regular-button']"));
		    // Wait for the element to be visible (optional)
		    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3000));
		    wait1.until(ExpectedConditions.visibilityOf(Draftmsg));

		    // MEDICATION
		    // FOUND
		    String DraftFound = Draftmsg.getText();

		    if ("Cancel".equals(DraftFound)) {
		        System.out.println("Draft transaction found: " + DraftFound);
		        Thread.sleep(2000);
		        WebElement Draftmsgclosebtn = driver.findElement(By.xpath("//p[@class='regular-button']"));
		        Draftmsgclosebtn.click();
		        Thread.sleep(2000);
		    } else {
		        // Proceed with the rest of the code
		        System.out.println("No Draft found. Proceeding with the operation.");
		        // ...rest of the code...
		    }
		} catch (NoSuchElementException e) {
		    // Handle the case when Draftmsg element is not found
		    System.out.println("Draftmsg element not found. Proceeding with the operation.");
		    // ...rest of the code...
		}
		// Print the Present available balance of that Medicaiton on the Stocktake

		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
		// New code to check if Medication_name is null or empty
		if (Medication_name == null || Medication_name.isEmpty()) {
			System.out.println("Medication_name is null or empty. Exiting the test.");
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

		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).sendKeys(Patient_Name); // Patient name from
																									// Excel
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
			System.out.println("Entry for this medication is not found");
			// System.out.println("Medication Name Not found: 0");

			// Set default values for MedicationName1, stock, and RemainingasString
			MedicationName1 = "-";
			stock = "-";
			String RemainingasString = "-";

			// Write default values to Excel or perform any necessary action
			//TestUtilOld.writeDataTRnasferoutPATIENT(rownum++, stock, RemainingasString, "Fail");
			// return; // This will exit the current method, allowing the program to

		}

		Thread.sleep(2000);

		try {
			WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			stock = AvailableBalance.getText();
			String numericStock = stock.replaceAll("[^0-9]", "");
			int intValue = Integer.parseInt(numericStock);

			if (intValue == 0) {
				Thread.sleep(2000);
				inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name
						+ "\n" + "Patient Name: " + Patient_Name + "\n" + "Medication QTY is found: Zero " + stock
						+ "\n";;
				

				softAssert.assertEquals("0", stock,
						"Initial Stock is 0, that's why the process of transfer out is not possible");
				System.out.println(stock);
				System.out.println(intValue);

				softAssert.assertAll();

				return;
			} else {
				// System.out.println("Continue");
			}
			System.out.println("Current Stock = " + stock);
		} catch (Exception e) {
			e.printStackTrace(); // print the stack trace for debugging
			System.out.println("Current Stock not found: 0");
			stock = "-";
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
		// Transfer out Process

		driver.findElement(By.xpath("//button[normalize-space()='Transfer Out']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Type in location to send to']")).sendKeys(location);

		driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']")).click();

		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note);

		driver.findElement(By.xpath("//p[normalize-space()='Patient Medication']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']"))
				.sendKeys(Patient_Name);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Search']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='patient-result-info']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")).sendKeys(Prescriber_Name);
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[3]"))
				.click();
		Thread.sleep(2000);

		WebElement Prescriber = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p[1]"));
		String PrisName = Prescriber.getText();
		System.out.println(PrisName);
		Thread.sleep(1000);

		WebElement clickmedication = driver
				.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder']"));
		clickmedication.click();

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/input[1]")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(5000);

		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]/li[1]")); // NO
		Thread.sleep(5000);
		// MEDICATION
		// FOUND
		String NoMedication = nomedicationnamefound.getText();

		if ("No results found".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
			Thread.sleep(2000);
			inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
					+ "Patient Name" + Patient_Name + "\n"
					+ "Entry for this medication for selected patient is not found: " + NoMedication + "\n";
			;

			softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
			softAssert.assertAll();
			return;
		} else {
			// System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]")); // SELECT
			String Test = clickonmedication.getText();
			// System.out.println(Test);// MEDICATION
			Thread.sleep(2000);
			clickonmedication.click(); // SELECT THE MEDICATION

		}
		Thread.sleep(2000);

		WebElement quantityInput = driver.findElement(By.xpath("//input[@placeholder='Quantity']"));
		quantityInput.click();
		quantityInput.clear();
		quantityInput.sendKeys(Quantity);
		Thread.sleep(2000);

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
		
		if (intValue < abc) {
			driver.findElement(By.xpath("//p[normalize-space()='Send Transfer']")).click();
			Thread.sleep(2000);
			
			WebElement usernameInput = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
			usernameInput.click();
			usernameInput.clear();
			usernameInput.sendKeys(username);
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pin);
			Thread.sleep(2000);

			driver.findElement(By.xpath("//div[@class='green-button']")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//h3[normalize-space()='Complete']")).click();
			Thread.sleep(2000);
			
		    // Display an error message or take appropriate actions
		    System.out.println("Error: Stock is less than Qty. Cannot proceed.");
            inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
					
          + "Patient Name:  " + Patient_Name + "\n"	+ "Out qty: " + Quantity + "\n";
			
			softAssert.assertEquals(intValue, abc, "Stock is less than Entered Qty. Cannot proceed.");
			softAssert.assertAll();
			return;
		    
		} else {
		    // Proceed with the rest of the code
		    System.out.println("Stock is sufficient. Proceeding with the operation.");
		    // ...rest of the code...
		}

		
		int Remaining = intValue - abc;
		String RemainingasString = String.valueOf(Remaining);
		System.out.println("Remaining Balance = " + Remaining);
		Thread.sleep(2000);
		// Continue the process of transfer in

		driver.findElement(By.xpath("//p[normalize-space()='Send Transfer']")).click();
		Thread.sleep(2000);
		
		WebElement usernameInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput.click();
		usernameInput.clear();
		usernameInput.sendKeys(username);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pin);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='green-button']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//h3[normalize-space()='Complete']")).click();
		Thread.sleep(2000);

		//WebElement againclickonsearchbtn = driver.findElement(By.xpath("//button[@class='button submit-button']"));
		//againclickonsearchbtn.click();
		//Thread.sleep(3000);
		
		String MedicationName2 = "0"; // Default value in case element not found
		//String stock1 = "0"; // Default value in case element not found
		String PatientName2 = "0";
		
		try {
			WebElement SelectedMedication1 = driver.findElement(By.xpath("//td[1]"));
			MedicationName2 = SelectedMedication1.getText();
		} catch (Exception e) {
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
		System.out.println("Final Balance on the stocktake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);

		inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer In Imprest Location: " + location + "\n" + "Transferin Imprest Drug Name: "
				+ Medication_name + "\n" + "Patient Name:  " + Patient_Name + "\n" + "Out Quantity:  " + Quantity
				+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + RemainingasString + "\n";;
		
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, RemainingasString, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
				softAssert.assertEquals(Patient_Name, PatientName2, "Patient Name mismatch");
				softAssert.assertAll();


//		if(RemainingasString.equals(numericStock1) ) {
//			TestUtil.writeDataTRnasferoutPATIENT(rownum++, stock, RemainingasString, "Pass");	
//		}else {
//			TestUtil.writeDataTRnasferoutPATIENT(rownum++, stock, RemainingasString, "Fail");	
//		}
	

	}


}
		
	

