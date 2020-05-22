package testRunners;


import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import pages.*;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions action;

    public static final Logger logger = LogManager.getLogger();

    protected StartPage startPage;
    protected EventListPage eventListPage;
    protected Event event;
    protected ReportPage report;
    protected VideosPage talks;

    @BeforeAll
    public static void setUp() {
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File file = new File("src/test/java/resources/log4j2.xml");
        context.setConfigLocation(file.toURI());



        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 12);
        action = new Actions(driver);

        driver.get("https://events.epam.com/");
        logger.info("WebDriver configured");
        System.out.println(logger.getName());
    }

    @BeforeEach
    public void initPages() {
        startPage = PageFactory.initElements(driver, StartPage.class);
        report = PageFactory.initElements(driver, ReportPage.class);

        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
