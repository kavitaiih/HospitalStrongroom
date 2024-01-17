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

public class TransferInimprest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("TransferINimprest.html");
		extent.attachReporter(spark);

//LOg In PROCESS
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

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
				medicationfilter.sendKeys("(guaifenesin) guaifenesin 100 mg/5 mL oral liquid");

				Thread.sleep(2000);

				WebElement clickonDisplyStock = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display In Stock Only']")));
				clickonDisplyStock.click();

				Thread.sleep(1000);

				WebElement clickonIncluseS8 = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Include S8']")));
				clickonIncluseS8.click();

				Thread.sleep(1000);
				
				WebElement Imprestonlybtn = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
				Imprestonlybtn.click();

				Thread.sleep(1000);

				WebElement SearchBTN = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button submit-button']")));
				SearchBTN.click();

				Thread.sleep(2000);
				
				
//Print the Available Balance 
				
				WebElement AvailableBalance = driver
						.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

				extent.createTest("Check the Available Balance for this medication ").assignCategory("TransferINimprest ")
						.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

				// Get the text content of the element and print it

				String stock = AvailableBalance.getText();
				System.out.println("Current Stock = " +  stock);
				
				Thread.sleep(2000);
				
				
//Transfer In Process with imprest selection

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

				drpdwn1.sendKeys("Emergency Ward");
				drpdwn.click();
				extent.createTest("Enter a location").assignCategory("TransferINimprest").assignDevice("Chrome")
						.log(Status.INFO, "Enter a location");

				Thread.sleep(2000);

				WebElement locationselect = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@aria-label='Emergency Ward']")));
				locationselect.click();

				extent.createTest("Select a location").assignCategory("TransferINimprest").assignDevice("Chrome")
						.log(Status.INFO, "Select a location");

				WebElement addnotes = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

				addnotes.sendKeys("Notes Will be here");

				Thread.sleep(2000);

				extent.createTest("Add text to notes").assignCategory("TransferINimprest").assignDevice("Chrome")
						.log(Status.INFO, "Add text to notes");

				WebElement Imprestbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
				Imprestbtn.click();
				
				Thread.sleep(2000);
				
				WebElement medicationbtn = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//input[@placeholder='Select Medication']")));
				medicationbtn.sendKeys("(guaifenesin) guaifenesin 100 mg/5 mL oral liquid");
				
				WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//li[@aria-label='(guaifenesin) guaifenesin 100 mg/5 mL oral liquid']")));
				selectmedication.click();

				Thread.sleep(2000);


				WebElement qty = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Qty...']")));

				qty.sendKeys("1");
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

				extent.createTest("Verify the entered Quantity for the selected medication ").assignCategory("TransferINimprest")
						.assignDevice("Chrome").log(Status.INFO, "Verify the entered Quantity for the selected medication ");

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
				
				
//Continue the process		

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

				Thread.sleep(2000);
				
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
				medicationfilter1.sendKeys("guaifenesin 100 mg/5 mL oral liquid");

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
				
				WebElement Imprestonlybtn1 = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Display Imprest Only']")));
				Imprestonlybtn1.click();

				Thread.sleep(1000);

				WebElement CLICKsearch = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
				CLICKsearch.click();

				Thread.sleep(2000);

		//Print 		
				WebElement AddedBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));

				String stringByConcatenation = sum + "";

				String textToRemove1 = "tablet(s)";

				// Remove text using replace()
				String modifiedString1 = stringByConcatenation.replace(textToRemove1, "");

				System.out.println(
						"Total Quantity after transaction , shown in the stocktake screen  =  "
								+ sum);

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

				extent.flush();

			}
		}



	
