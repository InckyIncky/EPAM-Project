package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static pages.EventListPage.LOCATION_DROPDOWN;
import static pages.EventListPage.selectCountry;

public class VideosPage extends StartPage {

    @FindBy(css = "div[class='evnt-toggle-filters-button evnt-button btn']")
    public static WebElement MORE_FILTERS_BTN;

    @FindBy(css = "div[class='evnt-show-filters-button evnt-button btn']")
    public static WebElement MORE_FILTERS_BTN_SMALL_SCREEN;

    @FindBy(id = "filter_category")
    public static WebElement CATEGORY_FILTER;

    @FindBy(css = "label[data-value='Design']")
    public static WebElement DESIGN_CATEGORY_VALUE;

    @FindBy(id = "filter_language")
    public static WebElement LANGUAGE_FILTER;

    @FindBy(css = "label[data-value='ENGLISH']")
    public static WebElement ENGLISH_LANGUAGE_VALUE;

    @FindBy(css = "div.evnt-talks-column cell-6")
    public static List<WebElement> REPORT_CARD;

    @FindBy(css = "input[placeholder='Search by Talk Name']")
    public static WebElement KEY_WORD_SEARCH;

    @FindBy(css = "div.evnt-talk-name span")
    public static List<WebElement> REPORT_NAMES;

    @FindBy(id="filter_speaker")
    public static WebElement SPEAKER;

    @FindBy(css = "div.evnt-popup-close")
    public static WebElement FILTERS_DIALOGUE_CLOSE;

    @FindBy(css = "button[class='evnt-button grass small submit-button show-results-button']")
    public static WebElement SHOW_RESULTS_BTN;

    @Step
    public void selectFilters() {
        try {
            MORE_FILTERS_BTN.click();
        } catch (NoSuchElementException nse) {
            MORE_FILTERS_BTN_SMALL_SCREEN.click();
        }
        try {
            CATEGORY_FILTER.click();
        }catch (ElementClickInterceptedException ee){
            FILTERS_DIALOGUE_CLOSE.click();
            MORE_FILTERS_BTN_SMALL_SCREEN.click();
            CATEGORY_FILTER.click();
        }
        DESIGN_CATEGORY_VALUE.click();
        try {
            LOCATION_DROPDOWN.click();
        }catch (ElementClickInterceptedException ee){
            FILTERS_DIALOGUE_CLOSE.click();
            MORE_FILTERS_BTN_SMALL_SCREEN.click();
            LOCATION_DROPDOWN.click();
        }
//        BELARUS_LOCATION_VALUE.click();
        selectCountry("Belarus");
        try {
            LANGUAGE_FILTER.click();
        }catch (ElementClickInterceptedException ee){
            FILTERS_DIALOGUE_CLOSE.click();
            MORE_FILTERS_BTN_SMALL_SCREEN.click();
            LANGUAGE_FILTER.click();
        }
        ENGLISH_LANGUAGE_VALUE.click();
        try {
            SHOW_RESULTS_BTN.click();
        }catch (NoSuchElementException nse) {
        }
    }

    @Step
    public void checkIfCardsCorrespondFilters() {
        for (WebElement reportCard : REPORT_CARD) {
            reportCard.click();
            report.checkReportsParams();
        }
    }

    @Step
    public void searchByKeyword(String keyword) {

        KEY_WORD_SEARCH.sendKeys(keyword);
        logger.info("Keyword search started");
    }

    @Step
    public void checkReportNames(String keyword) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!REPORT_NAMES.get(0).getText().toLowerCase().contains(keyword.toLowerCase())) {
            wait.withTimeout(Duration.ofSeconds(10L));
        }
        int i = REPORT_NAMES.size();
        int a;
        action = new Actions(driver);
        for (; ; ) {
            action.moveToElement(REPORT_NAMES.get(REPORT_NAMES.size() - 1)).perform();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = REPORT_NAMES.size();
            if (a == i) {
                System.out.println(REPORT_NAMES.size());
                break;
            } else {
                i = a;
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

            }
        }
        for (WebElement we : REPORT_NAMES) {
            System.out.println(REPORT_NAMES.indexOf(we) + ", " + we.getText());
            Assertions.assertTrue(we.getText().toLowerCase().contains(keyword.toLowerCase()), keyword + " not found in the name of the report");
        }
    }
}