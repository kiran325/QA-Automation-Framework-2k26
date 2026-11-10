package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement pwdConfirm;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement radioNewsletter;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String Telephone) {
		txtTelephone.sendKeys(Telephone);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setpwdConfirm(String pwd) {
		pwdConfirm.sendKeys(pwd);
	}
	public void setNewsletter() {
		radioNewsletter.click();
	}
	public void setAgree() {
		checkAgree.click();
	}
	public void setContinue() {
		btnContinue.click();
		
		// btnContinue.submit();
		
		// Actions act = new Actions(driver); act.moveToElement(btnContinue).click().perform();
		
		// JavascriptExecutor js = (JavascriptExecutor) driver;  js.executeScript("arguments[0].click();", btnContinue);
		
		// btnContinue.sendKeys(Keys.RETURN);
		
		// WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}
		catch(Exception e) {
		return (e.getMessage());
		}
  }
}