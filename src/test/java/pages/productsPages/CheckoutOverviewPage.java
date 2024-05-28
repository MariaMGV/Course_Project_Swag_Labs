package pages.productsPages;


import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(css =".title")
    private WebElement CheckoutOverviewTitle;
    @FindBy(css = ".btn.btn_action.btn_medium.cart_button")
    private WebElement finishBtn;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public List<String> createItemsListOnOverviewPage(){
        List<WebElement> itemsOnOverviewPage = driver.findElements(By.className("inventory_item_name"));
        List<String> extractedTextForItem = new ArrayList<>();

        for(WebElement item : itemsOnOverviewPage){
            String elementText = item.getText();
            extractedTextForItem.add(elementText);
        }
        return extractedTextForItem;
    }
    public boolean checkIfItemsAreValid(List<String> extractedTextForItem,List<String> csvItemsTextValue){
        if(extractedTextForItem.size() == csvItemsTextValue.size()){
            Collections.sort(extractedTextForItem);
            Collections.sort(csvItemsTextValue);

            if(extractedTextForItem.equals(csvItemsTextValue)){
                return true;
            }
            else{
                return false;
            }

        }else {
            return false;
        }
    }

    public CheckoutCompletePage goToCheckoutCompletePage(){
        finishBtn.click();
        return new CheckoutCompletePage(driver);
    }

    @Override
    public boolean isAt() {
        return CheckoutOverviewTitle.isDisplayed();
    }
}

