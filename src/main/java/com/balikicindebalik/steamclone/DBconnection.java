package com.balikicindebalik.steamclone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    /**
     * Connect to a sample database
     *
     * @return Connection object for db
     *
     */
    public static Connection connect() {

        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/com/balikicindebalik/steamclone/SteamCloneDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;

            // TODO handle case
        } catch (SQLException e) {
            System.out.println("sqlE " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("NO JDBC " + e);
        }
        return conn;
    }
}