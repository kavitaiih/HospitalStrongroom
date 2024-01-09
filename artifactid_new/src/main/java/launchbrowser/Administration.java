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

public class Administration {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Administration.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("Administration")
				.assignDevice("Chrome").log(Status.INFO, "Go to https://hospital-staging.strongroom.ai/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Orange Hospital");

		WebElement clickElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='drug-search-result' and text()='Orange Hospital']")));
		
//Log in	
		
		
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

		extent.createTest("Go to /drug-register").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);
		
		
//Print the Available Balance
		
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
				medidcationfilter.sendKeys("naproxen sodium 220 mg tablet");

				Thread.sleep(2000);
				
				WebElement patient1 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Patient...']")));
				patient1.click();
				patient1.sendKeys("meera");
				
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
				
				
// Print the Present available balance of that Medication
				

				WebElement AvailableBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				extent.createTest("Check the Available Balance for this medication ")
						.assignCategory("Administration").assignDevice("Chrome")
						.log(Status.INFO, "Check the Available Balance for this medication");

				// Get the text content of the element and print it

				String stock = AvailableBalance.getText();

				System.out.println("Available Balance = " + stock);

				Thread.sleep(2000);
				
				extent.createTest("Print the Available Balance")
				.assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Print the Available Balance ");
				
				Thread.sleep(2000);
				
				
//Administration process
				
				WebElement Administration = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Administration']")));
				Administration.click();
				
				extent.createTest("Click on the Administration button on the left menu of the dashboard ").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Administration button on the left menu of the dashboard");

				Thread.sleep(2000);
				
				
				WebElement SearchPatient = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='pom-imprest-choice-button']")));
				SearchPatient.click();
				
				extent.createTest("Click on the Search fpr patient button").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Click on the Search fpr patient button");

				Thread.sleep(2000);
				
				
				WebElement typePatientname = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
				typePatientname.click();
				typePatientname.sendKeys("meera" + Keys.ENTER);
				
				extent.createTest("Entere patient name").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Entere patient name");

				Thread.sleep(2000);
				
				WebElement SelectPatient = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='patient-result-info']//p[1]")));
				SelectPatient.click();
				
				extent.createTest("Select Patient").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Select Patient");
				
				Thread.sleep(2000);
				
				
				WebElement Prescriber = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
				Prescriber.click();
				Prescriber.sendKeys("jim");
				
				
				extent.createTest("Enter Prescriber name and select").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Enter Prescriber name and select");
				
				Thread.sleep(2000);
				
				WebElement selectPrescriber = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/p[3]")));
				selectPrescriber.click();
					
				Thread.sleep(2000);
				
				WebElement Medication = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Patient Medication']")));
				Medication.click();
				
				Thread.sleep(1000);
				
				WebElement Medicationfield = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder']")));
				Medicationfield.click();
				Thread.sleep(2000);
				
				
				WebElement selectMedication = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='naproxen sodium 220 mg tablet']//div[@class='ingredient-item']")));
				selectMedication.click();
				
				extent.createTest("Select medication form dropdown").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Select medication form dropdown");
				
				Thread.sleep(1000);
				
				
				WebElement qtybox = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Quantity']")));
				qtybox.click();
				qtybox.sendKeys("1");
				
				extent.createTest("Enter quantity for the selected medication").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Enter quantity for the selected medication");
				
				Thread.sleep(2000);
				
				WebElement Addbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
				Addbtn.click();
				
				
				
//Print Entered qty
				
				
				WebElement Enteredqty = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//p[normalize-space()='1 tablet/s']")));

				extent.createTest("Print the entred qty for the Administration  ")
						.assignCategory("Administration").assignDevice("Chrome")
						.log(Status.INFO, "Print the entred qty for the Manufacturing ");

				// Get the text content of the element and print it

				String QTY = Enteredqty.getText();

				System.out.println("Entered OUT qty for Manufacturing ="+ QTY);

				Thread.sleep(2000);
				
				
//Continue the process of Administration
				
				
				WebElement CompleteBTN = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Complete']")));
				CompleteBTN.click();
				
				
				extent.createTest("Click on the complete button").assignCategory("Administration").assignDevice("Chrome")
				.log(Status.INFO, "Click on the complete button");
				
				Thread.sleep(2000);
				
				WebElement Enterpassword = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Password']")));

				Enterpassword.sendKeys("1111");

				Thread.sleep(2000);

				WebElement signinbtn1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				signinbtn1.click();

				extent.createTest("1. Enter correct signature and click the Sign button").assignCategory("Administration")
						.assignDevice("Chrome").log(Status.INFO, "1. Enter correct signature and click the Sign button");

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

				extent.createTest("2. Enter correct signature and click the Sign button").assignCategory("Administration")
						.assignDevice("Chrome").log(Status.INFO, "2. Enter correct signature and click the Sign button");

				Thread.sleep(2000);
				
				
//check the updated qty of medication on stocktake
				
				
				WebElement Searchbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
				Searchbtn.click();
				
//Print the update qty from stocktake
				
				Thread.sleep(2000);
				
				WebElement NewBalance = wait.until(ExpectedConditions.presenceOfElementLocated
						(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				
				//WebElement NewBalance = wait.until(ExpectedConditions.presenceOfElementLocated(
						//By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				extent.createTest("Print the New balance after the dispensing process")
						.assignCategory("Administration").assignDevice("Chrome")
						.log(Status.INFO, "Print the New balance after the dispensing process");

				// Get the text content of the element and print it

				String newstock = NewBalance.getText();

				System.out.println("Updated Balance = " + newstock);

				
				extent.flush();
				
				

				
	}

}
