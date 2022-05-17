
package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StreamTest {

    private static AppController app;
    private static Gson gson;
    private static final List<Article> resetList = new ArrayList<>();

    @BeforeAll
    static void init() {
        app = new AppController();

        gson = new Gson();

        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            URL res = StreamTest.class.getClassLoader().getResource("input.json");
            File file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            Reader reader = Files.newBufferedReader(Paths.get(absolutePath));

            // convert JSON string to User object
            NewsResponse user = gson.fromJson(reader, NewsResponse.class);


            // close reader
            reader.close();

            app.setArticles(user.getArticles());
            resetList.addAll(user.getArticles());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    @DisplayName("Test Stream for headlines smaller than 15 digits")
    public void testStreamHeadlines() {
        //Arrange

        //Act
        app.setArticles(app.headLessThan15());

        //Assert
        assertEquals(1, app.getArticleCount());

        //Rest
        app.setArticles(resetList);
    }

    @Test
    @DisplayName("Test if the next Article in List really has less digits in the description")
    public void testDescriptionLess() {
        //Arrange


        //Act
        app.setArticles(app.sortByLengthDescending());

        //Assert
        for (int i = 0; i < resetList.size()-1; i++) {
            assertEquals(resetList.get(i), app.getArticles().get(i));
        }
        //Rest
        app.setArticles(resetList);
    }

    @Test
    @DisplayName("Countertest if the next Article in List really has less digits in the description")
    public void testDescriptionLess2() {
        //Arrange
        List<Article> functionVictim = app.sortByLengthDescending();
        List<Article> changed;

        //Act
        changed = (app.getArticles().stream().sorted(Comparator.comparing(Article::getAuthorLength)).collect(Collectors.toList()));

        //Assert
        for (int i = 0; i < 10; i++) {
            assertNotEquals(functionVictim.get(i), changed.get(i));
        }
        //Rest
        app.setArticles(resetList);
    }

    @Test
    @DisplayName("Test if the Author with the longest name gets returned")
    public void testAuthorLength() {
        //Arrange
        String correct = "Reuters EditorialEditorialEditorialEditorialEditorialEditorialEditorial";
        String afterFunction;

        //Act
        afterFunction = app.authorLength(resetList);

        //Assert
        assertEquals(correct, afterFunction);

        //Rest
        app.setArticles(resetList);
    }

    @Test
    @DisplayName("Test if the Author with the longest name gets returned based on numeric value")
    public void testAuthorLength2() {
        //Arrange
        String correct = "Reuters EditorialEditorialEditorialEditorialEditorialEditorialEditorial";
        String afterFunction;

        //Act
        afterFunction = app.authorLength(app.getArticles());

        //Assert
        assertEquals(correct.length(), afterFunction.length());

        //Rest
        app.setArticles(resetList);
    }

    //Next test requires an implementation of sourcedata like id or name
   /* @Test
    @DisplayName("Tests the number of articles with New York Times in it ")
    public void testNewYorkTimes() {
        //Arrange
        int afterFunction;

        //Act
        afterFunction= app.sourceNewYorkTimes(app.getArticles());

        //Assert
        assertEquals(3, afterFunction);

        //Rest
        app.setArticles(resetList);

    }*/
}


