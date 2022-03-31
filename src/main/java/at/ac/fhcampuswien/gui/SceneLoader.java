package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.gui.StartScreen.*;

/**
 * Used to dynamically change the Scene of the {@link StartScreen} without having to create a new window
 */
public class SceneLoader {

    /**
     * Creates a new scene with content based on which command is given
     *
     * @param command - Which content should the scene be filled with
     * @return a new Scene filled with the desired Content
     */
    public static Scene loadScene(String command) {
      
        AppController app = getApp();
        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        List<Article> newsList = new ArrayList<>();

        container.setPadding(new Insets(10));
        container.setAlignment(Pos.TOP_CENTER);

        // Based on which Button was pressed -> Different Content is Loaded into the Article List
        switch (command) {
            case "AT" -> newsList = app.getArticles();
            case "Bit" -> newsList = app.getArticles();
            case "Count" -> {
                Label label = new Label("Amount of Articles: " + app.getArticleCount());
                label.setStyle("-fx-font: 36 Verdana");
                container.setAlignment(Pos.CENTER);
                container.getChildren().add(label);
            }
            case "Fuck this shit I'm out" -> System.exit(0);
        }

        // Articles are put into Scene with Author and Title property
        for (Article a : newsList) {
            Label label = new Label(a.toString());
            label.setStyle("-fx-font: 14 Verdana");
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.CENTER);
            container.getChildren().add(label);
        }

        // Button to be able to go back to the MainScene
        Button goBack = new Button("<---");
        goBack.setOnAction(e -> goBack());
        container.getChildren().add(goBack);

        scrollPane.setContent(container);
        scrollPane.setFitToWidth(true);

        return new Scene(scrollPane, WIDTH, HEIGHT);

    }

//    private static HBox buildArticleItem() {
//        HBox cotainer = new HBox();
//        URL url = new URL("https://www.wiwo.de/images/imago0047108890h/28208162/2-format11240.jpg");
//        InputStream inputStream = url.openStream();
//    }
}
