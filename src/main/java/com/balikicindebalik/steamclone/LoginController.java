package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    @FXML
    void LoginAction(MouseEvent event) {
        String username = User.getText();
        String password = Password.getText();
        QueriesUtil queriesUtil = new QueriesUtil();
        if(queriesUtil.checkUser(username,password)){
            System.out.println("Giriş başarılı");
        }
        else
        warningLabel.setText("Kullanıcı adı veya şifre yanlış");
    }

    @FXML
    void initialize() {
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'loginV01.fxml'.";

    }

}
