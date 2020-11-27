package lesson06.pages;

import lesson06.BaseInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BaseInit {

    @FindBy(xpath = "//a[text()='Редактировать профиль']")
    private WebElement editButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public EditProfilePage changeProfile() {
        wait10sec.until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
        return new EditProfilePage(driver);
    }
}
