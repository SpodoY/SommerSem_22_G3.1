package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.SingletonAppController;
import at.ac.fhcampuswien.enums.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CustomSelectorController implements Initializable {

    @FXML private ChoiceBox<Endpoint> endpoint;
    @FXML private ChoiceBox<Category> category;
    @FXML private ChoiceBox<Language> language;
    @FXML private ChoiceBox<Country> country;
    @FXML private ChoiceBox<Sortby> sortBy;
    @FXML private ChoiceBox<Keywords> keyword;
    @FXML private Button submit;

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

        // Adds all countries to Country-Dropdown
        Arrays.stream(Country.values()).forEach(country -> this.country.getItems().add(country));

        // Adds all Categories to Category-Dropdown
        Arrays.stream(Category.values()).forEach(category -> this.category.getItems().add(category));

        // Adds all languages to Language-Dropdown
        Arrays.stream(Language.values()).forEach(language -> this.language.getItems().add(language));

        // Adds all sortTypes to SortBy-Dropdown
        Arrays.stream(Sortby.values()).forEach(sortby -> sortBy.getItems().add(sortby));

        // Adds all keywords to KeyWord-Dropdown
        Arrays.stream(Keywords.values()).forEach(keywords -> keyword.getItems().add(keywords));

        country.setValue(Country.NONE); category.setValue(Category.NONE); endpoint.setValue(Endpoint.NONE);
        language.setValue(Language.NONE); sortBy.setValue(Sortby.NONE); keyword.setValue(Keywords.NONE);

        // Disables submit-button is either endpoint is not set or category/keyword with corresponding
        // endpoint is not set
        submit.disableProperty().bind(Bindings.isEmpty(keyword.valueProperty().asString())
                .and(Bindings.equal("everything", endpoint.valueProperty().asString()))
                .or((Bindings.isEmpty(category.valueProperty().asString())
                        .and(Bindings.equal("top-headlines", endpoint.valueProperty().asString())))
                ).or(Bindings.isEmpty(endpoint.valueProperty().asString())));
    }

    private void updateEndpoint() {
        Endpoint endpointValue = endpoint.getValue();

        category.setDisable(endpointValue == Endpoint.EVERYTHING);
        category.setValue(Category.NONE);

        keyword.setDisable(endpointValue == Endpoint.TOP_HEADLINES);
        keyword.setValue(Keywords.NONE);
    }

    private List<Enum> fetchDropdownData() {
        List<Enum> callEnums = new ArrayList<>();
        callEnums.add(endpoint.getValue());
        callEnums.add(category.getValue());
        callEnums.add(keyword.getValue());
        callEnums.add(language.getValue());
        callEnums.add(country.getValue());
        callEnums.add(sortBy.getValue());
        return callEnums;
    }

    public void makeAPICall() {
        List<Enum> callEnums = new ArrayList<>();
        callEnums = fetchDropdownData();


        SingletonAppController.passCustomeNewsString(callEnums);
        goToCustomNews();
    }

    @FXML
    private void goToCustomNews() {
        Parent tableViewParent;
        try { tableViewParent = FXMLLoader.load(getClass().getResource("customNews.fxml")); }
        catch (IOException e) { throw new RuntimeException(e); }
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) endpoint.getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
