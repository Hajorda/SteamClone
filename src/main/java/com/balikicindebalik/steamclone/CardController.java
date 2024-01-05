package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileBtn;

    @FXML
    private Button StoreButton;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button purchaseButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBox;

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
    void GoStore(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("ProfileV01.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void purchaseButton(ActionEvent event) {

    }



    @FXML
    void initialize() {
        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert StoreButton != null : "fx:id=\"StoreButton\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert balanceLabel != null : "fx:id=\"balanceLabel\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert purchaseButton != null : "fx:id=\"purchaseButton\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'cardV01.fxml'.";

        QueriesUtil queriesUtil = new QueriesUtil();




    }

}
