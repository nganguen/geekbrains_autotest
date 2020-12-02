package lesson06.pages;

import lesson06.BaseInit;
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

    public LoginPage clickLogIn() {
        wait10sec.until(ExpectedConditions.visibilityOf(logInButton));
        logInButton.click();
        return new LoginPage(driver);
    }

}
