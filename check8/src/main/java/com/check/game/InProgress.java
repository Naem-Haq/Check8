package com.check.game;

public class InProgress implements GameState {
    @Override
    public void handleRequest(Game game) {
        // Game is in progress, handle game logic
        game.incrementRounds();
        if (isGameOver(game)) {
            game.setState(new GameOver());
        }
    }

    private boolean isGameOver(Game game) {
        return game.getPlayer().getHealthBar().getHealth() <= 0 || 
               game.getCPU().getHealthBar().getHealth() <= 0;
    }
}

