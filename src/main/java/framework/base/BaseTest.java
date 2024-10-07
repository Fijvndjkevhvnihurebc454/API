package framework.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;


public class BaseTest {
    private static String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod() {
        try {
            Configuration.getResourcesFromPropertyFile();
            baseUrl = Configuration.getUrl();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterAll(ITestResult result) {
        // TODO will be used to destroy some data
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
