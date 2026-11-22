package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By searchBox = By.name("search");
    By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");
    By searchResults = By.cssSelector(".product-thumb");
    By addToCartButton = By.id("button-cart");
    By successMessage = By.cssSelector(".alert-success");

    // Search product
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox))
                .clear();

        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // Verify search results
    public boolean isProductDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchResults))
                .isDisplayed();
    }

    // Select product from search results
    public void selectProduct(String productName) {
        By productLink = By.xpath(
                "//div[@class='product-thumb']//a[text()='" + productName + "']");

        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
    }

    // Add product to cart from product details page
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton))
                .click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                .getText();
    }
}
