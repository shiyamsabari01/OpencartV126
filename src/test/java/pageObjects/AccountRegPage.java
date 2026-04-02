package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegPage extends BasePage {

    public AccountRegPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private static WebElement firstName;

    @FindBy(id = "input-lastname")
    private static WebElement lastName;

    @FindBy(id = "input-email")
    private static WebElement email;

    @FindBy(id = "input-password")
    private static WebElement password;

    @FindBy(xpath = "(//input[@name='newsletter'])[2]")
    private static WebElement newsLetter;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    private static WebElement agreeBtn;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    private static WebElement submitBtn;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private static WebElement msgConfirmation;

    public void setFirstName(String fname){
        firstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        lastName.sendKeys(lname);
    }

    public void setEmail(String emails){
        email.sendKeys(emails);
    }
    public void setPassword(String pwd){
        password.sendKeys(pwd);
    }

    public void clickNewsLetter(){
        if (!newsLetter.isSelected()){
            javaScriptExecutor(newsLetter);
        }
    }

    public void clickAgree(){
       if (!agreeBtn.isSelected()){
           javaScriptExecutor(agreeBtn);
       }
    }
    public void clickSubmit(){
        javaScriptExecutor(submitBtn);
    }

    public String getConfirmMsg(){
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return(e.getMessage());
        }
    }
}
