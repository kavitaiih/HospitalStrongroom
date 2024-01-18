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

public class Dispensing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Dispensing.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("Dispensing")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

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

		WebElement dropdown1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'Pharmacy')]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement selectlocationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		selectlocationbtn.click();

		extent.createTest("Go to /drug-register").assignCategory("Dispensing").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);

//Check the stock on the medidcation

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
		medidcationfilter.sendKeys("Yasmin (inert substance) tablet");

		Thread.sleep(2000);

		WebElement Patientfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("meera");

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
				.assignCategory("Dispensing").assignDevice("Chrome")
				.log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();

		System.out.println("Available Balance = " + stock);

		Thread.sleep(2000);
		
		extent.createTest("Print the Available Balance")
		.assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Print the Available Balance ");


//Dispensing process

		WebElement Dispensingbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Dispensing']")));
		Dispensingbtn.click();
		
		extent.createTest("Click on the Dispensing button shown in the left menu of the dashboard").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Click on the Dispensing button shown in the left menu of the dashboard ");

		Thread.sleep(2000);

		WebElement Referencenmbr = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='refnum']")));
		Referencenmbr.click();
		Referencenmbr.sendKeys("12345");

		Thread.sleep(2000);

		WebElement Notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		Notes.click();
		Notes.sendKeys("Notes will be here");
		
		extent.createTest("Fill all the needfull datails like reference number and notes").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Fill all the needfull datails like reference number and notes ");

		Thread.sleep(2000);

		WebElement SearchPatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='pom-imprest-choice-button']")));
		SearchPatient.click();

		Thread.sleep(2000);

		WebElement Patientname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		Patientname.click();
		Patientname.sendKeys("meera" + Keys.ENTER);
		Thread.sleep(2000);
		
		extent.createTest("Search patient and select from dropdown").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Search patient and select from dropdown ");

		WebElement SelectPatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']")));
		SelectPatient.click();

		Thread.sleep(2000);

		WebElement Prescriber = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
		Prescriber.click();
		Prescriber.sendKeys("jim");
		
		extent.createTest("Search Prescriber and select form dropdown").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Search Prescriber and select form dropdown ");

		Thread.sleep(2000);

		WebElement selectPrescriber = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='drug-search-result']")));
		selectPrescriber.click();

		Thread.sleep(2000);

		WebElement TypeMedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		TypeMedication.click();
		TypeMedication.sendKeys("Yasmin (inert substance) tablet");
		
		extent.createTest("Search Medication and select form dropdown").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Search Prescriber and select form dropdown ");

		Thread.sleep(2000);

		WebElement selectMedication = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='ingredient-item']//div[1]//div[1]//p[1]")));
		selectMedication.click();

		Thread.sleep(2000);

		WebElement qtybox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter quantity']")));
		qtybox.click();
		qtybox.sendKeys("10");
		
		extent.createTest("Enter Quantity for medicaiton").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Enter Quantity for medicaiton");

		Thread.sleep(2000);

		WebElement Addbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		Addbtn.click();
		
		extent.createTest("Click on Add button to add the medication").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Click on Add button to add the medication");

		Thread.sleep(2000);
		
		
		
//Print Entered qty
		
		WebElement Enteredqty = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[3]/table/tr/td[4]/p/span")));

		extent.createTest("Print the entred qty for the dispension  ")
				.assignCategory("Dispensing").assignDevice("Chrome")
				.log(Status.INFO, "Print the entred qty for the dispension ");

		// Get the text content of the element and print it

		String QTY = Enteredqty.getText();

		System.out.println("Entered QTY for Dispensing = "+ QTY);

		Thread.sleep(2000);
		

		WebElement Dispensebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Dispense']")));
		Dispensebtn.click();

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
		
		WebElement searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		searchbtn.click();
		
		extent.createTest("Complete the process of Dispensing with signature verificaiton").assignCategory("Dispensing").assignDevice("Chrome")
		.log(Status.INFO, "Complete the process of Dispensing with signature verificaiton");

		Thread.sleep(2000);
		
//Print New Balance		
		
		WebElement NewBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

		extent.createTest("Print the New balance after the dispensing process")
				.assignCategory("Dispensing").assignDevice("Chrome")
				.log(Status.INFO, "Print the New balance after the dispensing process");

		// Get the text content of the element and print it

		String newstock = NewBalance.getText();

		System.out.println("New Balance = " + newstock);

		Thread.sleep(2000);
		
		extent.flush();

	}

}
