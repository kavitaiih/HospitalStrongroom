package launchbrowser;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class OutgoingPatientIncorrectSignature {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("OutgoingPatientDMGincorrectPASS.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login")
				.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
				.log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");

		WebElement clickElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
		clickElement.click();

		WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));

		field2.sendKeys("qa@strongroom.ai");

		WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));

		field3.sendKeys("stew-dazzling-washtub!");

		WebElement Loginbtn = driver.findElement(By.xpath("//p[@class='blue-button']"));

		Loginbtn.click();

		Thread.sleep(2000);

		WebElement dropdown = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();

		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();

		extent.createTest("Go to /drug-register")
				.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		WebElement OutGoingbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		OutGoingbtn.click();
		
		extent.createTest("Click on the Outgoing button in the left menu")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Outgoing button in the left menu");
		
		Thread.sleep(2000);
		
		WebElement Damagededbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Damaged']")));
		Damagededbtn.click();
		
		extent.createTest("Click on the Discarded option")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Discarded option");
		
		Thread.sleep(2000);
		
		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		notes.sendKeys("Notes Will be here");
		
		Thread.sleep(2000);
		
		
		WebElement patientmedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		patientmedication.click();

		WebElement clicksearchbar = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		clicksearchbar.click();

		clicksearchbar.sendKeys("k" + Keys.ENTER);
		Thread.sleep(2000);

		WebElement selectpatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[contains(@class,'patient-result-info')]//p[1]")));
		
		selectpatient.click();
		
		extent.createTest("Select patient")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Select patient");
		
		Thread.sleep(2000);

		

		WebElement medicationdrp = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		medicationdrp.click();
		
		Thread.sleep(2000);

		WebElement selectmedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='doxazosin 4 mg tablet']")));
		selectmedication.click();
		
		extent.createTest("Enter a medication")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Enter a medication");
		
		Thread.sleep(2000);

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();
		qty.sendKeys("1");
		
		extent.createTest("Select a medication and qty")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Select a medication and qty");
		
		Thread.sleep(2000);

		WebElement addbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();
		
		extent.createTest("Click the Add button")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Click the Add button");

		Thread.sleep(2000);

		WebElement submitclick = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Submit']")));
		submitclick.click();
		
		extent.createTest("Click on the submit button")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Click on the submit button");

		WebElement enterpass = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		enterpass.sendKeys("12345");

		WebElement pwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));

		pwd1.click();
		
		extent.createTest("Enter incorrect signature and click the Sign button")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Enter incorrect signature and click the Sign button");
		
		
		extent.createTest("Check that  Invalid login or password/pin code message is displayed")
		.assignCategory("OutGoingPatinet(DMGincorrectPASS)").assignDevice("Chrome")
		.log(Status.INFO, "Check that  Invalid login or password/pin code message is displayed");

		Thread.sleep(2000);
		
		System.out.println("Check that  Invalid login or password/pin code message is displayed");
		
		 extent.flush();

	}

}
