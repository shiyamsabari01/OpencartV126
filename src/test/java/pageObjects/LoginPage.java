package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "input-email")
    private static WebElement UserName;

    @FindBy(id = "input-password")
    private static WebElement Password;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    private static WebElement LoginBtn;

    public void setUsername(String userName){
        UserName.sendKeys(userName);
    }

    public void setPassword(String passcode){
        Password.sendKeys(passcode);
    }

    public void clickLoginBtn(){
        javaScriptExecutor(LoginBtn);

    }
}
