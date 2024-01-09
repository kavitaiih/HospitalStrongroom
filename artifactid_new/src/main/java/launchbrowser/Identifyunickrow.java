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
import java.util.List;
import org.openqa.selenium.interactions.Actions;

public class Identifyunickrow { 


	public static void main(String[] args) throws InterruptedException{
		
		
		// TODO Auto-generated method stub
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("AdjustmentPATIENT.html");
		extent.attachReporter(spark);

		// System.setProperty("webdriver.chrome.driver",
		// "/home/user/Desktop/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://hospital-staging.strongroom.ai/login");

		extent.createTest("Go to https://hospital-staging.strongroom.ai/login").assignCategory("AdjustmentPATIENT")
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

		extent.createTest("Go to /drug-register").assignCategory("AdjustmentPATIENT").assignDevice("Chrome")
				.log(Status.INFO, "Go to /drug-register");

		Thread.sleep(2000);
		
		
		
		
        
        WebElement contentElement = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]"));
        contentElement.clear();
        
        WebElement clickonarrow = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[3]/table[1]/tbody[1]/tr[1]/td[3]"));
        clickonarrow.clear();

        // Get the text content of the element
        String contentText = contentElement.getText();

        
     // Print or use the selected content
        System.out.println("glimepiride 2 mg tablet: " + contentText);
        
       
        /*
        //Use Actions class to simulate Ctrl+A (select all) and Ctrl+C (copy)
        Actions actions = new Actions(driver);
        actions.moveToElement(Arrowbtn)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.chord(Keys.CONTROL, "c"))
                .build()
                .perform();

		// Select existing content in the input field (if any)
        //Arrowbtn.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        // Paste new content into the input field
        Arrowbtn.sendKeys("Your new content");
		
		
		
		
	*/
		//Transfer IN process

		/*
				
				  WebElement transferin = wait
				  .until(ExpectedConditions.elementToBeClickable(By.
				  xpath("//button[normalize-space()='Transfer In']"))); transferin.click();
				 
				  Thread.sleep(2000);
				 
				 extent.createTest("Click the Transfer In button in the left menu").
				  assignCategory("AdjustmentPATIENT") .assignDevice("Chrome").log(Status.INFO,
				  "Click the Transfer In button in the left menu");
				  
				  WebElement drpdwn = wait.until(ExpectedConditions .elementToBeClickable(By.
				  xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
				  WebElement drpdwn1 = wait.until(ExpectedConditions .elementToBeClickable(By.
				  xpath("//input[@placeholder='Type in location to receive from']")));
				 
				  drpdwn1.sendKeys("Emergency Ward"); drpdwn.click();
				  extent.createTest("Enter a location").assignCategory("AdjustmentPATIENT").
				  assignDevice("Chrome") .log(Status.INFO, "Enter a location");
				  
				  Thread.sleep(2000);
				  
				  WebElement locationselect = wait
				  .until(ExpectedConditions.elementToBeClickable(By.
				  xpath("//li[@aria-label='Emergency Ward']"))); locationselect.click();
				  
				  extent.createTest("Select a location").assignCategory("AdjustmentPATIENT").
				  assignDevice("Chrome") .log(Status.INFO, "Select a location");
				  
				  WebElement addnotes = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//textarea[@id='note-modal']")));
				 
				
				 
				 addnotes.sendKeys("Notes Will be here");
				 
				  Thread.sleep(2000);
				  
				  extent.createTest("Add text to notes").assignCategory("AdjustmentPATIENT").
				  assignDevice("Chrome") .log(Status.INFO, "Add text to notes");
				  
				  WebElement Patientmedicationbtn = wait.until(
				  ExpectedConditions.elementToBeClickable(By.
				  xpath("//p[normalize-space()='Patient Medication']")));
				  Patientmedicationbtn.click();
				  extent.createTest("Click the Patient Medication button ").assignCategory(
				  "AdjustmentPATIENT") .assignDevice("Chrome").log(Status.INFO,
				  " Click the Patient Medication button"); Thread.sleep(2000);
				  
				  WebElement patientname = wait.until(ExpectedConditions
				  .elementToBeClickable(By.
				  xpath("//input[@placeholder='Enter Patient name or Medicare Number']")));
				  patientname.click(); patientname.sendKeys("k" + Keys.ENTER); extent.
				  createTest("Enter a patient name in the search field and click search ")
				  .assignCategory("AdjustmentPATIENT").assignDevice("Chrome") .log(Status.INFO,
				  " Enter a patient name in the search field and click search");
				  Thread.sleep(2000);
				  
				  WebElement clickresult = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//div[@class='patient-result-info']//p[1]"))); clickresult.click();
				  Thread.sleep(2000);
				  
				  WebElement prescribername = wait.until(ExpectedConditions
				  .elementToBeClickable(By.
				  xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
				  prescribername.click();
				  extent.createTest("Enter Prescriber name ").assignCategory(
				  "AdjustmentPATIENT").assignDevice("Chrome") .log(Status.INFO,
				  "Enter Prescriber name"); Thread.sleep(2000);
				  
				  WebElement addspace = wait.until(ExpectedConditions .elementToBeClickable(By.
				  xpath("//input[@placeholder='Enter Prescriber No. or Name']")));
				  addspace.sendKeys(" "); Thread.sleep(1000);
				  
				  WebElement resultclicked = wait.until(
				  ExpectedConditions.elementToBeClickable(By.
				  xpath("//p[normalize-space()='Reference only - Jim jam']")));
				  resultclicked.click(); Thread.sleep(1000);
				  
				  // Click on the dropdown to open it WebElement dropdownTrigger =
				  driver.findElement(By.xpath(
				  "//div[@class='right-form-section-drug-entry']//div//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']"
				  )); 
				  drpTrigger.click();
				  
				  WebElement drpdwn2 = wait.until(ExpectedConditions .elementToBeClickable(By.
				  xpath("//input[contains(@placeholder,'Select Medication')]"))); WebElement
				  drpdwn3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//div[contains(@class,'right-form-section-drug-entry')]//div//span[contains(@class,'p-dropdown-trigger-icon pi pi-chevron-down')]"
				  )));
				  
				  drpdwn2.sendKeys("(guaifenesin) guaifenesin 100 mg/5 mL oral liquid");
				  
				  drpdwn3.click();
				  
				  Thread.sleep(2000);
				  
				  WebElement selectmedication =
				  wait.until(ExpectedConditions.elementToBeClickable( By.
				  xpath("//li[contains(@aria-label,'(guaifenesin) guaifenesin 100 mg/5 mL oral liquid')]"
				  ))); selectmedication.click();
				  extent.createTest("Enter a medication").assignCategory("AdjustmentPATIENT").
				  assignDevice("Chrome") .log(Status.INFO, "Enter a medication");
				  
				  WebElement qty = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//input[@placeholder='Qty...']")));
				  
				  qty.sendKeys("1000");
				  extent.createTest("Select a medication and qty").assignCategory(
				  "AdjustmentPATIENT").assignDevice("Chrome") .log(Status.INFO,
				  "Select a medication and qty"); Thread.sleep(2000);
				  
				  WebElement addbtn =
				  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//p[@class='blue-button']"))); addbtn.click();
				  extent.createTest("Click the Add button").assignCategory("AdjustmentPATIENT")
				  .assignDevice("Chrome") .log(Status.INFO, "Click the Add button");
				  
				  Thread.sleep(2000);
				  
				  WebElement receivetranferbtn = wait
				  .until(ExpectedConditions.elementToBeClickable(By.
				  xpath("//p[normalize-space()='Receive Transfer']")));
				  receivetranferbtn.click();
				  
				  extent.createTest("Click the Recieve Transfer button").assignCategory(
				  "AdjustmentPATIENT") .assignDevice("Chrome").log(Status.INFO,
				  "Click the Recieve Transfer button");
				  
				  Thread.sleep(2000);
				  
				 WebElement pwd = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//input[@placeholder='Password']")));
				  
				  pwd.sendKeys("1111");
				  
				  Thread.sleep(2000);
				  
				  WebElement signinbtn = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//div[@class='green-button']"))); signinbtn.click();
				  
				  extent.createTest("Enter correct signature and click the Sign button").
				  assignCategory("AdjustmentPATIENT") .assignDevice("Chrome").log(Status.INFO,
				  "Enter correct signature and click the Sign button");
				  
				  Thread.sleep(2000);
				  
				  WebElement completebtn = wait
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//h3[normalize-space()='Complete']"))); completebtn.click();
				  
				  Thread.sleep(2000);
				  
				  extent.createTest("Click on complete button ").assignCategory(
				  "AdjustmentPATIENT").assignDevice("Chrome") .log(Status.INFO,
				  "Click on complete button ");
				  
				  Thread.sleep(2000);
				  
				  
				  String targetTimestamp = "Dec 04, 2023, 16:05";
			        String targetMedication = "(guaifenesin) guaifenesin 100 mg/5 mL oral liquid";

			        // Find all rows (assuming they share the same XPath)
			        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='your-row-class']"));

			        // Iterate through the rows and check for the target information
			        for (WebElement row : rows) {
			            String timestamp = row.findElement(By.xpath("td[1]")).getText(); // Adjust the XPath based on the column index
			            String medication = row.findElement(By.xpath("td[2]")).getText(); // Adjust the XPath based on the column index

			            if (timestamp.equals(targetTimestamp) && medication.equals(targetMedication)) {
			                // Click on the row or perform actions as needed
			                row.click();
			                break; // Exit the loop if the row is found
			            
			            }
			        }
			        
			        */
			                extent.flush();
			       
			        
				 
				  
				  
				  

	}

}
