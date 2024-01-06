package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileBtn;

    @FXML
    private Button StoreBtn;

    @FXML
    private Button adminBtn;

    @FXML
    private Button cardLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private VBox friendsVbox;

    @FXML
    private VBox gamesVbox;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label usernameLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

     @FXML
    void GoCard(ActionEvent event) throws Exception {

                root = FXMLLoader.load(getClass().getResource("cardV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    void GoProfile(ActionEvent event) {


    }

    @FXML
    void GoStore(ActionEvent event) throws Exception {

                root = FXMLLoader.load(getClass().getResource("StoreV01.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

     @FXML
    void adminBtnAction(ActionEvent event) {

            try {
                root = FXMLLoader.load(getClass().getResource("AdminPageV01.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println(e);
            }
    }


    @FXML
    void initialize() {

        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert StoreBtn != null : "fx:id=\"StoreBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert adminBtn != null : "fx:id=\"adminBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ProfileV01.fxml'.";

        if (Current.getCurrentUser().getUserName().equalsIgnoreCase("admin")) {
            adminBtn.setVisible(true);
        } else {
            adminBtn.setVisible(false);
        }

        QueriesUtil queriesUtil = new QueriesUtil();
        cardLabel.setText(cardLabel.getText() + " "+ queriesUtil.getBasket().size());

        nameLabel.setText("Name: " + Current.getCurrentUser().getName());
        surnameLabel.setText("Surname: " + Current.getCurrentUser().getSurname());
        usernameLabel.setText("Username: " + Current.getCurrentUser().getUserName());
        emailLabel.setText("Email: " + Current.getCurrentUser().getEmail());

        gamesVbox.getChildren().add(generateGameTile(Current.getCurrentGame()));

    }

    //Add games to from inventory
    public HBox generateGameTile(Game game){

        HBox hBox = new HBox();
        hBox.setPrefHeight(10);
        hBox.setPrefWidth(500);
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #ababab; -fx-background-radius: 10px;");

        Button gameButton = new Button(game.getGameName());
        gameButton.setPrefHeight(50);
        gameButton.setPrefWidth(209);
        gameButton.setStyle("-fx-background-color: #933737; -fx-background-radius: 10px;");

        Label gameNameLabel = new Label(game.getMadeBy());
        gameNameLabel.setPrefWidth(209);
        gameNameLabel.setAlignment(Pos.CENTER_LEFT);
        gameNameLabel.setTextFill(Color.web("#ffffff"));


        Label label2 = new Label(game.getGameReleaseDate());
        label2.setPrefWidth(109);
        gameNameLabel.setAlignment(Pos.CENTER_LEFT);
        gameNameLabel.setTextFill(Color.web("#ffffff"));


        Label gamePriceLabel = new Label(game.getGamePrice() + " TL");
        gamePriceLabel.setPrefWidth(59);
        gamePriceLabel.setAlignment(Pos.CENTER_RIGHT);
        gamePriceLabel.setTextFill(Color.web("#27861c"));
        gamePriceLabel.setStyle("-fx-font-weight: bold;");
        gamePriceLabel.setPadding(new javafx.geometry.Insets(0, 30, 3, 0));

        gameButton.setOnAction(e -> {
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
        hBox.getChildren().addAll(gameButton, gameNameLabel, label2, gamePriceLabel);
        return hBox;

    }
    private void addGamesToVbox() {
        QueriesUtil queriesUtil = new QueriesUtil();
        gamesVbox.getChildren().clear();
        for (Game game : queriesUtil.getInventory()) {

            gamesVbox.getChildren().add(generateGameTile(game));
        }
    }




}
