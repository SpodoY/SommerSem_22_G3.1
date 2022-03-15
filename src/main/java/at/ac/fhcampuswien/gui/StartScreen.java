package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;

    /* Pane & Shit */
    private static final VBox CONTAINER = new VBox();
    private static Scene mainScene;
    private static Stage mainStage;

    /* App Stuff */
    private static AppController app = new AppController();

    public static void main(String[] args) {

        Platform.startup(() -> {
            Button atButton = new Button("AT News");
            Button bitButton = new Button("Bitcoin News");
            Button countButton = new Button("Article Count");
            Button quitButton = new Button("Quit");

            atButton.setOnAction(e -> loadNewScene("AT"));
            bitButton.setOnAction(e -> loadNewScene("Bit"));
            countButton.setOnAction(e -> loadNewScene("Count"));
            quitButton.setOnAction(e -> loadNewScene("Fuck this shit I'm out"));

            CONTAINER.getChildren().addAll(atButton, bitButton, countButton, quitButton);

            mainScene = new Scene(CONTAINER, WIDTH, HEIGHT);

            mainStage = new Stage();
            mainStage.setTitle("News App OwO");
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.show();
        });
    }

    private static void loadNewScene(String scene) {

    }
}
