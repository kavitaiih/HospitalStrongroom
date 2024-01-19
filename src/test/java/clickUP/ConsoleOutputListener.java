package clickUP;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ConsoleOutputListener extends createTask implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String consoleError = extractConsoleError(result);
        String listId = "";
        String status = "";

        // Create ClickUp task with method name as task name and console error in description
        try {
        	createClickUpTask(methodName, consoleError, listId, status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractConsoleError(ITestResult result) {
        String consoleOutput = "";
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            consoleOutput = throwable.getMessage();
        }
        return consoleOutput;
    }

    // Other methods of ITestListener (not implemented for brevity)
    // You can choose to implement them if needed
    // ...

    @Override
    public void onStart(ITestContext context) {
        // Initialization or additional logic before all tests start
    }

    @Override
    public void onFinish(ITestContext context) {
        // Cleanup or additional logic after all tests have finished
    }
}
