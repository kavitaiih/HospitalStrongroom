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

public class AdjustmentImprest extends Base{

	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.adjustmentImprest();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void adjustmentImprest(String Medication_name, String username, String sessionId, String note, String quantity, String pin, String Current_Stock, String pRemaining_Balancein, String Status) throws InterruptedException {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		
		//Process of Adjustment
		WebElement Adjusmentbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Adjustment']")));
		Adjusmentbtn.click();
		Thread.sleep(2000);

		WebElement PasteTransID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Transaction ID...']")));
		PasteTransID.click();
		PasteTransID.sendKeys(sessionId);
		Thread.sleep(2000);

		WebElement addnotes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		addnotes.sendKeys(note);
		Thread.sleep(2000);
		
		WebElement ClickonImpresbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		ClickonImpresbtn.click();
		
		WebElement medicationdrp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		medicationdrp.click();
		medicationdrp.sendKeys(Medication_name);
		Thread.sleep(2000);

		WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[1]/li[1]")));
		selectmedication.click();
		Thread.sleep(2000);

		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();
		Thread.sleep(2000);
	
		WebElement EntermedicationAmount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
		EntermedicationAmount.click();
		EntermedicationAmount.sendKeys(quantity);
		Thread.sleep(3000);
		
		//Print the adjustment amount
		WebElement adjustmentQty = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[2]/td[3]/input[1]"));
		String adjustmentQtyValue = adjustmentQty.getAttribute("value");
		System.out.println("Adjustment qty =  " +adjustmentQtyValue);
		Thread.sleep(1000);

		WebElement Submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		Submitbtn.click();
		Thread.sleep(2000);

		WebElement pass1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
		pass1.click();
		pass1.sendKeys("1111");
		Thread.sleep(2000);

		WebElement sign1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']//div//div[1]//div[1]")));
		sign1.click();
		Thread.sleep(2000);

		WebElement EnterUSERNAME1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body//div[@id='app']//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='modal-body']//div[2]//input[1]")));
		EnterUSERNAME1.sendKeys(username);

		WebElement Enterpass1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-body']//div[2]//input[2]")));
		Enterpass1.sendKeys("1111");
		Thread.sleep(2000);

		WebElement sign2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		sign2.click();
		Thread.sleep(3000);

		WebElement clickonArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
		clickonArrow.click();
		Thread.sleep(2000);
		
		
		//Verify the same medication Adjustment in the stocktake			
		
		WebElement clickonStock = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		clickonStock.click();
		Thread.sleep(2000);
		
		WebElement clickonStockTake = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		clickonStockTake.click();
		Thread.sleep(2000);
		
		WebElement medicationFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationFilter.click();
		medicationFilter.sendKeys(Medication_name);
		Thread.sleep(2000);
		
		WebElement ClickonIncludeS8BTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='active-select-filter select-filter-item']")));
		ClickonIncludeS8BTN.click();
		Thread.sleep(2000);
		
		WebElement ClickonDisplstockbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		ClickonDisplstockbtn.click();
		Thread.sleep(2000);
		
		WebElement Imprestonlybtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		Imprestonlybtn.click();
		Thread.sleep(2000);
		
		WebElement ClickonSearchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		ClickonSearchbtn.click();
		Thread.sleep(2000);
		
		//Print the StockTake Balance
		WebElement StocktakeBalance = driver.findElement(By.xpath("//td[4]"));
		// Get the text content of the element and print it
		String stock = StocktakeBalance.getText();
		System.out.println("Stocktake Balance  = " +  stock);
		Thread.sleep(2000); 
		
		
		//print the comparission between adjustment qty and stocktake balance
		
		WebElement StocktakeBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[4]")));
		
		
		String stock1 = StocktakeBalance1.getText();

		
		String textToRemove = "tablet(s)";
		String modifiedString = stock1.replace(textToRemove, "");
		System.out.println("Checked qty on StockTake = " + modifiedString.trim());
		String st = modifiedString.trim();
		String st2 = adjustmentQtyValue.trim();
		
		if (st.equals(st2)) {
			TestUtil.writeDataadjustmentImprest(rownum++, st, st2, "Pass");
		} else {
			TestUtil.writeDataadjustmentImprest(rownum++, st, st2, "Fail");
		}
		
	}
}
