package lesson07.pageTest;

import lesson07.BaseAuthTes;
import lesson07.pages.LoginPage;
import lesson07.pages.StarterPage;
import org.junit.jupiter.api.Test;

import static lesson07.ConfigurationData.*;
import static lesson07.ConfigurationData.homePageTitle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginFailedTest extends BaseAuthTes {

    @Test
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(loginPageTitle, driver.getTitle());
        login.authorizeAs(USER_EMAIL, USER_PASS_WRONG);
        assertEquals(homePageTitle, driver.getTitle());
    }
}
