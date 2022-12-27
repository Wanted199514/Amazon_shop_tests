package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewItemPage extends BasePage {

    String newItemPageURL = "https://www.amazon.com/cart/smart-wagon";

    @FindBy(id = "sw-gtc")
    private WebElement goToCartButton;

    @FindBy(id = "sw-subtotal")
    private WebElement itemPrice;

    public NewItemPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public CartPage clickOnGoToCartButton() {
        goToCartButton.click();
        return new CartPage(webDriver);
    }

    public boolean newItemPageIsLoaded() {
        return webDriver.getCurrentUrl().contains(newItemPageURL);
    }

    private String itemPriceText() {
        return itemPrice.getAttribute("data-price").substring(4);
    }

    public double itemPriceNumber() {
        return Double.parseDouble(itemPriceText());
    }

    public void goBackToPastPage() {
        webDriver.navigate().back();
    }
}
