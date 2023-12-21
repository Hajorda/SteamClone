package com.balikicindebalik.steamclone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField Password;

    @FXML
    private TextField User;

    @FXML
    void LoginAction(ActionEvent event) {
        System.out.println("LoginAction");
        System.out.println(User.getText());
        System.out.println(Password.getText());
    }

}
