package Testcase;


import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Util.TestUtil;


public class TransferinPatientExcel extends Base{

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.DataTransferInWithPatient();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
    public void transferwithpatient(String Medication_name,String Patient_Name, String Prescriber_Name, String Quantity, String Current_Stock, String Total_Balance) throws InterruptedException {
				    

		// Print the Present available balance of that Medicaiton on the Stocktake 
		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name);
		System.out.println(Medication_name);
		Thread.sleep(2000);
					
		driver.findElement(By.xpath("//input[@placeholder='Patient...']")).sendKeys(Patient_Name);
		System.out.println(Patient_Name);
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

		//Print the Available Balance and selected Medication, and patient name
					
		WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
		String MedicationName1 = SelectedMedication.getText();
		System.out.println("Medication Name = " + MedicationName1);
		Thread.sleep(2000);
	
        WebElement SelectedPatient = driver.findElement(By.xpath("//td[2]"));
		String PatientName1 = SelectedPatient.getText();
		System.out.println("Patient Name = " + PatientName1);
		Thread.sleep(2000);
		

		WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
		String stock = AvailableBalance.getText();
		System.out.println("Current Stock = " + stock);
		Thread.sleep(2000);

		
		// Transfer In Process

		WebElement transferInBtn = driver.findElement(By.xpath("//button[normalize-space()='Transfer In']"));
		transferInBtn.click();
		Thread.sleep(2000);

		WebElement locationToReceiveFromInput =driver.findElement(By.xpath("//input[@placeholder='Type in location to receive from']"));
		locationToReceiveFromInput.sendKeys("Emergency Ward");

		WebElement locationDropdown = driver.findElement(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
		locationDropdown.click();
		Thread.sleep(2000);

		WebElement locationSelect = driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']"));
		locationSelect.click();

		WebElement notesInput = driver.findElement(By.xpath("//textarea[@id='note-modal']"));
		notesInput.sendKeys("Notes Will be here");
		
		WebElement patientbtn = driver.findElement(By.xpath("//p[normalize-space()='Patient Medication']"));
		patientbtn.click();
		

		WebElement patientname = driver.findElement(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']"));
		patientname.click();
		patientname.sendKeys(Patient_Name);
		Thread.sleep(2000);
		
		WebElement ClickonsearchBTN = driver.findElement(By.xpath("//p[normalize-space()='Search']"));
		ClickonsearchBTN.click();
		Thread.sleep(2000);
		
		
		WebElement ClickonPatient = driver.findElement(By.xpath("//div[@class='patient-result-info']"));
		ClickonPatient.click();
		Thread.sleep(2000);
		
		
		WebElement PrescRIBERINPUT = driver.findElement(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']"));
		PrescRIBERINPUT.sendKeys(Prescriber_Name);	
		Thread.sleep(2000);
		
		WebElement Selectprescriber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[3]"));
		Selectprescriber.click();
		Thread.sleep(2000);
		
		
		WebElement Prescriber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/div[1]/p[1]"));
		String PrisName = Prescriber.getText();
		System.out.println(PrisName);
		Thread.sleep(1000);
		
		
		WebElement medicationInput = driver.findElement(By.xpath("//input[@placeholder='Select Medication']"));
		medicationInput.sendKeys(Medication_name);
		Thread.sleep(2000);

		WebElement selectMedication = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]"));
		selectMedication.click();
		Thread.sleep(2000);

		WebElement quantityInput = driver.findElement(By.xpath("//input[@placeholder='Qty...']"));
		quantityInput.sendKeys(Quantity);

		WebElement addBtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
		addBtn.click();
		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = driver.findElement(By.xpath("//div[@class='right-form-section-drug-container']//span[1]"));
		String add = AddedBalance.getText().trim();
		System.out.println("Added qty =  " + add);
		Thread.sleep(1000);

		String numericAdd = add.replaceAll("[^0-9]", "");
		String numericStock = stock.replaceAll("[^0-9]", "");

		int intValue = Integer.parseInt(numericStock);
		int abc = Integer.parseInt(numericAdd);
		int sum = intValue + abc;

		System.out.println("Total Balance = " + sum);
		Thread.sleep(2000);

		// Continue the process of transfer in

		WebElement receiveTransferBtn = driver.findElement(By.xpath("//p[normalize-space()='Receive Transfer']"));
		receiveTransferBtn.click();
		Thread.sleep(2000);

		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput.sendKeys("1111");
		Thread.sleep(2000);

		WebElement signInBtn = driver.findElement(By.xpath("//div[@class='green-button']"));
		signInBtn.click();
		Thread.sleep(2000);

		WebElement completeBtn = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
		completeBtn.click();

		}    
}
