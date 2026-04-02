package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    private static WebElement MyAccount;

    @FindBy(xpath = "//a[text()='Register']")
    private static WebElement Register;

    @FindBy(xpath = "//a[text()='Login']")
    private static WebElement Login;

    public void clickMyAccount(){
        MyAccount.click();
    }

    public void clickRegister(){
        Register.click();
    }

    public void clickLogin(){
        Login.click();
    }
}