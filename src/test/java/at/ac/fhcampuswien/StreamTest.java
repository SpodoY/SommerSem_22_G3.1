
package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsResponse;
import com.google.gson.Gson;
import com.sun.javafx.scene.control.skin.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamTest {

    private static AppController app;
    private static Gson gson;

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
            NewsResponse user = gson.fromJson(reader,NewsResponse.class);


            // close reader
            reader.close();

            app.setArticles(user.getArticles());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Test
    @DisplayName("Test Stream for headlines smaller than 15 digits")
    public void TestStreamHeadlines()  {
        //Arrange
        List<Article> filter =  app.getArticles();

        //Act
        app.setArticles( app.headLessThan15());

        //Assert
        assertEquals(1,app.getArticleCount());

        //Rest
        app.setArticles(filter);
    }

}

