package pages.productsPages;

import pages.BasePage;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BasePage {

    public final static String Base_Product_ID = "add-to-cart-sauce-labs-";
    @FindBy(className="title")
    private WebElement productPageTitle;
    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCartLink;
    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public ProductsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addSingleItemToCart(String itemName) {
            String idItem = Base_Product_ID + itemName;
            WebElement item = driver.findElement(By.id(idItem));
            item.click();
    }

        public void addMultipleItemsToCart(List<String> itemsInCart){
            for(int i = 0; i < itemsInCart.size(); i++) {
                addSingleItemToCart(itemsInCart.get(i));
        }
    }
    public int getItemCountInCart(){
        int itemCount = Integer.parseInt(shoppingCartBadge.getText());
        return itemCount;
    }
    public YourCartPage goToYourCartPage(){
        shoppingCartLink.click();
        return new YourCartPage(driver);
    }

    @Override
    public boolean isAt() {
        return productPageTitle.isDisplayed();
    }
}
