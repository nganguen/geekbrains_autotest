package lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GeekbrainsTest {

    private static WebDriver driver;
    private static String url = "https://geekbrains.ru/";

    @BeforeClass
    public static void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        Assert.assertEquals("GeekBrains - образовательный портал", driver.getTitle());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void close() {
        driver.quit();
    }

    @Test
    public void authorizationTest() {
        driver.findElement(By.xpath("//a[@class='btn sign-in']")).click();
        Assert.assertEquals("Вход | GeekBrains - образовательный портал", driver.getTitle());

        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        WebElement email = driver.findElement(By.id("user_email"));
        email.sendKeys("ngythunga@gmail.com");
        Assert.assertNotNull(email.getText());

        WebElement pass = driver.findElement(By.id("user_password"));
        pass.sendKeys("nga13579");
        Assert.assertNotNull(pass.getText());
        pass.submit();
        Assert.assertEquals("Главная | GeekBrains - образовательный портал", driver.getTitle());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test
    public void logOut() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//img[@class='gb-top-menu__user-avatar user-avatar-image']")).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));
        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        Assert.assertEquals("GeekBrains - образовательный портал", driver.getTitle());
    }
}
