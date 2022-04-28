package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        container.setPadding(new Insets(20));
        container.setSpacing(20);
        container.setAlignment(Pos.TOP_CENTER);
        long startTime = System.currentTimeMillis();

        // Based on which Button was pressed -> Different Content is Loaded into the Article List
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
        System.out.printf("API Call needed %dms %n", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        // Articles are put into Scene with Author and Title property
        for (Article a : newsList) {
            container.getChildren().add(buildArticleItem(a));
        }

        System.out.printf("GUI generation needed %dms %n", System.currentTimeMillis() - startTime);

        Button toTop = new Button("To Top");
        toTop.setOnAction(e -> toTop(scrollPane));

        // Button to be able to go back to the MainScene
        Button goBack = new Button("<---");
        goBack.setOnAction(e -> goBack());

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setMinWidth(Math.floor(WIDTH/3.0));
        buttonContainer.setSpacing(WIDTH / 10.0);
        buttonContainer.getChildren().addAll(goBack, toTop);

        container.getChildren().add(buttonContainer);

        scrollPane.setContent(container);
        scrollPane.setFitToWidth(true);


        return new Scene(scrollPane, WIDTH, HEIGHT);

    }

    /**
     * Based on the Information the Artilce holds, it creates a new GUI Article Element to display the Article properly
     * @param a - Given article to display
     * @return - HBox Container that can be added to the GUI
     */
    private static HBox buildArticleItem(Article a) {
        HBox container = new HBox();
        VBox image = new VBox();
        VBox articleInfo = new VBox();

        //Image
        Image articlePicture;
        if (a.getUrlToImage() != null) articlePicture = new Image(a.getUrlToImage(),175, 0, true, false);
        else articlePicture = new Image("at/ac/fhcampuswien/imgNotFound.png", 175, 0, true, false);

        ImageView articlePictureView = new ImageView(articlePicture);
        image.getChildren().add(articlePictureView);

        //ArticleInfo
        Label title = new Label(a.getTitle());
        title.setTextAlignment(TextAlignment.RIGHT);
        title.setPadding(new Insets(0, 0,10,0 ));
        title.setStyle("-fx-font: 12 Verdana; -fx-font-weight: bold;");
        title.setWrapText(true);

        Label writtenBy = new Label("Written by: " + a.getAuthor());
        writtenBy.setWrapText(true);
        writtenBy.setTextAlignment(TextAlignment.RIGHT);

        Label date = new Label(formatDate(a.getPublishedAt()));
        date.setWrapText(true);

        articleInfo.setStyle("-fx-font: 11 Verdana");
        articleInfo.setAlignment(Pos.CENTER_RIGHT);
        articleInfo.setPadding(new Insets(0, 10, 0, 10));
        articleInfo.getChildren().addAll(title, writtenBy, date);

        container.getChildren().addAll(image, articleInfo);
        return container;
    }

    /**
     * Takes the Date format given by the NewsApi and converts it into a more readable form
     * @param date The given date by the NewsApi
     * @return Formatted Date in 'dd.MM.yyyy HH:mm:ss' format
     */
    private static String formatDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(date, pattern);
        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    private static void toTop(ScrollPane pane) {
        pane.setVvalue(0);
    }
}
