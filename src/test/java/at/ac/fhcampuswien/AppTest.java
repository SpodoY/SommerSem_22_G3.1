/*
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
    @DisplayName("List of articles contains 0 Elements")
    public void headlinesAustriaTest_Scenario1() {
        //Arrange
        List<Article> expectedList = new ArrayList<>(); //Output should be an empty list - therefore new empty list

        //Act
        var appController = new AppController();
        appController.setArticles(new ArrayList<>());  //is set Articles ok? it is another method... and therefore my test isn't independent
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("List of articles contains Austrian Articles")
    public void headlinesAustriaTest_Scenario2() {
        //Arrange
        List<Article> articlesList = new ArrayList<>();
        //Austria - in Author
        Article article3 = new Article("The local Austrian", "The best commuter towns if you work in Vienna");
        articlesList.add(article3);
        //Austria - in Title
        Article article5 = new Article("The local", "Today in Austria: A roundup of the latest news");
        articlesList.add(article5);

        List<Article> expectedList = new ArrayList<>();
        expectedList.add(article3);
        expectedList.add(article5);

        //Act
        Article article1 = new Article("The New York Times", "First Amendment Scholars Want to See the Media Lose These Cases");
        articlesList.add(article1);
        Article article2 = new Article("The Australian", "The start-up using tech to police global trade bans");
        articlesList.add(article2);
        Article article4 = new Article("Metropole", "The Way the World Works: Economics vs. Reality");
        articlesList.add(article4);

        var appController = new AppController();
        appController.setArticles(articlesList);
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("List of articles contains NO Austrian Articles")
    public void headlinesAustriaTest_Scenario3() {
        //Arrange
        List<Article> expectedList = new ArrayList<>();  //No Austrian articles - that's why empty list

        //Act
        List<Article> articlesList = new ArrayList<>();
        Article article1 = new Article("The New York Times", "First Amendment Scholars Want to See the Media Lose These Cases");
        articlesList.add(article1);
        Article article2 = new Article("The Australian", "The start-up using tech to police global trade bans");
        articlesList.add(article2);
        Article article3 = new Article("The local Ostrian", "The best commuter towns if you work in Vienna");
        articlesList.add(article3);
        Article article4 = new Article("Metropole", "The Way the World Works: Economics vs. Reality");
        articlesList.add(article4);
        Article article5 = new Article("The local", "Today in Ostria: A roundup of the latest news");
        articlesList.add(article5);

        var appController = new AppController();
        appController.setArticles(articlesList);
        List<Article> actualList = appController.getTopHeadlinesAustria();

        //Assert
        assertEquals(expectedList, actualList);
    }


    @Test
    @DisplayName("If returned Lists are Bitcoin articles")
    */
/*A test for a successful search result. If every article really has the query in it  *//*

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
    */
/*A test for the general search query*//*

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
    */
/*A test for an empty query*//*

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
*/
