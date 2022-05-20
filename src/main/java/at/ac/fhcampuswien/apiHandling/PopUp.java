package at.ac.fhcampuswien.apiHandling;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PopUp {

    public static void createAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.CLOSE);
        alert.showAndWait();
    }

    public static void createNotification(String msg) {
        Alert message = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.CLOSE);
        message.showAndWait();
    }
}
