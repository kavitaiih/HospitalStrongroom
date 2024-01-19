package Testcase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import clickUP.createTask;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Base extends createTask implements ITestListener {
    static WebDriver driver;

    @BeforeSuite
    public void start() {
        // Need to Add logic for starting suite logic
    }
    
    
    @BeforeClass
    public void setUp() {
        // Set the path to your ChromeDriver executable
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
		driver.manage().window().maximize();
    } // this method launch the browser 

    
    
    @BeforeMethod
    public void URL() throws InterruptedException {
    	driver.get("https://hospital-staging.strongroom.ai/login");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		
    	WebElement locationInput = driver.findElement(By.xpath("//input[@placeholder='Location']"));
    	locationInput.clear();
		locationInput.sendKeys("Orange Hospital"); // Location from Excel
		
		WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//p[@class='drug-search-result' and text()='" + "Orange Hospital" + "']")));
		locationOption.click();
		
		driver.findElement(By.xpath("//input[@placeholder='Username/email']")).clear();
    	driver.findElement(By.xpath("//input[@placeholder='Username/email']")).sendKeys("qa@strongroom.ai");
    	
    	driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
    	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("stew-dazzling-washtub!");
    	
    	driver.findElement(By.xpath("//p[@class='blue-button']")).click();
    	
    	
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

		Thread.sleep(2000);

    } 

    public void failed(String testMethodName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("C:/Users/bhako/eclipse-workspace/Calculator/src/test/"
                    + "java/Screenshots/" + testMethodName + "_" + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // this method is take a Screenshots when any test case fail and add in the above folder


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
    	String status = "to do";
    	String formattedDateTime = getCurrentDateTime();
        if (result.getStatus() == ITestResult.FAILURE) {
            String taskDescription = "This added by automation script";
            String listId = "901600971152";  
            //List id for Fail Task list
            createClickUpTask(formattedDateTime, taskDescription, listId, status); 
            // this method create one task with current date and time
            
            String methodName = result.getMethod().getMethodName();
            String consoleError = extractConsoleError(result);
            createClickUpTask(methodName, consoleError, listId, status); 
            // This method create task which is failed test case
            
            String mainTaskId = getTaskId(formattedDateTime, listId);
            String subTaskId = getTaskId(methodName, listId);
            updateTask(subTaskId, mainTaskId);  
            // this method move the fail test case task into current date and time task and make it sub task
        } else {
            String taskDescription = "This added by automation script";
            String listId = "901600971153";
            //List id for Pass Task list
            createClickUpTask(formattedDateTime, taskDescription, listId, status);  
            // this method create one task with current date and time
            
            String methodName = result.getMethod().getMethodName();
            String consoleError = extractConsoleError(result);
            createClickUpTask(methodName, consoleError, listId, status);  
            // This method create task which is pass test case
            
            String mainTaskId = getTaskId(formattedDateTime, listId);
            String subTaskId = getTaskId(methodName, listId);
            updateTask(subTaskId, mainTaskId);  
            // this method move the pass test case task into current date and time task and make it sub task
        }
        
        System.out.println("-------------------------------");
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
        return now.format(formatter);
    }

    private String extractConsoleError(ITestResult result) {
        String consoleOutput = "";
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            consoleOutput = throwable.getMessage();
        }
        return consoleOutput;
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    @AfterSuite
    public void report() {
    	
    }
}
