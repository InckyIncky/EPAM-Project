package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ReportPage {
    @FindBy(css = "div[class='evnt-topic evnt-talk-topic small gray']")
    public static List<WebElement> CATEGORIES;

    @FindBy(css = "div[class='evnt-talk-details location evnt-now-past-talk']>span")
    public static WebElement LOCATION;

    @FindBy(css = "div[class='evnt-talk-details language evnt-now-past-talk']>span")
    public static WebElement LANGUAGE;

    @Step
    public boolean checkReportsParams() {
        boolean correctCategoty = false;
        boolean correctLocation = false;
        boolean correctLanguage = false;
        for (WebElement we : CATEGORIES) {
            if (we.getText().equalsIgnoreCase("design")) {
                correctCategoty = true;
                break;
            } else {
                correctCategoty = false;
            }
        }
        if(LOCATION.getText().contains("Belarus")) correctLocation = true;
        if(LANGUAGE.getText().equalsIgnoreCase("ENGLISH")) correctLanguage = true;

        return correctCategoty && correctLanguage && correctLocation;
    }
}
