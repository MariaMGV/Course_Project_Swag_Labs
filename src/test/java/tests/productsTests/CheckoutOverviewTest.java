package tests.productsTests;


import pages.productsPages.CheckoutCompletePage;
import pages.productsPages.*;
import pages.LoginPage;
import tests.DataProvider;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutOverviewTest extends TestUtil {

    @Test(dataProvider = "itemsInCart",dataProviderClass = DataProvider.class)
    public void checkoutOverview(String[] allItemsList){

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("standard_user", "secret_sauce");

        List<String> trimmedItems = getFirstPartFromCSVString(allItemsList);
        productsPage.addMultipleItemsToCart(trimmedItems);

        YourCartPage yourCartPage = productsPage.goToYourCartPage();

        CheckoutYourInformationPage checkoutYourInformationPage = yourCartPage.goToCheckoutYourInformationPage();
        List<String> accessedUserData = checkoutYourInformationPage.accessConfigUserData("src/test/resources/userData.properties");
        checkoutYourInformationPage.fillUserData(accessedUserData);

        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.goToOverviewCheckoutPage();

        List<String> extractedItemsOnOverviewPage = checkoutOverviewPage.createItemsListOnOverviewPage();
        List<String> itemsSecondPartCSVDescription = getSecondPartFromCSVString(allItemsList);
        boolean areItemsOnOverviewPage = checkoutOverviewPage.checkIfItemsAreValid(extractedItemsOnOverviewPage, itemsSecondPartCSVDescription);
        Assert.assertTrue(areItemsOnOverviewPage,"The products on Checkout:Overview page do not match the ones, added from the previous page");

        CheckoutCompletePage checkoutcompletePage = checkoutOverviewPage.goToCheckoutCompletePage();
        Assert.assertTrue(checkoutcompletePage.isAt());

    }
}
