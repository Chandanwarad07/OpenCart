package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.Dataproviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = Dataproviders.class, groups = "DataDriven")
    public void verify_loginDDT(String email, String password, String expectedResult) {

        logger.info("***** Starting TC003_LoginDDT *****");
        logger.info("Testing login with Email: " + email + " | Password: " + password + " | Expected: " + expectedResult);

        try {
            // ✅ Home Page
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();
            logger.info("Navigated to Login page");

            // ✅ Login Page
            LoginPage lp = new LoginPage(getDriver());
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin();
            logger.info("Clicked on Login button");

            // ✅ My Account Page
            MyAccountPage macc = new MyAccountPage(getDriver());
            boolean isMyAccountDisplayed = macc.isMyAccountPageExists();

            // ✅ Validation Logic
            if (expectedResult.equalsIgnoreCase("valid")) 
            {
                if (isMyAccountDisplayed) {
                    logger.info("✅ Login passed for VALID data");
                    macc.clickLogout();
                    Assert.assertTrue(true);
                } 
                else 
                {
                    logger.error("❌ Login failed for VALID data");
                    Assert.fail("Login failed for valid credentials.");
                }
            } 
            else if (expectedResult.equalsIgnoreCase("invalid")) 
            {
                if (isMyAccountDisplayed) {
                    logger.error("❌ Login succeeded for INVALID data");
                    macc.clickLogout();
                    Assert.fail("Login should have failed for invalid credentials.");
                } 
                else 
                {
                    logger.info("✅ Login failed for INVALID data as expected");
                    Assert.assertTrue(true);
                }
            }

        } catch (Exception e) {
            logger.error("❌ Exception Occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception");
        }

        logger.info("***** Finished TC003_LoginDDT *****");
    }
}


   
