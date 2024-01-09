package launchbrowser;

import java.time.Duration;


import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TrsfrOutPatientReverse {

	public static void main(String[] args) throws InterruptedException {
		
		
		
	
		WebDriver driver = new ChromeDriver();
		
	
		driver.manage().window().maximize();
		
		

		driver.get("https://hospital-staging.strongroom.ai/login");
		
		
		//Display status log on html report page
		//extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("regression testing").assignDevice("Chrome")
		//.log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");
		
		
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

		Thread.sleep(2000);
		
		
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();
		
		WebElement dropdown1= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]")));
		dropdown1.click();
		
		Thread.sleep(2000);
		
		
		
		WebElement	locationbtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();
		
		Thread.sleep(2000);
		
	
		/*
		WebElement	transferout=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer Out']")));
		transferout.click();
		
		Thread.sleep(2000);
		 
		
		
		 WebElement drpdwn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		 WebElement drpdwn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to send to']")));

		drpdwn1.sendKeys("Emergency Ward");
		 drpdwn.click();
		 		 
		 
		 WebElement option1clicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
		 option1clicked.click();
		
		 WebElement notes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		 notes.sendKeys("Notes Will be here");
		 
		 Thread.sleep(2000);
		
		 
		 
		 WebElement	bluebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		 
		 
		 
		 bluebtn.click();
		 
		 
			
			WebElement	clicksearchbar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
			clicksearchbar.click();
			
			clicksearchbar.sendKeys("a" + Keys.ENTER);
			
			WebElement	clickresult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
			clickresult.click();
					
			Thread.sleep(2000);
			
			 
			 
			 WebElement  prescribername = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
			 prescribername.click();
			 
			 Thread.sleep(1000);

			 
			 WebElement addspace = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
			 addspace.sendKeys(" ");

			 Thread.sleep(1000);

			 
			 WebElement resultclicked = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Reference only - vishal parmar']")));
			 resultclicked.click();
			 
			 Thread.sleep(1000);
			 
			 
			 
			// Click on the dropdown to open it
			 WebElement dropdownTrigger = driver.findElement(By.xpath("//div[@class='right-form-section-drug-entry']//div//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
			 dropdownTrigger.click();

			 // Click on the specific option in the dropdown
			 WebElement optionElement = driver.findElement(By.xpath("//p[normalize-space()='methadone hydrochloride 10 mg/mL injection, vial']"));
			 optionElement.click();
			 
			 
			  Thread.sleep(1000);
			  
				 WebElement	bluebtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
				 bluebtn1.click();
				 Thread.sleep(1000);
				 
				 
				 
				 WebElement Sendtranferbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Send Transfer']")));
				 Sendtranferbtn.click();
				 
				 
				 
				 Thread.sleep(1000);
				 
				 WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

				 pwd.sendKeys("1111");
				 
				 
				 Thread.sleep(1000);
				
				 WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				 signinbtn.click();
				 
				 Thread.sleep(1000);
				 
				 WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
				 completebtn.click();
				 
				 
				 
				 Thread.sleep(2000);
				 
				 */
				 
				
					    // Click on the arrow button
					    WebElement FirstArrow1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
					    FirstArrow1.click();
			

					    // Wait for details to be visible
					    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='field-notes']")));
					    
					    Thread.sleep(5000);
					    
					    WebElement notification = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='new-patient-button data-v-tooltip']//i[@class='pi pi-exclamation-circle']")));
						 notification.click();
				
					    
					    Thread.sleep(3000);
					   
	
					    // Click on the reverse entry button
					    WebElement Reverse = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='transfer-action-button error-button']")));
					    Reverse.click();
					    
					    WebElement notes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //textarea[@id='note-modal']")));

						 notes.sendKeys("I am reversing this entry");
					    
						 Thread.sleep(3000);
						 
						 WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
						 Submit.click();
						 Thread.sleep(1000);
						 
						 Thread.sleep(1000);
						 
						 WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

						 pwd.sendKeys("1111");
						 
						 
						 Thread.sleep(1000);
						
						 WebElement signinbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
						 signinbtn.click();
						 
						 Thread.sleep(1000);
						 
						 WebElement completebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
						 completebtn.click();
						 
						 Thread.sleep(2000);
						 
						 WebElement FirstArrow2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
						    FirstArrow2.click();
				
					    Thread.sleep(3000);
				/*	 
		 
		 WebElement notes1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		 notes1.sendKeys("Notes Will be here SOON");
		 
		 Thread.sleep(2000);
		 
		 
		 WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		 Submit.click();
		 Thread.sleep(2000);
		 
		 
		 WebElement pswd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		 pswd.sendKeys("1111");
		 
		 
		 Thread.sleep(1000);
		
		 WebElement signinbtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		 signinbtn1.click();
		 
		 Thread.sleep(1000);
		 
		 WebElement completebtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		 completebtn1.click();
		 
		 Thread.sleep(1000);
		 
		 */
		 
		
		
			    }
			}