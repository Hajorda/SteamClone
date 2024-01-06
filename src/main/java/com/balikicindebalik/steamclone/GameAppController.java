package com.balikicindebalik.steamclone;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.naming.ldap.PagedResultsControl;

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
    private Button cardLabel;

    @FXML
    private Label warningLabel;

    @FXML
    void S(KeyEvent event) {

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void GoCard(ActionEvent event) throws Exception {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cardV01.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GoProfile(ActionEvent event) throws Exception {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProfileV01.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GoStore(ActionEvent event) throws Exception {


        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("storeV01.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void buyAction(MouseEvent event) {
        warningLabel.setOpacity(0.0);
        System.out.println("buying game");
        QueriesUtil queriesUtil = new QueriesUtil();

          if(queriesUtil.checkGameInventory(Current.getCurrentGame())){
            System.out.println("3");
            warningLabel.setText("Game already in inventory");
            warningLabel.setOpacity(1.0);
        }
        else if (queriesUtil.chechGameInBasket(Current.getCurrentGame())) {
            System.out.println("1");
            warningLabel.setText("Game already in basket");
            warningLabel.setOpacity(1.0);
            return;
        }
        else if (!queriesUtil.chechGameInBasket(Current.getCurrentGame())){
            System.out.println("2");
            queriesUtil.throwToBasket(Current.getCurrentGame());
            //cardLabel.setText(cardLabel.getText().substring(0,1) + queriesUtil.getBasket().size());
            cardLabel.setText("🛒" + " " + queriesUtil.getBasket().size());
            warningLabel.setText("Game added to basket");
            warningLabel.setOpacity(1.0);
        }

        else{
            System.out.println("4");
            warningLabel.setText("ZORT");
            warningLabel.setOpacity(1.0);
        }
        System.out.println("3");
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


        Game game = Current.getCurrentGame();

        game_title.setText(game.getGameName());
        gameDescription.setText(game.getDescription());
        gamePrice.setText(game.getGamePrice());
        System.out.println("image: " + game.getGameName() + ".jpg");
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResource(game.getGameName() + ".jpg")).toExternalForm());
            gamePicture.setPreserveRatio(true);
            gamePicture.setSmooth(true);
            gamePicture.setCache(true);
            gamePicture.setImage(image);
        } catch (Exception e) {
            System.out.println("image not found");
            try {

                Image image = new Image(Objects.requireNonNull(getClass().getResource("source.jpeg")).toExternalForm());
                gamePicture.setPreserveRatio(true);
                gamePicture.setSmooth(true);
                gamePicture.setCache(true);
                gamePicture.setImage(image);
            } catch (Exception exception) {
                System.out.println("!Source image not found");
            }
        }

        QueriesUtil queriesUtil = new QueriesUtil();
        cardLabel.setText(cardLabel.getText() + " " + queriesUtil.getBasket().size());
        //gamePicture.setImage(new Image(Objects.requireNonNull(getClass().getResource( game.getGameName() + ".jpg")).toExternalForm()));

    }
}
