package Testcase;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class CustomListener extends Base{
	public void onTestStart(ITestResult result) {
		 // not implemented
	}
	
	
	public void onTestSuccess(ITestResult result) {
		 // not implemented
	}
	
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Failed Test");
		failed(result.getMethod().getMethodName());
	}
	
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	 }
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	 }
	
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	 }
	
	public void onStart(ITestContext context) {
	    // not implemented
	 }
	
	public void onFinish(ITestContext context) {
	    // not implemented
	 }
}
