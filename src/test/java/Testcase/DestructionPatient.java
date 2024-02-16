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
import org.testng.asserts.SoftAssert;
import objects.NotificationPage;

import org.testng.annotations.Test;

import test.Util.TestUtil;

public class DestructionPatient extends Base {
	private static NotificationPage notificationPage;

//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData =TestUtilOld.DestructionwithPatient();
//		return testData.iterator();
//		
//	}
//
//	@Test(dataProvider="getTestData")
	public static void destructionPatient(String action, String location, String Medication_name, String transaction_id, String note,  String Patient_Name, String Prescriber_Name, String Quantity, String username, String pin) 
			throws InterruptedException {
		
		// Print the Present available balance of that Medicaiton on the Stocktake 
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
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).sendKeys(Patient_Name); // Patient name from Excel
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
	    String PatientName1 = "0";  //Default value in case element not found

		
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
	       // TestUtilOld.writeDataDestructionwithPatient(rownum++, stock, RemainingasString, "Fail");
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
				//TaskName = action ;
				inputdata ="\n"+ "Transaction Type: " +action +  "\n" + "Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name
						+ "\n" + "Patient Name: " + Patient_Name + "\n" + "Medication QTY is found: Zero " + stock
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

		try {
	        WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
	        PatientName1 = SelectedPatient.getText();
	        System.out.println("Patient Name = " + PatientName1);
	    } catch (Exception e) {
	        System.out.println("Patient Name not found: 0");
	    }

		Thread.sleep(2000);
				
		//Destruction Process

		driver.findElement(By.xpath("//button[normalize-space()='New Destruction']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note);
		
		driver.findElement(By.xpath("//p[normalize-space()='Patient Medication']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")).sendKeys(Patient_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[normalize-space()='Search']")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//div[@class='patient-result-info']")).click();
		Thread.sleep(2000);

		WebElement clickmedication = driver
				.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder']"));
		clickmedication.click();

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/input[1]")));
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(3000);

		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]/li[1]")); // NO
																														// MEDICATION
																														// FOUND
		String NoMedication = nomedicationnamefound.getText();

		if ("No results found".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
			Thread.sleep(2000);
			//TaskName = action ;
			inputdata = "\n"+ "Transaction Type: " +action + "\n" +"Transfer out Location: " + location + "\n" + "Medication Name: " + Medication_name + "\n"
					+ "Patient Name" + Patient_Name + "\n"
					+ "Entry for this medication for selected patient is not found: " + NoMedication + "\n";
			;

			softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
			softAssert.assertAll();
			return;
		} else {
			// System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]/li[1]/div[1]/div[1]/div[1]")); // SELECT
			clickonmedication.click(); // SELECT THE MEDICATION
			Thread.sleep(2000);
		}

		// Close the dropdown (assuming it opens after selection)

		WebElement quantityInput = driver.findElement(By.xpath("//input[@placeholder='Quantity']"));
		quantityInput.click();
		quantityInput.clear();
		quantityInput.sendKeys(Quantity);

		driver.findElement(By.xpath("//p[@class='submit-button blue-button']")).click();
		Thread.sleep(2000);

		//Print the entered qty

		WebElement AddedBalance = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[3]/table/tr/td[2]/p"));	
		String add = AddedBalance.getText().trim();
		
		// Extract the desired part (1 tablet) by removing the content within parentheses
		String add1 =  add.replaceAll("\\(.*?\\)", "").trim();
		System.out.println("Destruction qty =  " + add1);
		Thread.sleep(1000);
		
		String numericAdd = add1.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int Remaining = intValue - abc;
		String RemainingasString = String.valueOf(Remaining);
		System.out.println("Remaining Balance = " + Remaining);
		Thread.sleep(2000);

		// Continue the process of transfer in

		driver.findElement(By.xpath("//p[normalize-space()='Destroy']")).click();
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
		
		driver.findElement(By.xpath("//button[@class='button submit-button']")).click(); //click on search button stocktake screen
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
	
		//Compare the values 
				WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
				String stock2 = AvailableBalance1.getText();
				String numericStock1 = stock2.replaceAll("[^0-9]", "");
				System.out.println("Final Balance on the stoctake screen = " + numericStock1);
				System.out.println("---------------------------------------------------");

				Thread.sleep(3000);
				//TaskName = action ;
				inputdata = "\n"+ "Transaction Type: " +action + "\n" + "Transfer In Imprest Location: " + location + "\n" + "Transferin Imprest Drug Name: "
						+ Medication_name + "Patient Name" + Patient_Name + "\n" + "Transferin Imprest in quantity:  " + abc
						+ "\n" + "Current Stock: " + stock + "\n" + "Final Stock: " + RemainingasString + "\n";;
						
						Object action1 = null;
						Object Task_Name = action1;
						
						softAssert.assertEquals(numericStock1, RemainingasString, "final stock is not match with Expected stock");
						softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
						softAssert.assertEquals(Patient_Name, PatientName2, "Patient Name mismatch");	
						softAssert.assertAll();


//
//				if (RemainingasString.equals(numericStock1)) {
//					TestUtil.writeDataDestructionwithPatient(rownum++, stock, RemainingasString, "Pass");
//				}else {
//					TestUtil.writeDataDestructionwithPatient(rownum++, stock, RemainingasString, "Fail");
//				}
	}
}