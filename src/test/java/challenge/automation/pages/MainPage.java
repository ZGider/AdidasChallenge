package challenge.automation.pages;

import challenge.automation.utilities.Driver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public MainPage(){
        PageFactory.initElements(Driver.get(),this);
    }

}
