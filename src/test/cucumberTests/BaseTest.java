package cucumberTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class BaseTest {
    private WebDriver webDriver;
    public static MainPage mainPage;

    @Before
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        mainPage = new MainPage(webDriver);
    }

    @After
    public void afterMethod() {
        webDriver.quit();
    }
}
