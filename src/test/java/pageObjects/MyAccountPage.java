package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//h2[text()='My Account']")
    private WebElement msgHeading;

    /**
     * ✅ Checks whether the "My Account" heading is displayed
     * to confirm successful login.
     */
    public boolean isMyAccountPageExists() {
        try {
            return msgHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public void clickLogout() {
		// TODO Auto-generated method stub
		
	}
}







//@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='My Account']") @CacheLookup private WebElement myAccount;