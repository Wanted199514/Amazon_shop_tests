package cucumberTests;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;

public class MainTest {
    MainPage mainPage = BaseTest.mainPage;
    SignInPage signInPage;
    SearchPage searchPage;
    NewItemPage newItemPage;
    CartPage cartPage;
    ReturnAndOrdersPage returnAndOrdersPage;
    @When("Go to Amazon page")
    public void go_to_amazon_page() {
        mainPage.openMainPage();
    }

    @Then("Amazon main page opened successfully")
    public void amazon_main_page_opened_successfully() {
        Assert.assertEquals(mainPage.mainPageIsLoaded(), mainPage.getMainPageURL(), "Main Page is not loaded.");
        mainPage.waitSignInButton();
    }

    @When("Click on 'Hello, sign in Account & Lists'")
    public void click_on_hello_sign_in_account_and_lists() {
        signInPage = mainPage.clickOnSignInButton();
    }

    @Then("Sign in page opened successfully")
    public void sign_in_page_opened_successfully() {
        Assert.assertTrue(signInPage.signInPageURLIsLoaded(), "Sign In page is not loaded.");
    }

    @When("Input into field 'Email or mobile phone number' correct email")
    public void input_into_field_correct_email() {
        signInPage.inputEmail(signInPage.email);
    }

    @Then("Click on 'Continue' button")
    public void click_on_continue_button() {
        signInPage.clickOnContinueButton();
    }

    @When("Input into field 'Password' correct password")
    public void input_into_field_correct_password() {
        signInPage.inputPassword(signInPage.password);
    }
    @When("Input into field 'Email or mobile phone number' email {string}")
    public void input_into_field_email_or_mobile_phone_number_correct_email(String email) {
        signInPage.inputEmail(email);
    }
    @When("Input into field 'Password' password {string}")
    public void input_into_field_password_correct_password(String password) {
        signInPage.inputPassword(password);
    }
    @Then("Click on 'Sign in' button")
    public void click_on_sign_in_button() {
        signInPage.clickOnSignInButton();
    }

    @And("Amazon main page with authorized user opened successfully")
    public void amazon_main_page_with_authorized_user_opened_successfully() {
        Assert.assertEquals(mainPage.getMainPageAuthorized(), true, "Main Page authorized is not loaded.");
        Assert.assertEquals(mainPage.getHelloText(), "Hello, Vova", "User is not authorized.");
    }
    @And("Error message appeared successfully with text {string}")
    public void error_message_appeared_successfully_with_text(String text) {
        Assert.assertTrue(signInPage.errorMessageIsDisplayed(),"Error message is not displayed.");
        Assert.assertEquals(signInPage.errorMessageGetText(),text,"Error message is not corrected.");
    }
    @When("Input into 'Search' field {string} word")
    public void input_into_search_field_word_and_click_enter_button(String text) {
        mainPage.waitSearchField();
        mainPage.inputSearchData(text);

    }

    @Then("Click 'ENTER' button in search field")
    public void click_enter_button() {
        searchPage = mainPage.searchFieldClickOnENTER();
    }
    @And("Search page with correct results opened successfully")
    public void search_page_with_correct_results_opened_successfully() {
        Assert.assertEquals(searchPage.searchPageIsLoaded(),searchPage.getSearchPageURL(),"Books search page is not loaded.");
    }

    @When("Click on first product")
    public void click_on_first_product() {
        searchPage.scrollToProduct();
        searchPage.waitSecondProduct();
        searchPage.clickOnFirstProduct();
    }
    @Then("Correct product page opened successfully")
    public void correct_product_page_opened_successfully() {
        searchPage.waitAddToCartButton();
    }

    @And("Remember first item price")
    public void save_first_item_price() {
        searchPage.saveFirstItemPrice();
    }

    @And("Remember second item price")
    public void save_second_item_price() {
        searchPage.saveSecondItemPrice();
    }
    @When("Click on 'Add to Cart' button")
    public void click_on_add_to_cart_button() {
        newItemPage = searchPage.clickOnAddToCart();
    }
    @Then("Product added to cart successfully")
    public void product_added_to_cart_successfully() {
        Assert.assertTrue(newItemPage.newItemPageIsLoaded(),"New Item Page is not loaded.");
        Assert.assertEquals(newItemPage.itemPriceNumber(),searchPage.returnFirstSavedItemPrice(),"Item price is not corrected.");
    }
    @Then("Products added to cart successfully")
    public void products_added_to_cart_successfully() {
        Assert.assertTrue(newItemPage.newItemPageIsLoaded(),"New Item Page is not loaded.");
        Assert.assertEquals(newItemPage.itemPriceNumber(),searchPage.cutOffExtraNumbers(searchPage.returnFirstSavedItemPrice()+searchPage.returnSecondSavedItemPrice()),"Item price is not corrected.");
    }
    @When("Click on 'Go to Cart' button")
    public void click_on_go_to_cart_button() {
        cartPage = newItemPage.clickOnGoToCartButton();
    }
    @Then("Cart with {string} products opened successfully and {string} text is correct")
    public void cart_with_products_opened_successfully(String number, String text) {
        cartPage.waitSubtotalItemsTextNumber();
        Assert.assertEquals(cartPage.cartPageIsLoaded(),cartPage.getCartPageURL(),"Cart Page is not loaded.");
        Assert.assertEquals(cartPage.getSubtotalItemsTextNumber(),text,"Subtotals items is not correct");
        Assert.assertEquals(cartPage.shoppingCartSize(),Integer.parseInt(number),"The number of hopping cart is incorrect.");
    }
    @And("Check total price for one product")
    public void check_total_price_for_one_product() {
        Assert.assertEquals(cartPage.fullItemPriceNumber(),searchPage.returnFirstSavedItemPrice(),"Full item price is not corrected.");
    }
    @And("Check total price for two products")
    public void check_total_price_for_two_products() {
        Assert.assertEquals(cartPage.fullItemPriceNumber(),searchPage.cutOffExtraNumbers(searchPage.returnFirstSavedItemPrice()+searchPage.returnSecondSavedItemPrice()),"Full item price is not corrected.");
    }
    @When("Click on 'Delete' button")
    public void click_on_delete_button() {
        cartPage.clickOnDeleteButton();
    }
    @When("Click on 'Return & Orders' page")
    public void click_on_return_and_orders_page() {
        returnAndOrdersPage = mainPage.clickOnReturnAndOrdersButton();
    }
    @Then("'Return & Orders' page opened successfully")
    public void return_and_order_page_opened_successfully() {
        Assert.assertEquals(returnAndOrdersPage.returnAndOrdersPageIsLoaded(),returnAndOrdersPage.getReturnAndOrdersURL(),"Return & Orders page is not loaded.");
    }
    @Then("Search hint {string} opened successfully")
    public void search_hint_opened_successfully(String text) {
        mainPage.waitSearchHint();
        Assert.assertEquals(mainPage.searchHintListSize(),10,"The number of search hints is not correct.");
        Assert.assertEquals(mainPage.searchHintListText(text),mainPage.searchHintListIsCorrected(text),"Search hit data is not corrected.");
    }
    @And("Check navigation shop links is {string}")
    public void check_navigation_shop_links(String number) {
        Assert.assertEquals(mainPage.navigationLinksSize(), Integer.parseInt(number),"The number of navigation links is not corrected.");
    }
    @Then("Navigation shop links {string} is corrected")
    public void navigation_shop_links_is_corrected(String number) {
        mainPage.waitNavigationMenu();
        mainPage.saveNavigationLinks();
        for (int i = 0; i < Integer.parseInt(number); i++) {
            mainPage.openNavigationURL(i);
            Assert.assertEquals(mainPage.getTitle(),mainPage.navigationTitles().get(i),"Navigation link is not corrected.");
        }
    }
    @When("Back to past page")
    public void back_to_past_page() {
        newItemPage.goBackToPastPage();
    }
    @When("Click on second product")
    public void click_on_second_product() {
        searchPage.scrollToProduct();
        searchPage.waitSecondProduct();
        searchPage.clickOnSecondProduct();
    }
}
