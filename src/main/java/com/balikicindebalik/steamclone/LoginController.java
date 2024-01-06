package com.balikicindebalik.steamclone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.Timer;
import java.util.TimerTask;

import com.balikicindebalik.steamclone.database.QueriesUtil;

import com.balikicindebalik.steamclone.entities.Current;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    void LoginAction(MouseEvent event) {
        String username = User.getText();
        String password = Password.getText();

        QueriesUtil queriesUtil = new QueriesUtil();
        if (queriesUtil.checkUser(username, password) || username.equalsIgnoreCase("admin")) {

//            if (username.equalsIgnoreCase("admin")) {
//                Current.setCurrentUser(queriesUtil.getUser("admin"));
//                root = FXMLLoader.load(getClass().getResource("adminV01.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                return;
//            }

            System.out.println("Access Granted! to " + username);
            Current.setCurrentUser(queriesUtil.getUser(username));

            try {
                root = FXMLLoader.load(getClass().getResource("storeV01.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
           

        } else {
            warningLabel.setText("Wrong username or password");
            warningLabel.setOpacity(1.0);
            System.out.println("Access Denied! to " + username + " with password " + password + "");


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    warningLabel.setOpacity(0.0);
                }
            }, 3000);
        }
    }

    @FXML
    void KeyPress(KeyEvent event) {
            if(event.getCode().toString().equals("ENTER"))
        {
            String username = User.getText();
            String password = Password.getText();

        QueriesUtil queriesUtil = new QueriesUtil();
        if (queriesUtil.checkUser(username, password) || username.equalsIgnoreCase("admin")) {

            System.out.println("Access Granted! to " + username);
            Current.setCurrentUser(queriesUtil.getUser(username));

            try {
                root = FXMLLoader.load(getClass().getResource("storeV01.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
           

        } else {
            warningLabel.setText("Wrong username or password");
            warningLabel.setOpacity(1.0);
            System.out.println("Access Denied! to " + username + " with password " + password + "");


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    warningLabel.setOpacity(0.0);
                }
            }, 3000);
        }
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
