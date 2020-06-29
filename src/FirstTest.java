import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
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
    public void findCollectionsArticle() throws InterruptedException {
        waitForElementAndClick(
                xpath("//*[contains(@text,'SKIP')]"),
                "Не могу найти кнопку Skip",
                5
        );
        waitForElementAndClick(
                xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Не могу найти поле для ввода запроса",
                5
        );
        waitForElementAndSendKeys(
                xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Doom Patrol",
                "Cannot find input",
                5
        );
        WebElement element = waitForElementPresent(
                xpath( "//*[@index='1']"),
                "Не найдено больше одного результата",
                15
        );
        waitForElementAndClear(
                xpath("//*[contains(@text,'Doom Patrol')]"),
                "Строка поиска не очищена",
                5
        );
        WebElement add_language_button = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/add_languages_button']"),
                "Cannot find add button",
                15
        );

        String add_button_text = add_language_button.getText();

        Assert.assertEquals(
                "Не найден текст",
                "ADD WIKIPEDIA LANGUAGES",
                add_button_text
        );
    }

    private WebElement waitForElementAndClick(By by, String error, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error, timeoutInSeconds);
        element.clear();
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

}




