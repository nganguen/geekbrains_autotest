package lesson06.pages;

import lesson06.BaseInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseInit {

    @FindBy(id = "user_email")
    private WebElement email;

    @FindBy(id = "user_password")
    private WebElement password;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setEmail(String strEmail) {
        wait10sec.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys(strEmail);
        return this;
    }

    public LoginPage setPassword(String strPass) {
        password.sendKeys(strPass);
        return this;
    }

    public HomePage submit() {
        password.submit();
        return new HomePage(driver);
    }

    public HomePage authorizeAs(String strEmail, String strPass) {
        setEmail(strEmail);
        setPassword(strPass);
        submit();
        return new HomePage(driver);
    }
}
