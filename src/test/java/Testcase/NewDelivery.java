package Testcase;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.Util.TestUtil;

public class NewDelivery extends Base{

	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.newdelivary();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void newdelivary(String Medication_name, String supplier, String sessionId, String note, String quantity, String pin, String Current_Stock, String pRemaining_Balancein, String Status) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		
		//Check the stock on the medidcation
		

		WebElement medidcationfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medidcationfilter.click();
		medidcationfilter.sendKeys(Medication_name);
		Thread.sleep(2000);

		WebElement NotificationBTN = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='new-patient-button data-v-tooltip']//i[@class='pi pi-exclamation-circle']")));
		NotificationBTN.click();
		Thread.sleep(2000);

		WebElement SearchBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();
		Thread.sleep(2000);

		// Print the Present available balance of that Medicaiton

		WebElement AvailableBalance = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@style='width: 80px;']")));


		// Get the text content of the element and print it
		String stock = AvailableBalance.getText();
		System.out.println("Available Balance  " + stock);
		Thread.sleep(2000);

		//New Delivery Process

		WebElement NewDeliveryBTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='New Delivery']")));
		NewDeliveryBTN.click();
		Thread.sleep(2000);

		WebElement ClickonEnterSupplyINPUTfield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter supplier name']")));
		ClickonEnterSupplyINPUTfield.click();
		ClickonEnterSupplyINPUTfield.sendKeys(supplier);
		Thread.sleep(2000);

		WebElement SelectSupplier = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='SPL']")));
		SelectSupplier.click();
		Thread.sleep(2000);

		WebElement datePickerButton = driver.findElement(By.xpath("//input[@placeholder=' Date']"));
		datePickerButton.click();
		Thread.sleep(1000);

		// Calculate the target date (2 days from now)
		LocalDate currentDate = LocalDate.now();
		LocalDate targetDate = currentDate.plusDays(2);
		String targetDay = String.valueOf(targetDate.getDayOfMonth());

		// Use a relative XPath to locate the day after tomorrow in the calendar
		String xpathForTargetDate = "//td[not(@class='p-datepicker-other-month') and not(contains(@class,'p-disabled'))]//span[text()='"
				+ targetDay + "']";

		// Find and click on the WebElement representing the target date
		WebElement targetDateElement = driver.findElement(By.xpath(xpathForTargetDate));
		targetDateElement.click();

		Thread.sleep(1000);

		WebElement EnterRfncNumber = driver.findElement(By.xpath("//input[@placeholder='Reference Number']"));
		EnterRfncNumber.click();
		EnterRfncNumber.sendKeys(sessionId);
		Thread.sleep(1000);

		WebElement EnterNotes = driver.findElement(By.xpath("//textarea[@id='note-modal']"));
		EnterNotes.click();
		EnterNotes.sendKeys(note);
		Thread.sleep(1000);

		WebElement ClickonSelectMedication = driver.findElement(By.xpath("//input[@placeholder='Select Medication']"));
		ClickonSelectMedication.sendKeys(Medication_name);
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")).click();
		Thread.sleep(1000);
		

		WebElement qtyinput = driver.findElement(By.xpath("//input[@placeholder='Enter qty']"));
		qtyinput.click();
		qtyinput.sendKeys(quantity);
		Thread.sleep(1000);

		WebElement AddBTN = driver.findElement(By.xpath("//p[@class='submit-button blue-button']"));
		AddBTN.click();
		Thread.sleep(1000);


		//Print the entered qty
		
		
		WebElement AddedBalance = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/table[1]/tr[2]/td[4]/p[1]")));
		String add = AddedBalance.getText();
		System.out.println("New Delivered Qty = " + add);
		Thread.sleep(1000);
		

		int addedquantity = Integer.parseInt(add);
		int availablestock = Integer.parseInt(stock);

		int sum = addedquantity + availablestock;
		
		System.out.println("Total Balance : " + sum);
		WebElement ReceiveDlvryBTN = driver.findElement(By.xpath("//p[normalize-space()='Receive Delivery']"));
		ReceiveDlvryBTN.click();
		Thread.sleep(2000);

		WebElement EnterPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));
		EnterPassword.sendKeys(pin);
		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();
		Thread.sleep(2000);

		WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();
		Thread.sleep(2000);

		
		WebElement ArroBTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[2]")));
		ArroBTN.click();
		Thread.sleep(2000);
		
		WebElement stockclick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockclick.click();
		Thread.sleep(2000);
		

		WebElement stocktakeclick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stocktakeclick.click();

		WebElement medicationfilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();
		medicationfilter.sendKeys(Medication_name);
		Thread.sleep(2000);

		WebElement includeS8btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		includeS8btn.click();
		Thread.sleep(1000);

		WebElement Displaybtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		Displaybtn.click();
		Thread.sleep(1000);

		WebElement CLICKsearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		CLICKsearch.click();
		Thread.sleep(2000);

		String AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[4]"))).getText();
		String stringByConcatenation = sum + "";
		String textToRemove = "tablet(s)";

		// Remove text using replace()
		String modifiedString = stringByConcatenation.replace(textToRemove, "");
		System.out.println("Total New Balance  =  " + modifiedString);

		if (AddedBalance1.contentEquals(modifiedString)) {
			TestUtil.writeDatanewdelivary(rownum++, AddedBalance1, modifiedString, "Pass"); 
			} 
		
		else 
		{
			TestUtil.writeDatanewdelivary(rownum++, AddedBalance1, modifiedString, "Fail");
			}
		
		
	}
}
