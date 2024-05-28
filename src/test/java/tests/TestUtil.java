package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TestUtil {

    public WebDriver driver;
    private String browser, targetUrl;
    private int implicitWait;

    @BeforeTest
    public void setupDriverAndOpenTargetUrl(){
        readConfig("src/test/resources/config.properties");
        setupDriver();
        driver.manage().timeouts().implicitlyWait(Duration.from(Duration.ofSeconds(implicitWait)));
        driver.get(targetUrl);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


    public void readConfig(String pathToFile){
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            browser = properties.getProperty("browser");
            targetUrl = properties.getProperty("url");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        }
        catch (IOException e){
            System.out.println(e);

        }
    }
    private void setupDriver(){
        switch (browser){
            case "firefox":
                driver = setupFirefoxDriver();
                break;
            default:
                driver = setupChromeDriver();
                break;
        }
    }

    private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    public List<String> getFirstPartFromCSVString(String[] csvData)
    {
        String[] realValues = new String[csvData.length];
        for (int i = 0; i < csvData.length; i++)
        {
            realValues[i] = csvData[i].substring(0, csvData[i].indexOf("#;"));
        }
        return Arrays.asList(realValues);
    }

    public List<String> getSecondPartFromCSVString(String[] csvData)
    {
        String[] realValues = new String[csvData.length];
        for(int i = 0; i < csvData.length;i++ ){
            realValues[i]= csvData[i].substring(csvData[i].indexOf("#;") + 2);
        }
        return Arrays.asList(realValues);
    }

}

