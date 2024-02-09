package Testcase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.Util.TestUtil;

public class Requisition extends Base{
	
	
	private static int rownum = 2;
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData =TestUtil.RequisitionImprest();
		return testData.iterator();	
	}

	@Test(dataProvider="getTestData")
	public void RequisitionwithImprest(String Medication_name, String note, String Quantity, String pin) 
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		//Process of creation of new Requisition
		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click(); //stock
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[normalize-space()='Requisitions']")).click(); //Requisition
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@class='actions-menu-item']")).click(); //new requisition
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Distribute to...']")).sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//li[@aria-label='Emergency Ward']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note); // Take not from excel
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")).click(); //click on imprest button
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Type medication name here']")).sendKeys(Medication_name + Keys.ENTER); //
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Enter qty']")).sendKeys(Quantity); //enter qty
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
		Thread.sleep(2000);
		
		
		//Print the requested qty 
		
		WebElement Requestedqty = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='1']")));
		Requestedqty.click();
		Thread.sleep(2000);
		
		System.out.println("Requested qty = " +Requestedqty);
		
		driver.findElement(By.xpath("//p[@class='regular-button complete-button']")).click();
		Thread.sleep(2000);
		
		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput.sendKeys("1111");
		Thread.sleep(2000);

		WebElement signInBtn = driver.findElement(By.xpath("//div[@class='green-button']"));
		signInBtn.click();
		Thread.sleep(2000);

		WebElement completeBtn = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
		completeBtn.click();
		Thread.sleep(3000);

		WebElement Arrowclick = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]"));
		Arrowclick.click();
		Thread.sleep(3000);
		
		WebElement Supplybtn = driver.findElement(By.xpath("//button[normalize-space()='Supply']"));
		Supplybtn.click();
		Thread.sleep(3000);
		
		WebElement passwordInput1 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput1.sendKeys("1111");
		Thread.sleep(2000);

		WebElement signInBtn1 = driver.findElement(By.xpath("//div[@class='green-button']"));
		signInBtn1.click();
		Thread.sleep(2000);

		WebElement completeBtn1 = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
		completeBtn1.click();
		Thread.sleep(3000);
		
		WebElement ReadypickBTN = driver.findElement(By.xpath("//button[normalize-space()='Ready for Pickup']"));
		ReadypickBTN.click();
		Thread.sleep(3000);
		
		WebElement CollectBTN = driver.findElement(By.xpath("//button[normalize-space()='Collect']"));
		CollectBTN.click();
		Thread.sleep(3000);
		
		WebElement SubmitBTN = driver.findElement(By.xpath("//p[@class='regular-button complete-button']"));
		SubmitBTN.click();
		Thread.sleep(3000);
		
		WebElement passwordInput2 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		passwordInput2.sendKeys("1111");
		Thread.sleep(2000);

		WebElement signInBtn2 = driver.findElement(By.xpath("//div[@class='green-button']"));
		signInBtn2.click();
		Thread.sleep(2000);

		WebElement completeBtn2 = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
		completeBtn2.click();
		Thread.sleep(3000);
		
		// Change the location for completion the requistion process 
		
		
		
		WebElement Copylocation = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/p[1]"));
		Copylocation.click();
		
		 // Get the text from the element
        String locationName = Copylocation.getText();
		System.out.println("Copied location =" +locationName);
		Thread.sleep(3000);
		
		WebElement locationBtn = driver.findElement(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
		locationBtn.click();	
		Thread.sleep(3000);
		
		//WebElement locationBtn = driver.findElement(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
		locationBtn.sendKeys(locationName);
		Thread.sleep(3000);
	}
}
