package lesson07.pageTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import lesson07.listener.ScreenshotMaker;
import lesson07.pages.*;
import lesson07.BaseAuthTes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static lesson07.ConfigurationData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Edit profile")
public class EditProfileTes extends BaseAuthTes {

    @BeforeEach
    public void authorizationTest() {
        LoginPage login = new StarterPage(driver)
                .clickLogIn();
        assertEquals(loginPageTitle, driver.getTitle());
        login.authorizeAs(USER_EMAIL, USER_PASS);
        assertEquals(homePageTitle, driver.getTitle());
    }

    @Test
    public void editProfile() {
        ProfilePage profilePage = new HomePage(driver)
                .gotoProfile();
        EditProfilePage editPage = profilePage.changeProfile();
        Allure.addAttachment("Before change", new ByteArrayInputStream((ScreenshotMaker.makeScreenshotByte(driver))));
        editPage
                .changePhone(PHONE)
                .setGender(GENDER)
                .clickSave()
                .checkChanges();
        Allure.addAttachment("After change", new ByteArrayInputStream((ScreenshotMaker.makeScreenshotByte(driver))));
    }
}
