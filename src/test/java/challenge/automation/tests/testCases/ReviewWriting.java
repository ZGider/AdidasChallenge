package challenge.automation.tests.testCases;

import challenge.automation.pages.HomePage;
import challenge.automation.tests.TestBase;
import challenge.automation.utilities.AppiumUtils;
import org.testng.annotations.Test;

public class ReviewWriting extends TestBase {
    /*
    *This class includes the the Test Cases of the Epic-2 Review Writing*/

    @Test
    public void addReview() throws InterruptedException {
        /*Test Case 2.1:

        User should be navigated to the review page by clicking the "Add Review" Button

        * Pre-Condition:

        The user should be in Home Page of ChallengeApp

        * Test Steps

        1.Click one of the Product Images
        2.Click the "Add Review" Button

        */

        HomePage homePage = new HomePage();
        Thread.sleep(6000);
        homePage.products.get(1).click();


    }

    @Test
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
        5. Verify that your in the Product page


        */
    }

    @Test
    public void listReview(){
        /*Test Case 2.3:

        The review written by the user should have been added and displayed in the end of the review list

        * Pre-Condition:

        1. The user should have saved a new Review.
        2. The user should be in Product Page of ChallengeApp

        * Test Steps

        1.Scroll down until the end of the Review List
        2. Verify that the last saved review matches with the last review of the list


        */
    }

}
