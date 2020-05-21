package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testRunners.BaseTest;

import java.util.List;

public class Event extends BaseTest {

    @FindBy(id = "home")
    public static WebElement EVENT_PAGE_HEADER;

    @FindBy(css ="button[id*= 'no_reg_']")
    public static WebElement REGISTRATION_BTN;

    @FindBy(css = "div[class='evnt-content-text edit-wrapper hero']>h1")
    public static WebElement EVENTS_NAME;

    @FindBy(css = "div[class='evnt-details online-wrapper']")
    public static WebElement LOCATION;

    @FindBy(css = "div[class='evnt-details date-wrapper']>span.date")
    public static WebElement DATE;

    @FindBy(css = "div[class='evnt-icon-info]")
    public static List<WebElement> DATE_LOCATION_ADDITIONAL_INFO;
    // A list of three elements, first is a date, second is a location, third is addtional info.

    @FindBy(css = "div[class='evnt-card-wrapper']")
    public static List<WebElement> EVENT_PROGRAM;

    @Step
    public void checkEventPageContents() {
        Assertions.assertTrue(EVENT_PAGE_HEADER.isDisplayed());
        Assertions.assertTrue(REGISTRATION_BTN.isDisplayed());
        Assertions.assertTrue(EVENTS_NAME.isDisplayed());
        for (WebElement we : DATE_LOCATION_ADDITIONAL_INFO) {
            Assertions.assertTrue(we.isDisplayed());
        }
        for (WebElement we : EVENT_PROGRAM) {
            Assertions.assertTrue(we.isDisplayed());
        }
    }
}
