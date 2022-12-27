package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MainPage extends BasePage {
    String mainPageURL = "https://www.amazon.com/";
    String mainPageAuthorized = "signin";
    String searchHitResultExpected = "ram corsair";

    Hashtable<Integer, String> savedNavigationLinksTable = new Hashtable<>();

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(xpath = "//a[@data-csa-c-content-id='nav_ya_signin']")
    private WebElement signInButton;

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement helloText;

    @FindBy(id = "nav-orders")
    private WebElement returnAndOrdersButton;

    @FindBy(xpath = "//div[@class='s-suggestion-container']")
    private List<WebElement> searchHintList;

    @FindBy(xpath = "//span[@class='s-heavy']")
    private WebElement searchHint;

    @FindBy(xpath = "//div[@id='nav-xshop']/a[contains(@class,'nav')]")
    private List<WebElement> navigationList;

    @FindBy(id = "nav-xshop")
    private WebElement navigationMenu;

    public MainPage(WebDriver webDriver) {
        initWebElements(webDriver);
    }

    public void openMainPage() {
        webDriver.get(mainPageURL);
    }

    public String getMainPageURL() {
        return mainPageURL;
    }

    public String mainPageIsLoaded() {
        return webDriver.getCurrentUrl();
    }

    public boolean getMainPageAuthorized() {
        return webDriver.getCurrentUrl().contains(mainPageAuthorized);
    }

    public void waitSignInButton() {
        waitUntilElementIsAppear(signInButton);
    }

    public SignInPage clickOnSignInButton() {
        signInButton.click();
        return new SignInPage(webDriver);
    }

    public String getHelloText() {
        return helloText.getText();
    }

    public void waitSearchField() {
        waitUntilElementIsAppear(searchField);
    }

    public void inputSearchData(String searchData) {
        searchField.sendKeys(searchData);
    }

    public SearchPage searchFieldClickOnENTER() {
        searchField.sendKeys(Keys.ENTER);
        return new SearchPage(webDriver);
    }

    public ReturnAndOrdersPage clickOnReturnAndOrdersButton() {
        returnAndOrdersButton.click();
        return new ReturnAndOrdersPage(webDriver);
    }

    public void waitSearchHint() {
        waitUntilElementIsAppear(searchHint);
    }

    public int searchHintListSize() {
        return searchHintList.size();
    }

    public List<String> searchHintListText(String searchHitResultExpected) {
        List<String> searchHintCollection = new ArrayList<>();
        for (WebElement currentHint:
                        searchHintList) {
            searchHintCollection.add(currentHint.getText().substring(0,searchHitResultExpected.length()));
        }
        return searchHintCollection;
    }

    public List<String> searchHintListIsCorrected(String searchHitResultExpected) {
        List<String> searchHintCollection = new ArrayList<>();
        for (int i = 0; i < searchHintListSize(); i++) {
            searchHintCollection.add(searchHitResultExpected);
        }
        return searchHintCollection;
    }

    private Hashtable<Integer, String> navigationLinks() {
        Hashtable<Integer, String> navigationListDictionary = new Hashtable<>();
        for (int i = 0; i < navigationList.size(); i++) {
            navigationListDictionary.put(i,navigationList.get(i).getAttribute("href"));
        }
        return navigationListDictionary;
    }

    public void saveNavigationLinks() {
        savedNavigationLinksTable = navigationLinks();
    }

    public void waitNavigationMenu() {
        waitUntilElementIsAppear(navigationMenu);
    }

    public void openNavigationURL(int number) {

        webDriver.get(savedNavigationLinksTable.get(number));
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public int navigationLinksSize() {
        return navigationList.size();
    }

    public Hashtable<Integer, String> navigationTitles() {
        Hashtable<Integer, String> navigationTitlesDictionary = new Hashtable<>();
        navigationTitlesDictionary.put(0,"Amazon.com - Today's Deals");
        navigationTitlesDictionary.put(1,"Amazon Sign-In");
        navigationTitlesDictionary.put(2,"Holiday Gift Guide | Amazon.com Gift Finder");
        navigationTitlesDictionary.put(3,"Amazon.com Best Sellers: The most popular items on Amazon");
        navigationTitlesDictionary.put(4,"Amazon.com: Amazon Basics");
        navigationTitlesDictionary.put(5,"Amazon Customer Service Support – Amazon.com");
        navigationTitlesDictionary.put(6,"Amazon.com: Amazon Prime");
        navigationTitlesDictionary.put(7,"Amazon.com New Releases: The best-selling new & future releases on Amazon");
        navigationTitlesDictionary.put(8,"Amazon.com: Books");
        navigationTitlesDictionary.put(9,"Amazon Music Unlimited | 90 million songs ad-free\u200E");
        navigationTitlesDictionary.put(10,"Amazon Registry & Gifting");
        navigationTitlesDictionary.put(11,"Amazon Pharmacy: Save time, save money, stay healthy");
        navigationTitlesDictionary.put(12,"Shop Amazon Home Products");
        navigationTitlesDictionary.put(13,"Amazon.com: Clothing, Shoes & Jewelry");
        navigationTitlesDictionary.put(14,"Amazon.com Gift Cards");
        navigationTitlesDictionary.put(15,"Amazon.com: Toys & Games");
        navigationTitlesDictionary.put(16,"");
        navigationTitlesDictionary.put(17,"Amazon.com: Sell Products Online with Selling on Amazon.");
        navigationTitlesDictionary.put(18,"Amazon Coupons @ Amazon.com");
        navigationTitlesDictionary.put(19,"Automotive Parts & Accessories - Amazon.com");
        navigationTitlesDictionary.put(20,"Amazon.com: Computers & Accessories: Electronics: Computer Accessories & Peripherals, Tablet Accessories & More");
        navigationTitlesDictionary.put(21,"Tools and home improvement");
        navigationTitlesDictionary.put(22,"Amazon.com: Beauty & Personal Care");
        navigationTitlesDictionary.put(23,"Amazon.com: Video Games");
        navigationTitlesDictionary.put(24,"Amazon.com: Luxury Stores");
        navigationTitlesDictionary.put(25,"Amazon.com: Health & Household");
        navigationTitlesDictionary.put(26,"Amazon.com: Pet Supplies");
        navigationTitlesDictionary.put(27,"Smart Home Devices & Systems");
        navigationTitlesDictionary.put(28,"Amazon.com: Audible Books & Originals");
        navigationTitlesDictionary.put(29,"Amazon Handmade | Shop unique, handcrafted gifts, jewelry & more.");
        return navigationTitlesDictionary;
    }
}
