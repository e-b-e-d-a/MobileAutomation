package Tests.Ex;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class Ex11 extends CoreTestCase {
    private static final  String name_of_folder = "My Test Lists";

    @Test
    public void testSaveTwoArticleToMyListAndOneDelete() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.initSearchInput();
        String second_article_title = "Java";
        SearchPageObject.typeSearchLine("JavaScript");
        SearchPageObject.clickByArticleWithSubstring(second_article_title);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyCreatedList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.goToMainPage();
        }


        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeQuestionWindowSyncSaved();
        }
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.assertThereIsNoArticleWithsThatTitle(first_article_title);
        MyListsPageObject.waitForTitleArticleAndOpenIt(second_article_title);

        if (Platform.getInstance().isAndroid()) {
            assertEquals(
                    "Cannot find title of remaining article",
                    ArticlePageObject.getArticleTitle(), second_article_title
            );
        } else {
            assertEquals(
                    "Cannot find NAVIGATION_ARTICLE_BAR on page",
                    ArticlePageObject.getArticleBar(), second_article_title
            );
        }

    }


}