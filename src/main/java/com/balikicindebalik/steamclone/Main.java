package com.balikicindebalik.steamclone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("!");
       // addUser("deneme", "deneme", "deneme", "deneme", 1234);
        readUser();
        System.out.println("123123");
        findUser(963694);


    }

    public static int addUser(String nick, String firstName, String lastName, String email, int password) {

        int random = (int) (Math.random() * 1000000);

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