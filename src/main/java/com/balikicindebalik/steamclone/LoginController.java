package com.balikicindebalik.steamclone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.Node;


public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField Password;

    @FXML
    private TextField User;

    @FXML
    private Label warningLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    

    @FXML
    void LoginAction(MouseEvent event) throws IOException {
        String username = User.getText();
        String password = Password.getText();
        QueriesUtil queriesUtil = new QueriesUtil();
        if(queriesUtil.checkUser(username,password)){
            System.out.println("Giriş başarılı");

                root = FXMLLoader.load(getClass().getResource("storeV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

        }
        else{
        warningLabel.setText("Kullanıcı adı veya şifre yanlış");
        System.out.println("Kullanıcı adı veya şifre yanlış");
            }    
        }

    @FXML
    void initialize() {
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'loginV01.fxml'.";

    }

}
