package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.gui.StartScreen.*;

public class SceneLoader {

    public static Scene loadScene(String command) {

        AppController app = getApp();
        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        container.setPadding(new Insets(10));
        container.setAlignment(Pos.TOP_CENTER);

        List<Article> newsList = new ArrayList<>();
        switch (command) {
            case "AT" -> newsList = app.getTopHeadlinesAustria();
            case "Bit" -> newsList = app.getAllNewsBitcoin();
            case "Count" -> {
                Label label = new Label("Amount of Articles: " + app.getArticleCount());
                label.setStyle("-fx-font: 36 Verdana");
                container.setAlignment(Pos.CENTER);
                container.getChildren().add(label);
            }
            case "Fuck this shit I'm out" -> System.exit(0);
        }
        //TODO: Style Text better
        for (Article a : newsList) {
            Label label = new Label(a.toString());
            label.setStyle("-fx-font: 14 Verdana");
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.CENTER);
            container.getChildren().add(label);
        }

        Button goBack = new Button("<---");
        goBack.setOnAction(e -> goBack());
        container.getChildren().add(goBack);

        scrollPane.setContent(container);
        scrollPane.setFitToWidth(true);

        return new Scene(scrollPane, WIDTH, HEIGHT);
    }
}
