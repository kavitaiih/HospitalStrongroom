package launchbrowser;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class TransferInimprest {

	static WebDriver driver = new ChromeDriver();
	static ExtentReports extent = new ExtentReports();
	static ExtentSparkReporter spark = new ExtentSparkReporter("TransferINimprest.html");

static int	searchCount = 0;
static String[] blankArray = new String[10];


	public static void main(String[] args) throws InterruptedException, CsvException {

		// Extent report setup

		extent.attachReporter(spark);

		driver.manage().window().maximize();

//LOg In PROCESS		

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("TransferINimprest")
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

		extent.createTest("Login successfully").assignCategory("TransferINimprest").assignDevice("Chrome")
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

		extent.createTest("Go to /drug-register ").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);

// Print the  AVAILABLE and NEW data for transfer in process (for multipal medications)

		try (CSVReader csvReader = new CSVReader(new FileReader("/var/www/html/automationData/AutomationData.csv"))) {
			List<String[]> rows = csvReader.readAll();
			int count = 0;
			for (String[] row : rows) {
				if (count == 0) {
					count++;
					continue;
				}
				if (row.length > 0)
				{
					String medicationName = row[0].trim(); // Assuming medication name is in the first column
					String location = row[1].trim();
					String patientName = row[3].trim();
					String quantity = row[4].trim();
					String process = row[5].trim();
					System.out.println(medicationName);
					System.out.println(location);
					System.out.println(patientName);
					System.out.println(quantity);
					System.out.println(process);
					//quit();
					
					stocktake(medicationName, location, patientName, quantity, process);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	

		extent.flush();

		// driver.quit();

	}

	private static void quit() {
		// TODO Auto-generated method stub

	}

	public static void stocktake(String medicationName, String location, String patientName, String quantity,
			String process) throws InterruptedException, CsvException, IOException {
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

		WebElement clickonDisplyStock = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		clickonDisplyStock.click();

		Thread.sleep(1000);

		WebElement clickonIncluseS8 = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
		clickonIncluseS8.click();

		Thread.sleep(1000);

		WebElement clickonDisplyIMPRESTonly = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		clickonDisplyIMPRESTonly.click();

		Thread.sleep(1000);

		WebElement SearchBTN = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
		SearchBTN.click();

		Thread.sleep(2000);

		// Print the Available Balance

		WebElement AvailableBalance = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

		extent.createTest("Check the Available Balance for this medication ").assignCategory("TransferINimprest ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();
		System.out.println("Current Stock = " + stock);
		

		Thread.sleep(2000);

//Transfer In Process with IMprest Option			

		WebElement transferin = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Transfer In']")));
		transferin.click();

		Thread.sleep(2000);

		extent.createTest("Click the Transfer In button in the left menu").assignCategory("TransferINimprest")
				.assignDevice("Chrome").log(Status.INFO, "Click the Transfer In button in the left menu");

		WebElement drpdwn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		WebElement drpdwn1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Type in location to receive from']")));

		drpdwn1.sendKeys(location);
		drpdwn.click();
		extent.createTest("Enter a location").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Enter a location");

		Thread.sleep(2000);

		WebElement locationselect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Orange Hospital']")));
		locationselect.click();

		extent.createTest("Select a location").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Select a location");

		WebElement addnotes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

		addnotes.sendKeys("Notes Will be here");

		Thread.sleep(2000);

		extent.createTest("Add text to notes").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Add text to notes");

		WebElement Imprestbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
		Imprestbtn.click();

		extent.createTest("Click the Imprest/Emergency Meds/Ward Stock button").assignCategory("TransferINimprest")
				.assignDevice("Chrome").log(Status.INFO, "Click the Imprest/Emergency Meds/Ward Stock button");

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
		extent.createTest("Enter a medication").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Enter a medication");

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));

		qty.sendKeys(quantity);
		
		

		extent.createTest("Select a medication and qty").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Select a medication and qty");
		Thread.sleep(2000);

		WebElement addbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		addbtn.click();
		extent.createTest("Click the Add button").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Click the Add button");
		Thread.sleep(2000);

//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='right-form-section-drug-container']//span[1]")));

		extent.createTest("Verify the entered Quantity for the selected medication ")
				.assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

		String add = AddedBalance.getText().trim();

		System.out.println("Added qty =  " + add);

		extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("TransferINimprest")
				.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

		Thread.sleep(1000);

		String textToRemove = "mL(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		int abc = Integer.parseInt(add);

		int sum = intValue + abc;

		System.out.println("Total Balance = " + sum);

		
		
		WebElement receivetranferbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Receive Transfer']")));
		receivetranferbtn.click();

		extent.createTest("Click the Recieve Transfer button").assignCategory("TransferINimprest")
				.assignDevice("Chrome").log(Status.INFO, "Click the Recieve Transfer button");

		Thread.sleep(2000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd.sendKeys("1111");

		Thread.sleep(2000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		extent.createTest("Enter correct signature and click the Sign button").assignCategory("TransferINimprest")
				.assignDevice("Chrome").log(Status.INFO, "Enter correct signature and click the Sign button");

		Thread.sleep(2000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		Thread.sleep(2000);

		extent.createTest("Click on complete button ").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Click on complete button ");

//Comparission of the stock qty between drug register and stocktabke

		WebElement stockclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
		stockclick.click();

		extent.createTest("Click Stock in the top menu").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock in the top menu");

		Thread.sleep(2000);

		WebElement stocktakebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
		stocktakebtn.click();

		extent.createTest("Click Stock Take in the top menu").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Click Stock Take in the top menu");

		Thread.sleep(2000);

		WebElement medicationfilter1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter1.click();
		medicationfilter1.sendKeys(medicationName);

		Thread.sleep(2000);

		WebElement includeS8btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Include S8']")));
		includeS8btn.click();

		extent.createTest("Click on the Include s8 button").assignCategory("TransferINimprest").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Include s8 button");

		Thread.sleep(1000);

		WebElement Displaybtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
		Displaybtn.click();

		Thread.sleep(1000);

		WebElement clickonIMprestOnly = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display Imprest Only']")));
		clickonIMprestOnly.click();

		WebElement CLICKsearch = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		CLICKsearch.click();

		Thread.sleep(2000);

		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

		String stringByConcatenation = sum + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + sum);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("TransferINimprest").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("TransferINimprest").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");
		blankArray[searchCount] = stock;
		
		
		InitialBalance(stock);
		

	}

	
	public static void InitialBalance(String initialbal) throws CsvException, IOException {

		searchCount++;
        // Path to your CSV file
        String csvFilePath = "/var/www/html/automationData/AutomationData.csv";
        System.out.println("------------------");
        System.out.println(initialbal);
        System.out.println("------------------");
        String columnNameToUpdate = "initialBalance";
		// Specify the new values for the column
		List<String> newColumnData = new ArrayList<>();
		newColumnData.add("200");
		newColumnData.add("300");
		// Update the CSV file
		updateCSVColumn(csvFilePath, columnNameToUpdate, newColumnData);
	}
       

	private static void updateCSVColumn(String filePath, String columnName, List<String> newColumnData)
            throws CsvValidationException {
		
		System.out.println("------------------");
        System.out.println(newColumnData);
        System.out.println("------------------");
        
        
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));
             CSVWriter csvWriter = new CSVWriter(new FileWriter("/var/www/html/automationData/AutomationData.csv"))) {

            // Read the header
            String[] header = csvReader.readNext();
            if (header == null) {
                System.out.println("CSV file is empty or missing a valid header.");
                return;
            }

            int columnIndexToUpdate = -1;
            // Find the index of the column to update
            for (int i = 0; i < header.length; i++) {
                if (header[i].equals(columnName)) {
                    columnIndexToUpdate = i;
                    break;
                }
            }
            if (columnIndexToUpdate == -1) {
                System.out.println("Column not found: " + columnName);
                return;
            }

            // Write the header to the new file
            csvWriter.writeNext(header);

            // Process and update each row
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (columnIndexToUpdate < nextRecord.length) {
                    // Update the specified column data
                    nextRecord[columnIndexToUpdate] = newColumnData.get(columnIndexToUpdate);
                }
                // Write the updated row to the new file
                csvWriter.writeNext(nextRecord);
            }

            System.out.println("CSV file updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
     


