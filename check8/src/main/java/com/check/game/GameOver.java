package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameOver implements GameState {
    private static Logger logger = LoggerFactory.getLogger(GameOver.class.getName());

    @Override
    public void handleRequest(Game game) {
        String winner = game.getPlayer().getHealthBar().getHealth() <= 0 ? 
            game.getCPU().getName() : game.getPlayer().getName();
        logger.info("Game ended. Winner: {}", winner);
        game.setState(this);
    }
} 