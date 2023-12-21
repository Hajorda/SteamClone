package com.balikicindebalik.steamclone.entities;

public class Developer {
    private int developerID;
    private String developerName;
    private String establisedDate;

    public Developer(){

    }
    public Developer(int developerID, String developerName, String establisedDate) {
        this.developerID = developerID;
        this.developerName = developerName;
        this.establisedDate = establisedDate;
    }

    public int getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getEstablisedDate() {
        return establisedDate;
    }

    public void setEstablisedDate(String establisedDate) {
        this.establisedDate = establisedDate;
    }
}
