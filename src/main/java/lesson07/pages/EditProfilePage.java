package lesson07.pages;

import io.qameta.allure.Step;
import lesson07.BaseInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class EditProfilePage extends BaseInit {

    @FindBy(name = "user[phone]")
    private WebElement phone;

    @FindBy(name = "user[gender]")
    private WebElement gender;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement saveButton;

    private String saved = "//div[text()='Профиль сохранён']";

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Change phone number")
    public EditProfilePage changePhone(String newPhone) {
        wait30sec.until(ExpectedConditions.visibilityOf(phone));
        phone.clear();
        phone.sendKeys(newPhone);
        return this;
    }

    @Step(value = "Select gender")
    public EditProfilePage setGender(String strGen) {
        Select genderSelect = new Select(gender);
        genderSelect.selectByVisibleText(strGen);
        return this;
    }

    @Step(value = "Click save button")
    public EditProfilePage clickSave() {
        saveButton.click();
        return this;
    }

    @Step(value = "Check changes")
    public EditProfilePage checkChanges() {
        wait10sec.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(saved)));
        //Assertions.assertNotNull(driver.findElement(By.xpath(saved)));
        return this;
    }
}
