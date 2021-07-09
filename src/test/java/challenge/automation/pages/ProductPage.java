package challenge.automation.pages;

import challenge.automation.utilities.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import challenge.automation.utilities.AppiumUtils;
import challenge.automation.utilities.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.sun.java.swing.action.ActionManager.utilities;

public class ProductPage {
    public ProductPage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.driver), this);
    }

    @AndroidFindBy(id = "com.example.challenge:id/addReview")
    public MobileElement addReviewBtn;

    @AndroidFindBy(id = "com.example.challenge:id/reviewDetails")
    public MobileElement reviewInputBox;



    @AndroidFindBy(id = "com.example.challenge:id/reviewNumber")
    public MobileElement rateDropdown;

    @AndroidFindBy(xpath= "(//*[@resource-id='android:id/text1'])[2]")
    public MobileElement rateOption1;

    @AndroidFindBy(id = "com.example.challenge:id/saveReview")
    public MobileElement saveReviewBtn;

    @AndroidFindBy(id = "com.example.challenge:id/productImage")
    public MobileElement productDetailImg;

    @AndroidFindAll(
            @AndroidBy(className = "android.widget.CheckedTextView")
    )
    public List<MobileElement> reviewNumberOptions;











    @AndroidFindBy(id = "com.example.challenge:id/goBack")
    MobileElement goBackBtn;

    @AndroidFindBy(id = "com.example.challenge:id/productName")
    MobileElement productName;

    @AndroidFindBy(id = "com.example.challenge:id/productDescription")
    MobileElement productDescription;

    @AndroidFindBy(id = "com.example.challenge:id/productPrice")
    MobileElement productPrice;

    @AndroidFindBy(id = "com.example.challenge:id/reviewsRecycler")
    MobileElement reviewsRecycler;










//    public void clickProductByName(String productName) {
//        //Todo: use productName to scroll if we are able to display the product we created
//        AppiumUtils.scrollTo("product").click();
//    }

    public Map<String, String> getProductAttributes() {
        Map<String, String> map = new HashMap<>();

        int i = productPrice.getText().indexOf(" ");
        String currency = productPrice.getText().substring(i + 1);
        String price = productPrice.getText().substring(0, i);

        map.put("name", productName.getText());
        map.put("price", price);
        map.put("currency", currency);
        //Todo: uncomment it if 'display product description' is implemented
//        map.put("description", productDescription.getText());

        return map;
    }

    public boolean isScrollable() {
        return Boolean.parseBoolean(reviewsRecycler.getAttribute("scrollable"));
    }

    public void addReview(String reviewTxt, String reviewNumber) {



        AppiumUtils.clickListElementByText(reviewNumberOptions, reviewNumber);


    }

    public void goBack() {
        boolean isDisplay = AppiumUtils.isElementVisible(goBackBtn);
        Assert.assertTrue("there is no 'go back'", isDisplay);

        goBackBtn.click();
    }

}
