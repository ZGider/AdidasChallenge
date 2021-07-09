package challenge.automation.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

public class ChallengeTest {

        public AndroidDriver<WebElement> driver;
        public String projectPath = System.getProperty("user.dir");
        public String appFileRelativePath = "src/test/resources/app-debug.apk";
        public String appFilePath = projectPath+"/"+appFileRelativePath;

        @Test
        public void test1() throws Exception{
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

            WebElement productButton1 = driver.findElement(By.xpath("(//*[@resource-id='com.example.challenge:id/productImage'])[0]"));


            Thread.sleep(3000);
            productButton1.click();

            Thread.sleep(3000);
            driver.closeApp();




        }
    }

