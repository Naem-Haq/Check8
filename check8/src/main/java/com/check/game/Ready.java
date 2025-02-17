package com.check.game;

public class Ready implements GameState {
    @Override
    public void handleRequest(Game game) {
        // Game is ready to start
        game.setState(new InProgress());
    }
}
