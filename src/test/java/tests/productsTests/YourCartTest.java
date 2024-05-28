package tests.productsTests;

import pages.productsPages.YourCartPage;
import pages.productsPages.ProductsPage;
import pages.productsPages.CheckoutYourInformationPage;
import pages.LoginPage;
import tests.DataProvider;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class YourCartTest extends TestUtil {
    @Test(dataProvider = "itemsInCart",dataProviderClass = DataProvider.class)
    public void CheckItemsInCart(String[] allItemsList){

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("standard_user", "secret_sauce");
        List<String> trimmedItems = getFirstPartFromCSVString(allItemsList);
        productsPage.addMultipleItemsToCart(trimmedItems);
        productsPage.goToYourCartPage();

        YourCartPage yourCartPage = new YourCartPage(driver);
        List<String> itemsSecondPartCSVDescription = getSecondPartFromCSVString(allItemsList);
        List<String> extractedCartItemsText = yourCartPage.createItemsListOnPageYourCart();

        boolean areItemsValid = yourCartPage.checkIfItemsAreValid(extractedCartItemsText,itemsSecondPartCSVDescription);
        Assert.assertTrue(areItemsValid,"The items in the cart do not match the ones, added from the previous page");

        CheckoutYourInformationPage checkoutYourInformationPage = yourCartPage.goToCheckoutYourInformationPage();
        Assert.assertTrue(checkoutYourInformationPage.isAt());
    }
}