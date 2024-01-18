package Testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Requisition extends Base {
	
	@Test
	public void requisition() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		//Process of creation of new Requisition
		
		WebElement Stock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();
		Thread.sleep(2000);

		WebElement RequisitionBTN = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Requisitions']")));
		RequisitionBTN.click();
		Thread.sleep(2000);
		
		WebElement NewRequisition = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='actions-menu-item']")));
		NewRequisition.click();
		Thread.sleep(2000);
		
		WebElement Location = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Distribute to...']")));
		Location.click();
		Thread.sleep(2000);
		
        WebElement clickspacetogetLOCATIONS = driver.findElement(By.xpath("//input[@placeholder='Distribute to...']"));
        clickspacetogetLOCATIONS.sendKeys(Keys.SPACE);
        Thread.sleep(2000);
        
        WebElement selectLocation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Emergency Ward']")));
		selectLocation.click();
		Thread.sleep(2000);
		
		WebElement PatientMedication = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Patient Medication']")));
		PatientMedication.click();
		Thread.sleep(2000);
			
		WebElement SearchPatient = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		SearchPatient.click();
		SearchPatient.sendKeys("meera" + Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement SelectPatient = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='patient-result-info']//p[1]")));
		SelectPatient.click();
		Thread.sleep(2000);
	}
}
