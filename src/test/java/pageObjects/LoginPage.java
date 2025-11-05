package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // --- Locators ---
    @FindBy(id = "input-email")
    WebElement txtEmailAddress;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    // --- Actions ---
    public void setEmail(String email) {
        txtEmailAddress.clear(); // ✅ clears old text if any
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String password) {
        txtPassword.clear(); // ✅ clears old text if any
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
    }
}




//@FindBy(xpath="//input[@id='input-password']") @CacheLookup private WebElement password;
//@FindBy(xpath="//input[@value='Login']") @CacheLookup private WebElement login;
//@FindBy(xpath="//input[@id='input-email']") @CacheLookup private WebElement e-MailAddress;
