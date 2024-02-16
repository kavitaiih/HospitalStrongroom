package Testcase;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import objects.NotificationPage;
import test.Util.TestUtil;
import java.util.List;


public class RequisitionImprest extends Base{
	private static NotificationPage notificationPage;

	
//	private static int rownum = 2;
//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		ArrayList<Object[]> testData =TestUtilOld.RequisitionImprest();
//		return testData.iterator();	
//	}
//
//	@Test(dataProvider="getTestData")
	public static void RequisitionwithImprest(String action, String location,String note,  String Medication_name, String Quantity, String username, String pin, String username1, String pin1) 
			throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		TaskName = action ;		
		
		//Process of creation of new Requisition
//		driver.findElement(By.xpath("//p[normalize-space()='Stock']")).click(); //stock
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//p[normalize-space()='Requisitions']")).click(); //Requisition
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//button[@class='actions-menu-item']")).click(); //new requisition
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//input[@placeholder='Distribute to...']")).sendKeys(location);
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]")).click();
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//textarea[@id='note-modal']")).sendKeys(note); // Take not from excel
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//p[normalize-space()='Imprest/Emergency Meds/Ward Stock']")).click(); //click on imprest button
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//input[@placeholder='Type medication name here']")).sendKeys(Medication_name); //
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]")).click();
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//input[@placeholder='Enter qty']")).sendKeys(Quantity); //enter qty
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//p[@class='blue-button']")).click();
//		Thread.sleep(2000);
//		
//		
//		//Print the requested qty 
//		
//		WebElement Requestedqty = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='1']")));
//        String RequestedQTY = Requestedqty.getText();
//		System.out.println("Requested qty = " +RequestedQTY);
//	    Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//p[@class='regular-button complete-button']")).click();
//		Thread.sleep(2000);
//		
//		WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
//		usernameInput.click();
//		usernameInput.clear();
//		usernameInput.sendKeys(username);
//		Thread.sleep(2000);
//		
//		WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
//		passwordInput.sendKeys(pin);
//		Thread.sleep(2000);
//
//		WebElement signInBtn = driver.findElement(By.xpath("//div[@class='green-button']"));
//		signInBtn.click();
//		Thread.sleep(2000);
//
//		WebElement completeBtn = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
//		completeBtn.click();
//		Thread.sleep(3000);
//
//		WebElement Arrowclick = driver.findElement(By.xpath("//tbody[1]/tr[1]/td[1]/i[1]"));
//		Arrowclick.click();
//		Thread.sleep(3000);
//		
//		WebElement Supplybtn = driver.findElement(By.xpath("//button[normalize-space()='Supply']"));
//		Supplybtn.click();
//		Thread.sleep(3000);
//		
//		WebElement usernameInput1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Username']")));
//		usernameInput1.click();
//		usernameInput1.clear();
//		usernameInput1.sendKeys(username1);
//		Thread.sleep(2000);
//		
//		WebElement passwordInput1 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
//		passwordInput1.sendKeys(pin1);
//		Thread.sleep(2000);
//
//		WebElement signInBtn1 = driver.findElement(By.xpath("//div[@class='green-button']"));
//		signInBtn1.click();
//		Thread.sleep(2000);
//
//		WebElement completeBtn1 = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
//		completeBtn1.click();
//		Thread.sleep(3000);
//		
//		WebElement ReadypickBTN = driver.findElement(By.xpath("//button[normalize-space()='Ready for Pickup']"));
//		ReadypickBTN.click();
//		Thread.sleep(3000);
//		
//		WebElement CollectBTN = driver.findElement(By.xpath("//button[normalize-space()='Collect']"));
//		CollectBTN.click();
//		Thread.sleep(3000);
//		
//		WebElement SubmitBTN = driver.findElement(By.xpath("//p[@class='regular-button complete-button']"));
//		SubmitBTN.click();
//		Thread.sleep(3000);
//		
//		WebElement passwordInput2 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
//		passwordInput2.sendKeys(pin);
//		Thread.sleep(2000);
//
//		WebElement signInBtn2 = driver.findElement(By.xpath("//div[@class='green-button']"));
//		signInBtn2.click();
//		Thread.sleep(2000);
//
//		WebElement completeBtn2 = driver.findElement(By.xpath("//h3[normalize-space()='Complete']"));
//		completeBtn2.click();
//		Thread.sleep(2000);
		
		// Change the location for completion the requistion process 
		
		WebElement Reportsbtn = driver.findElement(By.xpath("//p[normalize-space()='Reports']"));
		Reportsbtn.click();
		Thread.sleep(2000);
		
		WebElement locationButton = driver.findElement(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
		locationButton.click();
		Thread.sleep(2000);
		
		
		// Replace the following XPath with the actual XPath of the container that holds the list of locations
		WebElement locationListContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']/div/div[2]/div[3]/div[3]")));
        
     // Replace the following XPath with the actual XPath of the element representing the list of locations
        List<WebElement> locationElements = locationListContainer.findElements(By.xpath("/html/body/div/div/div[2]/div[3]/div[3]/div"));
        
        StringBuilder locationText = new StringBuilder();
        for (WebElement locationElement : locationElements) {
            locationText.append(locationElement.getText()).append("\n");
        }
        String listoflocation = locationText.toString();
        System.out.println(listoflocation);
        
     // Desired location to click
       // String location1 = "Orange Hospital"; // Specify the correct location here
        String desiredLocation = "Orange Hospital- "+ location;

        boolean isLocationFound = false;

     // Iterate through the list of locations
        for (WebElement locationElement : locationElements) {
           String currentLocation = locationElement.getText().trim();
            System.out.println("Current Location: " + currentLocation);

            // Compare the current location with the desired location
            if (currentLocation.equals(desiredLocation)) {
                System.out.println("Desired location found in the list: " + desiredLocation);

                // Click on the desired location
                locationElement.click();

                isLocationFound = true;
                break; // Break out of the loop once the location is found
            }
        }

        if (!isLocationFound) {
            System.out.println("Desired location not found in the list: " + desiredLocation);
        }

		
		//String locationlist= Clickonlocationbtn.getText();
		//System.out.println(locationlist);
		
//        List<WebElement> locationLIST = driver.findElements(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
//        //locationLIST.click();
//        String locationList = ((WebElement) locationLIST).getText();
//     // Desired name to compare
//        String ToLocation = "Orange Hospital- "+ location;
//        System.out.println(ToLocation);
//
//
//        boolean isNameFound = false;
//		//WebElement Copylocation = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/p[1]"));
//		//Copylocation.click();
//		
//		// Iterate through the list of names
//        for (WebElement nameElement : locationLIST) {
//            String currentName = nameElement.getText();
//            
//            System.out.println(currentName);
//
//            // Compare the current name with the desired name
//            if (currentName.equals(ToLocation)) {
//                System.out.println("Desired name found in the list: " + ToLocation);
//                
//                nameElement.click();
//                isNameFound = true;
//                break; // Break out of the loop once the name is found
//            }
//        }
//
//        if (!isNameFound) {
//            System.out.println("Desired name not found in the list: " + ToLocation);
//        }

		 // Get the text from the element
//        String locationName = Copylocation.getText();
//		System.out.println("Copied location =" +locationName);
//		Thread.sleep(3000);
//		
//		WebElement locationBtn = driver.findElement(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
//		locationBtn.click();	
//		Thread.sleep(3000);
//		
//		//WebElement locationBtn = driver.findElement(By.xpath("//p[@class='new-patient-button data-v-tooltip']"));
//		locationBtn.sendKeys(locationName);
//		Thread.sleep(3000);
	}
}
