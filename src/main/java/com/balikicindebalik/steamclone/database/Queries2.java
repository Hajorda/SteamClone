package com.balikicindebalik.steamclone.database;
import com.balikicindebalik.steamclone.DBconnection;
import com.balikicindebalik.steamclone.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Queries2 {

    public void priceCheck() {
        String query = "SELECT EXISTS(SELECT * FROM Game WHERE Price BETWEEN 10 AND 50 )";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            boolean exists = false;
            if (rs.next()) {
                exists = rs.getBoolean(1);
            }

            conn.close();
            rs.close();
            ps.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Counting patladi");


    }

    public void distinctUser() {
        try {
            Connection conn = DBconnection.connect();
            String query = "SELECT DISTINCT Name FROM user ORDER BY Name";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                String name = rs.getString("Name");
                System.out.println("Distinct Name: " + name);
            }


            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dateFinder() {
        String query = "SELECT Date, " +
                "CASE " +
                "    WHEN Date > '2000-01-01' THEN 'PASS' " +
                "    WHEN Date < '2000-01-01' THEN 'FAIL' " +
                "    ELSE 'UNKNOWN' " +
                "END AS result " +
                "FROM Game";

        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String gameDate = rs.getString("game_date");
                String result = rs.getString("result");

                // Bu sonuçları kullanmak için gerekli işlemleri yapabilirsiniz.
                System.out.println("Game Date: " + gameDate + ", Result: " + result);
            }


            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void minPrice() {
        String query = "SELECT MIN(price) AS min_price FROM Game";
        try {
            Connection conn = DBconnection.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double maxPrice = rs.getDouble("min_price");
                System.out.println("En düşük fiyat: " + maxPrice);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userChecker() {
        String query = "SELECT ALL UserName FROM User WHERE User.UserName LIKE '%ali%'";

        try (Connection conn = DBconnection.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String userName = rs.getString("UserName");
                System.out.println("UserName: " + userName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void basketGameChecker(){
        String query ="SELECT GameID AS ID FROM Basket " +
                "UNION " +
                "SELECT Name AS ID FROM Game " +
                "ORDER BY ID";
        try (Connection conn = DBconnection.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID");
                System.out.println("ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //VIEW query baslangici
    public void createBasketView() {
        String query = "CREATE VIEW view_name AS SELECT GameID, price FROM Basket";
    
        try (Connection conn = DBconnection.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){
    
            System.out.println("Tablo oluşturuldu");
    
             }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showCreatedView() {
        String selectViewQuery = "SELECT * FROM view_name";
    
        try (Connection conn = DBconnection.connect();
             PreparedStatement ps = conn.prepareStatement(selectViewQuery);
             ResultSet rs = ps.executeQuery()) {
    
            System.out.println("VIEW içeriği:");
    
            while (rs.next()) {
                int gameID = rs.getInt("GameID");
                double price = rs.getDouble("price");
                System.out.println("GameID: " + gameID + ", Price: " + price);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //VIEW query bitisi


}