package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testRunners.BaseTest;

public class StartPage extends BaseTest {

    @FindBy(css = "a[class='nav-link'][href='/events']")
    public static WebElement EVENTS;

    @FindBy(css = "a[class='nav-link'][href^='/video']")
    public static WebElement VIDEOS_LIBRARY; //Ex Talks library

    @FindBy(css = "div.evnt-responsive-toggle-box")
    public static WebElement MAIN_MENU;

    @Step
    public EventListPage clickEvents() {
        try {
            EVENTS.click();
        }catch(NoSuchElementException nse) {
            MAIN_MENU.click();
            EVENTS.click();
        }
        return eventListPage = PageFactory.initElements(driver, EventListPage.class);
    }

    @Step
    public VideosPage clickTalks() {
        try {
            VIDEOS_LIBRARY.click();
        }catch(NoSuchElementException nse)  {
            MAIN_MENU.click();
            VIDEOS_LIBRARY.click();
        }
        return talks = PageFactory.initElements(driver, VideosPage.class);
    }
}
