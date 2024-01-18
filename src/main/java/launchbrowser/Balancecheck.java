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

public class Balancecheck {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("AdjustmentIMprest.html");
		extent.attachReporter(spark);

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		// Process of Log in

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("AdjustmentIMprest")
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

		extent.createTest("Go to /drug-register").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);

//Process of balance check and copy the transaction ID from Drug register  on the stocktake screen		

		WebElement stockbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockbtn.click();

		extent.createTest("Click on the stock button at the top of the menu").assignCategory("ProcessOfBalanceCheck")
				.assignDevice("Chrome").log(Status.INFO, "Click on the stock button at the top of the menu");

		Thread.sleep(2000);

		WebElement stockTakebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stockTakebtn.click();

		extent.createTest("Click on the stocktake button of submenu of stock").assignCategory("ProcessOfBalanceCheck")
				.assignDevice("Chrome").log(Status.INFO, "Click on the stocktake button of submenu of stock");

		Thread.sleep(2000);

		WebElement MedicationFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		MedicationFilter.click();
		MedicationFilter.sendKeys("Repreve 2 mg tablet");

		extent.createTest("Search for medication").assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "Search for medication");

		Thread.sleep(2000);

		WebElement ClickonInludes8 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		ClickonInludes8.click();
		Thread.sleep(2000);

		WebElement clickonDisplystock = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		clickonDisplystock.click();

		Thread.sleep(2000);

		WebElement Imprestonly = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		Imprestonly.click();

		WebElement Searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		Searchbtn.click();

		Thread.sleep(2000);

		WebElement clickcount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/input[1]")));
		clickcount.click();
		clickcount.sendKeys("100");

		extent.createTest("Enter the amount of medication").assignCategory("ProcessOfBalanceCheck")
				.assignDevice("Chrome").log(Status.INFO, "Enter the amount of medication");

		Thread.sleep(2000);

//Print the Balance check qty		

		WebElement AvailableBalance = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("ProcessOfBalanceCheck ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock2 = AvailableBalance.getText();

		String textToRemove1 = "tablet(s)";
		String modifiedString1 = stock2.replace(textToRemove1, "");

		int intValue = Integer.parseInt(modifiedString1.trim());

		Integer intValue1 = Integer.valueOf(intValue);

		System.out.println("Expected Stock = " + intValue1);

		Thread.sleep(3000);

		WebElement counted = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/input[1]"));

		extent.createTest("Verify the entered Quantity for the selected medication ")
				.assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

		String countedValue = counted.getAttribute("value");

		System.out.println("Counted Amount =  " + countedValue);

		extent.createTest(" Print New Added Quantity of the selected medication  ")
				.assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		WebElement Addnotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@placeholder='Notes']")));
		Addnotes.click();
		Addnotes.sendKeys("We found only 100 medications for this ");

		extent.createTest("Add Notes").assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome").log(Status.INFO,
				"Add Notes");

		Thread.sleep(2000);

		WebElement ClickonBlncCheckBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='submit-button']")));
		ClickonBlncCheckBTN.click();

		extent.createTest("Click on the Balance Check Button").assignCategory("ProcessOfBalanceCheck")
				.assignDevice("Chrome").log(Status.INFO, "Click on the Balance Check Button");

		Thread.sleep(2000);

		WebElement Enterpassword = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		Enterpassword.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn1.click();

		extent.createTest("1. Enter correct signature and click the Sign button")
				.assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "1. Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement EnterUSERNAME = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Username']")));

		EnterUSERNAME.sendKeys("nathan");

		WebElement Enterpass = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		Enterpass.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn2.click();

		extent.createTest("2. Enter correct signature and click the Sign button")
				.assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "2. Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement DrugRegister = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Drug Register']")));
		DrugRegister.click();

		extent.createTest("Click On the drug register").assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "Click On the drug register");

		Thread.sleep(2000);

		WebElement ClickARROW = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[normalize-space()='Balance check']")));
		ClickARROW.click();

		extent.createTest("Check for new balance check ").assignCategory("ProcessOfBalanceCheck").assignDevice("Chrome")
				.log(Status.INFO, "Check for new balance check");

		Thread.sleep(2000);

		

	}

}
