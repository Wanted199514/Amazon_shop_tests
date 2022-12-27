package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;

public class BaseTest {
    private WebDriver webDriver;
    MainPage mainPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver","C:\\Windows\\System32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
}
