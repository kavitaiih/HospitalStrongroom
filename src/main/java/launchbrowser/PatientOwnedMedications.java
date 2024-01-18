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

public class PatientOwnedMedications {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("PatientOWNEDmedication.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

//Log In Process

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		// Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

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

		extent.createTest("Login successfully").assignCategory("PatientOWNEDmedication").assignDevice("Chrome").log(Status.INFO,
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

		// Display status log on html report page
		extent.createTest("Go to /drug-register ").assignCategory("PatientOWNEDmedication").assignDevice("Chrome").log(Status.INFO,
				"Go to /drug-register");

		Thread.sleep(2000);

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
		medicationfilter.sendKeys("yellow fever live vaccine injection, vial");

		Thread.sleep(2000);

		WebElement Patientfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("meera");

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

		
		/*
//Print the Available Balance 

		WebElement AvailableBalance = driver
				.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div/div[2]/table/tr[2]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("PatientOWNEDmedication ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		System.out.println("Current Stock = " + stock);

		Thread.sleep(2000);
		
		*/

//Process of Patient Own

		WebElement ClickonPatientOwned = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Patient Owned']")));
		ClickonPatientOwned.click();

		extent.createTest("Click on the Patient Owned tab from the left menu of the dashboard ")
				.assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Patient Owned tab from the left menu of the dashboard");

		Thread.sleep(2000);

		WebElement addnotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		addnotes.sendKeys("Notes Will be here");

		Thread.sleep(2000);

		extent.createTest("Add text to notes").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Add text to notes");

		Thread.sleep(2000);

		WebElement ClickONPatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='pom-imprest-choice-button']")));
		ClickONPatient.click();

		extent.createTest("Select the patient ").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Select the patient");

		Thread.sleep(2000);

		WebElement PatientName = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		PatientName.click();
		PatientName.sendKeys("meera" + Keys.ENTER);

		Thread.sleep(2000);

		WebElement selectpatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
		selectpatient.click();
		Thread.sleep(2000);

		WebElement TypeMedication = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
		TypeMedication.click();
		TypeMedication.sendKeys("yellow fever live vaccine injection, vial" + Keys.ENTER);

		WebElement SelectMedication = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//p[contains(text(),'(yellow fever live vaccine) yellow fever live vacc')]")));
		SelectMedication.click();

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter qty']")));

		qty.sendKeys("1");
		extent.createTest("Select a medication and qty").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Select a medication and qty");
		Thread.sleep(2000);

		WebElement AddBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
		AddBTN.click();

		Thread.sleep(2000);

		/*

//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[1]/td[2]/p[1]/span[1]")));

		extent.createTest("Verify the entered Quantity for the selected medication ")
				.assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

		String add = AddedBalance.getText().trim();

		System.out.println("Added qty =  " + add);

		extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		String textToRemove = "vial(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		int abc = Integer.parseInt(add);

		int sum = intValue + abc;

		System.out.println("Total Balance = " + sum);
		
		*/

//Continue the process		

		WebElement ReceiveBTN = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		ReceiveBTN.click();

		Thread.sleep(2000);

		WebElement Enterpass = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		Enterpass.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		extent.createTest("Enter correct signature and click the Sign button")
				.assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		extent.createTest("Click on complete button ").assignCategory("PatientOWNEDmedication")
				.assignDevice("Chrome").log(Status.INFO, "Click on complete button ");
		Thread.sleep(2000);

		// Comparission of the stock qty between drug register and stocktabke

		WebElement stockclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockclick.click();

		extent.createTest("Click Stock in the top menu").assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock in the top menu");

		Thread.sleep(2000);

		WebElement stocktakebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stocktakebtn.click();

		extent.createTest("Click Stock Take in the top menu").assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock Take in the top menu");

		Thread.sleep(2000);

		WebElement medicationfilter1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter1.click();
		medicationfilter1.sendKeys("Yellow fever live vaccine injection, vial");

		Thread.sleep(2000);

		WebElement Patientfilter1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter1.click();
		Patientfilter1.sendKeys("meera");

		WebElement includeS8btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		includeS8btn.click();

		extent.createTest("Click on the Include s8 button").assignCategory("PatientOWNEDmedication").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Include s8 button");

		Thread.sleep(1000);

		WebElement Displaybtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		Displaybtn.click();

		Thread.sleep(1000);

		WebElement CLICKsearch = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		CLICKsearch.click();

		Thread.sleep(2000);
		
		System.out.println("Verify the  entery in the Stocktake tab");

		
		/*
//Print 		

		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tr[2]/td[4]")));

		String stringByConcatenation = sum + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + sum);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("PatientOWNEDmedication").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("PatientOWNEDmedication").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");


*/
		extent.flush();
	}

}
