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

public class Outgoingimprest extends Base{
	
	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.OtgoingImprest();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void outgoingimprest(String Medication_name, String note, String quantity, String pin) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name);
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
		
		
		WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
		String MedicationName1 = SelectedMedication.getText();
		System.out.println("Medication Name = " + MedicationName1);
		Thread.sleep(2000);
	
		//Print the Available Balance 
		WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
		String stock = AvailableBalance.getText();
		String textToRemove = "g(s)";
		String modifiedString = stock.replace(textToRemove, "");
		int intValue = Integer.parseInt(modifiedString.trim());
        Integer intValue1 = Integer.valueOf(intValue);
        System.out.println("Medication Name = " + intValue1);
        Thread.sleep(2000);
        

		//Outgoing Process with IMprest selection 
		
		WebElement Outingbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		Outingbtn.click();
		Thread.sleep(2000);
		
		WebElement lostbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Lost']")));
		lostbtn.click();
		Thread.sleep(2000);
		
		WebElement addnotes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		addnotes.sendKeys(note);
		System.out.println(addnotes);
		Thread.sleep(2000);
		
		WebElement IMprestbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		IMprestbtn.click();
		Thread.sleep(2000);
		
		WebElement typemedicationNAME = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		typemedicationNAME.sendKeys(Medication_name + Keys.ENTER);
		System.out.println(typemedicationNAME);
		Thread.sleep(2000);
		
		WebElement SELECTmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Anti-Inflammatory Pain Relief (Apohealth) 1% gel']")));
		SELECTmedication.click();
		Thread.sleep(2000);
		
		WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter quantity']")));
		qty.sendKeys(quantity);
		System.out.println(qty);
		
		WebElement addclicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();

		
		//Print the entered qty
		
		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[1]/td[2]/p[1]")));
		String Add = AddedBalance.getText();
		System.out.println(Add);

		// Check if the string is not empty
		if (!Add.isEmpty()) {
			// Get the first character as an integer
			int firstCharacter = Character.getNumericValue(Add.charAt(0));

			// Print the first character
			System.out.println("Outgoing Quantity = " + firstCharacter);
			Integer result = intValue1 - firstCharacter;
			
			// Print the result
			System.out.println("Result = " + result);
		} else {
			System.out.println("The string is empty");
		}

		// Continue the outgoing process
		
		WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class,'regular-button complete-button')]")));
		submitbtn.click();
		Thread.sleep(2000);
		
		WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		pwd.sendKeys("1111");

		WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
		
		WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		searchbtn.click();
		
		
		//Compare the values 
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[4]")));
		int firstCharacter = Character.getNumericValue(Add.charAt(0));

		// Print the first character
		Integer result = intValue1 - firstCharacter;
		String stringByConcatenation =  result + "";
		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");


		if (modifiedString1.contentEquals(stringByConcatenation)) {
			TestUtil.writeDataOtgoingImprest(rownum++, stock, modifiedString1, "Pass");
		}else {
			TestUtil.writeDataOtgoingImprest(rownum++, stock, modifiedString1, "Fail");
		}
	}
}
