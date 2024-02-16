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
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import objects.NotificationPage;

import test.Util.TestUtil;

public class OutgoingPatient extends Base {
	private static NotificationPage notificationPage;


//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtilOld.outgoingpatient();
//		return testData.iterator();
//	}
//
//	@Test(dataProvider = "getTestData")
	public static void outgoingpatient(String action, String Medication_name, String transaction_id, String Patient_Name, String note, String Quantity, String username, String pin)
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		SoftAssert softAssert = new SoftAssert();
		TaskName = action ;
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

		String MedicationName1 = "0"; // Default value in case element not found
		String stock = "0"; // Default value in case element not found
		String PatientName1 = "0"; // Default value in case element not found

		try {
	        WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
	        MedicationName1 = SelectedMedication.getText();
	        System.out.println("Medication Name = " + MedicationName1);
	    } catch (Exception e) {
	       // System.out.println("Medication Name Not found: 0");
	        System.out.println("Entry for this medication for this patient is not found");
	        
	        // Set default values for MedicationName1, stock, and RemainingasString
	        MedicationName1 = "-";
	        stock = "-";
	        String RemainingasString = "-";
	        
	        // Write default values to Excel or perform any necessary action
	        //TestUtilOld.writeDataoutgoingpatient(rownum++, stock, RemainingasString, "Fail");
	        //return; // This will exit the current method, allowing the program to continue
	    }

		Thread.sleep(2000);
				
		try {
			WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			stock = AvailableBalance.getText();
			String numericStock = stock.replaceAll("[^0-9]", "");
			int intValue = Integer.parseInt(numericStock);

			if (intValue == 0) {
				Thread.sleep(2000);
				inputdata ="\n"+ "Transaction Type: " +action +  "\n"  + "Medication Name: " + Medication_name
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
			//System.out.println("Current Stock not found: 0");
			stock = "-";
		}

		Thread.sleep(2000);
		
		try {
		    WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
		    PatientName1 = SelectedPatient.getText();
		    System.out.println("Patient Name = " + PatientName1);
		} catch (Exception e) {
		    //System.out.println("Patient Name not found: 0");
		    PatientName1 = "-"; // Set default value for PatientName1 if not found
		}

		// OutGoing  Process with Patient selection

		WebElement OutGoingbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		OutGoingbtn.click();
		Thread.sleep(2000);

		WebElement Discardedbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Discarded']")));
		Discardedbtn.click();
		Thread.sleep(2000);

		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys(note); // notes form excel sheet
		Thread.sleep(2000);

		WebElement patientmedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		patientmedication.click();
		Thread.sleep(2000);

		WebElement clicksearchbar = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		clicksearchbar.click();
		clicksearchbar.sendKeys(Patient_Name + Keys.ENTER); // patient name taking from excel sheet
		Thread.sleep(4000);

		WebElement selectpatient = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@class,'patient-result-info')]//p[1]")));
		selectpatient.click();
		Thread.sleep(2000);

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
			inputdata = "\n"+ "Transaction Type: " +action +"\n" + "Transferin Imprest Drug Name: "
					+ Medication_name + "\n" 
					+ "Patient Name:  " + Patient_Name + "\n"
					+ "Entry for this medication for selected patient is not found: " + "\n" + "Result:  " +NoMedication + "\n";
			;

			softAssert.assertEquals(NoMedication, Medication_name, "Medication name is not found in the dropdown list");
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

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();
		qty.sendKeys(Quantity);
		
		Thread.sleep(2000);

		WebElement addbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[4]/table[1]/tr[1]/td[2]/p[1]"));
		String add = AddedBalance.getText().trim();
		String add1 = add.replaceAll("\\(.*?\\)", "").trim();
		System.out.println("Out qty =  " + add1);
		Thread.sleep(1000);

		String numericAdd = add1.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int Remaining = intValue - abc;
		String RemainingasString = String.valueOf(Remaining);
		System.out.println("Remaining Balance = " + Remaining);
		Thread.sleep(2000);

		// Continue the outgoing process

		WebElement submitbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		submitbtn.click();
		Thread.sleep(2000);
		WebElement usernameInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput.click();
		usernameInput.clear();
		usernameInput.sendKeys(username);
		Thread.sleep(2000);

		WebElement enterpass = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		enterpass.sendKeys(pin);
		Thread.sleep(2000);

		WebElement pwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		pwd1.click();

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
		Thread.sleep(2000);
		
		WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		searchbtn.click();
		Thread.sleep(2000);
		
		String MedicationName2 = "0"; // Default value in case element not found
		//String stock1 = "0"; // Default value in case element not found
		String PatientName2 = "0";
		
		try {
			WebElement SelectedMedication1 = driver.findElement(By.xpath("//td[1]"));
			MedicationName2 = SelectedMedication1.getText();
			//System.out.println("Medication Name = " + MedicationName2);
		} catch (Exception e) {
			//System.out.println("Entry for this medication is not found");
		}
		
		Thread.sleep(3000);

		try {
			WebElement SelectedPatient1 = driver.findElement(By.xpath("//td[2]"));
			PatientName2 = SelectedPatient1.getText();
			System.out.println("Patient Name = " + PatientName2);
		} catch (Exception e) {
			//System.out.println("Patient Name not found: 0");
		}
		Thread.sleep(3000); 
		

		// Compare the values
		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);
		
		inputdata ="\n"+ "Transaction Type: " +action+ "\n" + "Transferin Imprest Drug Name: "
				+ Medication_name +"\n" + "Patient Name" + Patient_Name + "\n" + " Outgoing qty:  " + abc
				+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + RemainingasString + "\n";;
		

				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, RemainingasString, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
				softAssert.assertEquals(Patient_Name, PatientName2, "Patient Name mismatch");
				softAssert.assertAll();

//		if (RemainingasString.equals(numericStock1)) {
//			TestUtil.writeDataoutgoingpatient(rownum++, stock, RemainingasString, "Pass");
//		} else {
//			TestUtil.writeDataoutgoingpatient(rownum++, stock, RemainingasString, "Fail");
//		}

	}
}
