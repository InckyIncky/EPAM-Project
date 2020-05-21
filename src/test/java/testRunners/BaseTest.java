package testRunners;


import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions action;

    protected StartPage startPage;
    protected EventListPage eventListPage;
    protected Event event;
    protected ReportPage report;
    protected TalksLibraryPage talks;

    @BeforeAll
    public static void setUp() {

//        String browser = System.getProperty("browser");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 12);
        action = new Actions(driver);

        driver.get("https://events.epam.com/");
    }

    @BeforeEach
    public void initPages() {
    startPage = PageFactory.initElements(driver, StartPage.class);
//    eventListPage = PageFactory.initElements(driver, EventListPage.class);
    report = PageFactory.initElements(driver, ReportPage.class);
//    talks = PageFactory.initElements(driver, TalksLibraryPage.class);

    driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
