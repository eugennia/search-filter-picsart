package tests;

import pages.SearchPage;
import utils.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTest {

    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeEach
    void setUp() {
        driver = WebDriverSetup.initializeDriver();
        searchPage = new SearchPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1366x768",
            "1440x900",
            "1024x768"
    })
    void testFilters(String resolution) {
        driver.manage().window().setSize(new Dimension(
                Integer.parseInt(resolution.split("x")[0]),
                Integer.parseInt(resolution.split("x")[1])
        ));

        driver.get("https://picsart.com/search/images/");
        searchPage.acceptCookies();
        searchPage.switchToFrame();

        searchPage.applyPersonalLicenseFilter();
        assertTrue(driver.findElements(By.cssSelector("[data-testid='search-item-premium']")).isEmpty());

        searchPage.likeItem();
        searchPage.closeModal();
        searchPage.clearFilters();
        searchPage.tryNow();
    }
}
