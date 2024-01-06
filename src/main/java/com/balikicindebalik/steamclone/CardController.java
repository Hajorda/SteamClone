package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private Label totalLabel;

    @FXML
    private Button purchaseButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vBox;

    private double totalPrice = 0;

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

        root = FXMLLoader.load(getClass().getResource("storeV01.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void purchaseButton(ActionEvent event) {
        vBox.getChildren().clear();
    }

    HBox generateGameTile(Game game){

        HBox hBox = new HBox();
        Button gamebutton = new Button(game.getGameName());
        gamebutton.setPrefWidth(209);
        gamebutton.setPrefHeight(30);

        Button deleteButton = new Button("X");
        deleteButton.setStyle("-fx-background-color: #FFFFF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #94949a; -fx-border-width: 2px;");
        deleteButton.setPrefWidth(100);
        deleteButton.setPrefHeight(30);
        deleteButton.setStyle("-fx-background-color: rgba(255, 99, 71, 0.8)");
        deleteButton.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));

        Label gameprice = new Label(game.getGamePrice());
        gameprice.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        gameprice.setPrefWidth(209);
        gameprice.setPadding(new javafx.geometry.Insets(7, 50, 0, 0));
        hBox.setStyle("-fx-background-color: #FFFFF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: #94949a; -fx-border-width: 2px;");
        HBox.setHgrow(hBox, javafx.scene.layout.Priority.ALWAYS);
        HBox.setMargin( hBox, new Insets(10, 0, 10, 0));
        hBox.setSpacing(1);
        hBox.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));
        hBox.getChildren().addAll(gamebutton, deleteButton, gameprice);

        //When cliclked on game button
        gamebutton.setOnAction(e -> {
            System.out.println("button");
            System.out.println("Game name: " + game.getGameName());
            System.out.println("Game price: " + game.getGamePrice());

            QueriesUtil queriesUtil = new QueriesUtil();
            Game game1 = queriesUtil.getGame(game.getGameName());

            Current.setCurrentGame(game1);


            try {
                root = FXMLLoader.load(getClass().getResource("GameAppV01.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        //When clicked on delete button
        deleteButton.setOnAction(e -> {
            System.out.println("button");
            System.out.println("Game name: " + game.getGameName());
            System.out.println("Game price: " + game.getGamePrice());

            QueriesUtil queriesUtil = new QueriesUtil();
            queriesUtil.deleteFromBasket(game);

            try {
                root = FXMLLoader.load(getClass().getResource("cardV01.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        return hBox;
    }

    @FXML
    void initialize() {
        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert StoreButton != null : "fx:id=\"StoreButton\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert balanceLabel != null : "fx:id=\"balanceLabel\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert purchaseButton != null : "fx:id=\"purchaseButton\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'cardV01.fxml'.";
        assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'cardV01.fxml'.";

        balanceLabel.setText("Balance:" + 31.5 + " TL");
        //vBox.getChildren().add(generateGameTile(Current.getCurrentGame()));

        QueriesUtil queriesUtil = new QueriesUtil();
        List<Game> gameList = queriesUtil.getBasket();
        for (Game game : gameList) {
            System.out.println(game.getGameName());
        }
        for (Game game : gameList) {
            vBox.getChildren().add(generateGameTile(game));
            System.out.println(game.getGameName());
            if (!game.getGamePrice().equals("Free")){
                totalPrice += Double.parseDouble(game.getGamePrice().substring(1).replace(",", "."));
            }

        }

        totalLabel.setText("Total: " + totalPrice + " TL");

    }

}
