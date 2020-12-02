package lesson06.pageTest;

import lesson06.BaseAuthTest;
import lesson06.ConfigurationData;
import lesson06.pages.HomePage;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseAuthTest {

    @Test
    public void search() {
        HomePage page = new HomePage(driver)
                .search(ConfigurationData.SEARCH_INPUT)
                .checkSearchResult();
    }
}
