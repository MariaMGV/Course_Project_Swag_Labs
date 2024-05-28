package tests.productsTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.productsPages.CheckoutOverviewPage;
import pages.productsPages.CheckoutYourInformationPage;
import pages.productsPages.ProductsPage;
import pages.productsPages.YourCartPage;
import tests.DataProvider;
import tests.TestUtil;

import java.util.List;

public class CheckoutYourInformationProblemUser extends TestUtil {
    @Test(dataProvider = "itemsInCart",dataProviderClass = DataProvider.class)
    public void checkItemInformationInCart(String[] allItemsList){

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("problem_user", "secret_sauce");

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
