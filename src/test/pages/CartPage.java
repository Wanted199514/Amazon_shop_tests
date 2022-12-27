package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    String cartPageURL = "https://www.amazon.com/gp/cart/view.html?ref_=sw_gtc";

    @FindBy(id = "sc-subtotal-label-activecart")
    private WebElement subtotalItemsTextNumber;

    @FindBy(xpath = "//input[contains(@name,'submit.delete.C')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@class='sc-list-item-content']")
    private List<WebElement> shoppingCartList;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']/span")
    private WebElement fullItemPrice;

    public CartPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public String getSubtotalItemsTextNumber() {
        return subtotalItemsTextNumber.getText();
    }

    public String cartPageIsLoaded() {
        return webDriver.getCurrentUrl();
    }

    public String getCartPageURL() {
        return cartPageURL;
    }

    public void clickOnDeleteButton() {
        deleteButton.click();
    }

    public void waitSubtotalItemsTextNumber() {
        waitUntilElementIsAppear(subtotalItemsTextNumber);
    }

    public int shoppingCartSize() {
        return shoppingCartList.size();
    }

    private String fullItemPriceText() {
        return fullItemPrice.getText().substring(1);
    }

    public double fullItemPriceNumber() {
        return Double.parseDouble(fullItemPriceText());
    }
}
