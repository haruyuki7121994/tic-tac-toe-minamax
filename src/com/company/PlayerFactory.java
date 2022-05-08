package com.company;

public class PlayerFactory {
    Player createPlayer(String type, String symbol) {
        Player newPlayer = initPlayer(type);
        newPlayer.setSymbol(symbol);
        return newPlayer;
    }

    private Player initPlayer(String type) {
        if ("user".equals(type)) {
            return new User();
        }
        return new AI(type);
    }
}
