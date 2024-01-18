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


public class Requisition {

	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("DestructionPatient.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("DestructionPatient")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		
//Log IN PROCESS
		

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

		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();
		
		
//Process of creation of new Requisition
		
		WebElement Stock = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();

		extent.createTest("Click on the Stock button shown on the top menu of dashboard ")
				.assignCategory("Requisition ").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Stock button shown on the top menu of dashboard");

		Thread.sleep(2000);

		WebElement RequisitionBTN = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Requisitions']")));
		RequisitionBTN.click();
		
		extent.createTest("Click on the Requistion tab presenet in the sub menu of stock")
		.assignCategory("DestructionPatient ").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Requistion tab presenet in the sub menu of stock");

		Thread.sleep(2000);
		
		WebElement NewRequisition = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='actions-menu-item']")));
		NewRequisition.click();
		Thread.sleep(2000);
		
		WebElement Location = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Distribute to...']")));
		Location.click();
		
		extent.createTest("Click on the Requistion tab presenet in the sub menu of stock")
		.assignCategory("DestructionPatient ").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Requistion tab presenet in the sub menu of stock");

		Thread.sleep(2000);
		
        WebElement clickspacetogetLOCATIONS = driver.findElement(By.xpath("//input[@placeholder='Distribute to...']"));
        clickspacetogetLOCATIONS.sendKeys(Keys.SPACE);
        
        Thread.sleep(2000);
        
        WebElement selectLocation = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@aria-label='Emergency Ward']")));
		selectLocation.click();
		
		extent.createTest("Click on the Requistion tab presenet in the sub menu of stock")
		.assignCategory("DestructionPatient ").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Requistion tab presenet in the sub menu of stock");
		
		Thread.sleep(2000);
		
		 WebElement PatientMedication = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Patient Medication']")));
		 PatientMedication.click();
			
			Thread.sleep(2000);
			
			WebElement SearchPatient = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
			SearchPatient.click();
			SearchPatient.sendKeys("meera" + Keys.ENTER);
			
			Thread.sleep(2000);
			
			WebElement SelectPatient = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='patient-result-info']//p[1]")));
			SelectPatient.click();
			
			Thread.sleep(2000);
			
	}

}
