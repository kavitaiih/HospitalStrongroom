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

public class AdjustmentImprest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("AdjustmentIMprest.html");
		extent.attachReporter(spark);


		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		
//Process of Log in

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("AdjustmentIMprest")
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

		WebElement dropdown1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'Pharmacy')]")));
		dropdown1.click();

		Thread.sleep(2000);

		WebElement selectlocationbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
		selectlocationbtn.click();

		extent.createTest("Go to /drug-register").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);



		
//Process of Adjustment

				WebElement Adjusmentbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Adjustment']")));
				Adjusmentbtn.click();

				extent.createTest("Click on the Adjustment tab form the left side menu ").assignCategory("AdjustmentIMprest")
						.assignDevice("Chrome").log(Status.INFO, "Click on the Adjustment tab form the left side menu");

				Thread.sleep(2000);

				WebElement PasteTransID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Transaction ID...']")));
				PasteTransID.click();
				PasteTransID.sendKeys("123");
				

				//WebElement targetElement = driver.findElement(By.xpath("//input[@placeholder='Transaction ID...']"));
				//Actions actions = new Actions(driver);
				//actions.moveToElement(targetElement).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

				extent.createTest("Paste the copied Transaction ID ").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
						.log(Status.INFO, "Paste the copied Transaction ID");

				Thread.sleep(2000);

				WebElement addnotes = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='note-modal']")));

				addnotes.sendKeys("Notes Will be here");

				Thread.sleep(2000);
				
				WebElement ClickonImpresbtn = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")));
				ClickonImpresbtn.click();

				extent.createTest("Click on the stew-dazzling-washtub! ").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
						.log(Status.INFO, " Click on the stew-dazzling-washtub!");
				
				
				WebElement medicationdrp = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//input[@placeholder='Type medication name here']")));
				medicationdrp.click();
				medicationdrp.sendKeys("Repreve 2 mg tablet");

				extent.createTest("Enter a medication ").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
						.log(Status.INFO, " Enter a medication");

				Thread.sleep(2000);

				WebElement selectmedication = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//p[normalize-space()='Repreve 2 mg tablet']")));
				selectmedication.click();
				Thread.sleep(2000);

				WebElement addbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='submit-button blue-button']")));
				addbtn.click();
				Thread.sleep(2000);
				
//				WebElement EcpectedQTY = driver.findElement(By.xpath(
//						"/html/body/div/div/div[3]/div[1]/div/div[2]/div/div/div[2]/form/div/div[2]/div[3]/table/tr[2]/td[2]"));
//
//				extent.createTest("Verify the entered Quantity for the selected medication ")
//						.assignCategory("AdjustmentIMprest").assignDevice("Chrome")
//						.log(Status.INFO, "Verify the entered Quantity for the selected medication ");
//
//				// Get the value of the Adjustmentqty input field
//				String ExpectedQtyValue = EcpectedQTY.getAttribute("value");
//
//				// Print the value
//				System.out.println("Expected qty =  " +ExpectedQtyValue);
//
//				extent.createTest(" Print Expected Quantity of the selected medication  ").assignCategory("AdjustmentIMprest")
//						.assignDevice("Chrome").log(Status.INFO, "Print Expected Quantity of the selected medication ");
//
//				Thread.sleep(1000);

				WebElement EntermedicationAmount = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
				EntermedicationAmount.click();
				EntermedicationAmount.sendKeys("100");

				extent.createTest("Enter a medication Amount ").assignCategory("AdjustmentIMprest").assignDevice("Chrome")
						.log(Status.INFO, " Enter a medication Amount");

				Thread.sleep(3000);
				
				
				
//Print the adjustment amount
				
				
				WebElement adjustmentQty = driver.findElement(By.xpath(
						"/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tr[2]/td[3]/input[1]"));

				extent.createTest("Verify the entered Quantity for the selected medication ")
						.assignCategory("AdjustmentIMprest").assignDevice("Chrome")
						.log(Status.INFO, "Verify the entered Quantity for the selected medication ");

				// Get the value of the Adjustmentqty input field
				String adjustmentQtyValue = adjustmentQty.getAttribute("value");

				// Print the value
				System.out.println("Adjustment qty =  " +adjustmentQtyValue);

				extent.createTest(" Print New Added Quantity of the selected medication  ").assignCategory("AdjustmentIMprest")
						.assignDevice("Chrome").log(Status.INFO, "Print New Added Quantity of the selected medication ");

				Thread.sleep(1000);


				WebElement Submitbtn = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='regular-button complete-button']")));
				Submitbtn.click();
				Thread.sleep(2000);

				WebElement pass1 = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-section-container']//div//div[1]//input[2]")));
				pass1.click();
				pass1.sendKeys("1111");

				extent.createTest("1. Enter correct signature and click the Sign button").assignCategory("AdjustmentIMprest")
						.assignDevice("Chrome").log(Status.INFO, "1. Enter correct signature and click the Sign button");

				Thread.sleep(2000);

				WebElement sign1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='form-section-container']//div//div[1]//div[1]")));
				sign1.click();
				Thread.sleep(2000);

				WebElement EnterUSERNAME1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//body//div[@id='app']//div[@class='modal-mask']//div[@class='modal-mask']//div[@class='modal-body']//div[2]//input[1]")));

				EnterUSERNAME1.sendKeys("nathan");

				WebElement Enterpass1 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-body']//div[2]//input[2]")));

				Enterpass1.sendKeys("1111");

				Thread.sleep(2000);

				WebElement sign2 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='green-button']")));
				sign2.click();

				extent.createTest("2. Enter correct signature and click the Sign button").assignCategory("AdjustmentIMprest")
						.assignDevice("Chrome").log(Status.INFO, "2. Enter correct signature and click the Sign button");

				Thread.sleep(3000);

				WebElement clickonArrow = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]")));
				clickonArrow.click();
				
				Thread.sleep(2000);
				
				
				
	//Verify the same medication Adjustment in the stocktake			
				
				WebElement clickonStock = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stock']")));
				clickonStock.click();

				extent.createTest("Go to Stock -> Stock Take").assignCategory("AdjustmentIMprest")
						.assignDevice("Chrome").log(Status.INFO, "Go to Stock -> Stock Take");
				
				Thread.sleep(2000);
				
				WebElement clickonStockTake = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Stocktake']")));
				clickonStockTake.click();
				Thread.sleep(2000);
				
				WebElement medicationFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Medication...']")));
				medicationFilter.click();
				medicationFilter.sendKeys("Repreve 2 mg table");
				
				extent.createTest("Search for the Adjusted medication").assignCategory("AdjustmentIMprest")
				.assignDevice("Chrome").log(Status.INFO, "Search for the Adjusted medication");
				
				Thread.sleep(2000);
				
				WebElement ClickonIncludeS8BTN = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='active-select-filter select-filter-item']")));
				ClickonIncludeS8BTN.click();
				
				Thread.sleep(2000);
				
				WebElement ClickonDisplstockbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display In Stock Only']")));
				ClickonDisplstockbtn.click();
				
				Thread.sleep(2000);
				
				WebElement Imprestonlybtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Display Imprest Only']")));
				Imprestonlybtn.click();
				
				Thread.sleep(2000);
				
				WebElement ClickonSearchbtn = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button submit-button']")));
				ClickonSearchbtn.click();
				
				extent.createTest("Verify the medication adjustment for the searched medication").assignCategory("AdjustmentIMprest")
				.assignDevice("Chrome").log(Status.INFO, "Verify the medication adjustment for the searched medication");
				
				Thread.sleep(2000);
				
				
				
//Print the StockTake Balance
				
				WebElement StocktakeBalance = driver
						.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]"));

				extent.createTest("Check the Available Balance for this medication ").assignCategory("AdjustmentIMprest ")
						.assignDevice("Chrome").log(Status.INFO, "Check the Available Balance for this medication");

				// Get the text content of the element and print it

				String stock = StocktakeBalance.getText();
				
				//System.out.println("Stocktake Balance  = " +  stock);
				
				Thread.sleep(2000);
				
				
		//print the comparission between adjustment qty and stocktake balance
				
				WebElement StocktakeBalance1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				        By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")));
				
				
				String stock1 = StocktakeBalance1.getText();

				
				String textToRemove = "tablet(s)";
				String modifiedString = stock1.replace(textToRemove, "");

				

				extent.createTest("Print Total Balance of the Selected medication after transaction, shown in the stocktake screen")
				        .assignCategory("AdjustmentIMprest").assignDevice("Chrome").log(Status.INFO,
				        "Print Total Balance of the Selected medication after transaction, shown in the stocktake screen");

				System.out.println("Checked qty on StockTake = " + modifiedString.trim());
				
				
				String st = modifiedString.trim();
				String st2 = adjustmentQtyValue.trim();
				
				if (st.equals(st2)) {
				    System.out.println("Adjustment qty and stocktake qty is matched");
				} else {
				    System.out.println("Adjustment qty and stocktake qty is not matched");
				}
				
				
				
				
				
				extent.flush();

		
	}

}
