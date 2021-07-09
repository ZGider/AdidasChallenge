package challenge.automation.pages;

import challenge.automation.utilities.AppiumUtils;
import challenge.automation.utilities.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public boolean isHeaderVisible(){
        return AppiumUtils.isElementVisible(header);
    }
    public boolean isSearchInputVisible(){
        return AppiumUtils.isElementVisible(searchInput);
    }
    public boolean isMoreOptionsDropdownVisible(){
        return AppiumUtils.isElementVisible(moreOptionsDropdown);
    }
    public boolean isProductsContainerVisible(){
        return AppiumUtils.isElementVisible(productsContainer);
    }

    public List<String> optionsText(){
        //open dropdown and get all options text
        moreOptionsDropdown.click();
        AppiumUtils.waitForVisibility(moreOptionsDropdownOptions.get(0),3);
        return AppiumUtils.getElementsText(moreOptionsDropdownOptions);
    }

    public boolean hasProductAttributes(){
        try{
            getProductsSpecs();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Map<String,String>> getSearchResult(String searchTxt){
        List<Map<String,String>> ls = new ArrayList<>();
        //make search then get listed products specs
        searchInput.clear();
        searchInput.sendKeys(searchTxt, Keys.ENTER);

        try{
            ls = getProductsSpecs();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    List<Map<String,String>> getProductsSpecs(){
        List<Map<String,String>> ls = new ArrayList<>();
        Map<String,String> map = new HashMap<>();

        String productImageLocator = "com.example.challenge:id/productImage";
        String nameLocator = "com.example.challenge:id/productName";
        String descriptionLocator = "com.example.challenge:id/productDescription";
        String productPriceLocator = "com.example.challenge:id/productPrice";

            for(MobileElement el : products){
                //it will throw err if a product does not have following elements,
                //so we can use it to check if product has these attributes in hasProductAttributes() method
                MobileElement image = el.findElement(By.id(productImageLocator));
                MobileElement name = el.findElement(By.id(nameLocator));
                //Todo: uncomment it if 'display product description' is implemented
//                MobileElement description = el.findElement(By.id(descriptionLocator));
                MobileElement price = el.findElement(By.id(productPriceLocator));

                map.put("name", name.getText());
//                map.put("description",description.getText());
                map.put("price",price.getText());

                ls.add(map);
            }
            return ls;
    }
}
