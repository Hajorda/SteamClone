package com.balikicindebalik.steamclone.entities;
import javax.persistence.*;

//@Entity
//TODO ORM Annotations
@Entity
@Table(name="User")
public class User {
    private int userID;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User(){

    }
    public User(int userID, String userName, String name, String surname, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
