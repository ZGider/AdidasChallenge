package challenge.automation.tests.testCases;

import challenge.automation.pages.HomePage;
import challenge.automation.pages.ProductPage;
import challenge.automation.tests.TestBase;
import challenge.automation.utilities.AppiumUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReviewWriting extends TestBase {
    /*
    *This class includes the the Test Cases of the Epic-2 Review Writing*/

    public String reviewText = "The Last Review " + AppiumUtils.getDate();

    @Test(priority = 0)
    public void addReview() {
        /*Test Case 2.1:

        User should be navigated to the review page by clicking the "Add Review" Button

        * Pre-Condition:

        The user should be in Home Page of ChallengeApp

        * Test Steps

        1.Click one of the Product Images
        2.Click the "Add Review" Button
        3.Verify that you are in Review Page


        */
        extentLogger = report.createTest("2.1 User should be navigated to the review page by clicking the Add Review Button");

        HomePage homePage = new HomePage();
        homePage.products.get(1).click();
        extentLogger.info("Click The First Product In the Homepage");

        ProductPage productPage = new ProductPage();
        productPage.addReviewBtn.click();
        extentLogger.info("Click Add Review Button");

        boolean isDisplayed = AppiumUtils.isElementVisible(productPage.reviewInputBox);

        Assert.assertTrue(isDisplayed,"Verify that user is on the Review Page");
        extentLogger.info("PASS : 2.1 User should be navigated to the review page by clicking the Add Review Button");
    }

    @Test(priority = 1)
    public void writeReview(){
        /*Test Case 2.2:

        User should be able to write a review in the "Review InputBox" and rate the product

        * Pre-Condition:

        The user should be in Review Page of ChallengeApp

        * Test Steps

        1. Click the Review Inputbox
        2. Enter a free text into the review InputBox
        3. Rate the the product by using the Rating Dropdown
        4. Click "Save" Button
        5. Verify that you are in the Product page

        */

        extentLogger = report.createTest("2.2 User should be able to write a review in the Review InputBox and rate the product");

        HomePage homePage = new HomePage();
        homePage.products.get(1).click();
        extentLogger.info("Click The First Product In the Homepage");

        ProductPage productPage = new ProductPage();
        productPage.addReviewBtn.click();
        extentLogger.info("Click Add Review Button");

        productPage.reviewInputBox.sendKeys(reviewText);
        extentLogger.info("Enter the Review Text to the Review Input Box");

        productPage.rateDropdown.click();
        extentLogger.info("Click Review Rating Dropdown");

        productPage.rateOption1.click();
        extentLogger.info("Click one of the Review Rate Options");

        productPage.saveReviewBtn.click();
        extentLogger.info("Click Save Button");

        boolean isDisplayed = AppiumUtils.isElementVisible(productPage.productDetailImg);

        Assert.assertTrue(isDisplayed,"Verify that user is in the Product Page");

        extentLogger.info("PASS : 2.2 User should be able to write a review in the Review InputBox and rate the product ");


    }

    @Test(priority = 2)
    public void listReview() throws InterruptedException {
        /*Test Case 2.3:

        The review written by the user should have been added and displayed in the end of the review list

        * Pre-Condition:

        1. The user should have saved a new Review.
        2. The user should be in Product Page of ChallengeApp

        * Test Steps

        1.Scroll down until the end of the Review List
        2. Verify that the last saved review matches with the last review of the list

        */

        extentLogger = report.createTest("2.3 The review written by the user should have been added and displayed in the end of the review list");

        //HomePage homePage = new HomePage();
        AppiumUtils.scrollTo("Noisy Shame").click();
        extentLogger.info("Click The First Product In the Homepage");

        ProductPage productPage = new ProductPage();
        productPage.addReviewBtn.click();
        extentLogger.info("Click Add Review Button");

        productPage.reviewInputBox.sendKeys(reviewText);
        extentLogger.info("Enter the Review Text to the Review Input Box");

        productPage.rateDropdown.click();
        extentLogger.info("Click Review Rating Dropdown");

        productPage.rateOption1.click();
        extentLogger.info("Click one of the Review Rate Options");

        productPage.saveReviewBtn.click();
        extentLogger.info("Click Save Button");

        MobileElement lastReview = AppiumUtils.scrollTo(reviewText);

        Assert.assertNotNull(lastReview);

        extentLogger.info("PASS : 2.3 The review written by the user should have been added and displayed in the end of the review list ");
    }

}
