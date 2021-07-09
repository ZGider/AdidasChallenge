package challenge.automation.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    public static AppiumDriver driver;

    public static AppiumDriver get() {
        if (driver == null) {
            String platform = challenge.automation.utilities.ConfigurationReader.get("platformName");
            switch (platform) {
                case "Android":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability("platformName", "Android");
                    desiredCapabilities.setCapability("platformVersion", "7.0");
                    desiredCapabilities.setCapability("deviceName", "Pixel_2");
                    desiredCapabilities.setCapability("automationName", "UiAutomator2");
                    desiredCapabilities.setCapability("app", challenge.automation.utilities.ConfigurationReader.get("android.app.url"));
                    try {
                        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Driver is not implemented yet!");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
