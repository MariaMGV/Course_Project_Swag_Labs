package pages.productsPages;

import pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YourCartPage extends BasePage {
    public final static String Base_Inventory_ID = "//div[@class='inventory_item_name' and text()='Sauce Labs ";
    @FindBy(className = "title")
    private WebElement yourCartPageTitle;
    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCartLink;
    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartBadge;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;
    @FindBy(xpath = Base_Inventory_ID + "Bike Light']")
    private WebElement bikeLightItem;
    @FindBy(xpath = Base_Inventory_ID + "Backpack']")
    private WebElement backpackItem;
    @FindBy(xpath = Base_Inventory_ID + "Bolt T-Shirt']")
    private WebElement boltTShirtItem;
    @FindBy(xpath = Base_Inventory_ID + "Fleece Jacket']")
    private WebElement fleeceJacketItem;
    @FindBy(xpath = Base_Inventory_ID + "Onesie']")
    private WebElement onesieItem;

    public YourCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> createItemsListOnPageYourCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated((By.className("inventory_item_name"))));

        List<WebElement> itemsOnYourCartPage = driver.findElements(By.className("inventory_item_name"));
        List<String> itemsTextValueCartPage = new ArrayList<>();

        for(WebElement item : itemsOnYourCartPage){
            String elementText = item.getText();
            itemsTextValueCartPage.add(elementText);
        }
        return itemsTextValueCartPage;
    }
    public boolean checkIfItemsAreValid(List<String> extractedTexts,List<String> csvItemsTextValue){
        if(extractedTexts.size() == csvItemsTextValue.size()) {
            Collections.sort(extractedTexts);
            Collections.sort(csvItemsTextValue);

            if (extractedTexts.equals(csvItemsTextValue))
                return  true;
            else
                return false;

        } else {
            return false;
        }
    }

    public CheckoutYourInformationPage goToCheckoutYourInformationPage(){
        checkoutBtn.click();
        return new CheckoutYourInformationPage(driver);
    }
    @Override
    public boolean isAt() {
        return yourCartPageTitle.isDisplayed();
    }
}
