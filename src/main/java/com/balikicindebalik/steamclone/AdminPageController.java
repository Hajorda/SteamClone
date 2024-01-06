package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void GoProfile(ActionEvent event) throws Exception {
                root = FXMLLoader.load(getClass().getResource("ProfileV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    void initialize() {

    }

}
