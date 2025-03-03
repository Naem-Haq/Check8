package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InProgress implements GameState {
    private static Logger logger = LoggerFactory.getLogger(InProgress.class.getName());

    @Override
    public String play(Game game) {
        if (isGameOver(game)) {
            game.setState(new GameOver());
            return " ";
        }
        
        game.getGameHistory().save(game);
        game.incrementRounds();
        return "Round " + game.getNumRounds() + " complete";
    }
    
    private boolean isGameOver(Game game) {
        return game.getPlayer1().getHealthBar().getHealth() <= 0 || 
               game.getPlayer2().getHealthBar().getHealth() <= 0;
    }

    @Override
    public Type getType() {
        return Type.IN_PROGRESS;
    }
}

