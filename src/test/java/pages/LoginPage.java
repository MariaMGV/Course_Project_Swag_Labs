package pages;

import pages.productsPages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.productsPages.ProductsPage;

public class LoginPage extends BasePage{
    @FindBy(id="user-name")
    private WebElement userNameInput;
    @FindBy(id="password")
    private WebElement passwordInput;
    @FindBy(id="login-button")
    private WebElement loginBtn;
    @FindBy(css=".error-button")
    private WebElement errorMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public ProductsPage loginUsingClickButton(String userName, String password){
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductsPage(driver);
    }
    public ProductsPage loginUsingSubmit(String userName, String password){
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.submit();

        return new ProductsPage(driver);
    }

        public WebElement getErrorMessage(){
        return errorMsg;
    }

    @Override
    public boolean isAt() {
        return loginBtn.isDisplayed();
    }
}
