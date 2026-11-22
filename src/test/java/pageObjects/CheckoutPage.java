package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    By guestCheckout = By.xpath("//input[@name='account' and @value='guest']");
    By continueAccount = By.id("button-account");

    By firstName = By.id("input-payment-firstname");
    By lastName = By.id("input-payment-lastname");
    By email = By.id("input-payment-email");
    By telephone = By.id("input-payment-telephone");
    By address = By.id("input-payment-address-1");
    By city = By.id("input-payment-city");
    By postCode = By.id("input-payment-postcode");

    By country = By.id("input-payment-country");
    By region = By.id("input-payment-zone");

    By continueGuest = By.id("button-guest");
    By continueShipping = By.id("button-shipping-method");
    By agreeCheckbox = By.name("agree");
    By continuePayment = By.id("button-payment-method");
    By confirmOrder = By.id("button-confirm");

    By orderSuccessMessage = By.xpath("//div[@id='content']/h1");

    // Select guest checkout
    public void selectGuestCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckout)).click();
        driver.findElement(continueAccount).click();
    }

    // Enter billing details
    public void enterBillingDetails() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName))
                .sendKeys("Kiran");

        driver.findElement(lastName).sendKeys("Kumar");
        driver.findElement(email).sendKeys("kiran.demo@example.com");
        driver.findElement(telephone).sendKeys("9876543210");
        driver.findElement(address).sendKeys("Demo Street");
        driver.findElement(city).sendKeys("Bangalore");
        driver.findElement(postCode).sendKeys("560001");

        Select countrySelect = new Select(driver.findElement(country));
        countrySelect.selectByVisibleText("India");

        wait.until(driver -> {
            Select regionSelect = new Select(driver.findElement(region));
            return regionSelect.getOptions().size() > 1;
        });

        Select regionSelect = new Select(driver.findElement(region));
        regionSelect.selectByVisibleText("Karnataka");
    }

    // Continue billing details
    public void clickContinueGuest() {
        wait.until(ExpectedConditions.elementToBeClickable(continueGuest)).click();
    }

    // Continue delivery method
    public void clickContinueShipping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShipping)).click();
    }

    // Agree to terms and continue payment
    public void clickContinuePayment() {

        wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));

        if (!driver.findElement(agreeCheckbox).isSelected()) {
            driver.findElement(agreeCheckbox).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(continuePayment)).click();
    }

    // Confirm order
    public void clickConfirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrder)).click();
    }

    // Get order confirmation message
    public String getOrderSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage))
                .getText();
    }
}