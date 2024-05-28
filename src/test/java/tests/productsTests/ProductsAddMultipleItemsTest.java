package tests.productsTests;


import pages.productsPages.YourCartPage;
import pages.LoginPage;
import pages.productsPages.ProductsPage;
import tests.TestUtil;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductsAddMultipleItemsTest extends TestUtil {

    @Test(dataProvider = "itemsInCart",dataProviderClass = tests.DataProvider.class)
    public void addItemsToTheCart(String[] allItemsList) {
        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingClickButton("standard_user", "secret_sauce");

        List<String> trimmedItems = getFirstPartFromCSVString(allItemsList);
        productsPage.addMultipleItemsToCart(trimmedItems);
        int currentItemCount = productsPage.getItemCountInCart();
        int expectedItemCount = trimmedItems.size();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(currentItemCount, expectedItemCount, "Incorrect number of items in cart. This is the" + expectedItemCount + " item in the cart");

        YourCartPage yourCartPage = productsPage.goToYourCartPage();
        Assert.assertTrue(yourCartPage.isAt());

        softAssert.assertAll();

    }
}
