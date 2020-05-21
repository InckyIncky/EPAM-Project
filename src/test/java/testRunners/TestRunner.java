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

        eventListPage = startPage.clickEvents();
        Assertions.assertTrue(eventListPage.checkCountOfEventCards(UPCOMING_EVENTS_COUNT));
    }

    @Test
    @Description("Checking event card content")
    public void testTwo() {
        eventListPage = startPage.clickEvents();
        eventListPage.checkEventCardsInfo();
    }

    @Test
    @Description("Checking this week events")
    public void testThree() {
        eventListPage = startPage.clickEvents();
        eventListPage.checkIfDateWithinCurrentWeek();
    }

    @Test
    @Description("Past events presence and count")
    public void testFour() {
        eventListPage = startPage.clickEvents();
        eventListPage.showPastEvents();
        eventListPage.selectLocation();
        Assertions.assertTrue(eventListPage.checkCountOfEventCards(PAST_EVENTS_COUNT));
    }

    @Test
    @Description("Detailed event info")
    public void testFive() {
        eventListPage = startPage.clickEvents();
        event = eventListPage.openEventDetails(4);
        event.checkEventPageContents();
    }

    @Test
    @Description("Filtering of reports by categories")
    public void testSix() {
        talks = startPage.clickTalks();
        talks.selectFilters();
        talks.checkIfCardsCorrespondFilters();
    }

    @Test
    @Description("Search for report by keyword")
    public void testSeven() {
        String keyword = "Azure";
        talks = startPage.clickTalks();
        talks.searchByKeyword(keyword);
        talks.checkReportNames(keyword);
    }

}
