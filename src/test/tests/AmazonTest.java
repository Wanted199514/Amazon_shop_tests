package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AmazonTest extends BaseTest {

    /**
     * TC001
     */
    @Test()
    public void login() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSignInButton();
        SignInPage signInPage = mainPage.clickOnSignInButton();
        Assert.assertTrue(signInPage.signInPageURLIsLoaded(),"Sign In page is not loaded.");
        signInPage.inputEmail(signInPage.email);
        signInPage.clickOnContinueButton();
        signInPage.inputPassword(signInPage.password);
        signInPage.clickOnSignInButton();
        Assert.assertEquals(mainPage.getMainPageAuthorized(),true,"Main Page authorized is not loaded.");
        Assert.assertEquals(mainPage.getHelloText(),"Hello, Vova", "User is not authorized.");
    }

    /**
     * TC002
     */
    @Test()
    public void searchTest() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSearchField();
        mainPage.inputSearchData("Books");
        SearchPage searchPage = mainPage.searchFieldClickOnENTER();
        Assert.assertEquals(searchPage.searchPageIsLoaded(),searchPage.getSearchPageURL(),"Books search page is not loaded.");
    }

    /**
     * TC003, TC004
     */
    @Test()
    public void addToCartAndDeleteProductFromCart() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSearchField();
        mainPage.inputSearchData("Books");
        SearchPage searchPage = mainPage.searchFieldClickOnENTER();
        Assert.assertEquals(searchPage.searchPageIsLoaded(),searchPage.getSearchPageURL(),"Books search page is not loaded.");
        searchPage.scrollToProduct();
        searchPage.waitSecondProduct();
        searchPage.clickOnFirstProduct();
        searchPage.waitAddToCartButton();
        searchPage.saveFirstItemPrice();
        NewItemPage newItemPage = searchPage.clickOnAddToCart();
        Assert.assertTrue(newItemPage.newItemPageIsLoaded(),"New Item Page is not loaded.");
        Assert.assertEquals(newItemPage.itemPriceNumber(),searchPage.returnFirstSavedItemPrice(),"Item price is not corrected.");
        CartPage cartPage = newItemPage.clickOnGoToCartButton();
        Assert.assertEquals(cartPage.cartPageIsLoaded(),cartPage.getCartPageURL(),"Cart Page is not loaded.");
        Assert.assertEquals(cartPage.getSubtotalItemsTextNumber(),"Subtotal (1 item):","Subtotals items is not correct");
        Assert.assertEquals(cartPage.shoppingCartSize(),1,"Shopping cart is empty.");
        Assert.assertEquals(cartPage.fullItemPriceNumber(),searchPage.returnFirstSavedItemPrice(),"Full item price is not corrected.");
        cartPage.clickOnDeleteButton();
        cartPage.waitSubtotalItemsTextNumber();
        Assert.assertEquals(cartPage.getSubtotalItemsTextNumber(),"Subtotal (0 items):","Subtotals items is not correct");
        Assert.assertEquals(cartPage.shoppingCartSize(),0,"Shopping cart is not empty.");
    }

    /**
     * TC005
     */
    @Test()
    public void returnAndOrdersPage() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSignInButton();
        SignInPage signInPage = mainPage.clickOnSignInButton();
        Assert.assertTrue(signInPage.signInPageURLIsLoaded(),"Sign In page is not loaded.");
        signInPage.inputEmail(signInPage.email);
        signInPage.clickOnContinueButton();
        signInPage.inputPassword(signInPage.password);
        signInPage.clickOnSignInButton();
        Assert.assertEquals(mainPage.getHelloText(),"Hello, Vova", "User is not authorized.");
        ReturnAndOrdersPage returnAndOrdersPage = mainPage.clickOnReturnAndOrdersButton();
        Assert.assertEquals(returnAndOrdersPage.returnAndOrdersPageIsLoaded(),returnAndOrdersPage.getReturnAndOrdersURL(),"Return & Orders page is not loaded.");
    }

    /**
     * TC006
     */
    @Test()
    public void searchHint() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSearchField();
        mainPage.inputSearchData("Corsair");
        mainPage.waitSearchHint();
        Assert.assertEquals(mainPage.searchHintListSize(),10,"The number of search hints is not correct.");
        Assert.assertEquals(mainPage.searchHintListText("Corsair"),mainPage.searchHintListIsCorrected("corsair"),"Search hit data is not corrected.");
    }

    /**
     * TC007
     */
    int navigationLinksExpectedSize = 30;
    @Test
    public void navigationLinks() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSearchField();
        Assert.assertEquals(mainPage.navigationLinksSize(), navigationLinksExpectedSize,"The number of navigation links is not corrected.");
        mainPage.waitNavigationMenu();
        mainPage.saveNavigationLinks();
        for (int i = 0; i < navigationLinksExpectedSize; i++) {
            mainPage.openNavigationURL(i);
            Assert.assertEquals(mainPage.getTitle(),mainPage.navigationTitles().get(i),"Navigation link is not corrected.");
        }
    }

    /**
     * TC008
     */
    @Test()
    public void addToCartTwoItems() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(),mainPage.getMainPageURL(),"Main Page is not loaded.");
        mainPage.waitSearchField();
        mainPage.inputSearchData("Books");
        SearchPage searchPage = mainPage.searchFieldClickOnENTER();
        Assert.assertEquals(searchPage.searchPageIsLoaded(),searchPage.getSearchPageURL(),"Books search page is not loaded.");
        searchPage.scrollToProduct();
        searchPage.waitSecondProduct();
        searchPage.clickOnFirstProduct();
        searchPage.waitAddToCartButton();
        searchPage.saveFirstItemPrice();
        NewItemPage newItemPage = searchPage.clickOnAddToCart();
        Assert.assertTrue(newItemPage.newItemPageIsLoaded(),"New Item Page is not loaded.");
        Assert.assertEquals(newItemPage.itemPriceNumber(),searchPage.returnFirstSavedItemPrice(),"Item price is not corrected.");
        newItemPage.goBackToPastPage();
        Assert.assertEquals(searchPage.searchPageIsLoaded(),searchPage.getSearchPageURL(),"Books search page is not loaded.");
        searchPage.scrollToProduct();
        searchPage.waitSecondProduct();
        searchPage.clickOnSecondProduct();
        searchPage.waitAddToCartButton();
        searchPage.saveSecondItemPrice();
        searchPage.clickOnAddToCart();
        Assert.assertTrue(newItemPage.newItemPageIsLoaded(),"New Item Page is not loaded.");
        Assert.assertEquals(newItemPage.itemPriceNumber(),searchPage.cutOffExtraNumbers(searchPage.returnFirstSavedItemPrice()+searchPage.returnSecondSavedItemPrice()),"Item price is not corrected.");
        CartPage cartPage = newItemPage.clickOnGoToCartButton();
        Assert.assertEquals(cartPage.cartPageIsLoaded(),cartPage.getCartPageURL(),"Cart Page is not loaded.");
        Assert.assertEquals(cartPage.getSubtotalItemsTextNumber(),"Subtotal (2 items):","Subtotals items is not correct");
        Assert.assertEquals(cartPage.shoppingCartSize(),2,"Shopping cart is empty.");
        Assert.assertEquals(cartPage.fullItemPriceNumber(),searchPage.cutOffExtraNumbers(searchPage.returnFirstSavedItemPrice()+searchPage.returnSecondSavedItemPrice()),"Full item price is not corrected.");
    }
}