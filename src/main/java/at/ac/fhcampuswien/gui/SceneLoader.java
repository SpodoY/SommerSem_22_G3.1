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

public class SceneLoader {

    public static Scene loadScene(String command) {
        AppController app = getApp();

        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setAlignment(Pos.TOP_CENTER);

        List<Article> newsList = new ArrayList<>();
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
        //TODO: Style Text better
        for (Article a : newsList) {
            Text t = new Text(a.toString());
            t.setStyle("-fx-font: 14 Verdana");
            t.wrappingWidthProperty().bind(container.widthProperty());
            t.setTextAlignment(TextAlignment.CENTER);
            container.getChildren().add(t);
        }

        Button goBack = new Button("<---");
        goBack.setOnAction(e -> goBack());
        container.getChildren().add(goBack);
        return new Scene(container, WIDTH, HEIGHT);
    }
}
