package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson06.pages.HomePage;
import lesson06.pages.LoginPage;
import lesson06.pages.StarterPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseAuthTest {

    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(ConfigurationData.BASE_URL);
        assertEquals(ConfigurationData.starterPageTitle, driver.getTitle());
    }

    @BeforeEach
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(ConfigurationData.loginPageTitle, driver.getTitle());

        HomePage homePage = login.authorizeAs(ConfigurationData.USER_EMAIL, ConfigurationData.USER_PASS);
        assertEquals(ConfigurationData.homePageTitle, driver.getTitle());
    }

    @AfterAll
    public static void close() {
        driver.quit();
    }
}
