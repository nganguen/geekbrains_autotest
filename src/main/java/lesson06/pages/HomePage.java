package lesson06.pages;

import lesson06.BaseInit;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseInit {

    @FindBy(xpath = "//img[@class='gb-top-menu__user-avatar user-avatar-image']")
    private WebElement profileMenu;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logOutButton;

    @FindBy(xpath = "//span[text()='Профиль']")
    private WebElement profileButton;

    @FindBy(xpath = "//li/a[@class='show-search-form']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement searchInput;

    private static String searchResult = "//div[@class='profession-title']";


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StarterPage logOut() {
        wait10sec.until(ExpectedConditions.visibilityOf(profileMenu));
        Actions builder = new Actions(driver);
        builder
                .click(profileMenu)
                .click(logOutButton)
                .build()
                .perform();
        return new StarterPage(driver);
    }

    public ProfilePage gotoProfile() {
        wait10sec.until(ExpectedConditions.visibilityOf(profileMenu));
        Actions builder = new Actions(driver);
        builder
                .click(profileMenu)
                .click(profileButton)
                .build()
                .perform();
        return new ProfilePage(driver);
    }

    public HomePage search(String input) {
        wait10sec.until(ExpectedConditions.visibilityOf(searchButton));
        Actions builder = new Actions(driver);
        builder
                .click(searchButton)
                .sendKeys(searchInput, input)
                .build()
                .perform();
        return this;
    }

    public HomePage checkSearchResult() {
        wait30sec.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResult)));
        Assertions.assertFalse(driver.findElements(By.xpath(searchResult)).isEmpty());
        return this;
    }
}
