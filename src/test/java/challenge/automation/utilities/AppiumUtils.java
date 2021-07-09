package challenge.automation.utilities;

import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppiumUtils {

    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static String getDate(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String dateofDay = timestamp.toString().substring(0,16);

        return dateofDay;
    }

    /**
     * Performs a pause
     *
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isElementVisible(MobileElement el) {
        try {
            el.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //===============Explicit Wait==============//
    public static MobileElement waitForVisibility(MobileElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.driver, timeout);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
    }

    //==========Return a list of string given a list of Mobile Element====////
    public static List<String> getElementsText(List<MobileElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (MobileElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }

        return elemTexts;
    }

//    public static MobileElement scrollTo(String visibleText) {
//        return Driver.get().findElementByAndroidUIAutomator(
//                        "new UiScrollable(" +
//                                "new UiSelector().scrollable(true).instance(0))" +
//                                ".scrollIntoView(new UiSelector()" +
//                                ".textContains(\"" + visibleText + "\").instance(0))"
//                );
//    }

    public static void clickListElementByText(List<MobileElement> list, String elText) {
        List<String> txtList = getElementsText(list);
        //make sure that the list has element
        Assert.assertTrue(elText + " does not exist", txtList.contains(elText));
        for (MobileElement e : list) {
            if (!e.getText().isEmpty() && e.getText().equals(elText)) {
                e.click();
                //exit loop after clicking the desired element
                return;
            }
        }
    }
}
