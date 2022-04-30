package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.gui.StartScreen.getApp;

public class NewGUI extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NewGUI.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("NewsGUI");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
