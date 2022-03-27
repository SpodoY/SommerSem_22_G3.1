package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

        // Basic GUI Components
        AppController app = getApp();
        VBox container = new VBox();
        List<Article> newsList = new ArrayList<>();

        container.setPadding(new Insets(10));
        container.setAlignment(Pos.TOP_CENTER);

        // Based on which Button was pressed -> Different Content is Loaded into the Article List
        switch (command) {
            case "AT" -> newsList = app.getTopHeadlinesAustria();
            case "Bit" -> newsList = app.getAllNewsBitcoin();
            case "Count" -> {
                Text t = new Text("Amount of Articles: " + app.getArticleCount());
                t.setStyle("-fx-font: 36 Verdana");
                container.setAlignment(Pos.CENTER);
                container.getChildren().add(t);
            }
            case "Fuck this shit I'm out" -> System.exit(0);
        }

        // Articles are put into Scene with Author and Title property
        for (Article a : newsList) {
            Text t = new Text(a.toString());
            t.setStyle("-fx-font: 14 Verdana");
            t.wrappingWidthProperty().bind(container.widthProperty());
            t.setTextAlignment(TextAlignment.CENTER);
            container.getChildren().add(t);
        }

        // Button to be able to go back to the MainScene
        Button goBack = new Button("<---");
        goBack.setOnAction(e -> goBack());
        container.getChildren().add(goBack);

        //changes the current displayed scene to the changed one
        return new Scene(container, WIDTH, HEIGHT);
    }
}
