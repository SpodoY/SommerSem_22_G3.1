package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.SingletonAppController;
import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.apiHandling.PopUp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewsLoadingController implements Initializable {


    @FXML
    SingletonAppController app = SingletonAppController.getInstance();

    @FXML
    ListView<HBox> newsList;

    @FXML
    Label articleNum;

    @FXML
    private Label austria;

    @FXML
    private Label bitcoin;

    @FXML
    private Label customNews;

    private double imgWidth = 175;

    private double imgHeight = imgWidth / 16 * 9;

    public void fillGuiWithArticles(List<Article> articles) {
        if (newsList.getItems() != null) {
            newsList.getItems().clear();
        }

        articleNum.setText(String.format("Number of articles: %d", articles.size()));

        double windowWidth = newsList.getPrefWidth();

        for (Article a : articles) {
            HBox container = new HBox();
            VBox image = new VBox();
            VBox articleInfo = new VBox();

            //Image - An attempt to multi threading
            Runnable task = () -> {
                Platform.runLater(() -> {
                    Image articlePicture;
                    try {
                        articlePicture = new Image(a.getUrlToImage(), imgWidth, imgHeight, false, false);
                    } catch (Exception e) {
                        articlePicture = new Image("at/ac/fhcampuswien/imgNotFound.png", imgWidth, imgHeight, false, false);
                    }


                    ImageView articlePictureView = new ImageView(articlePicture);
                    image.getChildren().add(articlePictureView);
                    image.setAlignment(Pos.CENTER_LEFT);
                });
            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            //ArticleInfo
            Label title = new Label(a.getTitle());
            title.setPadding(new Insets(0, 0, 10, 0));
            title.setStyle("-fx-font: 12 Verdana; -fx-font-weight: bold;");
            title.setWrapText(true);

            Label writtenBy = new Label("Written by: " + a.getAuthor());
            Label date = new Label(formatDate(a.getPublishedAt()));

//          Label DesCount = new Label(""+a.getDescription().length());

            articleInfo.setStyle("-fx-font: 11 Verdana");
            articleInfo.setAlignment(Pos.CENTER_RIGHT);
            articleInfo.setPadding(new Insets(0, 10, 0, 10));
            articleInfo.getChildren().addAll(title, writtenBy, date);
            articleInfo.setMinWidth(windowWidth - imgWidth - 35);

            container.getChildren().addAll(image, articleInfo);
            container.setAlignment(Pos.CENTER_RIGHT);
            container.setMaxWidth(windowWidth - 35);
            newsList.getItems().add(container);

        }
//        checkIfEmpty(app.errorList());
    }

    /**
     * Takes the Date format given by the NewsApi and converts it into a more readable form
     *
     * @param date The given date by the NewsApi
     * @return Formatted Date in 'dd.MM.yyyy HH:mm:ss' format
     */
    private static String formatDate(String date) {
        try {
            DateTimeFormatter pattern = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(date, pattern);
            return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        } catch (Exception e) {
            return "now ;)";
        }

    }

    @FXML
    public void goToMainWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("startingWindow.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void mostArticles() {
        fillGuiWithArticles(app.guiSourceMostArticles());
        PopUp.createNotification(String.format("%s provides most Articles", app.sourceMostArticles()));
    }

    public void newYorkTimesArticles() {
        fillGuiWithArticles(app.guiSourceNewYorkTimes());
        PopUp.createNotification(String.format("There are %d New York Times Articles", app.sourceNewYorkTimes()));
    }

    public void authorLength() {
        fillGuiWithArticles(app.guiAuthorLength());
        PopUp.createNotification(String.format("The longest Author is: %s", app.authorLength()));
    }

    public void sortByLength() {
        fillGuiWithArticles(app.sortByLengthDescending());
    }

    public void lessThan15() {
        fillGuiWithArticles(app.headLessThan15());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Article> articles = new ArrayList<>();
        if (austria != null) articles = app.getTopHeadlinesAustria();
        if (bitcoin != null) articles = app.getAllNewsBitcoin();
        if (customNews != null) articles = app.getArticles();
        fillGuiWithArticles(articles);
    }

//    public void checkIfEmpty(List<Article> errorList) {
//        if (newsList.getItems().size() < 1) {
//            fillGuiWithArticles(errorList);
//        }
//    }

}
