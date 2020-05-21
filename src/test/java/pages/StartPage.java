package pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testRunners.BaseTest;

public class StartPage extends BaseTest {

    @FindBy(css = "a[class='nav-link'][href='/events']")
    public static WebElement EVENTS;

    @FindBy(css = "a[class='nav-link'][href='/talks']")
    public static WebElement TALKS_LIBRARY;

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
    public TalksLibraryPage clickTalks() {
        try {
            TALKS_LIBRARY.click();
        }catch(NoSuchElementException nse)  {
            MAIN_MENU.click();
            TALKS_LIBRARY.click();
        }
        return talks = PageFactory.initElements(driver, TalksLibraryPage.class);
    }
}
