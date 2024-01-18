package launchbrowser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TransferINincorrectsign {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		  ExtentSparkReporter spark = new ExtentSparkReporter("TransferINincorrectSign.html");
		  extent.attachReporter(spark);

		//System.setProperty("webdriver.chrome.driver", "/home/user/Desktop/chromedriver");
	
		WebDriver driver = new ChromeDriver();
		
	
		driver.manage().window().maximize();
		
		

		driver.get("https://hospital-staging.strongroom.ai/login");
		
		
		//Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
		.log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		
		
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");
		
		
	
	    WebElement locationElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
	    locationElement.click();
			    
	    
		WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
		
		field2.sendKeys("qa@strongroom.ai");
		
      WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		
		field3.sendKeys("stew-dazzling-washtub!");
		
		
		WebElement Loginbtn = driver.findElement(By.xpath("//p[@class='blue-button']"));
		
		Loginbtn.click();
		
		extent.createTest("Login successfully").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
		.log(Status.INFO, "Login successfully");

		Thread.sleep(2000);
		
		
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();
		
		WebElement selectLocation= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		selectLocation.click();
		
		Thread.sleep(2000);
		
	
		
		
		WebElement	locationbtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();
		
		
		//Display status log on html report page
				extent.createTest("Go to /drug-register ").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");
		
		WebElement	transferin=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
		transferin.click();
		
		Thread.sleep(2000);
		
		extent.createTest("Click the Transfer In button in the left menu").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
		.log(Status.INFO, "Click the Transfer In button in the left menu");
		
		
		 WebElement drpdwn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		 WebElement drpdwn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));

		drpdwn1.sendKeys("Emergency Ward");
		 drpdwn.click();
		 extent.createTest("Enter a location").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Enter a location");
		 
		 Thread.sleep(2000);
		 		 
		 
		 WebElement locationselect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
		 locationselect.click();
		 
		 extent.createTest("Select a location").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Select a location");
		
		 WebElement addnotes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		 addnotes.sendKeys("Notes Will be here");
		 
		 Thread.sleep(2000);
		 
		 extent.createTest("Add text to notes").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Add text to notes");
		 
		 WebElement	Imprestbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		 Imprestbtn.click();
		 
		 extent.createTest("Click the Imprest/Emergency Meds/Ward Stock button").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Click the Imprest/Emergency Meds/Ward Stock button");
		 
		 WebElement drpdwn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Select Medication')]")));
		 WebElement drpdwn3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'right-form-section-drug-entry')]//div//span[contains(@class,'p-dropdown-trigger-icon pi pi-chevron-down')]")));

		 drpdwn2.sendKeys("(guaifenesin) guaifenesin 100 mg/5 mL oral liquid");

		 drpdwn3.click();

		 
		 Thread.sleep(2000);
		 
		 WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'(guaifenesin) guaifenesin 100 mg/5 mL oral liquid')]")));
		 selectmedication.click();
		 extent.createTest("Enter a medication").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Enter a medication");
		 
		 
		 		
		 WebElement qty = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));

		 qty.sendKeys("1");
		 extent.createTest("Select a medication and qty").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Select a medication and qty");
		 Thread.sleep(2000);
		 
		 WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		 addbtn.click();
		 extent.createTest("Click the Add button").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Click the Add button");
		 
	Thread.sleep(2000);
		 
		 WebElement receivetranferbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Receive Transfer']")));
		 receivetranferbtn.click();
		 
		 extent.createTest("Click the Recieve Transfer button").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Click the Recieve Transfer button");
		 
		 Thread.sleep(2000);
		 
		 
		 WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		 pwd.sendKeys("12345");
		 
		 Thread.sleep(2000);
		
		 WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		 signinbtn.click();
		 
		 extent.createTest("Enter an incorrect signature").assignCategory("TransferINincorrectSign").assignDevice("Chrome")
			.log(Status.INFO, "Enter an incorrect signature");
		 
		 Thread.sleep(2000);
		 
		 extent.flush();
		 
		 System.out.println("Show the error if there is any wrong credentials are entered"); 
	}

}
