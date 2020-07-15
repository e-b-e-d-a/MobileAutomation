package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        QUESTION_WINDOW_SYNC_SAVE_BUTTON  = "id:Close";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
    }

    public iOSMyListsPageObject (AppiumDriver driver) {
        super(driver);
    }
}
