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
import java.util.List;
import java.util.Random;

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
        String search_word = "Doom Patrol";
        waitForElementAndSendKeys(
                xpath("//*[contains(@text,'" + search_field + "')]"),
                search_word,
                "Cannot find input" + search_field,
                5
        );
        waitForElementAndClick(
                xpath( "//*[@class='android.widget.TextView'][@text='" + search_word +"']"),
                "Не найдено ни одного результата",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button for saving the article",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='GOT IT']"),
                "Cannot Click on GOT IT button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"),
                "Cannot click on Create new list button",
                5
        );
        String my_list = "Something";
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                my_list,
                "Cannot click on Create new list button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'OK')]"),
                "Cannot click on OK button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find back button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index='1']"),
                "Cannot find article with index 1",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button for saving the article",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + my_list + "']"),
                "Cannot find " + my_list + " link",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
                "Cannot click on menu button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']"),
                "Cannot find My list link on menu",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + my_list + "']"),
                "Cannot find " + my_list + " button",
                5
        );

        int article = 2;
        waitForElementAndClick(
                By.xpath("//*[@class='android.view.ViewGroup'][@index='" + article + "']"),
                "Cannot click on article with index" + article,
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button for deleting the article",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Remove from " + my_list + "']"),
                "Cannot remove article",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
                "Cannot click on menu button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']"),
                "Cannot find My list link on menu",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + my_list + "']"),
                "Cannot find " + my_list + " button",
                5
        );
        String count_articles = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(count_articles),
                "Founded more than 1 element",
                5
        );
        int amountOfSearchResults = getAmountOfElements(
                By.xpath(count_articles)
        );
        System.out.println(amountOfSearchResults);
        Assert.assertTrue(
                "We found to few results",
                amountOfSearchResults == 1
        );

        String title = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "text",
                "Cannot find title of article",
                15
        );
        String title_after_open_article = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed",
                title,
                title_after_open_article
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

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAndGetAttribute(By by,String attribute,String error_message,long timeout)
    {
        WebElement element = waitForElementPresent(by,error_message,timeout);
        return element.getAttribute(attribute);
    }

}




