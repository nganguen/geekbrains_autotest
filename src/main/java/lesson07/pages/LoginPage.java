package lesson07.pages;

import io.qameta.allure.Step;
import lesson07.BaseInit;
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

    @Step(value = "Set email field {strEmail}")
    public LoginPage setEmail(String strEmail) {
        wait30sec.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys(strEmail);
        return this;
    }

    @Step(value = "Set password {strPass}")
    public LoginPage setPassword(String strPass) {
        password.sendKeys(strPass);
        return this;
    }

    @Step(value = "Click log in button")
    public HomePage submit() {
        password.submit();
        return new HomePage(driver);
    }

    @Step(value = "Authorize as {strEmail}, {strPass}")
    public HomePage authorizeAs(String strEmail, String strPass) {
        setEmail(strEmail);
        setPassword(strPass);
        submit();
        return new HomePage(driver);
    }
}
