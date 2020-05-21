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

    @Step
    public EventListPage clickEvents() {
        EVENTS.click();
        return eventListPage = PageFactory.initElements(driver, EventListPage.class);
    }

    @Step
    public TalksLibraryPage clickTalks() {
        TALKS_LIBRARY.click();
        return talks = PageFactory.initElements(driver, TalksLibraryPage.class);
    }
}
