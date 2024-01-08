package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StoreController {

    @FXML
    private Button cardbtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileBtn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField findGameField;

    @FXML
    private Button cardLabel;

    @FXML
    private Pane sutun;

    @FXML
    private VBox vbox;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void GoProfile(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("ProfileV01.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void GoCard(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("cardV01.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    //Searcing game boku yedik
    @FXML
    void gameSearchButton(MouseEvent event) {
        String gameName = findGameField.getText();
        System.out.println(gameName);
        QueriesUtil queriesUtil = new QueriesUtil();
        List<Game> gameList = queriesUtil.getGames();
        List<Game> searchedGameList = new ArrayList<>();
        for (Game game : gameList) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                searchedGameList.add(game);
            }
        }
        vbox.getChildren().clear();
        for (Game game : searchedGameList) {
            vbox.getChildren().add(generateHBox(game.getGameName(), game.getGamePrice(), "asd"));
            Pane p = new Pane();
            p.setPrefHeight(10);
            vbox.getChildren().add(p);
        }

        if (gameName.equals("")) { // yazı yoksa
            for (Game game : gameList) {
                vbox.getChildren().add(generateHBox(game.getGameName(), game.getGamePrice(), "asd"));
                Pane p = new Pane();
                p.setPrefHeight(10);
                vbox.getChildren().add(p);
            }
        }


    }

    // Hbox generation

    public HBox generateHBox(String gameName, String gamePrice, String gamePicture) {

        HBox hbox = new HBox();

        //hbox.setHgrow(hbox, javafx.scene.layout.Priority.ALWAYS);
        //hbox.setSpacing(10);
        hbox.setPadding(new javafx.geometry.Insets(10, 0, 10, 0));
        hbox.setStyle("-fx-background-color:  #d7d7d7;");
        HBox.setMargin(hbox, new Insets(10, 0, 10, 0));

        Button gameButton = new Button(gameName);
        gameButton.setPrefWidth(160);

        Label gameNameLabel = new Label(gameName);
        gameNameLabel.setPrefWidth(190);
        gameNameLabel.setAlignment(Pos.CENTER);

        Pane pp = new Pane();
        pp.setPrefWidth(166);

        Label gamePriceLabel = new Label(gamePrice);
        gamePriceLabel.setPrefWidth(170);
        gamePriceLabel.setAlignment(Pos.CENTER_RIGHT);
        gamePriceLabel.setTextFill(Color.web("#27861c"));


        gameButton.setOnAction(e -> {
            System.out.println("button");
            System.out.println("Game name: " + gameName);
            System.out.println("Game price: " + gamePrice);
            System.out.println("Game picture: " + gamePicture);

            QueriesUtil queriesUtil = new QueriesUtil();
            Game game = queriesUtil.getGame(gameName);

            Current.setCurrentGame(game);
            System.out.println("a");

            try {
                root = FXMLLoader.load(getClass().getResource("GameAppV01.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });
        hbox.getChildren().addAll(gameButton, gameNameLabel, pp, gamePriceLabel);
        return hbox;
    }


         @FXML
    void FindGameListen(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER"))
        {
            this.gameSearchButton(null);
        }
    }

    @FXML
    void initialize() {
        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'storeV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'storeV01.fxml'.";

        // Hbox generation
        QueriesUtil queriesUtil = new QueriesUtil();
        List<Game> gameList = queriesUtil.getGames();

        for (Game game : gameList) {
            vbox.getChildren().add(generateHBox(game.getGameName(), game.getGamePrice(), "asd"));

            Pane p = new Pane();
            p.setPrefHeight(10);

            vbox.getChildren().add(p);
        }

        cardLabel.setText(cardLabel.getText() + " " + queriesUtil.getBasket().size());
    }

}
