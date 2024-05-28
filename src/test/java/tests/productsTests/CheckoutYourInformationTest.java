package tests.productsTests;

import pages.productsPages.*;
import pages.LoginPage;
import tests.DataProvider;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutYourInformationTest extends TestUtil {

    @Test(dataProvider = "itemsInCart",dataProviderClass = DataProvider.class)
    public void checkItemInformationInCart(String[] allItemsList){

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("standard_user", "secret_sauce");

        YourCartPage yourCartPage = productsPage.goToYourCartPage();
        CheckoutYourInformationPage checkoutYourInformationPage = yourCartPage.goToCheckoutYourInformationPage();

        List<String> accessedUserData = checkoutYourInformationPage.accessConfigUserData("src/test/resources/userData.properties");
        checkoutYourInformationPage.fillUserData(accessedUserData);

        boolean areFieldsCorrectlyFilled = (checkoutYourInformationPage.verifyFilledFields(accessedUserData));
        Assert.assertTrue(areFieldsCorrectlyFilled,"The fields are not filled in correctly");

        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.goToOverviewCheckoutPage();
        Assert.assertTrue(checkoutOverviewPage.isAt());

    }
}
