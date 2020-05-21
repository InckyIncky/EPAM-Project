package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class TalksLibraryPage extends StartPage {

    @FindBy(css = "div[class='evnt-toggle-filters-button evnt-button btn']")
    public static WebElement MORE_FILTERS_BTN;

    @FindBy(css ="div[class='evnt-show-filters-button evnt-button btn']")
    public static WebElement MORE_FILTERS_BTN_SMALL_SCREEN;

    @FindBy(id="filter_category")
    public static WebElement CATEGORY_FILTER;

    @FindBy(css = "label[data-value='Design']")
    public static WebElement DESIGN_CATEGORY_VALUE;

    @FindBy(id = "filter_location")
    public static WebElement LOCATION_FILTER;

    @FindBy(css = "label[data-value='Belarus']")
    public static WebElement BELARUS_LOCATION_VALUE;

    @FindBy(id = "filter_language")
    public static WebElement LANGUAGE_FILTER;

    @FindBy(css = "label[data-value='ENGLISH']")
    public static WebElement ENGLISH_LANGUAGE_VALUE;

    @FindBy(css = "div.evnt-talks-column cell-6")
    public static List<WebElement> REPORT_CARD;

    @FindBy(css = "input[class='evnt-text-fields form-control evnt-search']")
    public static WebElement KEY_WORD_SEARCH;

    @FindBy(css = "div.evnt-talk-name span")
    public static List<WebElement> REPORT_NAMES;

    @Step
    public void selectFilters() {
        try {
            MORE_FILTERS_BTN.click();
        }catch(NoSuchElementException nse) {
            MORE_FILTERS_BTN_SMALL_SCREEN.click();
        }
        CATEGORY_FILTER.click();
        DESIGN_CATEGORY_VALUE.click();
        LOCATION_FILTER.click();
        BELARUS_LOCATION_VALUE.click();
        LANGUAGE_FILTER.click();
        ENGLISH_LANGUAGE_VALUE.click();
    }

    @Step
    public void checkIfCardsCorrespondFilters() {
        for(WebElement reportCard : REPORT_CARD) {
            wait.ignoring(StaleElementReferenceException.class).
                    until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.evnt-card-table")));
            reportCard.click();
            report.checkReportsParams();
        }
    }

    @Step
    public void searchByKeyword(String keyword) {
        KEY_WORD_SEARCH.sendKeys(keyword);
    }

    @Step
    public void checkReportNames(String keyword) {
        wait.withTimeout(Duration.ofSeconds(10L));
        if(!REPORT_NAMES.get(0).getText().toLowerCase().contains(keyword.toLowerCase())) {
            wait.withTimeout(Duration.ofSeconds(10L));
        }
        int i = REPORT_NAMES.size();
        int a;
        action = new Actions(driver);
        for(;;) {
            action.moveToElement(REPORT_NAMES.get(REPORT_NAMES.size() - 1)).perform();
            wait.withTimeout(Duration.ofSeconds(10L));
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");
            wait.withTimeout(Duration.ofSeconds(10L));
            a = REPORT_NAMES.size();
            if(a == i) {
                System.out.println(REPORT_NAMES.size());
                break;
            } else {
                i=a;
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

            }
        }
        for(WebElement we : REPORT_NAMES) {
            System.out.println(REPORT_NAMES.indexOf(we) + ", " + we.getText());
            Assertions.assertTrue(we.getText().toLowerCase().contains(keyword.toLowerCase()), keyword + " not found in the name of the report");
        }
    }
}