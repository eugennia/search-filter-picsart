package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends BasePage {

    private final WebDriverWait wait;

    @FindBy(css = "[data-testid='com.picsart.social.search']")
    private WebElement frameElement;

    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement cookiesButton;

    @FindBy(css = "[data-testid='search-header-filter']")
    private WebElement filterButton;

    @FindBy(css = "[data-testid='search-filter-root']")
    private WebElement filterSection;

    @FindBy(css = "[aria-label='licenses-Personal']")
    private WebElement personalButton;

    @FindBy(id = "base_card_item2")
    private WebElement baseCardItem2;

    @FindBy(id = "like_button_item2")
    private WebElement likeButtonItem2;

    @FindBy(css = "[data-testid='modal-close-icon']")
    private WebElement modalCloseButton;

    @FindBy(css = "[data-testid='search-filter-header-clear']")
    private WebElement clearAllFiltersButton;

    @FindBy(css = "[data-testid='try-now-button-root']")
    private WebElement tryNowButton;

    @FindBy(css = "[data-testid='modal-close-icon']")
    private WebElement popupCloseButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    public void switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void openFilter() {
        if (!filterSection.isDisplayed()) {
            filterButton.click();
        }
    }

    public void closeFilter() {
        if (filterSection.isDisplayed()) {
            filterButton.click();
        }
    }

    public void applyPersonalLicenseFilter() {
        openFilter();
        wait.until(ExpectedConditions.visibilityOf(filterSection));
        wait.until(ExpectedConditions.elementToBeClickable(personalButton)).click();
        closeFilter();
    }

    public void likeItem() {
        Actions actions = new Actions(driver);
        actions.moveToElement(baseCardItem2).perform();
        wait.until(ExpectedConditions.visibilityOf(baseCardItem2));
        wait.until(ExpectedConditions.elementToBeClickable(likeButtonItem2));
        actions.moveToElement(likeButtonItem2).click().perform();
    }

    public void closeModal() {
        switchToDefaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton)).click();
    }

    public void clearFilters() {
        switchToFrame();
        openFilter();
        wait.until(ExpectedConditions.elementToBeClickable(clearAllFiltersButton)).click();
        closeFilter();
    }

    public void tryNow() {
        Actions actions = new Actions(driver);
        actions.moveToElement(baseCardItem2).perform();
        actions.moveToElement(tryNowButton).click().perform();
        switchToDefaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(popupCloseButton)).click();
    }
}
