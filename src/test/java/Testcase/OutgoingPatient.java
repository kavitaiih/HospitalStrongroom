package Testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OutgoingPatient extends Base{

	@Test
	public void outgoingpatient() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		WebElement Stock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();

		WebElement StockTake = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
		StockTake.click();

		WebElement medicationfilter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();
		medicationfilter.sendKeys("doxazosin 4 mg tablet");

		WebElement Patientfilter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("kammo");

		WebElement clickonDisplyStock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		clickonDisplyStock.click();

		WebElement clickonIncluseS8 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
		clickonIncluseS8.click();

		WebElement SearchBTN = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();

		

		//Print the Available Balance 
		WebElement AvailableBalance = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());
		Integer intValue1 = Integer.valueOf(intValue);
		System.out.println("Current Stock = " + intValue1);

		//Transfer OUT Process with Patient selection			

		WebElement OutGoingbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		OutGoingbtn.click();

		WebElement Discardedbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Discarded']")));
		Discardedbtn.click();

		WebElement notes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys("Notes Will be here");

		WebElement patientmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		patientmedication.click();

		WebElement clicksearchbar = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		clicksearchbar.click();
		clicksearchbar.sendKeys("k" + Keys.ENTER);

		WebElement selectpatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'patient-result-info')]//p[1]")));
		selectpatient.click();

		WebElement medicationdrp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		medicationdrp.click();

		WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='doxazosin 4 mg tablet']")));
		selectmedication.click();

		WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();
		qty.sendKeys("1");

		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();

		//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[4]/table/tr/td[2]")));
		String Add = AddedBalance.getText();

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

		WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		submitbtn.click();

		WebElement enterpass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		enterpass.sendKeys("1111");

		WebElement pwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		pwd1.click();

		WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
	
		//Compare the values
	
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));
		int firstCharacter = Character.getNumericValue(Add.charAt(0));

		// Print the first character
		Integer result = intValue1 - firstCharacter;
		String stringByConcatenation =  result + "";
		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");
		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + result);
;

		if (modifiedString1.contentEquals(stringByConcatenation)) {
			System.out.println(
					"Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched");
		}else {
			System.out.println(
					"Quantity of /edication after transaction in the  Drug Register Screen and Stocktake Screen is not matched");
		}
		Thread.sleep(2000);
		
	}
}
