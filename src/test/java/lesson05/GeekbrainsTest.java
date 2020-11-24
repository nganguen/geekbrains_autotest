package lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeekbrainsTest {
    private static WebDriver driver;
    private static String url = "https://geekbrains.ru/";
    private static String user = "ngythunga@gmail.com";
    private static String passw = "nga13579";
    private static JavascriptExecutor jsExecutor;
    private static Actions builder;

    @BeforeAll
    public static void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        assertEquals("GeekBrains - образовательный портал", driver.getTitle());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn sign-in']")));

    }

    @AfterAll
    public static void close() {
        driver.quit();
    }

    @BeforeEach
    public void authorizationTest() {
        driver.findElement(By.xpath("//a[@class='btn sign-in']")).click();
        assertEquals("Вход | GeekBrains - образовательный портал", driver.getTitle());

        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.id("user_email")));
        WebElement email = driver.findElement(By.id("user_email"));
        email.sendKeys(user);
        assertNotNull(email.getText());

        WebElement pass = driver.findElement(By.id("user_password"));
        pass.sendKeys(passw);
        assertNotNull(pass.getText());
        pass.submit();
        assertEquals("Главная | GeekBrains - образовательный портал", driver.getTitle());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void logOut() {
        driver.findElement(By.xpath("//img[@class='gb-top-menu__user-avatar user-avatar-image']")).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/logout']")));
        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        assertEquals("GeekBrains - образовательный портал", driver.getTitle());
    }

    @Test
    public void search() throws InterruptedException {
        //Thread.sleep(15000);
        driver.manage().deleteCookieNamed("show_notifications");
        builder = new Actions(driver);
        builder
                .click(driver.findElement(By.xpath("//li/a[@class='show-search-form']")))
                .sendKeys(driver.findElement(By.xpath("//input[@placeholder='Поиск по сайту']")),"java")
                .build()
                .perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='profession-title']")));
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='profession-title']"));
        assertEquals("Программист Java", titles.get(0).getText());
    }

    @Test
    public void changeProfile() throws InterruptedException {
        driver.manage().deleteCookieNamed("show_notifications");
        //Thread.sleep(5000);
        builder = new Actions(driver);
        builder
                .click(driver.findElement(By.xpath("//img[@class='gb-top-menu__user-avatar user-avatar-image']")))
                .click(driver.findElement(By.xpath("//span[text()='Профиль']")))
                .build()
                .perform();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Редактировать профиль']")));
        driver.findElement(By.xpath("//a[text()='Редактировать профиль']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.name("user[phone]")));
        driver.findElement(By.name("user[phone]")).clear();
        driver.findElement(By.name("user[phone]")).sendKeys("8657985467");
        Select gender = new Select(driver.findElement(By.name("user[gender]")));
        gender.selectByVisibleText("Женский");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Профиль сохранён']")));
        assertNotNull(driver.findElement(By.xpath("//div[text()='Профиль сохранён']")));

    }

}
