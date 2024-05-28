package tests.loginTests;


import pages.LoginPage;
import tests.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnsuccessfulLoginTest extends TestUtil {

    @Test(dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUsingClickButton(username,password);

        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvResult[i] = csvData.get(i);
            }
            return csvResult;

        } catch (IOException e) {
            System.out.println(e);
            return null;

        } catch (CsvException e) {
            System.out.println(e);
            return null;
        }
    }
}