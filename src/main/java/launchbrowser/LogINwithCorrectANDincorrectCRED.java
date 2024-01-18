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




public class LogINwithCorrectANDincorrectCRED {

	public static void main(String[] args) throws InterruptedException {
		
		
		// TODO Auto-generated method stub
		ExtentReports extent = new ExtentReports();
		  ExtentSparkReporter spark = new ExtentSparkReporter("lOGINwithcorrectANDicorrectCREDENTIALS");
		  extent.attachReporter(spark);

		//System.setProperty("webdriver.chrome.driver", "/home/user/Desktop/chromedriver");
	
		WebDriver driver = new ChromeDriver();
		
	
		driver.manage().window().maximize();
		
		

		driver.get("https://hospital-staging.strongroom.ai/login");
		
		
		//Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("Test incorrect login").assignDevice("Chrome")
		.log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
		
		
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");
		
		
	
	    WebElement clickElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
	    clickElement.click();
			    
	    
		WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
		
		field2.sendKeys("qa@strongroom.ai");
		
        WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		field3.sendKeys("12345");
		
		extent.createTest("Enter Incorrect login ").assignCategory("Test incorrect login").assignDevice("Chrome")
		.log(Status.INFO, "Enter Incorrect login");
		Thread.sleep(2000);
		
		WebElement Loginbtn1 = driver.findElement(By.xpath("//p[@class='blue-button']"));
		Loginbtn1.click();
		
		Thread.sleep(2000);
		
	    field3.clear();
	    Thread.sleep(2000);
	    
	    
        
	    WebElement field4 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		field4.sendKeys("12345");
		
		extent.createTest("Check that the locked out message appears ").assignCategory("Test incorrect login lockout optional").assignDevice("Chrome")
		.log(Status.INFO, "Check that the locked out message appears");
		Thread.sleep(2000);
		
        WebElement Loginbtn2 = driver.findElement(By.xpath("//p[@class='blue-button']"));
		Loginbtn2.click();
		
		Thread.sleep(2000);
		
		field4.clear();
	    Thread.sleep(2000);
	    

	    
WebElement field5 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		
		field5.sendKeys("12345");
		
		extent.createTest("Check that the locked out message appears").assignCategory("Test incorrect login lockout optional").assignDevice("Chrome")
		.log(Status.INFO, "Check that the locked out message appears");
		
		
		Thread.sleep(2000);
		
WebElement Loginbtn3 = driver.findElement(By.xpath("//p[@class='blue-button']"));
		
		Loginbtn3.click();
		
		Thread.sleep(2000);
		
		field5.clear();
	    Thread.sleep(2000);
	    
	  
		
        WebElement field8 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		
		field8.sendKeys("stew-dazzling-washtub!");
		
		extent.createTest("Enter correct login").assignCategory("Test correct login").assignDevice("Chrome")
		.log(Status.INFO, "Enter correct login");

		Thread.sleep(2000);
		
WebElement Loginbtn6 = driver.findElement(By.xpath("//p[@class='blue-button']"));
		
		Loginbtn6.click();
		
		
		Thread.sleep(2000);
		
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();
		
		WebElement dropdown1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]")));
		dropdown1.click();
		
		Thread.sleep(2000);
		
		WebElement	locationbtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();
		
		extent.createTest("Select a location and click the Select Location button").assignCategory("Test correct login").assignDevice("Chrome")
		.log(Status.INFO, "Select a location and click the Select Location button");
		
		extent.createTest("Go to Dashboard").assignCategory("Test correct login").assignDevice("Chrome")
		.log(Status.INFO, "Go to Dashboard");
		
		
	
		
		extent.flush();
		
		

	}

}
