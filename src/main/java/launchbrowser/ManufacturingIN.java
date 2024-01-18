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
import org.openqa.selenium.interactions.Actions;

public class ManufacturingIN {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("ManufacturingIN.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("Manufacturing")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");

		WebElement clickElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
		
//Log in	
		
		
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

		WebElement dropdown1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'Pharmacy')]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement selectlocationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		selectlocationbtn.click();

		extent.createTest("Go to /drug-register").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);
		
		
//Print the Available Balance
		
		WebElement Stock = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();
		Thread.sleep(2000);

		WebElement StockTake = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		StockTake.click();
		Thread.sleep(2000);

		WebElement medidcationfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medidcationfilter.click();
		medidcationfilter.sendKeys("Panadol Extra effervescent tablet");

		Thread.sleep(2000);
		
		WebElement IncluseS8 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		IncluseS8.click();

		Thread.sleep(2000);

		WebElement DisplayInstakeonly = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		DisplayInstakeonly.click();

		Thread.sleep(2000);

		WebElement SearchBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();

		Thread.sleep(2000);

		
// Print the Present available balance of that Medicaiton
		

				WebElement AvailableBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				extent.createTest("Check the Available Balance for this medication ")
						.assignCategory("Manufacturing").assignDevice("Chrome")
						.log(Status.INFO, "Check the Available Balance for this medication");

				// Get the text content of the element and print it

				String stock = AvailableBalance.getText();

				System.out.println("Available Balance = " + stock);

				Thread.sleep(2000);
				
				extent.createTest("Print the Available Balance")
				.assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Print the Available Balance ");
				
				
//	Process of Manufacturing 
				
				WebElement Manufacturing = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Manufacturing']")));
				Manufacturing.click();	
				
				extent.createTest("Click on the Manufacturing button shown on the left menu of the dashboard ").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Manufacturing button shown on the left menu of the dashboard");
				
				Thread.sleep(2000);
				
				
				WebElement Rfncnumber = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='refnum']")));
				Rfncnumber.click();	
				Rfncnumber.sendKeys("12345");
				
				Thread.sleep(2000);
				
				
				WebElement Notes = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
				Notes.click();	
				Notes.sendKeys("Notes will be here");
				
				extent.createTest("Enter reference number ans notes on the respected fields").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Enter reference number ans notes on the respected fields");
				
				Thread.sleep(2000);
				
				
				WebElement Medication = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
				Medication.click();	
				Medication.sendKeys("(caffeine) Panadol Extra effervescent tablet");
				
				extent.createTest("Search for medication and select form dropdown").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Search for medication and select form dropdown");
				
				Thread.sleep(2000);
				
				WebElement SelectMedication = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")));
				SelectMedication.click();	
				
				Thread.sleep(2000);
				
				
				WebElement qty = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter qty']")));
				qty.click();
				qty.sendKeys("1");
				
				Thread.sleep(2000);
				

				WebElement Addbtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
				Addbtn.click();
				
				
//Print Entered qty
				
				WebElement Enteredqty = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html/body/div/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div/table/tr/td[2]/p/span")));

				extent.createTest("Print the entred qty for the Manufacturing  ")
						.assignCategory("Manufacturing").assignDevice("Chrome")
						.log(Status.INFO, "Print the entred qty for the Manufacturing ");

				// Get the text content of the element and print it

				String QTY = Enteredqty.getText();

				System.out.println("Entered IN qty for Manufacturing = "+ QTY);

				Thread.sleep(2000);
				
				
// Continue the manufacturing process	
				
				WebElement Submitbtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
				Submitbtn.click();
				
				Thread.sleep(2000);
				
				WebElement pass1 = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
				pass1.click();
				pass1.sendKeys("1111");

				Thread.sleep(2000);

				WebElement Sign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				Sign.click();

				Thread.sleep(2000);

				WebElement Completebtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
				Completebtn.click();

				Thread.sleep(2000);
				extent.createTest("Complete the process of Dispensing with signature verificaiton").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Complete the process of Dispensing with signature verificaiton");
				
				Thread.sleep(2000);
				
//Verify the transaction on the drug register 
				
				
				WebElement DrugRegister = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Drug Register']")));
				DrugRegister.click();
				Thread.sleep(2000);
				
				WebElement ClickonArrow = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[1]/i[1]")));
				ClickonArrow.click();
				
				Thread.sleep(2000);
				extent.createTest("Verify that the Transaction for maunfacturing is IN or OUT").assignCategory("Manufacturing").assignDevice("Chrome")
				.log(Status.INFO, "Verify that the Transaction for maunfacturing is IN or OUT");
				
				Thread.sleep(2000);
				
				
//Check the updated stock on the stocktake 
				
				WebElement Stock1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
				Stock1.click();
				Thread.sleep(2000);

				WebElement StockTake1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
				StockTake1.click();
				Thread.sleep(2000);

				WebElement medidcationfilter1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
				medidcationfilter1.click();
				medidcationfilter1.sendKeys("Panadol Extra effervescent tablet");

				Thread.sleep(2000);
				
				WebElement IncluseS81 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
				IncluseS81.click();

				Thread.sleep(2000);

				WebElement DisplayInstakeonly1 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
				DisplayInstakeonly1.click();

				Thread.sleep(2000);

				WebElement SearchBTN1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
				SearchBTN1.click();

				Thread.sleep(2000);
				
				
//Print New Balance		
				
				
				WebElement NewBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				extent.createTest("Print the New balance after the dispensing process")
						.assignCategory("Manufacturing").assignDevice("Chrome")
						.log(Status.INFO, "Print the New balance after the dispensing process");

				// Get the text content of the element and print it

				String newstock = NewBalance.getText();

				System.out.println("New Balance = " + newstock);

				Thread.sleep(2000);

				extent.flush();
				
				
				
	}

}
