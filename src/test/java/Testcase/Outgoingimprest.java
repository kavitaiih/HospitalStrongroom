package Testcase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Outgoingimprest extends Base{
	
	@Test
	public void outgoingimprest() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		WebElement Stock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();

		WebElement StockTake = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
		StockTake.click();

		WebElement medicationfilter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();
		medicationfilter.sendKeys("Anti-Inflammatory Pain Relief (Apohealth) 1% gel");

		WebElement ImprestOnly = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		ImprestOnly.click();

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
		String textToRemove = "g(s)";
		String modifiedString = stock.replace(textToRemove, "");
		int intValue = Integer.parseInt(modifiedString.trim());

        Integer intValue1 = Integer.valueOf(intValue);
		System.out.println("Current Stock = " + intValue1);
		

		//Outgoing Process with IMprest selection 
		
		WebElement Outingbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		Outingbtn.click();

		WebElement lostbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Lost']")));
		lostbtn.click();

		WebElement addnotes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		addnotes.sendKeys("Notes Will be here");

		WebElement IMprestbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		IMprestbtn.click();

		WebElement typemedicationNAME = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		typemedicationNAME.click();
		typemedicationNAME.sendKeys("a" + Keys.ENTER);

		WebElement SELECTmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Anti-Inflammatory Pain Relief (Apohealth) 1% gel']")));
		SELECTmedication.click();

		WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter quantity']")));
		qty.sendKeys("1");

		WebElement addclicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();

		
		//Print the entered qty
		
		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[1]/td[2]/p[1]")));
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
		
		WebElement submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class,'regular-button complete-button')]")));
		submitbtn.click();

		WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		pwd.sendKeys("1111");

		WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
		
		WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		searchbtn.click();
		
		
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


		if (modifiedString1.contentEquals(stringByConcatenation)) {
			System.out.println(
					"Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched");
		}else {
			System.out.println(
					"Quantity of /edication after transaction in the  Drug Register Screen and Stocktake Screen is not matched");
		}
	}
}
