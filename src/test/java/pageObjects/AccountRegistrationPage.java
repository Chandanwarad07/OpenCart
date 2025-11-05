package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement MsgConfirmation;
	
	
	public void setfirstname(String firstname) {
		txtFirstName.clear();
		txtFirstName.sendKeys(firstname);
	}
	
	public void setlastname(String lastname) {
		txtLastName.clear();
		txtLastName.sendKeys(lastname);
	}
	
	public void setemail(String email) 
	{
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	
	public void settelephone(String telephone) 
	{
		txtTelephone.clear();
		txtTelephone.sendKeys(telephone);
	}
	
	public void setpassword(String password) 
	{
		txtpassword.clear();
		txtpassword.sendKeys(password);
	}
	
	public void setconfirm(String confirm) 
	{
		txtPasswordConfirm.clear();
		txtPasswordConfirm.sendKeys(confirm);
	}
	
	
	 public void setcheckPrivacyPolicy() {
	        if (!chkAgree.isSelected()) {
	            chkAgree.click();
	        }
	 }
	 
	 public void clickContinue() {
	        btnContinue.click();
	    }
	public String getConfirmationMsg() {
		try {
			return (MsgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}	



//@FindBy(xpath="//input[@value='Continue']") @CacheLookup private WebElement continue;
//@FindBy(xpath="//input[@name='agree']") @CacheLookup private WebElement agree;
//@FindBy(xpath="//input[@value='Continue']") @CacheLookup private WebElement continue;
//	

//@FindBy(xpath="//input[@id='input-firstname']") @CacheLookup private WebElement firstName;
//@FindBy(xpath="//input[@id='input-lastname']") @CacheLookup private WebElement lastName;
//@FindBy(xpath="//input[@id='input-email']") @CacheLookup private WebElement e-Mail;
//@FindBy(xpath="//input[@id='input-telephone']") @CacheLookup private WebElement telephone;
//@FindBy(xpath="//input[@id='input-password']") @CacheLookup private WebElement password;
//@FindBy(xpath="//input[@id='input-confirm']") @CacheLookup private WebElement passwordConfirm;



//@FindBy(xpath="//input[@value='Continue']") @CacheLookup private WebElement continue;
//@FindBy(xpath="//input[@name='agree']") @CacheLookup private WebElement agree;