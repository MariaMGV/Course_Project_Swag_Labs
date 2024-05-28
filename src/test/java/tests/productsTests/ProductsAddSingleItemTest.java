package tests.productsTests;

import pages.productsPages.YourCartPage;
import pages.LoginPage;
import pages.productsPages.ProductsPage;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsAddSingleItemTest extends TestUtil {
    @Test(dataProvider = "itemsInCart")
    public void addItemToTheCart(String itemName) {

        LoginPage loginpage = new LoginPage(driver);
        ProductsPage productsPage = loginpage.loginUsingSubmit("standard_user", "secret_sauce");

        productsPage.addSingleItemToCart(itemName);
        int currentItemCount = productsPage.getItemCountInCart();
        int expectedItemCount = 1;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(currentItemCount, expectedItemCount, "Incorrect number of items in cart. This is the " + expectedItemCount + " item in the cart");

        YourCartPage yourCartPage = productsPage.goToYourCartPage();
        Assert.assertTrue(yourCartPage.isAt());

        softAssert.assertAll();
    }

    @DataProvider(name = "itemsInCart")
    public Object[] getShoppingItems() {
        return new Object[]{
                "bike-light",
                "backpack",
                "bolt-t-shirt",
                "fleece-jacket",
                "onesie"

        };

    }
}
