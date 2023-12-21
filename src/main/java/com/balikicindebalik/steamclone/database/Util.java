package com.balikicindebalik.steamclone.database;


import com.balikicindebalik.steamclone.entities.*;

import java.util.List;

public interface Util {
    // User
    public void addUser(User user);

    public void addUsers(List<User> userList);

    public User getUser(String name);

    public List<User> getUsers();

    public void removeUser(User user);

    // Game
    public void addGame(Game game);

    public void addGames(List<Game> gameList);

    public Game getGame(String name);

    public List<Game> getGames();

    public void removeGame(Game game);

    // Dlc
    public void addDlc(Dlc dlc);

    public void addDlcs(List<Dlc> dlcList);

    public Dlc getDlc(String name);

    public List<Dlc> getDlcs();

    public void removeDlc(Dlc dlc);

    // Developer
    public void addDeveloper(Developer developer);

    public void addDevelopers(List<Developer> developerList);

    public Developer getDeveloper(String name);

    public List<Developer> getDevelopers();

    public void removeDeveloper(Developer developer);

}
