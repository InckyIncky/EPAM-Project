package util;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testRunners.BaseTest;

import java.util.Optional;

public class MyTestWatcher extends BaseTest implements TestWatcher {


    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        screenShot();
        System.out.println("Watcher says: Successful!");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        screenShot();
        System.out.println("Watcher says: Aborted!");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        screenShot();
        System.out.println("Watcher says: Failed!");
    }

    @Attachment
    public byte[] saveScreenshot(byte[] screenShot) { return screenShot; }

    public void screenShot() {
        if(driver == null) {
            System.out.println("No driver defined");
            return;
        }
        saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}
