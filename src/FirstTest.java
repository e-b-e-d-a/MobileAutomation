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
    public void assertTextIsExist(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Не могу найти кнопку Skip",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Не могу найти поле для ввода запроса",
                5
        );
        WebElement search_line = waitForElementPresent(
           By.xpath("//*[@class='android.widget.EditText']"),
           "Не найдена строка поиска",
           15
        );

        String search_line_text = search_line.getText();
        Assert.assertEquals(
                "Текст не совпадает",
                "Search Wikipedia",
                search_line_text);
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

}




