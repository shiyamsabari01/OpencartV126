package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAcctPage extends BasePage{

    public MyAcctPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    private static WebElement msgHeading;

    public boolean myAccountPage(){
        try {
            return (msgHeading.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }
}
