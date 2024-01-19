package Testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.Util.TestUtil;


public class NewDestructionImprest extends Base {

	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.destruction();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void destruction(String Medication_name, String note, String quantity, String pin ) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
	
		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//p[normalize-space()='Stocktake']")).click();
		Thread.sleep(2000);

		// New code to read medication name from Excel
		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name);
		System.out.println(Medication_name);
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

		//Print the Available Balance 

		WebElement SelectedMedication = driver.findElement(By.xpath("//td[1]"));
		String MedicationName1 = SelectedMedication.getText();
		System.out.println("Medication Name = " + MedicationName1);
		Thread.sleep(2000);
		
		// Get the text content of the element and print it
		WebElement AvailableBalance = driver.findElement(By.xpath("//td[4]"));
		String stock = AvailableBalance.getText();
		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());
		Integer intValue1 = Integer.valueOf(intValue);
		System.out.println("Current Stock = " + intValue1);
		Thread.sleep(2000);

		//Perform the new Destruction process with Imprest option 	

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

		WebElement SelectMedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/ul/li/div")));
		SelectMedication.click();
		Thread.sleep(2000);
		

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter qty']")));
		qty.sendKeys(quantity);
		Thread.sleep(2000);

		WebElement addclicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();
		Thread.sleep(2000);

		//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/table[1]/tr[1]/td[2]/p[1]")));
		String Add = AddedBalance.getText();
		// Check if the string is not empty
		if (!Add.isEmpty()) {
			// Get the first character as an integer
			int firstCharacter = Character.getNumericValue(Add.charAt(0));

			// Print the first character
			System.out.println("Destroy Quantity = " + firstCharacter);

			Integer result = intValue1 - firstCharacter;

			// Print the result
			System.out.println("New stock qty after destruction = " + result);
		} else {
			System.out.println("The string is empty");
		}
		
		
		//Continue the destruction process		
		WebElement destroybtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Destroy']")));
		destroybtn.click();
		Thread.sleep(2000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		pwd.sendKeys("1111");
		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();
		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);
		WebElement Searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		Searchbtn.click();
		Thread.sleep(2000);
		


		// Compare the values

		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[4]")));
		int firstCharacter = Character.getNumericValue(Add.charAt(0));

		// Print the first character
		Integer result = intValue1 - firstCharacter;
		String stringByConcatenation = result + "";
		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");
		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + result);


		if (modifiedString1.contentEquals(stringByConcatenation)) {
			System.out.println(
					"Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched");
		}

		else {
			System.out.println(
					"Quantity of /edication after transaction in the  Drug Register Screen and Stocktake Screen is not matched");
		}
		Thread.sleep(2000);
		
		//Go to the destruction tab and check the entry of that particular medication
		
		driver.findElement(By.xpath("//p[normalize-space()='Destructions']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Medication...']")).sendKeys(Medication_name);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[@class='select-filter-item']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//i[@class='pi pi-angle-right']")).click();
		Thread.sleep(2000);
		
		if (modifiedString1.contentEquals(stringByConcatenation)) {
			TestUtil.writeDatadestruction(rownum++, stock, modifiedString1, "Pass");
		}else {
			TestUtil.writeDatadestruction(rownum++, stock, modifiedString1, "Fail");
		}
	}
}
 