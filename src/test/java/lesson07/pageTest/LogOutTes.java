package lesson07.pageTest;

import io.qameta.allure.Feature;
import lesson07.pages.HomePage;
import lesson07.pages.LoginPage;
import lesson07.pages.StarterPage;
import lesson07.BaseAuthTes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lesson07.ConfigurationData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("LogOut")
public class LogOutTes extends BaseAuthTes {

    @BeforeEach
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(loginPageTitle, driver.getTitle());
        login.authorizeAs(USER_EMAIL, USER_PASS);
        assertEquals(homePageTitle, driver.getTitle());
    }

    @Test
    public void logOut() {
        StarterPage logOut = new HomePage(driver)
                .logOut();
        assertEquals(starterPageTitle, driver.getTitle());
        //assertEquals(homePageTitle, driver.getTitle());
    }
}
