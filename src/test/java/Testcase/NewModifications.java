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

public class NewModifications extends Base {
//	private static int rownum = 2;
//	
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData =TestUtil.TransferoutImprestFromExcelSheet();
//		return testData.iterator();
//	}
//
//	@Test(dataProvider="getTestData")
	public static void transferOutImprest(String action, String location, String note, String Medication_name, String Quantity, String username, String pin ) 
			throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000)); 
		SoftAssert softAssert = new SoftAssert();
		TaskName = action ;
		Thread.sleep(3000);

		WebElement Draftmsg = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/h3[1]")); // NO
		Thread.sleep(2000);
		// MEDICATION
		// FOUND
		String DraftFound = Draftmsg.getText();

		if ("A draft form was found: Transfer Out".equals(DraftFound)) {
			System.out.println("Draft transaction found: " + DraftFound);
			Thread.sleep(2000);
			WebElement Draftmsgclosebtn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/h3[1]"));
			Draftmsgclosebtn.click(); 
			Thread.sleep(2000);
			
		} 
		

		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/a[2]/p[1]")).click();
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

		driver.findElement(By.xpath("//p[normalize-space()='Display In Stock Only']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Include S8']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//p[normalize-space()='Display Imprest Only']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@class='button submit-button']")).click();
		Thread.sleep(2000);
	
		//Print the Available Balance and selected Medication
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
		    String RemainingasString = "-";
		    
		    // Write default values to Excel or perform any necessary action
		   // TestUtil.writeDataTransferoutImprestFromExcelSheet(rownum++, stock, RemainingasString, "Fail");
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
		        inputdata = "\n"+ "Transaction Type: " + action + "\n" + "Transfer In Imprest Location: " + location + "\n" + "Medication Name: " + Medication_name
		                + "\n" + "\n" + "Medication QTY is found: Zero " + stock + "\n";;

		        softAssert.assertEquals("0", stock, "Initial Stock is 0, that's why the process of transfer out is not possible");
		        System.out.println(stock);
		        System.out.println(intValue);

		        softAssert.assertAll();
		        
		       // return;
		    }else {
		    	//System.out.println("Continue");
		    }
		    System.out.println("Current Stock = " + stock);
		} catch (Exception e) {
		    e.printStackTrace(); // print the stack trace for debugging
		    System.out.println("Current Stock not found: 0");
		    stock = "-";
		}

		//Transfer In Process

		driver.findElement(By.xpath("//button[normalize-space()='Transfer Out']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Type in location to send to']")).sendKeys(location);
		

		driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']")).click();
		
		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note);

		driver.findElement(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")).click();
		Thread.sleep(2000);
		
		////input[@placeholder='Type medication name here']

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(3000);

		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); // NO
		Thread.sleep(2000);
		// MEDICATION
		// FOUND
		String NoMedication = nomedicationnamefound.getText();

		if ("No available options".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
			Thread.sleep(2000);
			inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
					
					+ "Entry for this medication for selected patient is not found: " + NoMedication + "\n";
			
			softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
			softAssert.assertAll();
			return;
		} else {
			// System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]")); // SELECT
			clickonmedication.click(); // SELECT THE MEDICATION

		}
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Qty...']")).sendKeys(Quantity);
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
		Thread.sleep(2000);

		//Print the entered qty

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
			
			WebElement errormsg = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div[2]/div/div/div[2]/p[3]")));
			String Errormsg = errormsg.getText();
			Thread.sleep(2000);
			
		    System.out.println(Errormsg);
		    Thread.sleep(2000);
		    
		    WebElement closepopup = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[1]/i[1]")));
		    closepopup.click();
		    Thread.sleep(2000);
		    
		    WebElement cancelbtn = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='regular-button error-button']")));
		    cancelbtn.click();
		    Thread.sleep(2000);
		    
			
		    // Display an error message or take appropriate actions
		    System.out.println("Error: Stock is less than Qty. Cannot proceed.");
            inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
					
					+ "Out qty: " + Quantity + "\n" + "Error Message: " + Errormsg;
			
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
		
		inputdata = "\n"+ "Transaction Type: " +action +  "\n" + "Transfer In Imprest Location: " + location + "\n" + "Medication Name: " + Medication_name 
				+ "\n" + "Out Quantity:  " + abc + "\n" + "Current Stock: "
				+ stock + "\n" + "Final Stock: " + RemainingasString + "\n";;
		
				
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, RemainingasString, "final stock is not match with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
				softAssert.assertAll();

				
//		if(RemainingasString.equals(numericStock1) ) {
//			TestUtil.writeDataTransferoutImprestFromExcelSheet(rownum++, stock, RemainingasString, "Pass");	
//		}else {
//			TestUtil.writeDataTransferoutImprestFromExcelSheet(rownum++, stock, RemainingasString, "Fail");	
//		}
	}
}