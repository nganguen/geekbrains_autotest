package lesson07.pageTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import lesson07.pages.HomePage;
import lesson07.BaseAuthTes;
import lesson07.listener.ScreenshotMaker;
import lesson07.pages.LoginPage;
import lesson07.pages.StarterPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static lesson07.ConfigurationData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Search")
public class SearchTes extends BaseAuthTes {

    @BeforeEach
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(loginPageTitle, driver.getTitle());
        login.authorizeAs(USER_EMAIL, USER_PASS);
        assertEquals(homePageTitle, driver.getTitle());
    }

    @Test
    public void search() {
        HomePage page = new HomePage(driver)
                .search(SEARCH_INPUT)
                .checkSearchResult();
        ScreenshotMaker.makeScreenshot(driver, "searchResult.jpg");
        Allure.addAttachment("Search result", new ByteArrayInputStream(ScreenshotMaker.makeScreenshotByte(driver)));
    }
}
