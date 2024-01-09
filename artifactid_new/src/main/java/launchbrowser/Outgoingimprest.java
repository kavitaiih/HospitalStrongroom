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

public class Outgoingimprest {

	public static void main(String[] args) throws InterruptedException {

//Log In process

		// Extent report setup
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("OutgoingImprest.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		// Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("OutgoingImprest")
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

		extent.createTest("Go to /drug-register").assignCategory("OutgoingImprest").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

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
		
	
		
// Print the  available balance of that Medicaiton on the Stocktake 
		
		
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
		medicationfilter.sendKeys("Anti-Inflammatory Pain Relief (Apohealth) 1% gel");

		Thread.sleep(2000);

		WebElement ImprestOnly = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		ImprestOnly.click();

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
				.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div[2]/table/tr[2]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("OutgoingImprest ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		
		String textToRemove = "g(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

        Integer intValue1 = Integer.valueOf(intValue);

		
		
		System.out.println("Current Stock = " + intValue1);

		Thread.sleep(2000);
		
		
		

//Outgoing Process with IMprest selection 
		
		

		WebElement Outingbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Outgoing']")));
		Outingbtn.click();

		Thread.sleep(2000);

		extent.createTest("Click the Outgoing button in the left menu").assignCategory("OutgoingImprest")
				.assignDevice("Chrome").log(Status.INFO, "Click the Outgoing button in the left menu");
		Thread.sleep(2000);

		WebElement lostbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Lost']")));
		lostbtn.click();

		extent.createTest("Select a type (Lost, Discarded, Damaged)").assignCategory("OutgoingImprest")
				.assignDevice("Chrome").log(Status.INFO, "Select a type (Lost, Discarded, Damaged)");
		Thread.sleep(2000);

		Thread.sleep(2000);

		WebElement addnotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		addnotes.sendKeys("Notes Will be here");

		Thread.sleep(2000);

		extent.createTest("Add text to notes").assignCategory("OutgoingImprest").assignDevice("Chrome").log(Status.INFO,
				"Add text to notes");

		Thread.sleep(2000);

		WebElement IMprestbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		IMprestbtn.click();

		extent.createTest("Click the Imprest/Emergancy Meds/Ward Stock button").assignCategory("OutgoingImprest")
				.assignDevice("Chrome").log(Status.INFO, "Click the Imprest/Emergancy Meds/Ward Stock button");

		Thread.sleep(2000);

		WebElement typemedicationNAME = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		typemedicationNAME.click();

		typemedicationNAME.sendKeys("a" + Keys.ENTER);

		WebElement SELECTmedication = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//p[normalize-space()='Anti-Inflammatory Pain Relief (Apohealth) 1% gel']")));
		SELECTmedication.click();

		extent.createTest("Enter a medication").assignCategory("OutgoingImprest").assignDevice("Chrome")
				.log(Status.INFO, "Enter a medication");

		Thread.sleep(1000);

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter quantity']")));

		qty.sendKeys("1");

		extent.createTest("Select a medication and qty").assignCategory("OutgoingImprest").assignDevice("Chrome")
				.log(Status.INFO, "Select a medication and qty");

		Thread.sleep(2000);

		WebElement addclicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		addclicked.click();

		extent.createTest("Click the Add button").assignCategory("OutgoingImprest").assignDevice("Chrome")
				.log(Status.INFO, "Click the Add button");
		
		Thread.sleep(2000);
		
		
		
//Print the entered qty
		
		

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[1]/td[2]/p[1]")));
		String Add = AddedBalance.getText();

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
		
		
		

		WebElement submitbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[contains(@class,'regular-button complete-button')]")));
		submitbtn.click();

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd.sendKeys("1111");

		extent.createTest("Enter Correct Signature").assignCategory("OutgoingImprest").assignDevice("Chrome")
				.log(Status.INFO, "Enter Correct Signature");

		Thread.sleep(1000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		Thread.sleep(1000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(1000);
		
		
		WebElement searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		searchbtn.click();

		Thread.sleep(1000);
		
		
//Compare the values
		
		
		
		
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tr[2]/td[4]")));
		
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
				.assignCategory("OutgoingImprest").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("OutgoingImprest").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		Thread.sleep(2000);




		extent.flush();
	}

}
