package lesson06.pageTest;

import lesson06.BaseAuthTest;
import lesson06.ConfigurationData;
import lesson06.pages.HomePage;
import lesson06.pages.StarterPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogOutTest extends BaseAuthTest {

    @Test
    public void logOut() {
        StarterPage logOut = new HomePage(driver)
                .logOut();
        assertEquals(ConfigurationData.starterPageTitle, driver.getTitle());
    }
}
