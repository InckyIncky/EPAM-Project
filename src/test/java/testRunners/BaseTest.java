package testRunners;



import io.github.bonigarcia.seljup.SeleniumExtension;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import pages.*;


@ExtendWith(SeleniumExtension.class)
public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions action;

    public static final Logger logger = LogManager.getLogger("epam-logs");

    protected StartPage startPage;
    protected EventListPage eventListPage;
    protected Event event;
    protected ReportPage report;
    protected VideosPage talks;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File file = new File("src/test/java/resources/log4j2.xml");
        context.setConfigLocation(file.toURI());

        String browser = System.getProperty("browser");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser.toLowerCase());
//        caps.setVersion("81.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("enableVideo", false);

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                caps
        );

        //For debugging
//        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
//
//        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        logger.info("WebDriver configured");
    }

    @BeforeEach
    public void initPages() {

        driver.manage().deleteAllCookies();

        startPage = PageFactory.initElements(driver, StartPage.class);
        report = PageFactory.initElements(driver, ReportPage.class);

        driver.get("https://events.epam.com/");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
