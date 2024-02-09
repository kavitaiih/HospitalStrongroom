package Testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.Util.TestUtil;

public class AdjustmentwithPatient extends Base {

	private static int rownum = 2;

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtil.adjustmentWithPatient();
		return testData.iterator();

	}

	@Test(dataProvider = "getTestData")
	public void AdjustmentWITHpatient(String Medication_name, String Patient_Name,  String quantity, String Current_Stock, String Final_Balance, String pin, String note, String Status)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		// Print the Present available balance of that Medicaiton on the Stocktake 
				
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
			        String numericStock1 = "-";
			        
			        // Write default values to Excel or perform any necessary action
			        TestUtil.writeDataadjustmentWithPatient(rownum++, stock, numericStock1, "Fail");
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
		
		//Adjustment process
		WebElement Adjustmentbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Adjustment']")));
		Adjustmentbtn.click();
		Thread.sleep(2000);
		
		WebElement PasteTransID = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Transaction ID...']")));
		PasteTransID.click();
		PasteTransID.sendKeys("123");

		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys(note);
		Thread.sleep(2000);

		WebElement Patientbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		Patientbtn.click();
		Thread.sleep(2000);
		
		WebElement enterpatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		enterpatient.sendKeys(Patient_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[normalize-space()='Search']")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//div[@class='patient-result-info']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder']")).click();
		Thread.sleep(2000);

		WebElement ClickONmedicationINPUT = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='p-dropdown-filter p-inputtext p-component']")));
		ClickONmedicationINPUT.click();
		ClickONmedicationINPUT.sendKeys(Medication_name);
		Thread.sleep(2000);
		
		WebElement nomedicationnamefound = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")); //NO MEDICATION FOUND
		String NoMedication = nomedicationnamefound.getText();
		
		if ("No available options".equals(NoMedication)) {
			System.out.println("Medication not selected: " + NoMedication);
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

		WebElement addclicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();
		Thread.sleep(2000);
		
		WebElement EntermedicationAmount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
		EntermedicationAmount.click();

		// Convert quantity to an integer to remove the decimal part
		int quantityValue = (int) Double.parseDouble(quantity);

		EntermedicationAmount.sendKeys(String.valueOf(quantityValue));
		System.out.println("Actual Qty = " + quantityValue);


		Thread.sleep(2000);

		// Print the entered qty

		WebElement DiffBalance = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[2]/td[4]"));
		String adjustmentQtyValue = DiffBalance.getText().trim();
		System.out.println("Adjustment Difference =  " +adjustmentQtyValue);
		Thread.sleep(2000);
		
		WebElement Submitbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		Submitbtn.click();
		Thread.sleep(2000);

		WebElement pass1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
		pass1.click();
		pass1.sendKeys("1111");		
		Thread.sleep(2000);

		WebElement sign1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']//div//div[1]//div[1]")));
		sign1.click();
		Thread.sleep(2000);

		WebElement EnterUSERNAME1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//body//div[@id='app']//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='modal-body']//div[2]//input[1]")));

		EnterUSERNAME1.sendKeys("nathan");

		WebElement Enterpass1 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-body']//div[2]//input[2]")));

		Enterpass1.sendKeys("1111");

		Thread.sleep(2000);

		WebElement sign2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		sign2.click();

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		WebElement Searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		Searchbtn.click();
		Thread.sleep(2000);
		

		// Compare the values
		WebElement AvailableBalance1 = driver.findElement(By.xpath("//td[4]"));
		String stock2 = AvailableBalance1.getText();
		String numericStock1 = stock2.replaceAll("[^0-9]", "");
		System.out.println("Final Balance = " + numericStock1);
		System.out.println("---------------------------------------------------");

		Thread.sleep(3000);

		if (numericStock1.equals(quantityValue)) {
			TestUtil.writeDataadjustmentWithPatient(rownum++, stock, numericStock1, "Pass");
		} else {
			TestUtil.writeDataadjustmentWithPatient(rownum++, stock, numericStock1, "Fail");
		}

	}

}
