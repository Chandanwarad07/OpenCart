package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC0002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verfiy_login() {

        logger.info("******* Starting Login Test *******");

        // Home Page
        HomePage hp = new HomePage(getDriver());
        hp.clickMyAccount();
        hp.clickLogin();

        // Login Page
        LoginPage lp = new LoginPage(getDriver());
        lp.setEmail(p.getProperty("email"));
        logger.info("Enter email: " + p.getProperty("email"));

        lp.setPassword(p.getProperty("password"));
        logger.info("Enter password");

        lp.clickLogin();
        logger.info("Clicked on Login button");

        // My Account
        MyAccountPage macc = new MyAccountPage(getDriver());
        boolean targetPage = macc.isMyAccountPageExists();

        Assert.assertTrue(targetPage, "❌ Login verification failed! My Account page not displayed.");
        logger.info("✅ Login Test Passed Successfully!");
    }
}

	

