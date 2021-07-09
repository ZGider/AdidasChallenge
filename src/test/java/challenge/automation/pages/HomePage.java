package challenge.automation.pages;


import challenge.automation.utilities.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class HomePage {
    public HomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.get()), this);
        }


    @AndroidFindAll(
            @AndroidBy(xpath = "//*[@resource-id='com.example.challenge:id/recyclerview']/android.view.ViewGroup")
    )
    public List<MobileElement> products;



    @AndroidFindBy(id = "com.example.challenge:id/toolbar")
    MobileElement header;

    @AndroidFindBy(id = "com.example.challenge:id/search")
    MobileElement searchInput;

    @AndroidFindBy(accessibility = "More options")
    MobileElement moreOptionsDropdown;

    @AndroidFindAll(
            @AndroidBy(id = "com.example.challenge:id/title")
    )
    List<MobileElement> moreOptionsDropdownOptions;

    @AndroidFindBy(id = "com.example.challenge:id/recyclerview")
    MobileElement productsContainer;


}
