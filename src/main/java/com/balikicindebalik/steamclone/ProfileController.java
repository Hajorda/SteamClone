package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

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
    private Button adminBtn;

    @FXML
    private Button cardLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private VBox friendsVbox;

    @FXML
    private VBox gamesVbox;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label usernameLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

     @FXML
    void GoCard(ActionEvent event) throws Exception {

                root = FXMLLoader.load(getClass().getResource("cardV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

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
    void adminBtnAction(ActionEvent event) {

            try {
                root = FXMLLoader.load(getClass().getResource("adminV01.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e);
            }
    }


    @FXML
    void initialize() {

        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert StoreBtn != null : "fx:id=\"StoreBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert adminBtn != null : "fx:id=\"adminBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ProfileV01.fxml'.";

        if (Current.getCurrentUser().getUserName().equalsIgnoreCase("admin")) {
            adminBtn.setVisible(true);
        } else {
            adminBtn.setVisible(false);
        }

        QueriesUtil queriesUtil = new QueriesUtil();
        cardLabel.setText(cardLabel.getText() + " "+ queriesUtil.getBasket().size());

        nameLabel.setText("Name: " + Current.getCurrentUser().getName());
        surnameLabel.setText("Surname: " + Current.getCurrentUser().getSurname());
        usernameLabel.setText("Username: " + Current.getCurrentUser().getUserName());
        emailLabel.setText("Email: " + Current.getCurrentUser().getEmail());

    }




}
