package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import pageObjects.CheckoutPage;
import testBase.BaseClass;

public class TC006_EndtoEndTest extends BaseClass {

    @Test
    public void verifyEndToEndPurchaseFlow() {

        logger.info("Starting TC006 - End To End Purchase Flow");

        SearchPage sp = new SearchPage(driver);
        ShoppingCartPage sc = new ShoppingCartPage(driver);
        CheckoutPage cp = new CheckoutPage(driver);

        // Step 1: Search product
        sp.searchProduct("MacBook");

        Assert.assertTrue(
                sp.isProductDisplayed(),
                "Product is not displayed in search results");

        // Step 2: Select product
        sp.selectProduct("MacBook");

        // Step 3: Add product to cart
        sp.clickAddToCart();

        // Step 4: Open shopping cart
        sc.openShoppingCart();

        Assert.assertTrue(
                sc.isCartPageDisplayed(),
                "Shopping cart page is not displayed");

        // Step 5: Verify cart product
        Assert.assertEquals(
                sc.getCartProductName(),
                "MacBook",
                "Incorrect product in cart");

        // Step 6: Proceed to checkout
        sc.clickCheckout();

        // Step 7: Select guest checkout
        cp.selectGuestCheckout();

        // Step 8: Enter billing details
        cp.enterBillingDetails();

        cp.clickContinueGuest();

        // Step 9: Continue shipping method
        cp.clickContinueShipping();

        // Step 10: Agree to terms and continue payment
        cp.clickContinuePayment();

        // Step 11: Confirm order
        cp.clickConfirmOrder();

        // Step 12: Verify order confirmation
        Assert.assertTrue(
                cp.getOrderSuccessMessage().contains("Your order has been placed!"),
                "Order confirmation message is not displayed");

        logger.info("End-to-end purchase completed successfully");
    }
}