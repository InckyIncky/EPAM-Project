package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testRunners.BaseTest;

public class StartPage extends BaseTest {

    @FindBy(css = "div[class = 'evnt-navigation-wrapper navbar-expand']>ul>li>a[href='/events']")
    public static WebElement EVENTS;

    @FindBy(css = "a[class = 'nav-link'][href='/talks']")
    public static WebElement TALKS_LIBRARY;

    @FindBy(css = "div.evnt-responsive-toggle-box")
    publc static WebElement MAIN_MENU;

    @Step
    public EventListPage clickEvents() {
        if(EVENTS.isDisplayed()) {
            EVENTS.click();
        }else {
            MAIN_MENU.click();
            EVENTS.click();
        }
        return eventListPage = PageFactory.initElements(driver, EventListPage.class);
    }

    @Step
    public TalksLibraryPage clickTalks() {
        if(TALKS_LIBRARY.isDisplayed()) {
            TALKS_LIBRARY.click();
        }else {
            MAIN_MENU.click();
            TALKS_LIBRARY.click();
        }
        return talks = PageFactory.initElements(driver, TalksLibraryPage.class);
    }
}
