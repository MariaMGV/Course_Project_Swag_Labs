package pages.productsPages;

import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage {

    @FindBy(className="title")
    private WebElement completeCheckoutTitle;
    @FindBy(className="complete-header")
    private WebElement completedOrderMsg;
    @FindBy(className="complete-text")
    private WebElement successfulShoppingMsg;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean successfulShopping(){

        return successfulShoppingMsg.isDisplayed();

    }

    @Override
    public boolean isAt() {
        return completeCheckoutTitle.isDisplayed();
    }
}
