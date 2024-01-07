package com.balikicindebalik.steamclone;

import java.io.IOException;
import java.net.URL;
import java.util.Currency;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField passTF;

    @FXML
    private TextField surNameTF;

    @FXML
    private TextField userNameTF;

    @FXML
    private Label warningLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void GoLogin(ActionEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("loginV01.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e);
            }
    }

    QueriesUtil queriesUtil = new QueriesUtil();

    final String[] emailcheck = {"@gmail.com", "@hotmail.com", "@admin", "@tedu.edu.tr"};

    @FXML
    void createUserAction(ActionEvent event) {

        //System.out.println("userNameTF.getText() " + userNameTF.getText() + " " + "NameTF.getText() " + NameTF.getText() +" "+ "surNameTF.getText() " + surNameTF.getText() + " " + "emailTF.getText() " + emailTF.getText() +" "+ "passTF.getText() " + passTF.getText()   );

        if(userNameTF.getText().equals("") ||  NameTF.getText().equals("") || surNameTF.getText().equals("") || emailTF.getText().equals("") || passTF.getText().equals("")){
            //System.out.println("Bütün Hepsi Doldurulmadı");
            warningLabel.setTextFill(Color.RED);
            warningLabel.setText("Bütün Hepsi Doldurulmadı");
        }
        else{
            //System.out.println("Bütün Hepsi Doldu");
            //warningLabel.setTextFill(Color.GREEN);
            //warningLabel.setText("Bütün Hepsi Doldu");

            boolean b = false;

            for(String s : emailcheck){
                if(emailTF.getText().indexOf(s) == -1){
                    //System.out.println("içinde yok");
                    warningLabel.setTextFill(Color.RED);
                    warningLabel.setText("Doğru bir mail girin");
                }
                else{
                    System.out.println("içinde var");
                    b = true;
                }
            }

            if(b){
                //warningLabel.setTextFill(Color.GREEN);
                //warningLabel.setText("Bütün Hepsi Doldu");

                b = true;
                for(User u : queriesUtil.getUsers()){
                    if(u.getUserName().equals(userNameTF.getText()) ){
                        b = false;
                    }
                }

                if(b){
                    //System.out.println("çakışan username yok");
                    warningLabel.setTextFill(Color.GREEN);
                    warningLabel.setText("Veriler Doğru + Kullanıcı Oluşturulabilir");

                    User C_user = new User(0, userNameTF.getText(), NameTF.getText(), surNameTF.getText(), emailTF.getText(), passTF.getText());
                    queriesUtil.addUser(C_user);

                    Current.setCurrentUser(C_user);

                     try {
                        root = FXMLLoader.load(getClass().getResource("storeV01.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    //System.out.println("çakışan username var");
                    warningLabel.setTextFill(Color.RED);
                    warningLabel.setText("username daha önce kullanılmış");
                }

            }

        }

    }

    @FXML
    void initialize() {
        assert NameTF != null : "fx:id=\"NameTF\" was not injected: check your FXML file 'SignInPageV01.fxml'.";
        assert emailTF != null : "fx:id=\"emailTF\" was not injected: check your FXML file 'SignInPageV01.fxml'.";
        assert passTF != null : "fx:id=\"passTF\" was not injected: check your FXML file 'SignInPageV01.fxml'.";
        assert surNameTF != null : "fx:id=\"surNameTF\" was not injected: check your FXML file 'SignInPageV01.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'SignInPageV01.fxml'.";
        assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'SignInPageV01.fxml'.";

    }

}
