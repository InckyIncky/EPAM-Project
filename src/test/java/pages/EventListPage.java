package pages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testRunners.BaseTest;

public class EventListPage extends StartPage {

    @FindBy(css = "span[class='evnt-tab-counter evnt-label small white']")
    public static WebElement UPCOMING_EVENTS_COUNT;

    @FindBy(css = "div[class*='card size']")
    public static List<WebElement> EVENT_CARDS;

    @FindBy(css = "div[class='evnt-details-cell calendar-cell desktop']")
    public static List<WebElement> SAVE_EVENT_TO_CALENDAR_LIST;

    @FindBy(css = "div.evnt-details-cell online-cell")
    public static List<WebElement> EVENT_LOCATIONS;

    @FindBy(css = "div[class*='card size']>a>div>div>div>div>p>span")
    public static List<WebElement> EVENT_LOCATION_AND_LANGUAGE;

    @FindBy(css = "div.evnt-details-cell language-cell")
    public static List<WebElement> EVENT_LANGUAGES;

    @FindBy(css = "div[class='evnt-dates-cell dates']>p>span.date")
    public static List<WebElement> EVENT_DATES;

    @FindBy(css = "div[class='evnt-dates-cell dates']>p>span[class='status free-attend']")
    public static List<WebElement> ATTEND_INFO;

    @FindBy(css = "div.evnt-event-name")
    public static List<WebElement> EVENT_NAMES;

    @FindBy(css = "div[class*='cell speakers']")
    public static List<WebElement> SPEAKERS;

    @FindBy(css = "div.evnt-event-details-table")
    public static List<WebElement> EVENT_CARD_HEADER;

    @FindBy(css = "div.evnt-card-body>div")
    public static List<WebElement> EVENT_CARD_BODY;

    @FindBy(css = "div.evnt-event-dates-table")
    public static List<WebElement> SPEAKER_DATE_REGISTRATION;

    @FindBy(xpath = "//div[@class='evnt-cards-container'][1]//span[@class='date']")
    public static List<WebElement> THIS_WEEKS_EVENT_DATES;

    @FindBy(css = "a[class='evnt-tab-link nav-link active']")
    public static WebElement PAST_EVENTS_BTN;

    @FindBy(css = "div[class='evnt-dates-cell dates']>p>span[class='status free-attend']")
    public static WebElement PAST_EVENTS_COUNT;

    @FindBy(id = "filter_location")
    public static WebElement LOCATION_DROPDOWN;

    @FindBy(css = "div[data-group='Canada']")
    public static WebElement CANADA;

    @Step
    public boolean checkCountOfEventCards(WebElement UPCOMING_OR_PAST_EVENTS) {
        logger.info(EVENT_CARDS.size());
        logger.info(UPCOMING_OR_PAST_EVENTS.getText());
        return SAVE_EVENT_TO_CALENDAR_LIST.size() == Integer.parseInt(UPCOMING_EVENTS_COUNT.getText());
    }

    @Step
    public void checkEventCardsInfo() {
        for (int i = 0; i < EVENT_CARDS.size(); i++) {
            Assertions.assertTrue(EVENT_LOCATION_AND_LANGUAGE.get(i).isDisplayed(), "Location is missing");
            Assertions.assertTrue(EVENT_LOCATION_AND_LANGUAGE.get(i + 1).isDisplayed(), "Language is missing");
            Assertions.assertTrue(EVENT_NAMES.get(i).isDisplayed(), "Name of the event is missitg");
            Assertions.assertTrue(EVENT_DATES.get(i).isDisplayed(), "Date of the event is missing");
//            Assertions.assertTrue(ATTEND_INFO.get(i).isDisplayed(), "Registration info is missing");
            Assertions.assertTrue(SPEAKERS.get(i).isDisplayed(), "Speakers are missing");

            Assertions.assertTrue(EVENT_CARD_HEADER.get(i).getLocation().getY() < EVENT_CARD_BODY.get(i).getLocation().getY());
            Assertions.assertTrue(EVENT_CARD_BODY.get(i).getLocation().getY() < SPEAKER_DATE_REGISTRATION.get(i).getLocation().getY() &&
                    EVENT_CARD_BODY.get(i).getLocation().getX() == SPEAKER_DATE_REGISTRATION.get(i).getLocation().getX());
            System.out.println("card N" + i + " ok");
        }
    }

    @Step
    public void checkEventCardsArePresent() {
        for (WebElement eventCard : EVENT_CARDS) {
            Assertions.assertTrue(eventCard.isDisplayed());
        }
    }

    @Step
    public void checkIfDateWithinCurrentWeek() {
        for (WebElement thisWeeksEventDate : THIS_WEEKS_EVENT_DATES) {
            String dateOfEvent = thisWeeksEventDate.getText();
            DateTimeFormatter form = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US);
            LocalDate eventDate = LocalDate.parse(dateOfEvent, form);
            LocalDate checkDate = LocalDate.now();
            LocalDate endOfTheWeek = eventDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            logger.info("Events date: " + eventDate + ", current date: " + checkDate + ", week's end date: " + endOfTheWeek);
            Assertions.assertTrue((eventDate.isAfter(checkDate) || eventDate.isEqual(checkDate)) && eventDate.isBefore(endOfTheWeek),
                    eventDate + " is past or is not within the week");
        }
    }

    @Step
    public void showPastEvents() {
        PAST_EVENTS_BTN.click();
        logger.info("Past events displayed");
    }

    @Step
    public void selectLocation() {
        LOCATION_DROPDOWN.click();
        CANADA.click();
        logger.info("Location selected");
    }

    @Step
    public Event openEventDetails(int cardsNumber) {
        EVENT_CARDS.get(cardsNumber).click();
        logger.info(EVENT_CARDS.get(cardsNumber).getAttribute("value") + "Event is opened");
        Event eventPage;
        return eventPage = PageFactory.initElements(driver, Event.class);
    }
}