package cucumberTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class BaseTest {
    protected static WebDriver webDriver;

    @Before
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

    }

    @After
    public void afterMethod() {
        webDriver.quit();
    }
}
