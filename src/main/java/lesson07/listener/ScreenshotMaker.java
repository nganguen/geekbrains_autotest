package lesson07.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {

    public static void makeScreenshot (WebDriver driver, String filename) {
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./target/screenshot/" + filename);
        try {
            FileUtils.copyFile(tmp, destination);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static byte[] makeScreenshotByte(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
