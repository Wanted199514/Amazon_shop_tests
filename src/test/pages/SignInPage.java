package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    String signInPageURL = "https://www.amazon.com/ap/signin";

    public String email = "";
    public String password = "";

    @FindBy(id = "ap_email")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(id = "ap_password")
    private WebElement passwordField;

    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    @FindBy(id = "auth-error-message-box")
    private WebElement authorizationMessageError;

    @FindBy(xpath = "//div[@id='auth-error-message-box']/div/div/ul/li/span")
    private WebElement authorizationMessageErrorText;

    public SignInPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public String getSignInPageURL() {
        return signInPageURL;
    }

    public boolean signInPageURLIsLoaded() {
        return webDriver.getCurrentUrl().contains(signInPageURL);
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public MainPage clickOnSignInButton() {
        signInButton.click();
        return new MainPage(webDriver);
    }

    public boolean errorMessageIsDisplayed() {
        return authorizationMessageError.isDisplayed();
    }

    public String errorMessageGetText() {
        return authorizationMessageErrorText.getText();
    }
}
