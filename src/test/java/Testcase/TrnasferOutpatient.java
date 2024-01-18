package Testcase;

import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Util.TestUtil;

public class TrnasferOutpatient extends Base {
	private static int rownum = 2;

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.TRnasferoutPATIENT();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void transferoutpatient(String Medication_name, String Patient_Name, String Prescriber_Name, String Quantity, String Current_Stock, String Remaining_Balance, String pin, String note, String location, String Status) throws InterruptedException {
		
		// Print the Present available balance of that Medicaiton on the Stocktake 

		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name); // Medication name from Excel
		Thread.sleep(2000);
		
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
	    //String PatientName1 = "0";  //Default value in case element not found

		
	    try {
	        WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
	        MedicationName1 = SelectedMedication.getText();
	        System.out.println("Medication Name = " + MedicationName1);
	    } catch (Exception e) {
	        System.out.println("Medication Name Not found: 0");
	    }

		Thread.sleep(2000);
					
		
		try {
	        WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
	        stock = SelectedPatient.getText();
	        System.out.println("Patient Name = " + stock);
	    } catch (Exception e) {
	        System.out.println("Patient Name not found: 0");
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

		driver.findElement(By.xpath("//button[normalize-space()='Transfer Out']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Type in location to send to']")).sendKeys("Emergency Ward");

		driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']")).click();

		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys("Notes Will be here");
		
		driver.findElement(By.xpath("//p[normalize-space()='Patient Medication']")).click();
		

		driver.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")).sendKeys(Patient_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[normalize-space()='Search']")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//div[@class='patient-result-info']")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")).sendKeys(Prescriber_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[3]")).click();
		Thread.sleep(2000);
		
		
		WebElement Prescriber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p[1]"));
		String PrisName = Prescriber.getText();
		System.out.println(PrisName);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@class='p-dropdown-filter p-inputtext p-component']")).sendKeys(Medication_name);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/ul[1]")).click();
		Thread.sleep(2000);


		WebElement quantityInput = driver.findElement(By.xpath("//input[@placeholder='Quantity']"));
		quantityInput.click();
		quantityInput.clear();
		quantityInput.sendKeys(Quantity);

		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
		Thread.sleep(2000);

		//Print the entered qty

		WebElement AddedBalance = driver.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
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

		// Continue the process of transfer in

		driver.findElement(By.xpath("//p[normalize-space()='Send Transfer']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1111");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='green-button']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//h3[normalize-space()='Complete']")).click();
		Thread.sleep(2000);
		
		
		if(RemainingasString == stock ) {
			TestUtil.writeDataTRnasferoutPATIENT(rownum++, stock, RemainingasString, "Pass");	
		}else {
			TestUtil.writeDataTRnasferoutPATIENT(rownum++, stock, RemainingasString, "Fail");	
		}
	}
}