package testRunners;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import util.MyTestWatcher;

import static pages.EventListPage.UPCOMING_EVENTS_COUNT;
import static pages.EventListPage.PAST_EVENTS_COUNT;

@ExtendWith(MyTestWatcher.class)
public class TestRunner extends BaseTest{

    @Test
    @Description("Check if event cards are present on the Events page and the count of cards is similar to number on the counter")
    public void testOne() {
        logger.info("Test one started");

        eventListPage = startPage.clickEvents();
        Assertions.assertTrue(eventListPage.checkCountOfEventCards(UPCOMING_EVENTS_COUNT), "Count of upcoming events is not equal to the number on the counter");
    }

    @Test
    @Description("Checking event card content")
    public void testTwo() {
        logger.info("Test two started");
        eventListPage = startPage.clickEvents();
        eventListPage.checkEventCardsInfo();
    }

    @Test
    @Description("Checking this week events")
    public void testThree() {
        logger.info("Test three started");

        eventListPage = startPage.clickEvents();
        eventListPage.checkIfDateWithinCurrentWeek();
    }

    @Test
    @Description("Past events presence and count")
    public void testFour() {
        logger.info("Test four started");

        eventListPage = startPage.clickEvents();
        eventListPage.showPastEvents();
        eventListPage.selectLocation();
        Assertions.assertTrue(eventListPage.checkCountOfEventCards(PAST_EVENTS_COUNT));
    }

    @Test
    @Description("Detailed event info")
    public void testFive() {
        logger.info("Test five started");

        eventListPage = startPage.clickEvents();
        event = eventListPage.openEventDetails(4);
        event.checkEventPageContents();
    }

    @Test
    @Description("Filtering of reports by categories")
    public void testSix() {
        logger.info("Test six started");

        talks = startPage.clickTalks();
        talks.selectFilters();
        talks.checkIfCardsCorrespondFilters();
    }

    @Test
    @Description("Search for report by keyword")
    public void testSeven() {
        logger.info("Test seven started");

        String keyword = "Azure";
        talks = startPage.clickTalks();
        talks.searchByKeyword(keyword);
        talks.checkReportNames(keyword);
    }

}
