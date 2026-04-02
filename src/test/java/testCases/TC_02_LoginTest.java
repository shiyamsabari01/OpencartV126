package testCases;

import driverManager.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAcctPage;
import testBase.BaseClass;
import utilities.Constants;

public class TC_02_LoginTest extends BaseClass {

    @Test(groups = {"sanity","master"})
    public void verifyLoginTest() {
        try {
            logger.info("Started Login Page Test");
            HomePage homePage = new HomePage(DriverManager.getDriver());
            homePage.clickMyAccount();
            logger.info("Navigated to Login Page");
            homePage.clickLogin();
            logger.info("Provide valid Username and Password");
            LoginPage lp = new LoginPage(DriverManager.getDriver());
            lp.setUsername(Constants.USERNAME);
            lp.setPassword(Constants.PASSWORD);
            lp.clickLoginBtn();
            logger.info("Verify the My Account Page...Heading");
            MyAcctPage acctPage = new MyAcctPage(DriverManager.getDriver());
            boolean acctPageMsg = acctPage.myAccountPage();
            Assert.assertTrue(acctPageMsg);
            logger.info("Completed Login Page Test");
        } catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

    }
}
