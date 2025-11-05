package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    void verify_account_registration() {
        logger.info("******** Starting registration test ********");

        try {
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            logger.info("Clicked on My Account link");

            hp.clickRegister();
            logger.info("Clicked on Register link");

            AccountRegistrationPage arp = new AccountRegistrationPage(getDriver());
            logger.info("Entering first name");
            arp.setfirstname("John");

            logger.info("Entering last name");
            arp.setlastname("David");

            String email = "john" + System.currentTimeMillis() + "@gmail.com";
            arp.setemail(email);
            logger.debug("Generated random email: " + email);

            logger.info("Entering phone number");
            arp.settelephone("2345678891");

            logger.info("Entering password");
            arp.setpassword("Xyz1234");
            arp.setconfirm("Xyz1234");

            arp.setcheckPrivacyPolicy();
            arp.clickContinue();

            logger.info("Validating confirmation message...");
            String confMsg = arp.getConfirmationMsg();

            Assert.assertEquals(confMsg, "Your Account Has Been Created!", 
                "❌ Account registration confirmation message mismatch!");

            logger.info("✅ Account registration test passed successfully!");

        } catch (Exception e) {
            logger.error("❌ Exception occurred during account registration", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("******** Finished TC001_AccountRegistrationTest ********");
    }
}



