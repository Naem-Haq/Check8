package com.check.game;

public class InProgress extends GameState {
    public void handleRequest(Game game) {
        System.out.println("Game is already in progress.");
    }
}

