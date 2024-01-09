package launchbrowser;

import java.io.FileReader;

//import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
//import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
//import com.opencsv.exceptions.CsvValidationException;

//Import necessary libraries for CSV handling
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;

//... (existing imports)

public class TRansferINCSV {

	// Existing code...
	static WebDriver driver = new ChromeDriver();
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("TransferINimprest.html");

	static int searchCount = 0;
	static String[] blankArray = new String[10];

	public static void main(String[] args) throws InterruptedException, IOException, CsvException {
		// ... (existing code)
		
		transferInProcess("medicationName1", "patientName1", "quantity1");


		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("TransferinPatient.html");
		extent.attachReporter(spark);

//Log In Process

		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Opening the URL");


		driver.get("https://hospital-staging.strongroom.ai/login");

		// Display status log on html report page
		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("TransferINpatient")
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

		extent.createTest("Login successfully").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Login successfully");

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
		extent.createTest("Go to /drug-register ").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");
		Thread.sleep(2000);

		// Read data from CSV
		try (CSVReader csvReader = new CSVReader(new FileReader("/var/www/html/automationData/AutomationData.csv"))) {
			List<String[]> rows = csvReader.readAll();
			int count = 0;
			for (String[] row : rows) {
				if (count == 0) {
					count++;
					continue;
				}
				// List<String[]> medicationData =
				// readCSV("/var/www/html/automationData/AutomationData.csv");

				// Iterate over medication data
				if (row.length > 0) {

					String medicationName = row[0].trim(); // Assuming the first column is MedicationName
					String patientName = row[1].trim(); // Assuming the second column is PatientName
					String quantity = row[2].trim(); // Assuming the third column is Qty

					transferInProcess(medicationName, patientName, quantity);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		

		extent.flush();
		
		//private static void wait() {}

	}

	// Method for reading data from CSV
//	public static List<String[]> readCSV(String filePath) throws IOException, CsvException {
//		// Use a CSV library to read data from the CSV file
//		// Example using OpenCSV library:
//		List<String[]> records;
//		try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
//			records = csvReader.readAll();
//		}
//		return records;
	}

	// Method for the transfer-in process
	//public static void transferInProcess(WebDriver driver, ExtentReports extent, WebDriverWait wait,
			//String medicationName, String patientName, String quantity)

			//throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5000));
//
		// Print the Present available balance of that Medicaiton on the Stocktake
		
public static void transferInProcess(String medicationName, String patientName, String quantity)
        throws InterruptedException, IOException {
	
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));


			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));


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
		medicationfilter.sendKeys(medicationName);

		Thread.sleep(2000);

		WebElement Patientfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("patientName");

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

		// Print the Available Balance
		WebElement AvailableBalance = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("TransferINpatient ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		System.out.println("Current Stock = " + stock);

		Thread.sleep(2000);

		// Transfer In Process with Patient selection

		WebElement transferin = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
		transferin.click();

		Thread.sleep(2000);

		extent.createTest("Click the Transfer In button in the left menu").assignCategory("TransferINpatient")
				.assignDevice("Chrome").log(Status.INFO, "Click the Transfer In button in the left menu");

		WebElement drpdwn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		WebElement drpdwn1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));

		drpdwn1.sendKeys("Emergency Ward");
		drpdwn.click();
		extent.createTest("Enter a location").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Enter a location");

		Thread.sleep(2000);

		WebElement locationselect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
		locationselect.click();

		extent.createTest("Select a location").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Select a location");

		WebElement addnotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		addnotes.sendKeys("Notes Will be here");

		Thread.sleep(2000);

		extent.createTest("Add text to notes").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Add text to notes");

		WebElement Patientmedicationbtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		Patientmedicationbtn.click();
		extent.createTest("Click the Patient Medication button ").assignCategory("TransferINpatient")
				.assignDevice("Chrome").log(Status.INFO, " Click the Patient Medication button");
		Thread.sleep(2000);

		WebElement patientname = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		patientname.click();
		patientname.sendKeys("k" + Keys.ENTER);
		extent.createTest("Enter a patient name in the search field and click search ")
				.assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, " Enter a patient name in the search field and click search");
		Thread.sleep(2000);

		WebElement clickresult = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
		clickresult.click();
		Thread.sleep(2000);

		WebElement prescribername = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
		prescribername.click();
		extent.createTest("Enter Prescriber name ").assignCategory("TransferINpatient").assignDevice("Chrome")
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

		WebElement drpdwn2 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Select Medication')]")));
		WebElement drpdwn3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[contains(@class,'right-form-section-drug-entry')]//div//span[contains(@class,'p-dropdown-trigger-icon pi pi-chevron-down')]")));

		drpdwn2.sendKeys("(guaifenesin) guaifenesin 100 mg/5 mL oral liquid");

		drpdwn3.click();

		Thread.sleep(2000);

		WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(@aria-label,'(guaifenesin) guaifenesin 100 mg/5 mL oral liquid')]")));
		selectmedication.click();
		extent.createTest("Enter a medication").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Enter a medication");

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));

		qty.sendKeys(quantity);
		extent.createTest("Select a medication and qty").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Select a medication and qty");
		Thread.sleep(2000);

		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		addbtn.click();
		extent.createTest("Click the Add button").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click the Add button");

		Thread.sleep(2000);

		// Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

		extent.createTest("Verify the entered Quantity for the selected medication ")
				.assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

		String add = AddedBalance.getText().trim();

		System.out.println("Added qty =  " + add);

		extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("TransferINpatient")
				.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		String textToRemove = "mL(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		int abc = Integer.parseInt(add);

		int sum = intValue + abc;

		System.out.println("Total Balance = " + sum);

		// Continue the process

		WebElement receivetranferbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Receive Transfer']")));
		receivetranferbtn.click();

		extent.createTest("Click the Recieve Transfer button").assignCategory("TransferINpatient")
				.assignDevice("Chrome").log(Status.INFO, "Click the Recieve Transfer button");

		Thread.sleep(2000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		extent.createTest("Enter correct signature and click the Sign button").assignCategory("TransferINpatient")
				.assignDevice("Chrome").log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		extent.createTest("Click on complete button ").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click on complete button ");

		Thread.sleep(2000);

		// Comparission of the stock qty between drug register and stocktabke

		WebElement stockclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockclick.click();

		extent.createTest("Click Stock in the top menu").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock in the top menu");

		Thread.sleep(2000);

		WebElement stocktakebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stocktakebtn.click();

		extent.createTest("Click Stock Take in the top menu").assignCategory("TransferINpatient").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock Take in the top menu");

		Thread.sleep(2000);

		WebElement medicationfilter1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter1.click();
		medicationfilter1.sendKeys(medicationName);

		Thread.sleep(2000);

		WebElement Patientfilter1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter1.click();
		Patientfilter1.sendKeys(patientName);

		WebElement includeS8btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		includeS8btn.click();

		extent.createTest("Click on the Include s8 button").assignCategory("TransferINpatient").assignDevice("Chrome")
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

		// Print
		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

		String stringByConcatenation = sum + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + sum);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("TransferINpatient").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("TransferINpatient").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		// ... (existing code for transfer-in process)

		// Modify the code to use dynamic data (medicationName, patientName, quantity)
		// in the transfer-in process

		// ... (modify the code accordingly)

		// ... (modify the code accordingly)

		// ... (modify the code accordingly)

		// ... (modify the code accordingly)

		// Print or log relevant information based on your requirements

		// ... (existing code)

		Thread.sleep(2000);

	}
}