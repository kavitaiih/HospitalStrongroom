package Testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.Util.TestUtil;

public class OutgoingPatient extends Base{

	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.outgoingpatient();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void outgoingpatient(String Medication_name, String Patient_Name, String note, String quantity, String pin) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name);
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
		

		//Print the Available Balance 
		//WebElement AvailableBalance = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));
		
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
		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");
		Thread.sleep(2000);
		
		int intValue = Integer.parseInt(modifiedString.trim());
		Integer intValue1 = Integer.valueOf(intValue);
		System.out.println("Current Stock = " + intValue1);
		Thread.sleep(2000);
		
		//Transfer OUT Process with Patient selection			

		WebElement OutGoingbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		OutGoingbtn.click();
		Thread.sleep(2000);
		
		WebElement Discardedbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Discarded']")));
		Discardedbtn.click();
		Thread.sleep(2000);
		
		WebElement notes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys(note);
		System.out.println(note);
		Thread.sleep(2000);
		
		WebElement patientmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		patientmedication.click();
		Thread.sleep(2000);
		
		WebElement clicksearchbar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		clicksearchbar.click();
		clicksearchbar.sendKeys(Patient_Name + Keys.ENTER);
		Thread.sleep(4000);
		
		WebElement selectpatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'patient-result-info')]//p[1]")));
		selectpatient.click();
		Thread.sleep(2000);
		
		WebElement medicationdrp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		medicationdrp.click();
		Thread.sleep(2000);
		
		WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='doxazosin 4 mg tablet']")));
		selectmedication.click();
		Thread.sleep(2000);
		
		WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();
		qty.sendKeys(quantity);
		System.out.println(quantity);
		Thread.sleep(2000);
		
		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();

		//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[4]/table/tr/td[2]")));
		String Add = AddedBalance.getText();
		Thread.sleep(2000);
		
		// Check if the string is not empty
		if (!Add.isEmpty()) {
			// Get the first character as an integer
			int firstCharacter = Character.getNumericValue(Add.charAt(0));
			// Print the first character
			System.out.println("Outgoing Quantity = " + firstCharacter);
			Integer result = intValue1 - firstCharacter;
			Thread.sleep(2000);
			
			// Print the result
			System.out.println("Result = " + result);
		} else {
			System.out.println("The string is empty");
		}
		

		// Continue the outgoing process

		WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		submitbtn.click();
		Thread.sleep(2000);
		
		WebElement enterpass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		enterpass.sendKeys("1111");
		Thread.sleep(2000);
		
		WebElement pwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		pwd1.click();

		WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
	
		//Compare the values
	
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[4]")));
		int firstCharacter = Character.getNumericValue(Add.charAt(0));
		Thread.sleep(2000);
		
		// Print the first character
		Integer result = intValue1 - firstCharacter;
		String stringByConcatenation =  result + "";
		String textToRemove1 = "tablet(s)";
		Thread.sleep(2000);
		
		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");
		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + result);
;

		if (modifiedString1.contentEquals(stringByConcatenation)) {
			TestUtil.writeDataoutgoingpatient(rownum++, stock, modifiedString1, "Pass");
		}else {
			TestUtil.writeDataoutgoingpatient(rownum++, stock, modifiedString1, "Fail");
		}
		Thread.sleep(2000);
		
	}
}
