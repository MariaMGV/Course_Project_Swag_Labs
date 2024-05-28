package tests.loginTests;

import pages.LoginPage;
import pages.productsPages.ProductsPage;
import tests.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulLoginTest extends TestUtil {
    @Test
    public void successfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage =  loginPage.loginUsingClickButton("standard_user","secret_sauce");

        Assert.assertTrue(productsPage.isAt());

    }
}

