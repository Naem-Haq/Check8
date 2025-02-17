package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InProgress implements GameState {
    private static Logger logger = LoggerFactory.getLogger(InProgress.class.getName());

    @Override
    public void handleRequest(Game game) {
        if (isGameOver(game)) {
            game.setState(new GameOver());
            logger.info("Game ended after {} rounds", game.getRounds());
        }
    }

    private boolean isGameOver(Game game) {
        boolean isOver = game.getPlayer().getHealthBar().getHealth() <= 0 || 
                        game.getCPU().getHealthBar().getHealth() <= 0;
        if (isOver) {
            logger.debug("Game over condition met - Player HP: {}, CPU HP: {}", 
                game.getPlayer().getHealthBar().getHealth(),
                game.getCPU().getHealthBar().getHealth());
        }
        return isOver;
    }
}

