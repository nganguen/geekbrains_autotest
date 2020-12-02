package lesson07.pages;

import io.qameta.allure.Step;
import lesson07.BaseInit;
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

    @FindBy(xpath = "//div[@class='profession-title']")
    private WebElement result;
    private static String searchResult = "//div[@class='profession-item-wrapper search_row col-md-6 col-xs-12 col-lg-4'][1]";


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Log out")
    public StarterPage logOut() {
        wait30sec.until(ExpectedConditions.visibilityOf(profileMenu)).click();
        /*Actions builder = new Actions(driver);
        builder
                .click(profileMenu)
                .click(logOutButton)
                .build()
                .perform();*/
        wait10sec.until(ExpectedConditions.visibilityOf(logOutButton)).click();
        return new StarterPage(driver);
    }

    @Step(value = "Go to profile page")
    public ProfilePage gotoProfile() {
        wait30sec.until(ExpectedConditions.visibilityOf(profileMenu));
        Actions builder = new Actions(driver);
        builder
                .click(profileMenu)
                .click(profileButton)
                .build()
                .perform();
        return new ProfilePage(driver);
    }

    @Step(value = "Search info about {input}")
    public HomePage search(String input) {
        wait30sec.until(ExpectedConditions.visibilityOf(searchButton));
        Actions builder = new Actions(driver);
        builder
                .click(searchButton)
                .sendKeys(searchInput, input)
                .build()
                .perform();
        return this;
    }

    @Step(value = "Check search result")
    public HomePage checkSearchResult() {
        wait30sec.until(ExpectedConditions.elementToBeClickable(By.xpath(searchResult)));
        //Assertions.assertFalse(driver.findElements(By.xpath(searchResult)).isEmpty());
        return this;
    }
}
