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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BTNfindFriend;

    @FXML
    private Button ProfileBtn;

    @FXML
    private Button StoreBtn;

    @FXML
    private TextField TFfindFriend;

    @FXML
    private Button adminBtn;

    @FXML
    private Label avarageTotalPrice;

    @FXML
    private Button cardLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label findfriendwarning;

    @FXML
    private VBox friendsVbox;

    @FXML
    private VBox gamesVbox;

    @FXML
    private Label maxPriceGame;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label totalGame;

    @FXML
    private Label totalPrice;

    @FXML
    private Label usernameLabel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    QueriesUtil queriesUtil = new QueriesUtil();

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
    void maxToMin(ActionEvent event) {

        gamesVbox.getChildren().clear();
        QueriesUtil queriesUtil = new QueriesUtil();
        for (Game game : queriesUtil.getInventory()) {
            gamesVbox.getChildren().add(generateGameTile(game));
        }

    }

    @FXML
    void minToMax(ActionEvent event) {

        gamesVbox.getChildren().clear();
        QueriesUtil queriesUtil = new QueriesUtil();
        for (Game game : queriesUtil.getInventory()) {
            gamesVbox.getChildren().add(generateGameTile(game));
        }

    }

    @FXML
    void FindGameListen(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")){
            BTNfindFriendAction(null);
        }
    }
    @FXML
    void gameSearchButton(MouseEvent event) {

    }

    @FXML
    void initialize() {

        assert BTNfindFriend != null : "fx:id=\"BTNfindFriend\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert ProfileBtn != null : "fx:id=\"ProfileBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert StoreBtn != null : "fx:id=\"StoreBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert TFfindFriend != null : "fx:id=\"TFfindFriend\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert adminBtn != null : "fx:id=\"adminBtn\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert avarageTotalPrice != null : "fx:id=\"avarageTotalPrice\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert cardLabel != null : "fx:id=\"cardLabel\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert findfriendwarning != null : "fx:id=\"findfriendwarning\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert friendsVbox != null : "fx:id=\"friendsVbox\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert gamesVbox != null : "fx:id=\"gamesVbox\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert maxPriceGame != null : "fx:id=\"maxPriceGame\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert surnameLabel != null : "fx:id=\"surnameLabel\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert totalGame != null : "fx:id=\"totalGame\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'ProfileV01.fxml'.";
        assert usernameLabel != null : "fx:id=\"usernameLabel\" was not injected: check your FXML file 'ProfileV01.fxml'.";

        findfriendwarning.setVisible(false);

        if (Current.getCurrentUser().getUserName().equalsIgnoreCase("admin")) {
            adminBtn.setVisible(true);
        } else {
            adminBtn.setVisible(false);
        }

        //QueriesUtil queriesUtil = new QueriesUtil();
        cardLabel.setText(cardLabel.getText() + " "+ queriesUtil.getBasket().size());

        nameLabel.setText("Name: " + Current.getCurrentUser().getName());
        surnameLabel.setText("Surname: " + Current.getCurrentUser().getSurname());
        usernameLabel.setText("Username: " + Current.getCurrentUser().getUserName());
        emailLabel.setText("Email: " + Current.getCurrentUser().getEmail());

        addGamesToVbox();

        totalGame.setText("Total Game: " + queriesUtil.numberOfItemsInInventory());

        avarageTotalPrice.setText("Avarage Price: " + queriesUtil.avaragePriceOfGamesInInventory());

        if(queriesUtil.maxPriceGameInInventory().getGameName() == null){
            //System.out.println("asd");
            maxPriceGame.setText("Max Price Game: " + "No Game");
        }
        else{
            //System.out.println("dsa");
            maxPriceGame.setText("Max Price Game: " + queriesUtil.maxPriceGameInInventory().getGamePrice());
        }
        
        //maxPriceGame.setText("Max Price Game: " + queriesUtil.maxPriceGameInInventory().getGamePrice());
        
        System.out.println(queriesUtil.totalPriceOfGameInInventory());
        totalPrice.setText("Total Price: " + queriesUtil.totalPriceOfGameInInventory());

        lable_giver();
     }

     @FXML
    void BTNfindFriendAction(ActionEvent event) {

        String FriendName = TFfindFriend.getText();
        //System.out.println("Yazdın = " + FriendName);
        if(queriesUtil.getUser(FriendName) == null){
            //System.out.println("Boyle kullanıcı Yok");
            findfriendwarning.setText("Boyle kullanıcı Yok");
            findfriendwarning.setTextFill(Color.RED);
            findfriendwarning.setVisible(true);
        }
        else if(queriesUtil.getUser(FriendName).getUserID() == Current.getCurrentUser().getUserID()){
            //System.out.println("Aynı kişiyi yazdın");
            findfriendwarning.setText("Aynı kişiyi yazdın");
            findfriendwarning.setTextFill(Color.RED);
            findfriendwarning.setVisible(true);
        }
        else{

            boolean C = true;
            
            for(int a : queriesUtil.getCurrFriendsID()){
                if(a == queriesUtil.getUser(FriendName).getUserID()){
                    C = false;
                }
            }

            if(C){
                //System.out.println("Boyle kullanıcı Var");
                findfriendwarning.setText("Boyle kullanıcı Var +EKLENDİ");
                findfriendwarning.setTextFill(Color.GREEN);
                findfriendwarning.setVisible(true);

                queriesUtil.addFriend(Current.getCurrentUser().getUserID() , queriesUtil.getUser(FriendName).getUserID());
                
                friendsVbox.getChildren().clear();

                lable_giver();
            }
            else{
                //System.out.println("Zaten arkadaş listesinde");
                findfriendwarning.setText("Zaten arkadaş listesinde");
                findfriendwarning.setTextFill(Color.RED);
                findfriendwarning.setVisible(true);
            }

        }

    }

    public void lable_giver(){
         for(int a : queriesUtil.getCurrFriendsID()){
            //System.out.println(queriesUtil.getUserByID(a).getUserName());

            Label l = new Label();
            l.setText(queriesUtil.getUserByID(a).getUserName());
            l.setPrefWidth(420);
            l.setPrefHeight(20);
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-border-color: Black;");
            friendsVbox.getChildren().add(l);
        }
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
        System.out.println("adding games to vbox");
        QueriesUtil queriesUtil = new QueriesUtil();
        //gamesVbox.getChildren().clear();
        for (Game game : queriesUtil.getInventory()) {
            System.out.println("adding game: " + game.getGameName());
            gamesVbox.getChildren().add(generateGameTile(game));
        }
    }





}
