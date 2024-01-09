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

public class NweDestructionpatient {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("DestructionPatient.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("DestructionPatient")
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

		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[4]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement locationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		locationbtn.click();

// check the current balance of that particular medication for which you want to destro or perform destruction process.		

		WebElement Stock = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stock']")));
		Stock.click();

		extent.createTest("Check the Available Balance for that medication on the stocktake ")
				.assignCategory("DestructionPatient ").assignDevice("Chrome")
				.log(Status.INFO, "Check the Available Balance for this medication");

		Thread.sleep(2000);

		WebElement StockTake = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Stocktake']")));
		StockTake.click();

		Thread.sleep(2000);

		WebElement medicationfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Medication...']")));
		medicationfilter.click();
		medicationfilter.sendKeys("naproxen sodium 220 mg tablet");

		Thread.sleep(2000);

		WebElement patientfilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Patient...']")));
		patientfilter.click();
		patientfilter.sendKeys("meera");

		Thread.sleep(2000);

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

		extent.createTest("Print the current balance of that medication on the stocktake")
				.assignCategory("DestructionPatient").assignDevice("Chrome")
				.log(Status.INFO, "Print the current balance of that medication on the stocktake");

		extent.createTest("Check the Available Balance for this medication ").assignCategory("DestructionPatient ")
				.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

		// Get the text content of the element and print it

		String stock = AvailableBalance.getText();

		String textToRemove = "tablet(s)";
		String modifiedString = stock.replace(textToRemove, "");

		int intValue = Integer.parseInt(modifiedString.trim());

		Integer intValue1 = Integer.valueOf(intValue);

		System.out.println("Current Stock = " + intValue1);

		Thread.sleep(2000);

//Perform the new Destruction process with Imprest option			

		WebElement NewDEstuctionBTN = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='New Destruction']")));
		NewDEstuctionBTN.click();

		Thread.sleep(2000);

		WebElement notes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));
		notes.sendKeys("Notes Will be here");

		WebElement cLICKoNpatientmedicationBTN = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
		cLICKoNpatientmedicationBTN.click();

		WebElement PatientINputfield = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
		PatientINputfield.click();
		PatientINputfield.sendKeys("meera" + Keys.ENTER);
		Thread.sleep(1000);

		WebElement Selectpatient = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
		Selectpatient.click();

		Thread.sleep(2000);

		WebElement ClickonMedicationdrpdown = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
		ClickonMedicationdrpdown.click();

		Thread.sleep(2000);

		WebElement SelectMedicationdrpdown = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[normalize-space()='naproxen sodium 220 mg tablet']")));
		SelectMedicationdrpdown.click();

		Thread.sleep(2000);

		WebElement qtyclick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qtyclick.click();

		WebElement qty = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
		qty.clear();

		qty.sendKeys("1");

		Thread.sleep(1000);

		WebElement addclicked = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[2]/p")));
		addclicked.click();

		Thread.sleep(1000);

//Print the entered qty

		WebElement AddedBalance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[1]/td[2]/p[1]")));
		String Add = AddedBalance.getText();

		extent.createTest("Print the entered qty ").assignCategory("DestructionPatient").assignDevice("Chrome")
				.log(Status.INFO, "Print the entered qty");

		// Check if the string is not empty
		if (!Add.isEmpty()) {
			// Get the first character as an integer
			int firstCharacter = Character.getNumericValue(Add.charAt(0));

			// Print the first character
			System.out.println("Destroy Quantity = " + firstCharacter);

			Integer result = intValue1 - firstCharacter;

			// Print the result
			System.out.println("New qty After Destruction = " + result);
		} else {
			System.out.println("The string is empty");
		}

		extent.createTest("Print Result qty after the destro process completion  ").assignCategory("DestructionPatient")
				.assignDevice("Chrome").log(Status.INFO, "Print Result qty after the destro process completion ");

//Continue the destruction process		

		WebElement Destroy = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Destroy']")));
		Destroy.click();

		Thread.sleep(1000);

		WebElement pwd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

		pwd.sendKeys("1111");

		Thread.sleep(1000);

		WebElement signinbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
		signinbtn.click();

		Thread.sleep(1000);

		WebElement completebtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Complete']")));
		completebtn.click();

		WebElement Searchbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
		Searchbtn.click();
		Thread.sleep(2000);

		Thread.sleep(2000);

// Compare the values

		WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tr[2]/td[4]")));

		extent.createTest("Compare the values of new qty after destror process and the new qty on the stocktake ")
				.assignCategory("DestructionPatient").assignDevice("Chrome").log(Status.INFO,
						"Compare the values of new qty after destror process and the new qty on the stocktake");

		int firstCharacter = Character.getNumericValue(Add.charAt(0));

		// Print the first character

		Integer result = intValue1 - firstCharacter;

		String stringByConcatenation = result + "";

		String textToRemove1 = "tablet(s)";

		// Remove text using replace()
		String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

		System.out.println("Total Quantity after transaction , shown in the stocktake screen  =  " + result);

		extent.createTest(
				"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen")
				.assignCategory("DestructionPatient").assignDevice("Chrome").log(Status.INFO,
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
				.assignCategory("DestructionPatient").assignDevice("Chrome").log(Status.INFO,
						"Print Total Balance of the Selected medication after transaction , shown in the stocktake screen");

		Thread.sleep(2000);

//Go to the destruction tab and check the entry of that particular medication

		WebElement Destructionbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Destructions']")));
		Destructionbtn.click();

		extent.createTest("Go to the destruction tan to check the entry of new destruction")
				.assignCategory("DestructionPatient").assignDevice("Chrome")
				.log(Status.INFO, "Go to the destruction tan to check the entry of new destruction");

		Thread.sleep(2000);

		WebElement Medicationfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
		Medicationfilter.click();
		Medicationfilter.sendKeys("naproxen sodium 220 mg tablet");

		Thread.sleep(2000);

		WebElement Patientfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
		Patientfilter.click();
		Patientfilter.sendKeys("meera");

		Thread.sleep(2000);

		WebElement DestructionRegisterBTN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='select-filter-item']")));
		DestructionRegisterBTN.click();

		Thread.sleep(2000);

		System.out.println("Verify the destruction entery in the Destructions tab");

		Thread.sleep(2000);

		extent.flush();

	}

}
