package lesson07.pages;

import io.qameta.allure.Step;
import lesson07.BaseInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StarterPage extends BaseInit {

    @FindBy(xpath = "//a[@class='btn sign-in']")
    private WebElement logInButton;

    public StarterPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Click log in button")
    public LoginPage clickLogIn() {
        wait30sec.until(ExpectedConditions.visibilityOf(logInButton));
        logInButton.click();
        return new LoginPage(driver);
    }

}
