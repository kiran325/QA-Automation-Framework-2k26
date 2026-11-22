package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC005_AddToCartPageTest extends BaseClass {

    @Test
    public void verifyAddProductToCart() {

        logger.info("Starting TC005 - Add Product To Cart");

        SearchPage sp = new SearchPage(driver);
        ShoppingCartPage sc = new ShoppingCartPage(driver);

        sp.searchProduct("MacBook");
        sp.selectProduct("MacBook");
        sp.clickAddToCart();

        logger.info("Product added to cart");

        sc.openShoppingCart();

        Assert.assertTrue(
                sc.isCartPageDisplayed(),
                "Shopping cart page is not displayed");

        Assert.assertEquals(
                sc.getCartProductName(),
                "MacBook",
                "Product name does not match");

        logger.info("Product successfully verified in shopping cart");
    }
}