package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button back;

    @FXML
    private Button buyButton;

    @FXML
    private Label gameDescription;

    @FXML
    private ImageView gamePicture;

    @FXML
    private Label gamePrice;

    @FXML
    private Label game_title;

    @FXML
    private Button store_button;

    @FXML
    void S(KeyEvent event) {

    }

    @FXML
    void buyAction(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert ProfileButton != null : "fx:id=\"ProfileButton\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert buyButton != null : "fx:id=\"buyButton\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert gameDescription != null : "fx:id=\"gameDescription\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert gamePicture != null : "fx:id=\"gamePicture\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert gamePrice != null : "fx:id=\"gamePrice\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert game_title != null : "fx:id=\"game_title\" was not injected: check your FXML file 'GameAppV01.fxml'.";
        assert store_button != null : "fx:id=\"store_button\" was not injected: check your FXML file 'GameAppV01.fxml'.";

    }

}
