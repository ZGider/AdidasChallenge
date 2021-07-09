package challenge.automation.tests;

import challenge.automation.utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import challenge.automation.utilities.ConfigurationReader;
import challenge.automation.utilities.Driver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.appium.java_client.MobileElement;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import java.net.URL;
import java.util.List;

public class TestBase {

    protected AndroidDriver<WebElement> driver;

    protected String projectPath = System.getProperty("user.dir");
    protected String appFileRelativePath = "src/test/resources/app-debug.apk";
    protected String appFilePath = projectPath+"/"+appFileRelativePath;

    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;
    protected String url;

    @BeforeTest
    public void setUpTest(){

        //initialize the class
        report = new ExtentReports();

        //create report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter = new ExtentHtmlReporter(path);

        //attach the html report to the report object
        report.attachReporter(htmlReporter);

        //title in the report
        htmlReporter.config().setReportName("Challenge App Test");

        //set enviroment information
        report.setSystemInfo("Environment", "QA");
        report.setSystemInfo("Platform Name", ConfigurationReader.get("platformName"));
        report.setSystemInfo("Platform Version", ConfigurationReader.get("platformVersion"));
        report.setSystemInfo("Device Name",System.getProperty("deviceName"));

    }

    @AfterTest
    public void tearDownTest(){
        //this is when the report is actually created
        report.flush();
    }

    @BeforeMethod
    public void setUpMethod() throws MalformedURLException {

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
    desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");

    //if the application will be installed by an apk file we show the filepath
            desiredCapabilities.setCapability("app", appFilePath);

    //if the application is already installed, then we use the codes below to run the app

//            desiredCapabilities.setCapability("appActivity","com.example.challenge.MainActivity");
//            desiredCapabilities.setCapability("appPackage","com.example.challenge");

    driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);

    }

    //ITestReslt clas describes the result of a test in TestNG
    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        //if test failed
        if(result.getStatus()== ITestResult.FAILURE){

            //record the name of the failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of the screenshot
            String screenshotPath = AppiumUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);

            //capture the exception
            extentLogger.fail(result.getThrowable());

        }else if (result.getStatus()==ITestResult.SKIP){
            extentLogger.skip("Test Skipped"+result.getName());

        }

        //Close driver
        Thread.sleep(10000);
        Driver.closeDriver();

    }
}
