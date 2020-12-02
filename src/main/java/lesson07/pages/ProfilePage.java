package lesson07.pages;

import io.qameta.allure.Step;
import lesson07.BaseInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BaseInit {

    @FindBy(xpath = "//a[text()='Редактировать профиль']")
    private WebElement editButton;

    @FindBy(name = "user[phone]")
    private WebElement phone;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Go to edit profile page")
    public EditProfilePage changeProfile() {
        wait30sec.until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
        wait10sec.until(ExpectedConditions.visibilityOf(phone));
        return new EditProfilePage(driver);
    }
}
