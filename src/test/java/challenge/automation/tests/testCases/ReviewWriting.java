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

    @Test
    public void addReview() throws InterruptedException {
        /*Test Case 2.1:

        User should be navigated to the review page by clicking the "Add Review" Button

        * Pre-Condition:

        The user should be in Home Page of ChallengeApp

        * Test Steps

        1.Click one of the Product Images
        2.Click the "Add Review" Button
        3.Verify that you are in Review Page


        */
        HomePage homePage = new HomePage();
        Thread.sleep(6000);
        homePage.products.get(1).click();

        ProductPage productPage = new ProductPage();
        Thread.sleep(3000);
        productPage.addReviewBtn.click();
        Thread.sleep(3000);
        Assert.assertTrue(productPage.reviewInputBox.isDisplayed(),"Verify that user is on the Review Page");




    }

    @Test
    public void writeReview() throws InterruptedException {
        /*Test Case 2.2:

        User should be able to write a review in the "Review InputBox" and rate the product

        * Pre-Condition:

        The user should be in Review Page of ChallengeApp

        * Test Steps

        1. Click the Review Inputbox
        2. Enter a free text into the review InputBox
        3. Rate the the product by using the Rating Dropdown
        4. Click "Save" Button
        5. Verify that your in the Product page


        */
        HomePage homePage = new HomePage();
        Thread.sleep(6000);
        homePage.products.get(1).click();

        ProductPage productPage = new ProductPage();
        Thread.sleep(3000);
        productPage.addReviewBtn.click();
        Thread.sleep(3000);
        productPage.reviewInputBox.sendKeys(reviewText);

        WebElement reviewListOption = driver.findElement(By.xpath("//*[@class='' and @text = '"+ reviewText +"'"));

        Thread.sleep(2000);
        productPage.rateDropdown.click();
        Thread.sleep(1000);
        productPage.rateOption1.click();
        Thread.sleep(1000);
        productPage.saveReviewBtn.click();
        Thread.sleep(1000);

        Assert.assertTrue(productPage.productDetailImg.isDisplayed(),"Verify that user is in the Product Page");




    }

    @Test
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

        HomePage homePage = new HomePage();
        Thread.sleep(6000);
        homePage.products.get(1).click();

        ProductPage productPage = new ProductPage();
        Thread.sleep(3000);
        productPage.addReviewBtn.click();
        Thread.sleep(3000);
        productPage.reviewInputBox.sendKeys(reviewText);

        String reviewListText = reviewText;

        Thread.sleep(2000);
        productPage.rateDropdown.click();
        Thread.sleep(1000);
        productPage.rateOption1.click();
        Thread.sleep(1000);
        productPage.saveReviewBtn.click();
        Thread.sleep(1000);








    }

}
