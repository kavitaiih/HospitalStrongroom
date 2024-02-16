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

public class AdjustmentWithImprest extends Base {
	private static NotificationPage notificationPage;


//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtilOld.adjustmentWithImprest();
//		return testData.iterator();
//
//	}
//
//	@Test(dataProvider = "getTestData")
	public static void AdjustmentWITHimprest(String action, String transaction_id, String note, String Medication_name,String Quantity, String username, String pin , String username1, String pin1)
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
		Thread.sleep(2000);

		WebElement ImprestOnly = driver.findElement(By.xpath("//p[normalize-space()='Display Imprest Only']"));
		ImprestOnly.click();
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
		

		try {
	        WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
	        MedicationName1 = SelectedMedication.getText();
	        System.out.println("Medication Name = " + MedicationName1);
	    } catch (Exception e) {
	        System.out.println("Entry for this medication is not found");
	        System.out.println("Medication Name Not found: 0");
	        
	        // Set default values for MedicationName1, stock, and RemainingasString
	        MedicationName1 = "-";
	        stock = "-";
	        String numericStock1 = "-";
	        
	        // Write default values to Excel or perform any necessary action
	       // TestUtilOld.writeDataadjustmentWithImprest(rownum++, stock, numericStock1, "Fail");
	        //return;// This will exit the current method, allowing the program to continue
	    }

		Thread.sleep(2000);
				
		try {
			WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
			stock = AvailableBalance.getText();
			String numericStock = stock.replaceAll("[^0-9]", "");
			int intValue = Integer.parseInt(numericStock);

			if (intValue == 0) {
				Thread.sleep(2000);
				inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Medication Name: " + Medication_name
						+ "\n" +  "Medication QTY is found: Zero " + stock
						+ "\n";
				

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

		//Adjustment process
		WebElement Adjustmentbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Adjustment']")));
		Adjustmentbtn.click();
		Thread.sleep(2000);
		
		WebElement PasteTransID = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Transaction ID...']")));
		PasteTransID.click();
		PasteTransID.sendKeys(transaction_id);

		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys(note);
		Thread.sleep(2000);

		WebElement ImprestBTN = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		ImprestBTN.click();
		Thread.sleep(2000);
		
		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(2000);

		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); // NO
		Thread.sleep(2000);
		// MEDICATION
		// FOUND
		String NoMedication = nomedicationnamefound.getText();

		if ("No available options".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
			Thread.sleep(2000);
			inputdata = "\n"+ "Transaction Type: " +action + "\n" +  "Medication Name: " + Medication_name + "\n"
					+  "Entry for this medication for selected patient is not found: " + NoMedication + "\n";
			

			softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
			softAssert.assertAll();
			return;
		} else {
			// System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]")); // SELECT
			//String Test = clickonmedication.getText();
			// System.out.println(Test);// MEDICATION
			Thread.sleep(2000);
			clickonmedication.click(); // SELECT THE MEDICATION

		}
		Thread.sleep(2000);

		WebElement addclicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();
		Thread.sleep(2000);
		
		WebElement EntermedicationAmount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
		EntermedicationAmount.click();

		// Convert quantity to an integer to remove the decimal part
		int quantityValue = (int) Double.parseDouble(Quantity);

		EntermedicationAmount.sendKeys(String.valueOf(quantityValue));
		System.out.println("Actual Qty = " + quantityValue);
		Thread.sleep(2000);

		// Print the difference qty

		WebElement DiffBalance = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[2]/td[4]"));
		String adjustmentQtyValue = DiffBalance.getText().trim();
		System.out.println("Adjustment Difference =  " +adjustmentQtyValue);
		Thread.sleep(2000);
		
		WebElement Submitbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		Submitbtn.click();
		Thread.sleep(2000);
		
		WebElement usernameInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput.click();
		usernameInput.clear();
		usernameInput.sendKeys(username);
		Thread.sleep(2000);

		WebElement pass = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
		pass.click();
		pass.sendKeys(pin);
		System.out.println(pin);
		Thread.sleep(2000);
		
		WebElement sign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']//div//div[1]//div[1]")));
		sign.click();
		Thread.sleep(2000);

		WebElement usernameInput1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body//div[@id='app']//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='modal-body']//div[2]//input[1]")));
		usernameInput1.click();
		System.out.println(username1);

		usernameInput1.sendKeys(username1);
		
		Thread.sleep(2000);
		
		WebElement Enterpass1 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-body']//div[2]//input[2]")));

		Enterpass1.sendKeys(pin1);
		

		WebElement sign1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]")));
		sign1.click();
		Thread.sleep(2000);
		
		WebElement Searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		Searchbtn.click();
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

		
		// Compare the values
		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);
		
		inputdata = "\n"+ "Transaction Type: " +action + "\n" +  "Transferin Imprest Drug Name: "
				+ Medication_name + "\n" +  "Adjustment quantity:  " + adjustmentQtyValue
				+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + numericStock1 + "\n";
		
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, quantityValue, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
				softAssert.assertAll();

//		if (numericStock1.equals(quantityValue)) {
//			TestUtilOld.writeDataadjustmentWithImprest(rownum++, stock, numericStock1, "Pass");
//		} else {
//			TestUtilOld.writeDataadjustmentWithImprest(rownum++, stock, numericStock1, "Fail");
//		}

	}

}
