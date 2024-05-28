package tests.productsTests;

import pages.productsPages.CheckoutCompletePage;
import pages.productsPages.*;
import pages.LoginPage;
import tests.DataProvider;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutCompleteTest extends TestUtil {
    @Test(dataProvider = "itemsInCart", dataProviderClass = DataProvider.class)
    public void checkoutOverview(String[] allItemsList) {

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("standard_user", "secret_sauce");

        List<String> trimmedItems = getFirstPartFromCSVString(allItemsList);
        productsPage.addMultipleItemsToCart(trimmedItems);

        YourCartPage yourCartPage = productsPage.goToYourCartPage();
        CheckoutYourInformationPage checkoutYourInformationPage = yourCartPage.goToCheckoutYourInformationPage();

        List<String> accessedUserData = checkoutYourInformationPage.accessConfigUserData("src/test/resources/userData.properties");
        checkoutYourInformationPage.fillUserData(accessedUserData);

        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.goToOverviewCheckoutPage();
        CheckoutCompletePage checkoutcompletePage = checkoutOverviewPage.goToCheckoutCompletePage();

        boolean isDisplayedSuccessfulShoppingMsg = checkoutcompletePage.successfulShopping();
        Assert.assertTrue(isDisplayedSuccessfulShoppingMsg);
    }
}