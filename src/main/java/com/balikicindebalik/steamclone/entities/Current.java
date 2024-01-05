package com.balikicindebalik.steamclone.entities;

public class Current {

    private static User currentUser;
    private static Game currentGame;

    private static int BasketID = 0;

    public static int getBasketID() {
        if(BasketID == 0){
            BasketID = Integer.parseInt("111"+ currentUser.getUserID());
            return BasketID;
        }
        return BasketID;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
           Current.currentUser = currentUser;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Current.currentGame = currentGame;
    }
}
