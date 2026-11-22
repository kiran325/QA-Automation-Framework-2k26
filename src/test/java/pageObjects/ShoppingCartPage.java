package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By cartButton = By.id("cart");
    By viewCartLink = By.xpath("//strong[text()='View Cart']");
    By cartHeading = By.xpath("//div[@id='content']/h1");
    By cartProduct = By.xpath("//div[@class='table-responsive']//td[@class='text-left']/a");
    By quantityBox = By.name("quantity");
    By updateButton = By.cssSelector("button[data-original-title='Update']");
    By checkoutButton = By.xpath("//a[contains(@href,'route=checkout/checkout')]");

    // Open cart dropdown and click View Cart
    public void openShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    }

    // Verify shopping cart page
    public boolean isCartPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeading))
                .getText().contains("Shopping Cart");
    }

    // Get product name from cart
    public String getCartProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartProduct))
                .getText();
    }

    // Update product quantity
    public void updateQuantity(String quantity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityBox))
                .clear();

        driver.findElement(quantityBox).sendKeys(quantity);
        driver.findElement(updateButton).click();
    }

    // Click checkout
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}