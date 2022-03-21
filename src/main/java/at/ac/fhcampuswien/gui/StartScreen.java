package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Used as the Startup GUI where the user can choose which news or information he wants to be displayed
 */
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

            // Welcome Label
            Label welcomeText = new Label("News App - G3.1");
            welcomeText.setStyle("-fx-font: 36 Verdana");

            // Four buttons, each for a corresponding task
            Button atButton = new Button("AT News");
            Button bitButton = new Button("Bitcoin News");
            Button countButton = new Button("Article Count");
            Button quitButton = new Button("Quit");

            // Setting ActionHandlers to define method executed on click
            atButton.setOnAction(e -> loadNewScene("AT"));
            bitButton.setOnAction(e -> loadNewScene("Bit"));
            countButton.setOnAction(e -> loadNewScene("Count"));
            quitButton.setOnAction(e -> loadNewScene("Fuck this shit I'm out"));

            // Styling
            atButton.setMinWidth(WIDTH); atButton.setMinHeight(75);
            bitButton.setMaxWidth(Double.MAX_VALUE); bitButton.setMinHeight(75);
            countButton.setMaxWidth(Double.MAX_VALUE); countButton.setMinHeight(75);
            quitButton.setMaxWidth(Double.MAX_VALUE); quitButton.setMinHeight(75);

            CONTAINER.setSpacing(40);
            CONTAINER.setAlignment(Pos.CENTER);

            // Adding everything in the mainScene
            CONTAINER.getChildren().addAll(welcomeText, atButton, bitButton, countButton, quitButton);

            mainScene = new Scene(CONTAINER, WIDTH, HEIGHT);

            mainStage = new Stage();
            mainStage.setTitle("News App G3.1");
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.show();
        });
    }

    /**
     * Sets the Scene that is currently shown on the mainStage
     *
     * @param scene - The scene you want to be displayed
     */
    private static void loadNewScene(String scene) {
        mainStage.setScene(SceneLoader.loadScene(scene));
    }

    /**
     * Returns the {@link AppController}
     *
     * @return
     */
    public static AppController getApp() {
        return app;
    }

    /**
     * Needed to be able to go back to the mainScene after loading in another scene
     */
    public static void goBack() {
        mainStage.setScene(mainScene);
    }
 }
