package at.ac.fhcampuswien.gui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import static at.ac.fhcampuswien.gui.StartScreen.*;

public class SceneLoader {

    public static Scene loadScene() {
        return new Scene(new VBox(), WIDTH, HEIGHT);
    }
}
