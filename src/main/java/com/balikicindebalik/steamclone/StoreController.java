package com.balikicindebalik.steamclone;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.balikicindebalik.steamclone.database.QueriesUtil;
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

public class StoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProfileBtn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox vbox;

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


    // Hbox generation

        public HBox generateHBox(String gameName, String gamePrice, String gamePicture) {

            HBox hbox = new HBox();
            hbox.setHgrow(hbox, javafx.scene.layout.Priority.ALWAYS);
            hbox.setSpacing(10);
            hbox.setPadding(new javafx.geometry.Insets(10, 0, 10, 0));
            hbox.setStyle("-fx-background-color: #FF12FF;");
            HBox.setMargin( hbox, new Insets(10, 0, 10, 0));

            Button gameButton = new Button(gameName);
            Label gameNameLabel = new Label(gameName);
            Label gamePriceLabel = new Label(gamePrice);



            gameButton.setOnAction(e -> {
                System.out.println("Game name: " + gameName);
                System.out.println("Game price: " + gamePrice);

                System.out.println("Game picture: " + gamePicture);
            });
            hbox.getChildren().addAll(gameButton, gameNameLabel, gamePriceLabel);
            return hbox;
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
        }
    }

}
