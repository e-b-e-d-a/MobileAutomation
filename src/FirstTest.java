import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.openqa.selenium.By.xpath;

public class FirstTest {

    //emulator @and80
    //uiautomatorviewer

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("AutomationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\admin\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void saveArticles() {
        waitForElementAndClick(
                xpath("//*[contains(@text,'SKIP')]"),
                "Не могу найти кнопку Skip",
                5
        );
        String search_field = "Search Wikipedia";
        waitForElementAndClick(
                xpath("//*[contains(@text,'" + search_field + "')]"),
                "Не могу найти поле для ввода запроса" + search_field,
                5
        );
        String word = "Linkedin";
        waitForElementAndSendKeys(
                xpath("//*[contains(@text,'" + search_field + "')]"),
                word,
                "Cannot find input" + search_field,
                5
        );
        String title = "//*[@text='" + word +"']";
                waitForElementAndClick(
                xpath(title),
                "Не могу найти элемент" + word + "в списке статей",
                5
        );
        assertElementPresent(
                title,
                "Нет заголовка на странице");

    }

    private WebElement waitForElementAndClick(By by, String error, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private void assertElementPresent(String titleText,String error_message){
        String title = driver.findElementByXPath(titleText).getText();
        System.out.println(title);
        if (title == null){
            String default_message = "An element " + titleText + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

}




