package com.balikicindebalik.steamclone.entities;

public class Dlc {
    private int dlcID;
    private String dlcName;
    private String dlcPrice;
    private String dlcReleaseDate;
    private String madeBy;

    public Dlc(){

    }
    public Dlc(int dlcID, String dlcName, String dlcPrice, String dlcReleaseDate,String madeBy) {
        this.dlcID = dlcID;
        this.dlcName = dlcName;
        this.dlcPrice = dlcPrice;
        this.dlcReleaseDate = dlcReleaseDate;
        this.madeBy = madeBy;
    }

    public int getDlcID() {
        return dlcID;
    }

    public void setDlcID(int dlcID) {
        this.dlcID = dlcID;
    }

    public String getDlcName() {
        return dlcName;
    }

    public void setDlcName(String dlcName) {
        this.dlcName = dlcName;
    }

    public String getDlcPrice() {
        return dlcPrice;
    }

    public void setDlcPrice(String dlcPrice) {
        this.dlcPrice = dlcPrice;
    }

    public String getDlcReleaseDate() {
        return dlcReleaseDate;
    }

    public void setDlcReleaseDate(String dlcReleaseDate) {
        this.dlcReleaseDate = dlcReleaseDate;
    }
}
