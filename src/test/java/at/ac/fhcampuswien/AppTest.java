package at.ac.fhcampuswien;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    private static AppController app;

    @BeforeAll
    static void init() {
        app = new AppController();
    }

    @Test
    @DisplayName("If no Articles given, size = 0")
    public void setArticlesSize0() {
        // Arrange
        AppController appController = new AppController();

        //Act
        appController.setArticles(new ArrayList<>());

        //Assert
        assertEquals(0, appController.getArticles().size());
    }

    @Test
    @DisplayName("Articles filled and order right")
    public void setTwoArticles() {
        // Arrange
        AppController appController = new AppController();
        List<Article> l = new ArrayList<>();

        // Act
        l.add(new Article("Tester1", "This is a test article"));
        l.add(new Article("Tester2", "This is also a test article"));
        appController.setArticles(l);

        // Assert
        assertEquals(2, appController.getArticles().size());
        assertEquals("Tester1", appController.getArticles().get(0).getAuthor());
        assertEquals("This is also a test article", appController.getArticles().get(1).getTitle());
    }

    @Test
    @DisplayName("If counted articles correct size")
    public void getArticleCountTest() {
        // Arrange
        int generated_mock_articles = 9;

        // Act
        int articleCount = app.getArticleCount();

        // Assert
        assertEquals(generated_mock_articles, articleCount);
    }


    @Test
    @DisplayName("If counted articles correct size")
    public void getArticleCountTest1() {
        // Arrange
        int zero_article = 0;

        // Act
        var app = new AppController();
        app.setArticles(new ArrayList<>());
        int articleCount = app.getArticleCount();

        // Assert
        assertEquals(zero_article, articleCount);
    }


    @Test
    @DisplayName("getTopHeadlinesAustria list contains 0 elements/articles")
    public void getTopHeadlinesAustriaTest_Scenario1() {
        //Arrange
        //Output should be an empty list - therefore new empty list
        List<Article> expectedList = new ArrayList<>();

        //Act
        var appController = new AppController();
        appController.setArticles(new ArrayList<>());
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }


    @Test
    @DisplayName("getTopHeadlinesAustria list contains 1 element/article")
    public void getTopHeadlinesAustriaTest_Scenario2() {
        //Arrange
        List<Article> expectedList = new ArrayList<>();
        Article article1 = new Article("The local Austrian", "The best commuter towns if you work in Vienna");
        expectedList.add(article1);

        //Act
        List<Article> articlesList = new ArrayList<>();
        articlesList.add(article1);

        var appController = new AppController();
        appController.setArticles(articlesList);
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }


    @Test
    @DisplayName("getTopHeadlinesAustria list contains more than 1 element/article")
    public void getTopHeadlinesAustriaTest_Scenario3() {
        //Arrange
        List<Article> expectedList = new ArrayList<>();
        Article article1 = new Article("The Austrian", "The start-up using tech to police global trade bans");
        expectedList.add(article1);
        Article article2 = new Article("The local Austrian", "The best commuter towns if you work in Vienna");
        expectedList.add(article2);
        Article article3 = new Article("The local", "Today in Austria: A roundup of the latest news");
        expectedList.add(article3);

        //Act
        List<Article> articlesList = new ArrayList<>();
        articlesList.add(article1);
        articlesList.add(article2);
        articlesList.add(article3);

        var appController = new AppController();
        appController.setArticles(articlesList);
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }


    @Test
    @DisplayName("If returned Lists are Bitcoin articles")
    /*A test for a successful search result. If every article really has the query in it  */
    public void bitcoinNewsTest() {
        //arrange
        String query = "bitcoin";                                                                                        //defining the query which is searched for
        //act
        for (Article a : app.filterList("bitcoin", app.getArticles())) {                                           //a for-each loop calls every element of the returned filtered list
            //assert
            assertTrue(a.toString().toLowerCase().contains("bitcoin"));                                                  //asserting if every entry in the list contains the query
        }
    }

    @Test
    @DisplayName("Filtering based on query string")
    /*A test for the general search query*/
    public void filterListTest() {
        //arrange
        String query = "top";                                                                                            //the query which is searched for
        int expectedSize = 3;                                                                                            //the expected number of successful matches

        //act
        int actualSize = app.filterList(query, app.getArticles()).size();                                                //using the function "filterList" to get a list of articles and then counting it

        //assert
        assertEquals(actualSize, expectedSize);                                                                          //comparing the size of the actual filtered list with the outcome that should be
    }

    @Test
    @DisplayName("Filtering based on query string")
    /*A test for an empty query*/
    public void filterListTest1() {
        //arrange
        String query = "";                                                                                               //the query which is searched for
        int expectedSize = 0;                                                                                            //the expected number of successfull matches

        //act
        int actualSize = app.filterList(query, app.getArticles()).size();                                                //using the function "filterList" to get a list of articles and then counting it

        //assert
        assertEquals(actualSize, expectedSize);                                                                          //comparing the size of the actual filtered list with the outcome that should be
    }


}
