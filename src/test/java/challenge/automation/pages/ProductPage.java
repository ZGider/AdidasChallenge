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

    @AndroidFindBy(id = "com.example.challenge:id/addReview")
    MobileElement addReview;

    @AndroidFindBy(id = "com.example.challenge:id/reviewDetails")
    MobileElement reviewDetailsInput;

    @AndroidFindBy(id = "com.example.challenge:id/reviewNumber")
    MobileElement reviewNumberDropdown;

    @AndroidFindAll(
            @AndroidBy(className = "android.widget.CheckedTextView")
    )
    List<MobileElement> reviewNumberOptions;

    @AndroidFindBy(id = "com.example.challenge:id/saveReview")
    MobileElement saveReview;


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
        boolean isVisible = AppiumUtils.isElementVisible(addReview);
        Assert.assertTrue(isVisible);

        addReview.click();
        reviewDetailsInput.sendKeys(reviewTxt);

        reviewNumberDropdown.click();
        AppiumUtils.clickListElementByText(reviewNumberOptions, reviewNumber);

        saveReview.click();
    }

    public void goBack() {
        boolean isDisplay = AppiumUtils.isElementVisible(goBackBtn);
        Assert.assertTrue("there is no 'go back'", isDisplay);

        goBackBtn.click();
    }

}
