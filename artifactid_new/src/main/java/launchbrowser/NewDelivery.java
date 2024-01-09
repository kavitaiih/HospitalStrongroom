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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchWindowException;

public class NewDelivery {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("NewDelivery.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

//Log In Process

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		// Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("Log In")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));

		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");

		WebElement locationElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
		locationElement.click();

		WebElement field2 = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));

		field2.sendKeys("qa@strongroom.ai");

		WebElement field3 = driver.findElement(By.xpath("//input[@placeholder='Password']"));

		field3.sendKeys("stew-dazzling-washtub!");

		WebElement Loginbtn = driver.findElement(By.xpath("//p[@class='blue-button']"));

		Loginbtn.click();

		extent.createTest("Login successfully").assignCategory("Log In").assignDevice("Chrome").log(Status.INFO,
				"Login successfully");

		Thread.sleep(2000);

		WebElement dropdown = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		dropdown.click();

		WebElement selectLocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		selectLocation.click();

		Thread.sleep(2000);

		WebElement locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();

		extent.createTest("Go to /drug-register ").assignCategory("Log In").assignDevice("Chrome").log(Status.INFO,
				"Go to /drug-register");

		Thread.sleep(2000);
		

//Check the stock on the medidcation
		

		WebElement medidcationfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medidcationfilter.click();
		medidcationfilter.sendKeys("tiagabine 15 mg tablet");

		Thread.sleep(2000);

		WebElement NotificationBTN = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='new-patient-button data-v-tooltip']//i[@class='pi pi-exclamation-circle']")));
		NotificationBTN.click();

		Thread.sleep(2000);

		WebElement SearchBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();

		Thread.sleep(2000);

// Print the Present available balance of that Medicaiton

		WebElement AvailableBalance = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@style='width: 80px;']")));

		extent.createTest("Check the Available Balance for this medication ")
				.assignCategory("Verify the Available Balance ").assignDevice("Chrome")
				.log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();

		System.out.println("Available Balance  " + stock);

		Thread.sleep(2000);

//New Delivery Process

		WebElement NewDeliveryBTN = wait

				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='New Delivery']")));
		NewDeliveryBTN.click();

		extent.createTest("CLICK on the New delivery button present at the left menu on the dashboard  ")
				.assignCategory("New Delivery").assignDevice("Chrome")
				.log(Status.INFO, "CLICK on the New delivery button present at the left menu on the dashboard");
		Thread.sleep(2000);

		WebElement ClickonEnterSupplyINPUTfield = wait

				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//input[@placeholder='Enter supplier name']")));
		ClickonEnterSupplyINPUTfield.click();
		ClickonEnterSupplyINPUTfield.sendKeys("s");

		extent.createTest("Select the supplier name form the dropdown  ").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Select the supplier name form the dropdown");
		Thread.sleep(2000);

		WebElement SelectSupplier = wait

				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='SPL']")));
		SelectSupplier.click();
		Thread.sleep(2000);

		WebElement datePickerButton = driver.findElement(By.xpath("//input[@placeholder=' Date']"));
		datePickerButton.click();

		extent.createTest("select the date   ").assignCategory("New Delivery").assignDevice("Chrome").log(Status.INFO,
				"select the date ");
		Thread.sleep(1000);

		// Calculate the target date (2 days from now)
		LocalDate currentDate = LocalDate.now();
		LocalDate targetDate = currentDate.plusDays(2);
		String targetDay = String.valueOf(targetDate.getDayOfMonth());

		// Use a relative XPath to locate the day after tomorrow in the calendar
		String xpathForTargetDate = "//td[not(@class='p-datepicker-other-month') and not(contains(@class,'p-disabled'))]//span[text()='"
				+ targetDay + "']";

		// Find and click on the WebElement representing the target date
		WebElement targetDateElement = driver.findElement(By.xpath(xpathForTargetDate));
		targetDateElement.click();

		Thread.sleep(1000);

		WebElement EnterRfncNumber = driver.findElement(By.xpath("//input[@placeholder='Reference Number']"));
		EnterRfncNumber.click();
		EnterRfncNumber.sendKeys("12345");

		extent.createTest("select the date   ").assignCategory("New Delivery").assignDevice("Chrome").log(Status.INFO,
				"select the date ");
		Thread.sleep(1000);

		WebElement EnterNotes = driver.findElement(By.xpath("//textarea[@id='note-modal']"));
		EnterNotes.click();
		EnterNotes.sendKeys("We have to deliver this medication on urgent basis");

		extent.createTest("select the date   ").assignCategory("New Delivery").assignDevice("Chrome").log(Status.INFO,
				"select the date ");
		Thread.sleep(1000);

		WebElement ClickonSelectMedication = driver.findElement(By.xpath("//input[@placeholder='Select Medication']"));
		ClickonSelectMedication.click();
		ClickonSelectMedication.sendKeys("t");
		Thread.sleep(1000);

		WebElement SelectMedication = driver
				.findElement(By.xpath("//li[@aria-label='(tiagabine) tiagabine 15 mg tablet']"));
		SelectMedication.click();

		extent.createTest("Select Medication  ").assignCategory("New Delivery").assignDevice("Chrome").log(Status.INFO,
				"Select Medication ");
		Thread.sleep(1000);

		WebElement qtyinput = driver.findElement(By.xpath("//input[@placeholder='Enter qty']"));
		qtyinput.click();
		qtyinput.sendKeys("10");
		Thread.sleep(1000);

		extent.createTest("Enter QUANTITY to deliver  ").assignCategory("New Delivery").assignDevice("Chrome")
				.log(Status.INFO, "Enter QUANTITY to deliver  ");
		Thread.sleep(1000);

		WebElement AddBTN = driver.findElement(By.xpath("//p[@class='submit-button blue-button']"));
		AddBTN.click();

		extent.createTest("Click on Add button to add the medication  ").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Click on Add button to add the medication ");
		Thread.sleep(1000);


//Print the entered qty
		
		
		WebElement AddedBalance = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='10']")));

		extent.createTest("Verify the entered Quantity for the selected medication ").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Verify the entered Quantity for the selected medication ");


		String add = AddedBalance.getText();

		System.out.println("New Delivered Qty = " + add);
		
		extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("New Delivery")
		.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		int addedquantity = Integer.parseInt(add);
		int availablestock = Integer.parseInt(stock);

		int sum = addedquantity + availablestock;
		
		System.out.println("Total Balance : " + sum);
		
		extent.createTest(" Print Total Balance of Selected Medication After transaction  ").assignCategory("New Delivery")
		.assignDevice("Chrome").log(Status.INFO, "Print Total Balance of Selected Medication After transaction");

		WebElement ReceiveDlvryBTN = driver.findElement(By.xpath("//p[normalize-space()='Receive Delivery']"));
		ReceiveDlvryBTN.click();

		extent.createTest("Click on Add button to add the medication  ").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Click on Add button to add the medication ");
		Thread.sleep(2000);

		WebElement EnterPassword = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		EnterPassword.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		extent.createTest("Enter correct signature and click the Sign button").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		extent.createTest("Click on complete button ").assignCategory("New Delivery").assignDevice("Chrome")
				.log(Status.INFO, "Click on complete button ");

		Thread.sleep(2000);

		
		WebElement ArroBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
		ArroBTN.click();

		extent.createTest("Verify the transaction of delicry on Drug register ").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Verify the transaction of delicry on Drug register");

		WebElement stockclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockclick.click();

		extent.createTest("Click Stock in the top menu").assignCategory("New Delivery").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock in the top menu");

		Thread.sleep(2000);

		WebElement stocktakeclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stocktakeclick.click();

		WebElement medicationfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();

		medicationfilter.sendKeys("tiagabine 15 mg tablet");

		Thread.sleep(2000);

		WebElement includeS8btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		includeS8btn.click();

		extent.createTest("Click on the Include s8 button").assignCategory("New Delivery").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Include s8 button");

		Thread.sleep(1000);

		WebElement Displaybtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		Displaybtn.click();

		extent.createTest("Click on the Display in staock only button").assignCategory("New Delivery")
				.assignDevice("Chrome").log(Status.INFO, "Click on the Display in staock only button");

		Thread.sleep(1000);

		WebElement CLICKsearch = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		CLICKsearch.click();
		Thread.sleep(2000);

		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tr[2]/td[4]")));

		String stringByConcatenation = sum + "";

		String textToRemove = "tablet(s)";

		// Remove text using replace()
		String modifiedString = stringByConcatenation.replace(textToRemove, "");

		System.out.println("Total New Balance  =  " + modifiedString);
		
		extent.createTest("Print Total Balance of the Selected medication after transaction , shown in the stocktake screen").assignCategory("New Delivery").assignDevice("Chrome")
		.log(Status.INFO, "Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		if 
		(modifiedString.contentEquals(stringByConcatenation)) 
		{
			System.out.println(
					"Quantity of medication after transaction  is matched"); 
			} 
		
		else 
		{
			System.out.println(
					"Quantity of medication after transaction is not matched");
			}
		
		extent.createTest("Print Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched or Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is not matched , if the qty not matched. ")
		.assignCategory("New Delivery").assignDevice("Chrome")
		.log(Status.INFO, "Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		extent.flush();

	}

}
