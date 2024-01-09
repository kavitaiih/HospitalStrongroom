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

public class OutgoingPatient {

	public static void main(String[] args) throws InterruptedException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("OutgoingPatientDiscarded.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

//Log In

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login")
				.assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome")
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

		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();

		extent.createTest("Go to /drug-register").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Go to /drug-register");

// Print the Present available balance of that Medicaiton on the Stocktake 

		WebElement Stock = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();

		Thread.sleep(2000);

		WebElement StockTake = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
		StockTake.click();

		Thread.sleep(2000);

		WebElement medicationfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();
		medicationfilter.sendKeys("doxazosin 4 mg tablet");

		Thread.sleep(2000);

		WebElement Patientfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("kammo");

		Thread.sleep(1000);

		WebElement clickonDisplyStock = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		clickonDisplyStock.click();

		Thread.sleep(1000);

		WebElement clickonIncluseS8 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
		clickonIncluseS8.click();

		Thread.sleep(1000);

		WebElement SearchBTN = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();

		Thread.sleep(2000);
		

//Print the Available Balance 
		

		WebElement AvailableBalance = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();

		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		Integer intValue1 = Integer.valueOf(intValue);

		System.out.println("Current Stock = " + intValue1);

		Thread.sleep(2000);

//Transfer OUT Process with Patient selection			

		WebElement OutGoingbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		OutGoingbtn.click();

		extent.createTest("Click on the Outgoing button in the left menu")
				.assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Outgoing button in the left menu");

		Thread.sleep(2000);

		WebElement Discardedbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Discarded']")));
		Discardedbtn.click();

		extent.createTest("Click on the Discarded option").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Click on the Discarded option");

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

		WebElement selectpatient = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@class,'patient-result-info')]//p[1]")));

		selectpatient.click();

		extent.createTest("Select patient").assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome")
				.log(Status.INFO, "Select patient");

		Thread.sleep(2000);

		WebElement medicationdrp = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		medicationdrp.click();

		Thread.sleep(2000);

		WebElement selectmedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='doxazosin 4 mg tablet']")));
		selectmedication.click();

		extent.createTest("Enter a medication").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Enter a medication");

		Thread.sleep(2000);

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();
		qty.sendKeys("1");

		extent.createTest("Select a medication and qty").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Select a medication and qty");

		Thread.sleep(2000);

		WebElement addbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addbtn.click();

		extent.createTest("Click the Add button").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Click the Add button");

		Thread.sleep(1000);

//Print the entered qty

		

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[4]/table/tr/td[2]")));
		String Add = AddedBalance.getText();
		
		extent.createTest("Print the entered qty").assignCategory("OutGoingPatinet(DiscardedMedication)")
		.assignDevice("Chrome").log(Status.INFO, "Print the entered qty");

		// Check if the string is not empty
		if (!Add.isEmpty()) {
			// Get the first character as an integer
			int firstCharacter = Character.getNumericValue(Add.charAt(0));

			// Print the first character
			System.out.println("Outgoing Quantity = " + firstCharacter);

			Integer result = intValue1 - firstCharacter;

			// Print the result
			System.out.println("Result = " + result);
		} else {
			System.out.println("The string is empty");
		}
		
		
		

// Continue the outgoing process
		
		

		WebElement submitbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		submitbtn.click();

		extent.createTest("Click on the submit button").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Click on the submit button");

		Thread.sleep(2000);

		WebElement enterpass = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		enterpass.sendKeys("1111");

		WebElement pwd1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));

		pwd1.click();

		extent.createTest("Enter correct signature and click the Sign button")
				.assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome")
				.log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		extent.createTest("Go to the dashboard").assignCategory("OutGoingPatinet(DiscardedMedication)")
				.assignDevice("Chrome").log(Status.INFO, "Go to the dashboard");

		Thread.sleep(2000);
		
		

	
//Compare the values
		
		
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));
		
		int firstCharacter = Character.getNumericValue(Add.charAt(0));

		// Print the first character
		
		
		Integer result = intValue1 - firstCharacter;

		String stringByConcatenation =  result + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + result);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		if (modifiedString1.contentEquals(stringByConcatenation)) {
			System.out.println(
					"Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched");
		}

		else {
			System.out.println(
					"Quantity of /edication after transaction in the  Drug Register Screen and Stocktake Screen is not matched");
		}

		extent.createTest(
				"Print Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is matched or Quantity of medication after transaction in the  Drug Register Screen and Stocktake Screen is not matched , if the qty not matched. ")
				.assignCategory("OutGoingPatinet(DiscardedMedication)").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		Thread.sleep(2000);




		extent.flush();

	}

}
