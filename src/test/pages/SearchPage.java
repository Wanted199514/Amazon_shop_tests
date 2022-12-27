package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    String searchPageURL = "https://www.amazon.com/b?node=283155";

    double firstItemPriceSaved;
    double secondItemPriceSaved;

    @FindBy(xpath = "//div[@id='p13n-asin-index-1']/div/div/img")
    private WebElement firstProduct;

    @FindBy(xpath = "//*[@id='p13n-asin-index-7']/div/div[1]/img")
    private WebElement secondProduct;

    @FindBy(xpath = "//input[@name='submit.addToCart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a/div[@class='a-row']/span/span")
    private WebElement itemPrice;

    public SearchPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public String searchPageIsLoaded() {
        return webDriver.getCurrentUrl();
    }

    public String getSearchPageURL() {
        return searchPageURL;
    }

    public void waitSecondProduct() {
        waitUntilElementIsAppear(secondProduct);
        secondProduct.isEnabled();
    }

    public void scrollToProduct() {
        scrollDown(firstProduct);
    }

    public void clickOnFirstProduct() {
        firstProduct.click();
    }

    public void clickOnSecondProduct() {
        secondProduct.click();
    }

    public void waitAddToCartButton() {
        waitUntilElementIsAppear(addToCartButton);
        addToCartButton.isEnabled();
    }

    public NewItemPage clickOnAddToCart() {
        addToCartButton.click();
        return new NewItemPage(webDriver);
    }

    private String itemPriceText() {
        return itemPrice.getText().substring(1);
    }

    private double itemPriceNumber() {
        return Double.parseDouble(itemPriceText());
    }

    public void saveFirstItemPrice() {
        firstItemPriceSaved = itemPriceNumber();
    }

    public void saveSecondItemPrice() {
        secondItemPriceSaved = itemPriceNumber();
    }

    public double returnFirstSavedItemPrice() {
        return firstItemPriceSaved;
    }

    public double returnSecondSavedItemPrice() {
        return secondItemPriceSaved;
    }

    public double cutOffExtraNumbers(double number) {
        return Double.parseDouble(String.valueOf(number).substring(0,5));
    }
}
