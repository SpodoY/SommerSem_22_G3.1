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
    static void init(){
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
    @DisplayName("If returned Lists are Bitcoin articles")
    public void bitcoinNewsTest() {
        for (Article a: app.filterList("bitcoin",app.getArticles())) {
            assertTrue(a.toString().toLowerCase().contains("bitcoin"));
        }
    }

    @Test
    @DisplayName("Filtering based on query string")
    public void filterListTest() {
        assertEquals(app.filterList("top",app.getArticles()).size(), 3);
    }

    @Test
    @DisplayName("Filtering based on query string")
    public void filterListTest1() {
        assertEquals(app.filterList("",app.getArticles()).size(), 0);
    }

}
