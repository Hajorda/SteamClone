package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileBtn;

    @FXML
    private Button StoreBtn;

    @FXML
    private AnchorPane rootPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void GoProfile(ActionEvent event) {

    }

    @FXML
    void GoStore(ActionEvent event) throws Exception {

                root = FXMLLoader.load(getClass().getResource("StoreV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    void initialize() {
        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert StoreBtn != null : "fx:id=\"StoreBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ProfileV01.fxml'.";

    }

}
