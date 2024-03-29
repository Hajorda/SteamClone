package com.balikicindebalik.steamclone.database;

import com.balikicindebalik.steamclone.DBconnection;
import com.balikicindebalik.steamclone.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueriesUtil implements Util {






    public void throwToBasket(Game game) {
        System.out.println("query");
        String query = "INSERT INTO Basket (GameID, UserID) VALUES (?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);


            ps.setInt(1, game.getGameID());
            ps.setInt(2, Current.getCurrentUser().getUserID());
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "done");
        }
    }

    public void deleteFromBasket(Game game) {
        System.out.println("query");
        String query = "DELETE FROM Basket WHERE GameID = ? AND UserID = ?";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, game.getGameID());
            ps.setInt(2, Current.getCurrentUser().getUserID());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User removed successfully");
            } else {
                System.out.println("User not found or could not be removed");
            }

            conn.close();
            ps.close();

            System.out.println("Connection closed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public int numberOfItemsInBasket() {
        String query = "SELECT COUNT(*) FROM Basket WHERE UserID = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            conn.close();
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return -1;

    }

    public List<Game> getBasket() {
        List<Game> gameList = new ArrayList<>();
        String query = "SELECT * FROM Basket LEFT JOIN Game ON Basket.GameID = Game.GameID WHERE UserID = ? ORDER BY Date DESC";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Game game = new Game(
                        rs.getInt("GameID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Price"),
                        rs.getString("Date"),
                        rs.getString("DeveloperID"),
                        rs.getLong("Score")
                );
                gameList.add(game);
            }

            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return gameList;
    }

    public boolean chechGameInBasket(Game game){
        String query = "SELECT * FROM Basket WHERE UserID = ? AND GameID = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ps.setInt(2, game.getGameID());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                conn.close();
                rs.close();
                ps.close();
                return true;
            }

            conn.close();
            rs.close();
            ps.close();
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return false;
    }

    public void throwToBasket(Dlc dlc) throws SQLException {
        System.out.println("query");
        String query = "INSERT INTO Basket (DLC_ID, UserID) VALUES (?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) {
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        String nick = user.getUserName();
        String firstName = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String password = passwordAuthentication.hash(user.getPassword());

        int random = (int) (Math.random() * 1000000);

        //Burası s
        String query = "INSERT INTO User (UserID, UserName, Name, Surname,Email,Password) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, random);
            ps.setString(2, nick);
            ps.setString(3, firstName);
            ps.setString(4, surname);
            ps.setString(5, email);
            ps.setString(6, password);
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "done");
        }

    }

    @Override
    public void addUsers(List<User> userList) {
        for (User user : userList) {
            addUser(user);
        }
    }

    @Override
    public User getUser(String name) {
        String query = "Select * from User WHERE UserName = ?";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            int count = 0;


            if (rs.next()) {
                User tUser = new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getString("Password"));
                conn.close();
                rs.close();
                ps.close();

                return tUser;
            }

                conn.close();
                rs.close();
                ps.close();
                System.out.println("Connection closed");
                return null;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ?");
            //TODO Exception handling!
        }
        return null;
    }


    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("UserName"),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
                userList.add(user);
            }

            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // TODO: Handle the exception or log it
        }

        return userList;
    }

    @Override
    public void removeUser(User user) {
        String query = "DELETE FROM User WHERE UserID = ? AND UserName = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUserName());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User removed successfully");
            } else {
                System.out.println("User not found or could not be removed");
            }

            conn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // TODO: Handle the exception or log it
        }
    }

    public boolean checkUser(String name, String password) {
        if (getUser(name) != null) {
            PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
            return passwordAuthentication.authenticate(password, getUser(name).getPassword());
        } else
            return false;
    }


    public double getBalanceOfUser(){
        String query = "SELECT balance FROM User WHERE UserID = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            double balance = 0;
            while (rs.next()) {
                balance = rs.getDouble(1);
            }

            conn.close();
            rs.close();
            ps.close();
            return balance;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return -1;
    }

    @Override
    public void addGame(Game game) {
        int random = (int) (Math.random() * 1000000);
        String query = "INSERT INTO Game (GameID, Name, Description, Price, Date, DeveloperID,Score) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, random);
            ps.setString(2, game.getGameName());
            ps.setString(3, game.getDescription());
            ps.setString(4, game.getGamePrice());
            ps.setString(5, game.getGameReleaseDate());
            ps.setString(6, game.getMadeBy());
            ps.setLong(7, game.getScore());
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "done");
        }
    }

    @Override
    public void addGames(List<Game> gameList) {
        for (Game game : gameList) {
            addGame(game);
        }
    }



    @Override
    public Game getGame(String name) {
        String query = "Select * from Game WHERE Name = ?";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            int count = 0;

            if (rs.next()) {
                Game tGame = new Game(rs.getInt("GameID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Price"), rs.getString("Date"), rs.getString("DeveloperID"), rs.getLong("Score"));
                conn.close();
                rs.close();
                ps.close();

                return tGame;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Game not found");
        return null;
    }

    public Game getGameById(int id){
        String query = "Select * from Game WHERE GameID = ?";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int count = 0;

            if (rs.next()) {
                Game tGame = new Game(rs.getInt("GameID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Price"), rs.getString("Date"), rs.getString("DeveloperID"), rs.getLong("Score"));
                conn.close();
                rs.close();
                ps.close();

                return tGame;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Game not found");
        return null;
    }

    @Override
    public List<Game> getGames() {
        List<Game> gameList = new ArrayList<>();
        String query = "SELECT * FROM Game";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Game game = new Game(
                        rs.getInt("GameID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Price"),
                        rs.getString("Date"),
                        rs.getString("DeveloperID"),
                        rs.getLong("Score")
                );
                gameList.add(game);
            }

            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return gameList;
    }

    @Override
    public void removeGame(Game game) {

    }

    @Override
    public void addDlc(Dlc dlc) {

    }

    @Override
    public void addDlcs(List<Dlc> dlcList) {

    }

    @Override
    public Dlc getDlc(String name) {
        return null;
    }

    @Override
    public List<Dlc> getDlcs() {
        return null;
    }

    @Override
    public void removeDlc(Dlc dlc) {

    }

    @Override
    public void addDeveloper(Developer developer) {

    }

    @Override
    public void addDevelopers(List<Developer> developerList) {

    }

    @Override
    public Developer getDeveloper(String name) {
        return null;
    }

    @Override
    public List<Developer> getDevelopers() {
        return null;
    }

    @Override
    public void removeDeveloper(Developer developer) {

    }

    public void addGameToInventory(Game game) {
        System.out.println("query");
        String query = "INSERT INTO Inventory (GameID, UserID,AddedPrice) VALUES (?, ?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, game.getGameID());
            ps.setInt(2, Current.getCurrentUser().getUserID());
            ps.setString(3, game.getGamePrice());
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Game> getInventory() {
        List<Game> gameList = new ArrayList<>();
        String query = "SELECT * FROM Inventory LEFT JOIN Game ON Inventory.GameID = Game.GameID WHERE UserID = ? AND Game.GameID IS NOT NULL ORDER BY Date DESC";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Game game = new Game(
                        rs.getInt("GameID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("Price"),
                        rs.getString("Date"),
                        rs.getString("DeveloperID"),
                        rs.getLong("Score")
                );
                gameList.add(game);
            }

            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return gameList;
    }

    public boolean checkGameInventory(Game game){
        String query = "SELECT * FROM Inventory WHERE UserID = ? AND GameID = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ps.setInt(2, game.getGameID());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                conn.close();
                rs.close();
                ps.close();
                return true;
            }

            conn.close();
            rs.close();
            ps.close();
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return false;
    }

    public int numberOfItemsInInventory(){
        String query = "SELECT COUNT(*) FROM Inventory WHERE UserID = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            conn.close();
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return -1;
    }

    public double avaragePriceOfGamesInInventory(){
        //Bu query Fırat hoca için özel yazılmıştır Ultra casting avarage substring kombosu kullanılmıştır.
        String query = "SELECT AVG(CAST(substring(AddedPrice,2) AS INT)) AS 'AVG' FROM Inventory WHERE UserID = ? AND AddedPrice IS NOT 'Free' GROUP BY UserID;;";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            double count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            conn.close();
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return -1;
    }

    public double totalPriceOfGameInInventory(){
        String query = "SELECT SUM(CAST(substring(AddedPrice,2) AS INT)) AS 'SUM' FROM Inventory WHERE UserID = ? AND AddedPrice IS NOT 'Free' GROUP BY UserID;;";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            double count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            conn.close();
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return -1;
    }

    public void setBalanceOfUser(double v) {
        //Check v is a positive number


        String query = "UPDATE User SET balance = ? WHERE UserID = ?";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDouble(1, v);
            ps.setInt(2, Current.getCurrentUser().getUserID());
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Write using MAX() querie
    public Game maxPriceGameInInventory() {
        String query = "SELECT max(AddedPrice), Game.GameID FROM Inventory LEFT JOIN Game ON Inventory.GameID = Game.GameID WHERE UserID = ? AND Game.GameID IS NOT NULL ORDER BY AddedPrice DESC LIMIT 1";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            Game a = new Game();

            if (rs.next()) {

                String s = rs.getString("GameID");

                if(s == null){
                    //System.out.println("dnm");
                }
                else{
                    //System.out.println("dnm2");
                    a = getGameById(Integer.parseInt(s));
                }

                //a = getGameById(Integer.parseInt(rs.getString("GameID")));

                conn.close();
                rs.close();
                ps.close();
                return a;
            }

            conn.close();
            rs.close();
            ps.close();

            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return null;

    }

    public List<String> getAllUserNames(){
        String query = "SELECT UserName FROM User";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            List<String> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(rs.getString("UserName"));
            }

            conn.close();
            rs.close();
            ps.close();
            return userList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Counting patladı");
        return null;
    }

    //Drop Basket Table
    public void dropBasketTable(){
        String query = "DROP TABLE Basket";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void alterTableBasket(){
        String query = "ALTER TABLE Basket RENAME COLUMN GameID TO GameID2";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // for frends -----------------------------------------

     public void addFriend(int user1ID , int user2ID) {

        //Burası s
        String query = "INSERT INTO FriendR (FriendID1, FriendID2) VALUES (?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, user1ID);
            ps.setInt(2, user2ID);
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "done");
        }

        query = "INSERT INTO FriendR (FriendID2, FriendID1) VALUES (?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, user1ID);
            ps.setInt(2, user2ID);
            ps.executeUpdate();
            try {
                conn.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "done");
        }

    }

    public List<Integer> getCurrFriendsID() {
        List<Integer> FriendsList = new ArrayList<>();
        String query = "SELECT * FROM FriendR WHERE FriendID1 = ?";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Current.getCurrentUser().getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int fri = rs.getInt("FriendID2");
                FriendsList.add(fri);
            }

            conn.close();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return FriendsList;
    }

    public User getUserByID(int ID) {
        String query = "Select * from User WHERE UserID = ?";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            int count = 0;


            if (rs.next()) {
                User tUser = new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getString("Password"));
                conn.close();
                rs.close();
                ps.close();

                return tUser;
            }


            try {
                conn.close();
                rs.close();
                ps.close();
                System.out.println("Connection closed");
                return null;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ?");
            //TODO Exception handling!
        }
        return null;
    }

    // ------------------------------------------

    //ADD 10 RANDOM GAMES TO BASKET



}
