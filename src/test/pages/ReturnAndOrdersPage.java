package pages;

import org.openqa.selenium.WebDriver;

public class ReturnAndOrdersPage extends BasePage {

    String returnAndOrdersURL = "https://www.amazon.com/gp/css/order-history?ref_=nav_orders_first";

    public ReturnAndOrdersPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public String returnAndOrdersPageIsLoaded() {
        return webDriver.getCurrentUrl();
    }

    public String getReturnAndOrdersURL() {
        return returnAndOrdersURL;
    }
}
