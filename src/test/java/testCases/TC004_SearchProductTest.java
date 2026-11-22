package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

    @Test
    public void verifySearchProduct() {

        logger.info("Starting TC004 - Search Product");

        SearchPage sp = new SearchPage(driver);

        sp.searchProduct("MacBook");

        Assert.assertTrue(
                sp.isProductDisplayed(),
                "Product search results are not displayed");

        logger.info("Product search results displayed successfully");
    }
}