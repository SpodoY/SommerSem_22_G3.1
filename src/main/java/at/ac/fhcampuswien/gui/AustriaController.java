package at.ac.fhcampuswien.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AustriaController {  //implements Initializable

    /**
     * When this method is called, it will change the Scene to
     * a TableView example
     */
    @FXML
    public void goToMainWindow(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    /*
    @FXML
    private ListView<String> austriaList;  //Article
    //@FXML
    //AppController app = getApp();
    @FXML
    private Label myLabel;

    String[] example = {"Article1", "Article2", "Article3"};

    String currentArticle; //Article

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //myListView.getItems().addAll(app.getTopHeadlinesAustria());
        austriaList.getItems().addAll(example);

        //changeListener
        /*
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue<? extends Article> observableValue, Article article, Article t1) {
                currentArticle = myListView.getSelectionModel().getSelectedItem();

                myLabel.set(currentArticle);
            }
        });
         */
    /*
        austriaList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentArticle = austriaList.getSelectionModel().getSelectedItem();

                myLabel.setText(currentArticle);


            }
        });

    }
     */
}
