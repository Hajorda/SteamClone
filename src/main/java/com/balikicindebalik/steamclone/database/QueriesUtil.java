package com.balikicindebalik.steamclone.database;

import com.balikicindebalik.steamclone.DBconnection;
import com.balikicindebalik.steamclone.entities.Developer;
import com.balikicindebalik.steamclone.entities.Dlc;
import com.balikicindebalik.steamclone.entities.Game;
import com.balikicindebalik.steamclone.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueriesUtil implements Util {


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
       if( getUser(name) != null){
           PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
           return passwordAuthentication.authenticate(password,getUser(name).getPassword());
       }
       else
              return false;
    }

    @Override
    public void addGame(Game game) {

    }

    @Override
    public void addGames(List<Game> gameList) {

    }

    @Override
    public Game getGame(String name) {
        return null;
    }

    @Override
    public List<Game> getGames() {
        return null;
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
}
