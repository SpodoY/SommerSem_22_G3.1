package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.enums.Endpoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomSelectorController implements Initializable {

    @FXML
    private ChoiceBox<Endpoint> endpoint;

    @FXML
    private ChoiceBox<String> category;

    private Endpoint endpointValue;

    @FXML
    public void goToMainWindow(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("startingWindow.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endpoint.getItems().add(Endpoint.EVERYTHING);
        endpoint.getItems().add(Endpoint.TOP_HEADLINES);
        endpoint.setOnAction(e -> updateEndpoint());
    }

    private void updateEndpoint() {
        endpointValue = endpoint.getValue();

        System.out.println(endpointValue);
        category.setDisable(endpointValue == Endpoint.TOP_HEADLINES);
    }
}
