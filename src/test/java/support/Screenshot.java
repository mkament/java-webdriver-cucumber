package support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static support.TestContext.getTimestamp;
import static support.TestContext.saveFile;

public interface Screenshot {

    default void takeScreenshot() {
        TakesScreenshot screen = (TakesScreenshot) TestContext.getDriver();
        byte[] screenshotBytes = screen.getScreenshotAs(OutputType.BYTES);
        saveFile("screenshot" + getTimestamp(), "png", screenshotBytes);
    }
}