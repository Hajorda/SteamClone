package com.balikicindebalik.steamclone;
import com.balikicindebalik.steamclone.database.QueriesUtil;
import com.balikicindebalik.steamclone.entities.Current;
import com.balikicindebalik.steamclone.entities.Game;
import com.balikicindebalik.steamclone.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//addGames();

        //deneme -------------------------------------------------------

        //QueriesUtil queriesUtil = new QueriesUtil();
        //queriesUtil.addFriend(queriesUtil.getUser("admin").getUserID(), queriesUtil.getUser("anan").getUserID());

        //queriesUtil.addUser(new User(00,"koray","koray","başkoca","koray.başkoca@tedu.edu.tr","123456")); // Id random atanıyor
        
        /* Current.setCurrentUser(new User(queriesUtil.getUser("kutay").getUserID(),"a","a","a","a","a"));// nick name dali
        for(int a : queriesUtil.getCurrFriendsID()){
            System.out.println(queriesUtil.getUserByID(a).getUserName());
        } */
        //deneme -------------------------------------------------------

    }

    private static void addGames() {
        Game game1 = new Game(1, "CS:GO", "Counter-Strike: Global Offensive (CS:GO) expands upon the team-based action gameplay that it pioneered when it launched in 1999.", "Free", "21 Aug, 2012", "Valve");
        Game game2 = new Game(2, "Dota 2", "Every day, millions of players worldwide enter battle as one of over a hundred Dota heroes. And no matter if it's their 10th hour of play or 1,000th, there's always something new to discover.", "Free", "9 Jul, 2013", "Valve");
        Game game3 = new Game(3, "Team Fortress 2", "Nine distinct classes provide a broad range of tactical abilities and personalities. Constantly updated with new game modes, maps, equipment and, most importantly, hats!", "Free", "10 Oct, 2007", "Valve");
        Game game4 = new Game(4, "Portal 2", "The \"Perpetual Testing Initiative\" has been expanded to allow you to design co-op puzzles for you and your friends!", "₺ 24,00", "18 Apr, 2011", "Valve");
        Game game5 = new Game(5, "Left 4 Dead 2", "Set in the zombie apocalypse, Left 4 Dead 2 (L4D2) is the highly anticipated sequel to the award-winning Left 4 Dead, the #1 co-op game of 2008.", "₺ 24,00", "17 Nov, 2009", "Valve");
        Game game6 = new Game(6, "Half-Life 2", "1998. HALF-LIFE sends a shock through the game industry with its combination of pounding action and continuous, immersive storytelling.", "₺ 24,00", "16 Nov, 2004", "Valve");
        Game game7 = new Game(7, "Half-Life", "Named Game of the Year by over 50 publications, Valve's debut title blends action and adventure with award-winning technology to create a frighteningly realistic world where players must think to survive.", "₺ 24,00", "8 Nov, 1998", "Valve");
        Game game8 = new Game(8, "Counter-Strike: Source", "THE NEXT INSTALLMENT OF THE WORLD'S # 1 ONLINE ACTION GAME Counter-Strike: Source blends Counter-Strike's award-winning teamplay action with the advanced technology of Source™ technology.", "₺ 24,00", "1 Nov, 2004", "Valve");
        QueriesUtil queriesUtil = new QueriesUtil();
        queriesUtil.addGame(game1);
        queriesUtil.addGame(game2);
        queriesUtil.addGame(game3);
        queriesUtil.addGame(game4);
        queriesUtil.addGame(game5);
        queriesUtil.addGame(game6);
        queriesUtil.addGame(game7);
        queriesUtil.addGame(game8);

        List<Game> a = queriesUtil.getGames();

        for (Game game : a) {
            System.out.println(game.getGameName());
        }



    }

    private static void getGame() {
        QueriesUtil queriesUtil = new QueriesUtil();
        try {
            Game game1 = queriesUtil.getGame("CS:GO");
            System.out.println(game1.getGameName());
        }
        catch (NullPointerException e){
            System.out.println("Game not found");
        }
    }

    public static int addUser(String nick, String firstName, String lastName, String email, int password) {

        int random = (int) (Math.random() * 1000000);

        //Burası s
        String query = "INSERT INTO User (UserID, UserName, Name, Surname,Email,Password) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBconnection.connect();

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, random);
            ps.setString(2, nick);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, email);
            ps.setInt(6, password);
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
        return random;
    }

    public static void readUser() {
        String query = "Select * from User";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("Users");
            while (rs.next()) {
                System.out.println(rs.getInt("UserID") + " " + rs.getString("UserName") + " " + rs.getString("Name") + " " + rs.getString("Surname") + " " + rs.getString("Email") + " " + rs.getString("Password"));
            }
            try {
                conn.close();
                rs.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ?");
        }
    }

    public static void findUser(int id) {
        String query = "Select * from User WHERE UserID = ?";
        try {

            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            System.out.println("Users");
            while (rs.next()) {
                System.out.println(rs.getInt("UserID") + " " + rs.getString("UserName") + " " + rs.getString("Name") + " " + rs.getString("Surname") + " " + rs.getString("Email") + " " + rs.getString("Password"));
            }
            try {
                conn.close();
                rs.close();
                ps.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ?");
            //TODO Exception handling!
        }
    }
}