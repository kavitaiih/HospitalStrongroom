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

public class AdjustmentPatient extends Base{
	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.adjusttmentPatient();
		return testData.iterator();
		
	}

	@Test(dataProvider="getTestData")
	public void adjusttmentPatient(String Medication_name, String patient, String username, String sessionId, String note, String quantity, String pin, String Current_Stock, String pRemaining_Balancein, String Status ) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		
		//Process of Adjustment
		WebElement Adjusmentbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Adjustment']")));
		Adjusmentbtn.click();
		Thread.sleep(2000);

		WebElement PasteTransID = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Transaction ID...']")));
		PasteTransID.click();

		WebElement targetElement = driver.findElement(By.xpath("//input[@placeholder='Transaction ID...']"));
		targetElement.sendKeys(sessionId);
		Thread.sleep(2000);

		WebElement addnotes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		addnotes.sendKeys(note);
		Thread.sleep(2000);

		WebElement Patientmedicationbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		Patientmedicationbtn.click();
		Thread.sleep(2000);

		WebElement patientname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		patientname.click();
		patientname.sendKeys(patient + Keys.ENTER);
		Thread.sleep(2000);

		WebElement clickresult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
		clickresult.click(); 
		Thread.sleep(2000);

		WebElement medicationdrp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		medicationdrp.click();
		Thread.sleep(2000);

		WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='naproxen sodium 220 mg tablet']")));
		selectmedication.click();
		Thread.sleep(2000);

		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();
		Thread.sleep(2000);

		WebElement EntermedicationAmount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
		EntermedicationAmount.click();
		EntermedicationAmount.sendKeys(quantity);
		Thread.sleep(2000);

		// Find the Adjustmentqty element by its name attribute
		WebElement adjustmentQty = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[4]/table[1]/tr[2]/td[3]/input[1]"));
		String adjustmentQtyValue = adjustmentQty.getAttribute("value");
		System.out.println("Adjustment qty =  " + adjustmentQtyValue);
		Thread.sleep(1000);

		//Continue the process	
		WebElement Submitbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		Submitbtn.click();
		Thread.sleep(2000);

		WebElement pass1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
		pass1.click();
		pass1.sendKeys(pin);
		Thread.sleep(2000);

		WebElement sign1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']//div//div[1]//div[1]")));
		sign1.click();
		Thread.sleep(2000);

		WebElement EnterUSERNAME1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body//div[@id='app']//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='modal-body']//div[2]//input[1]")));
		EnterUSERNAME1.sendKeys(username);

		WebElement Enterpass1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-body']//div[2]//input[2]")));
		Enterpass1.sendKeys(pin);
		Thread.sleep(2000);

		WebElement sign2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		sign2.click();
		Thread.sleep(3000);

		WebElement clickonArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
		clickonArrow.click();
		Thread.sleep(2000);

		//Verify the same Medication Adjustment in the StockTake
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

		WebElement PatientFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
		PatientFilter.click();
		PatientFilter.sendKeys(patient);
		Thread.sleep(2000);

		WebElement ClickonIncludeS8BTN = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='active-select-filter select-filter-item']")));
		ClickonIncludeS8BTN.click();
		Thread.sleep(2000);

		WebElement ClickonDisplstockbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		ClickonDisplstockbtn.click();
		Thread.sleep(2000);

		WebElement ClickonSearchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		ClickonSearchbtn.click();
		Thread.sleep(2000);

		
		//print the comparission between adjustment qty and stocktake balance
		
		WebElement StocktakeBalance =driver.findElement(By.xpath("//td[4]"));
		String stock = StocktakeBalance.getText();
		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");
		System.out.println("Stocktake Balance qty =  " +modifiedString);
		
		String st = modifiedString.trim();
		String st2 = adjustmentQtyValue.trim();
		
		if (st.equals(st2)) {
			TestUtil.writeDataadjusttmentPatient(rownum++, st, st2, "Pass");
		} else {
			TestUtil.writeDataadjusttmentPatient(rownum++, st, st2, "Fail");
		}
	}
}
