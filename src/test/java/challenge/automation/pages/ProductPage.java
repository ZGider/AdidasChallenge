package challenge.automation.pages;

import challenge.automation.utilities.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


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

}
