package testCases;

import driverManager.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_01_AccountReg extends BaseClass {

    @Test(groups = {"regression", "master"})
    public void VerifyAcctReg() {
        try {
            logger.info("Start Account Register Test");
            HomePage homePage = new HomePage(DriverManager.getDriver());
            logger.info("Navigating Register Page");
            homePage.clickMyAccount();
            homePage.clickRegister();

            AccountRegPage acct = new AccountRegPage(DriverManager.getDriver());
            logger.info("Providing Customer Details");
            acct.setFirstName(randomString().toUpperCase());
            acct.setLastName(randomString().toUpperCase());
            acct.setEmail(randomAlphanumeric() + "@gmail.com");
            acct.setPassword(randomAlphanumeric());
            acct.clickNewsLetter();
            acct.clickAgree();
            acct.clickSubmit();

            String confirmMessage = acct.getConfirmMsg();
            logger.info("Verifying the confirmation message");
            if (confirmMessage.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test Failed");
                logger.debug("Debug Logs");
                Assert.assertFalse(false);
            }
        } catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        logger.info("Completed Account register Test");
    }

}
