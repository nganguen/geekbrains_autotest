package lesson06.pageTest;

import lesson06.BaseAuthTest;
import lesson06.ConfigurationData;
import lesson06.pages.EditProfilePage;
import lesson06.pages.HomePage;
import lesson06.pages.ProfilePage;
import org.junit.jupiter.api.Test;

public class EditProfileTest extends BaseAuthTest {

    @Test
    public void editProfile() {
        ProfilePage profilePage = new HomePage(driver)
                .gotoProfile();
        EditProfilePage editPage = profilePage.changeProfile();
        editPage
                .changePhone(ConfigurationData.PHONE)
                .setGender(ConfigurationData.GENDER)
                .clickSave()
                .checkChanges();
    }
}
