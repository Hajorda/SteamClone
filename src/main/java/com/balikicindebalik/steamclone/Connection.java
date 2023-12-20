package com.balikicindebalik.steamclone;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    /**
     * Connect to a sample database
     */
    public static void connect() {
        java.sql.Connection conn = null;
        try {
            URL a = Connection.class.getResource("SteamCloneDB.db");
            // db parameters
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:"+a.getPath();
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println("HataSQL"+e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("jdbc api"+e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}