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
import test.Util.TestUtilOld;

public class DestructionImprest extends Base {
	private static NotificationPage notificationPage;


//	private static int rownum = 2;
//
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData = TestUtilOld.destructionWithImprest();
//		return testData.iterator();
//
//	}
//
//	@Test(dataProvider = "getTestData")
	public static void DestructionIMPREST(String action, String note, String Medication_name,  String Quantity, String username, String pin)
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
		    String RemainingasString = "-";
		    
		    // Write default values to Excel or perform any necessary action
		   // TestUtilOld.writeDatadestructionWithImprest(rownum++, stock, RemainingasString, "Fail");
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
		        inputdata = "\n"+ "Transaction Type: " +action + "\n"+ "Medication Name: " + Medication_name
		                + "\n" + "\n" + "Medication QTY is found: Zero " + stock + "\n";;

		        softAssert.assertEquals("0", stock, "Initial Stock is 0, that's why the process of transfer is not possible");
		        System.out.println(stock);
		        System.out.println(intValue);

		        softAssert.assertAll();
		        
		        return;
		    }else {
		    	//System.out.println("Continue");
		    }
		    System.out.println("Current Stock = " + stock);
		} catch (Exception e) {
		    e.printStackTrace(); // print the stack trace for debugging
		    System.out.println("Current Stock not found: 0");
		    stock = "-";
		}
		Thread.sleep(2000);

		// Perform the new Destruction process with Imprest option

		WebElement destructionbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='New Destruction']")));
		destructionbtn.click();
		Thread.sleep(2000);

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
		ClickONmedicationINPUT.click();
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(2000);
		
		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); //NO MEDICATION FOUND
		String NoMedication = nomedicationnamefound.getText();
		

		if ("No available options".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
			Thread.sleep(2000);
			inputdata =  "\n"+ "Transaction Type: " +action + "\n"+ "Medication Name: " + Medication_name 
					+ "\n" + "\n" + "Entry for this medication is not found: " + NoMedication + "\n";;
					
					softAssert.assertEquals(NoMedication, Medication_name, "final stock is not match with Expected stock");
					softAssert.assertAll();
			return;
		} else {
			System.out.println("Medication selected: " + Medication_name);
			WebElement clickonmedication = driver
					.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]")); // SELECT
																													// MEDICATION
			Thread.sleep(2000);
			clickonmedication.click(); // SELECT THE MEDICATION

		}
		Thread.sleep(2000);

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter qty']")));
		qty.click();
		qty.sendKeys(Quantity);
		Thread.sleep(2000);

		WebElement addclicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/table[1]/tr[1]/td[2]/p[1]"));
		String add = AddedBalance.getText().trim();

		// Extract the desired part (1 tablet) by removing the content within
		// parentheses
		String add1 = add.replaceAll("\\(.*?\\)", "").trim();
		System.out.println("Destruction qty =  " + add1);
		Thread.sleep(1000);

		String numericAdd = add1.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int Remaining = intValue - abc;
		String RemainingasString = String.valueOf(Remaining);
		System.out.println("Remaining Balance = " + RemainingasString);
		Thread.sleep(2000);

		// Continue the destruction process
		WebElement destroybtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Destroy']")));
		destroybtn.click();
		Thread.sleep(2000);
		
		WebElement usernameInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		usernameInput.click();
		usernameInput.clear();
		usernameInput.sendKeys(username);
		Thread.sleep(2000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		pwd.sendKeys(pin);
		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();
		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		//WebElement Searchbtn = wait
				//.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		//Searchbtn.click();
		//Thread.sleep(2000);
		
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
		System.out.println("Final Balance on the stoctake screen = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);
		
		inputdata = "\n"+ "Transaction Type: " +action +  "\n"+ "Medication Name: " + Medication_name 
				+ "\n" + "Quantity:  " + abc + "\n" + "Current Stock: "
				+ stock + "\n" + "Final Stock: " + numericStock1 + "\n";
		
				
				Object action1 = null;
				Object Task_Name = action1;
				
				softAssert.assertEquals(numericStock1, RemainingasString, "Final stock is not matching with Expected stock");
				softAssert.assertEquals(Medication_name, MedicationName2, "Resident Name mismatch");
				softAssert.assertAll();

//		if (RemainingasString.equals(numericStock1)) {
//			TestUtil.writeDatadestructionWithImprest(rownum++, stock, RemainingasString, "Pass");
//		} else {
//			TestUtil.writeDatadestructionWithImprest(rownum++, stock, RemainingasString, "Fail");
//		}
	}
}
