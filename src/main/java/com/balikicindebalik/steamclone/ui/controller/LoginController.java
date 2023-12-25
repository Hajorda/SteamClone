package com.balikicindebalik.steamclone.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void loginAction(MouseEvent event) {
        System.out.println("Login button clicked");
        System.out.println("Username: " + User.getText());
        System.out.println("Password: " + Password.getText());
    }

    @FXML
    void initialize() {
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'loginV01.fxml'.";
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'loginV01.fxml'.";

    }

}
