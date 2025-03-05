package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InProgress implements GameState {
    private static Logger logger = LoggerFactory.getLogger(InProgress.class.getName());

    @Override
    public String play(Game game) {
        game.getGameHistory().save(game);
        return "Round " + game.getNumRounds() + " complete";
    }

    @Override
    public Type getType() {
        return Type.IN_PROGRESS;
    }
}

