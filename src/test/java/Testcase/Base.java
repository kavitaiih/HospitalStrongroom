package Testcase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    protected static String inputdata;
    public String loginTaskDescription;
    // Declare at the class level
    protected static String TaskName ;

    @BeforeSuite
    public void start() {
        // Need to Add logic for starting suite logic
    }
    
    
    @BeforeClass
    public void setUp() {
        // Set the path to your ChromeDriver executable
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
		driver.manage().window().maximize();
    } // this method launch the browser 

    
    
    @BeforeMethod
    public void URL() throws InterruptedException {
        driver.get("https://hospital-staging.strongroom.ai/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

        WebElement locationInput = driver.findElement(By.xpath("//input[@placeholder='Location']"));
        locationInput.clear();
        locationInput.sendKeys("Orange Hospital"); // Location from Excel
        Thread.sleep(2000);


        WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[@class='drug-search-result' and text()='" + "Orange Hospital" + "']")));
        locationOption.click();
        Thread.sleep(2000);


        WebElement usernameInput = driver.findElement(By.xpath("//input[@placeholder='Username/email']"));
        usernameInput.clear();
        usernameInput.sendKeys("qa@strongroom.ai");
        Thread.sleep(2000);


        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordInput.clear();
        passwordInput.sendKeys("stew-dazzling-washtub!");
        Thread.sleep(1000);


        String loginPageURL = driver.getCurrentUrl();
        String enteredLocation = locationInput.getAttribute("value");
        String  enteredUsername = usernameInput.getAttribute("value");
        String enteredPassword = passwordInput.getAttribute("value");
        String selectedLocation = "Pharmacy";
        Thread.sleep(1000);
        

        WebElement loginButton = driver.findElement(By.xpath("//p[@class='blue-button']"));
        loginButton.click();

        WebElement dropdown = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[@class='p-dropdown-trigger-icon pi pi-chevron-down']")));
        dropdown.click();

    
        WebElement dropdown1 = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@aria-label,'Pharmacy')]")));
        dropdown1.click();

        Thread.sleep(2000);

        WebElement selectLocationBtn = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='blue-button']")));
        selectLocationBtn.click();
        Thread.sleep(2000);

        // Capture login task description
         // You might need to retrieve the actual selected location

        loginTaskDescription = "Login Page URL: " + loginPageURL + "\n" +
                "Entered Location: " + enteredLocation + "\n" +
                "Entered Username: " + enteredUsername + "\n" +
                "Entered Password: " + enteredPassword + "\n" +
                "Selected Location: " + selectedLocation;      
        
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
    	
    	String formattedDateTime = getCurrentDateTime();
		if (result.getStatus() == ITestResult.FAILURE) {
            String taskDescription = loginTaskDescription;
            //String taskDescription = "This added by automation script";
            String listId = "901601274942"; 
        	String status = "Fail";

            //List id for Fail Task list
            createClickUpTask(formattedDateTime, taskDescription, listId, status); 
            // this method create one task with current date and time
            
            String methodName = TaskName;
            String consoleError = extractConsoleError(result);
            //String loginTaskDescription1 = null;
			String fails =  loginTaskDescription + "\n" + inputdata + "\n" +  "Status: " + status + "\n" + "Error message: "
					+ consoleError;
            createClickUpTask(TaskName, fails, listId, status); 
            // This method create task which is failed test case
                       
            String mainTaskId = getTaskId(formattedDateTime, listId);
            String subTaskId = getTaskId(TaskName, listId);
            updateTask(subTaskId, mainTaskId);  
            // this method move the fail test case task into current date and time task and make it sub task
        } else {
            String taskDescription = loginTaskDescription;
            String listId = "901601274938";
        	String status = "Pass";

            //List id for Pass Task list
            createClickUpTask(formattedDateTime, taskDescription, listId, status);  
            // this method create one task with current date and time
            
            String methodName = TaskName;
            String consoleError = extractConsoleError(result);
            
			String fails = loginTaskDescription + "\n" + inputdata + "\n" + consoleError + "Status: " + status;
            createClickUpTask(TaskName, fails, listId, status); 
            // This method create task which is pass test case
            
            String mainTaskId = getTaskId(formattedDateTime, listId);
            String subTaskId = getTaskId(TaskName, listId);
            updateTask(subTaskId, mainTaskId);  
            // this method move the pass test case task into current date and time task and make it sub task
        }
        
        System.out.println("-------------------------------");
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
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
    public static WebElement findElementWithRetry(WebDriver driver, By by) {
	    WebElement element = null;
	    int maxAttempts = 3;
	    int attempt = 0;
	    while (attempt < maxAttempts) {
	        try {
	            element = driver.findElement(by);
	            if (element != null && element.isDisplayed() && !element.getText().isEmpty()) {
	            	System.out.println("Attempts fail" + attempt);
	                break;
	            }
	        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
	        	 System.out.println("Exception occurred: " + e.getMessage());
	        	    // print stack trace
	        	    e.printStackTrace();
	        }
	        attempt++;
	    }
	    return element;
	}
    
}
