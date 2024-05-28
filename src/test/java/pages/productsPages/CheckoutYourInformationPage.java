package pages.productsPages;

import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CheckoutYourInformationPage extends BasePage {

    @FindBy(className="title")
    private WebElement YourInformationCheckoutPage;
    @FindBy(id="first-name")
    private WebElement firstNameField;
    @FindBy(id="last-name")
    private WebElement lastNameField;
    @FindBy(id="postal-code")
    private WebElement postalCodeField;
    @FindBy(className ="error-message-container")
    private WebElement errorMsg;
    @FindBy(id ="continue")
    private WebElement continueBtn;

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public List<String> accessConfigUserData(String pathToFile){
        List<String> userDataRead = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/userData.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            userDataRead.add(properties.getProperty("firstName"));
            userDataRead.add(properties.getProperty("lastName"));
            userDataRead.add(properties.getProperty("zipCode"));
        }
        catch (IOException e){
            System.out.println(e);
        }
        return userDataRead;
    }
    public void fillUserData(List<String > userDataRead){
        if(userDataRead.size() >= 3) {
            firstNameField.click();
            firstNameField.clear();
            firstNameField.sendKeys(userDataRead.get(0));

            lastNameField.click();
            lastNameField.clear();
            lastNameField.sendKeys(userDataRead.get(1));

            postalCodeField.click();
            postalCodeField.clear();
            postalCodeField.sendKeys(userDataRead.get(2));
        }
        else {
            System.out.println("Insufficient data provided");
        }
    }
    public boolean verifyFilledFields(List<String> propertiesFileUserData){
        boolean firstNameMatch = firstNameField.getAttribute("value").equals(propertiesFileUserData.get(0));
        boolean lastNameMatch = lastNameField.getAttribute("value").equals(propertiesFileUserData.get(1));
        boolean zipCodeMatch =  postalCodeField.getAttribute("value").equals(propertiesFileUserData.get(2));

        return firstNameMatch & lastNameMatch & zipCodeMatch;
    }
    public CheckoutOverviewPage goToOverviewCheckoutPage(){
        continueBtn.click();
        return new CheckoutOverviewPage(driver);
    }

    @Override
    public boolean isAt() {
        return YourInformationCheckoutPage.isDisplayed();
    }
}
