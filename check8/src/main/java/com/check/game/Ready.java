package com.check.game;

public class Ready extends GameState {
    public void handleRequest(Game game) {
        System.out.println("Game is now starting...");
        game.setState(new InProgress());
    }
}
