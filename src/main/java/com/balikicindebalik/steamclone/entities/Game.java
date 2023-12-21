package com.balikicindebalik.steamclone.entities;

public class Game {
    private int gameID;
    private String gameName;
    private String description;
    private String gamePrice;
    private String gameReleaseDate;
    private String madeBy;
    private long score;

    public Game() {

    }

    public Game(int gameID, String gameName, String description, String gamePrice, String gameReleaseDate, String madeBy) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
        this.gameReleaseDate = gameReleaseDate;
        this.madeBy = madeBy;
        this.description = description;
    }

    public Game(int gameID, String gameName, String description, String gamePrice, String gameReleaseDate, String madeBy, long score) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
        this.gameReleaseDate = gameReleaseDate;
        this.madeBy = madeBy;
        this.description = description;
        this.score = score;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int dlcID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String dlcName) {
        this.gameName = gameName;
    }

    public String getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(String dlcPrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameReleaseDate() {
        return gameReleaseDate;
    }

    public void setGameReleaseDate(String dlcReleaseDate) {
        this.gameReleaseDate = gameReleaseDate;
    }
}
