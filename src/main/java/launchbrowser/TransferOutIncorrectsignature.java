package launchbrowser;

import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

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

public class TransferOutIncorrectsignature {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		  ExtentSparkReporter spark = new ExtentSparkReporter("TransferOUTPatientIncorrectsign.html");
		  extent.attachReporter(spark);

		//System.setProperty("webdriver.chrome.driver", "/home/user/Desktop/chromedriver");
	
		WebDriver driver = new ChromeDriver();
		
	
		driver.manage().window().maximize();
		
		

		driver.get("https://hospital-staging.strongroom.ai/login");
		System.out.println("process complete");
		
		
		//Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
		.log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		
		
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");
		
		
	
	    WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
	    clickElement.click();
			    
	    
		WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
		
		field2.sendKeys("qa@strongroom.ai");
		
    WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		
		field3.sendKeys("stew-dazzling-washtub!");
		
		
		WebElement Loginbtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
		
		Loginbtn.click();
		
		extent.createTest("Login successfully").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
		.log(Status.INFO, "Login successfully");

		Thread.sleep(2000);
		
		
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();
		
		WebElement dropdown1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]")));
		dropdown1.click();
		
		Thread.sleep(2000);
		
		WebElement	locationbtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();
		
		extent.createTest(" Go to /drug-register").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
		.log(Status.INFO, " Go to /drug-register");
		Thread.sleep(1000);
		
		WebElement	transferout=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer Out']")));
		transferout.click();
		
		extent.createTest("Click the Transfer Out button in the left menu ").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
		.log(Status.INFO, " Click the Transfer Out button in the left menu");
		Thread.sleep(2000);
		
		
		 WebElement drpdwn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		 WebElement drpdwn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to send to']")));

		drpdwn1.sendKeys("Emergency Ward");
		 drpdwn.click();
		 extent.createTest(" Enter a location").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
			.log(Status.INFO, " Enter a location");
			Thread.sleep(2000);
		 
		 		 
		 WebElement option1clicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
		 option1clicked.click();
		 extent.createTest("Select a location ").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
			.log(Status.INFO, " Select a location");
			Thread.sleep(2000);
			
		
		 WebElement notes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
       notes.sendKeys("Notes Will be here");
       extent.createTest(" Add text to notes").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
			.log(Status.INFO, " Add text to notes");
			Thread.sleep(2000);
			
		 
		 WebElement	Patientmedicationbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		 Patientmedicationbtn.click();
		 extent.createTest("Click the Patient Medication button ").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
			.log(Status.INFO, " Click the Patient Medication button");
			Thread.sleep(2000);
			
			
			WebElement	clicksearchbar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
			clicksearchbar.click();
			clicksearchbar.sendKeys("k" + Keys.ENTER);
			extent.createTest("Enter a patient name in the search field and click search ").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
			.log(Status.INFO, " Enter a patient name in the search field and click search");
			Thread.sleep(2000);
			
		
			WebElement	clickresult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
			clickresult.click();
			Thread.sleep(2000);
			
			 WebElement  prescribername = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
			 prescribername.click();
			 extent.createTest("Enter Prescriber name ").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
				.log(Status.INFO, "Enter Prescriber name");
				Thread.sleep(2000);

			 
			 WebElement addspace = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
			 addspace.sendKeys(" ");
           Thread.sleep(1000);

			 
			 WebElement resultclicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Reference only - Jim jam']")));
			 resultclicked.click();
			 Thread.sleep(1000);
			 
			 
			// Click on the dropdown to open it
			 WebElement dropdownTrigger = driver.findElement(By.xpath("//div[@class='right-form-section-drug-entry']//div//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
			 dropdownTrigger.click();

			 // Click on the specific option in the dropdown
			 WebElement optionElement = driver.findElement(By.xpath("//li[@aria-label='doxazosin 4 mg tablet']//div[@class='ingredient-item']//div[1]//div[1]"));
			 optionElement.click();
			 extent.createTest(" Enter a medication").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
				.log(Status.INFO, "Enter a medication");
			 
			 Thread.sleep(1000);
			 
			 WebElement qtybox = driver.findElement(By.xpath("//input[@placeholder='Quantity']"));
			 qtybox.click();
			 
			 qtybox.clear();
			 qtybox.sendKeys("1");
			 extent.createTest(" Select a medication and qty").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
				.log(Status.INFO, "Select a medication and qty");
			 Thread.sleep(2000);
			 
			 WebElement addbox = driver.findElement(By.xpath("//p[@class='blue-button']"));
			 addbox.click();
			 extent.createTest(" Click the Add button").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
				.log(Status.INFO, "Click the Add button");
			 Thread.sleep(2000);
		
			 
				 WebElement Sendtranferbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Send Transfer']")));
				 Sendtranferbtn.click();
				 
				 extent.createTest("Click the Send Transfer button").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
					.log(Status.INFO, "Click the Send Transfer button");
				 Thread.sleep(1000);
				 
				 WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

				 pwd.sendKeys("1234");
				 Thread.sleep(1000);
				
				 WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				 signinbtn.click();
				 extent.createTest("Enter an incorrect signature").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
					.log(Status.INFO, "Enter an incorrect signature");
				 Thread.sleep(1000);
				 
				 extent.flush();
				 
				 /*
				 WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
				 completebtn.click();
				 
				 extent.createTest("complete process").assignCategory("TransferOUTpatientIncorrectsign").assignDevice("Chrome")
					.log(Status.INFO, "complete process");
					Thread.sleep(2000);
					
					*/

	}

}
