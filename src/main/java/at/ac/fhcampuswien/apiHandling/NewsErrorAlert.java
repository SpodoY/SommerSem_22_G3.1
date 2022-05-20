package at.ac.fhcampuswien.apiHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class NewsErrorAlert {

    public static void createAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.CLOSE);
        alert.showAndWait();
    }
}
