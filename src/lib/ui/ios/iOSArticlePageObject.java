package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        SEARCH_BUTTON = "xpath://android.widget.TextView[@content-desc='Search Wikipedia']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        NAVIGATION_ARTICLE_BAR = "xpath://XCUIElementTypeNavigationBar";
        MAIN_PAGE_BUTTON = "id:W";

    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}
