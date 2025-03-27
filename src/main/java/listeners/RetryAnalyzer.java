package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Adjust as needed

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { // Only retry failed tests
            if (retryCount < maxRetryCount) {
                retryCount++;
                return true; // Retry the test
        }
    }
        return false; // Do not retry
    }
}