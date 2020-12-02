package lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import lesson07.listener.CustomLogger;
import lesson07.listener.ScreenshotMaker;
import lesson07.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static lesson07.ConfigurationData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyTestWatcher.class)
public abstract class BaseAuthTes {

    protected static RemoteWebDriver chromeDriver;
    protected static EventFiringWebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //options.setHeadless(true);

        //Настройка логирования через RemoteWebDriver, setLogLevel
        chromeDriver = new ChromeDriver(options);
        chromeDriver.setLogLevel(Level.INFO);

        //“Обертка над экземпляром WebDriver, позволяющая регистрировать WebDriverEventListener в целях логирования”.
        driver = new EventFiringWebDriver(chromeDriver);
        driver.register(new CustomLogger());

        driver.get(BASE_URL);
        assertEquals(starterPageTitle, driver.getTitle());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*@BeforeEach
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(loginPageTitle, driver.getTitle());
        login.authorizeAs(USER_EMAIL, USER_PASS);
        assertEquals(homePageTitle, driver.getTitle());
    }*/

    @AfterAll
    public static void close() {
        //Вывод всех ошибок браузера
        driver
                .manage()
                .logs()
                .get(LogType.BROWSER)
                .getAll()
                .forEach(System.out::println);
        driver.quit();
    }

    public void failed() {
        ScreenshotMaker.makeScreenshot(driver, "testfailed.jpg");
        saveScreenshot(ScreenshotMaker.makeScreenshotByte(driver));
    }

    @Attachment(value = "test failed screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
