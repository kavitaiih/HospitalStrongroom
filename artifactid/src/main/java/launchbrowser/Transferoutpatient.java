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

public class Transferoutpatient {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("TransferOUTPatient.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		// Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("TransferOUTpatient")
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

		extent.createTest("Login successfully").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Login successfully");

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

		extent.createTest(" Go to /drug-register").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, " Go to /drug-register");

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

		extent.createTest("Check the Available Balance for this medication ").assignCategory("TransferOUTpatient ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		System.out.println("Current Stock = " + stock);

		Thread.sleep(2000);

//Transfer OUT Process with Patient selection				

		WebElement transferout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer Out']")));
		transferout.click();

		extent.createTest("Click the Transfer Out button in the left menu ").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, " Click the Transfer Out button in the left menu");
		Thread.sleep(2000);

		WebElement drpdwn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		WebElement drpdwn1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to send to']")));

		drpdwn1.sendKeys("Emergency Ward");
		drpdwn.click();
		extent.createTest(" Enter a location").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, " Enter a location");
		Thread.sleep(2000);

		WebElement option1clicked = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
		option1clicked.click();
		extent.createTest("Select a location ").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, " Select a location");
		Thread.sleep(2000);

		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys("Notes Will be here");
		extent.createTest(" Add text to notes").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, " Add text to notes");
		Thread.sleep(2000);

		WebElement Patientmedicationbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		Patientmedicationbtn.click();
		extent.createTest("Click the Patient Medication button ").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, " Click the Patient Medication button");
		Thread.sleep(2000);

		WebElement patientname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		patientname.click();
		patientname.sendKeys("k" + Keys.ENTER);
		extent.createTest("Enter a patient name in the search field and click search ")
				.assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, " Enter a patient name in the search field and click search");
		Thread.sleep(2000);

		WebElement clickresult = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
		clickresult.click();
		Thread.sleep(2000);

		WebElement prescribername = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
		prescribername.click();
		extent.createTest("Enter Prescriber name ").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Enter Prescriber name");
		Thread.sleep(2000);

		WebElement addspace = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
		addspace.sendKeys(" ");
		Thread.sleep(1000);

		WebElement resultclicked = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Reference only - Jim jam']")));
		resultclicked.click();
		Thread.sleep(1000);

		// Click on the dropdown to open it
		WebElement dropdownTrigger = driver.findElement(By.xpath(
				"//div[@class='right-form-section-drug-entry']//div//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"));
		dropdownTrigger.click();

		// Click on the specific option in the dropdown
		WebElement optionElement = driver.findElement(
				By.xpath("//li[@aria-label='doxazosin 4 mg tablet']//div[@class='ingredient-item']//div[1]//div[1]"));
		optionElement.click();
		extent.createTest(" Enter a medication").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Enter a medication");

		Thread.sleep(1000);

		WebElement qtybox = driver.findElement(By.xpath("//input[@placeholder='Quantity']"));
		qtybox.click();

		qtybox.clear();
		qtybox.sendKeys("1");
		extent.createTest(" Select a medication and qty").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Select a medication and qty");
		Thread.sleep(2000);

		WebElement addbox = driver.findElement(By.xpath("//p[@class='blue-button']"));
		addbox.click();
		extent.createTest(" Click the Add button").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click the Add button");
		Thread.sleep(2000);

//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

		extent.createTest("Verify the entered Quantity for the selected medication ")
				.assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

		String add = AddedBalance.getText().trim();

		System.out.println("Out qty =  " + add);

		extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		int out = Integer.parseInt(add);

		int Final = intValue - out;

		System.out.println("Remaining Balance = " + Final);

		WebElement Sendtranferbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Send Transfer']")));
		Sendtranferbtn.click();

		extent.createTest("Click the Send Transfer button").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click the Send Transfer button");
		Thread.sleep(1000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd.sendKeys("1111");
		Thread.sleep(1000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();
		extent.createTest("Enter correct signature and click the Sign button").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Enter correct signature and click the Sign button");
		Thread.sleep(1000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		extent.createTest("complete process").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "complete process");
		Thread.sleep(2000);

//Complete the pending transfer out process by changing the location		

		WebElement ChangeLocationbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='new-patient-button data-v-tooltip']")));
		ChangeLocationbtn.click();
		Thread.sleep(2000);

		WebElement selectLocationbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Orange Hospital- Emergency Ward']")));
		selectLocationbtn.click();

		extent.createTest("Switch to the location chosen in step 4").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Switch to the location chosen in step 4");
		Thread.sleep(2000);

		WebElement clickontransferbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Transfers']")));
		clickontransferbtn.click();

		extent.createTest("Go to Stock -> Transfers in the top menu").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Go to Stock -> Transfers in the top menu");
		Thread.sleep(3000);

		WebElement Arrow2btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
		Arrow2btn.click();

		extent.createTest("Open the new transfer row").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Open the new transfer row");
		Thread.sleep(3000);

		WebElement CompleteTransferbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='transfer-action-button complete-button']")));
		CompleteTransferbtn.click();

		extent.createTest("Click the Complete Transfer Button").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Click the Complete Transfer Button");
		Thread.sleep(3000);

		WebElement Extranote = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		Extranote.sendKeys("You can write extra note here");

		extent.createTest("Add extra info to notes").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Add extra info text to notes");

		Thread.sleep(2000);

		WebElement Submitbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
		Submitbtn.click();

		extent.createTest("Add a note and click submit").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Add a note and click submit");
		Thread.sleep(3000);

		WebElement pwd1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd1.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signin1btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signin1btn.click();
		extent.createTest("Enter correct signature and click the Sign button").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement complete1btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		complete1btn.click();

		extent.createTest("complete button clicked").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "complete button clicked");

		Thread.sleep(2000);
		
	
//Again change the location
		
		
		WebElement Locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[2]/div[3]/div[3]")));
		Locationbtn.click();

		WebElement SelectLocationbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Orange Hospital- Pharmacy']")));
		SelectLocationbtn.click();

		Thread.sleep(2000);

		WebElement ClickonArrow = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
		ClickonArrow.click();

		Thread.sleep(2000);

		WebElement Stockbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		Stockbtn.click();

		extent.createTest("Click Stock in the top menu").assignCategory("TransferOUTpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock in the top menu");

		Thread.sleep(2000);

		WebElement Stocktakebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		Stocktakebtn.click();

		extent.createTest("Click Stock Take in the top menu").assignCategory("TransferOUTpatient")
				.assignDevice("Chrome").log(Status.INFO, "Click Stock Take in the top menu");
		Thread.sleep(2000);

		WebElement medicationfilter1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter1.click();
		medicationfilter1.sendKeys(" doxazosin 4 mg tablet");

		Thread.sleep(2000);

		WebElement Patientfilter1 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter1.click();
		Patientfilter1.sendKeys("kammo");

		Thread.sleep(1000);

		WebElement clickonDisplyStock1 = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		clickonDisplyStock1.click();

		Thread.sleep(1000);

		WebElement clickonIncluseS81 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
		clickonIncluseS81.click();

		Thread.sleep(1000);

		WebElement SearchBTN1 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
		SearchBTN1.click();

		Thread.sleep(2000);

//Print 		
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

		String stringByConcatenation = Final + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + Final);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("TransferOUTpatient").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("TransferOUTpatient").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		Thread.sleep(2000);

		extent.flush();

	}

}
