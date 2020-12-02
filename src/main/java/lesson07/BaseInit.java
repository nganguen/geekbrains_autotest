package lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseInit {

    protected WebDriver driver;
    protected WebDriverWait wait10sec;
    protected WebDriverWait wait30sec;

    public BaseInit(WebDriver driver) {
        this.driver = driver;
        this.wait10sec = new WebDriverWait(driver, 10);
        this.wait30sec = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }
}
