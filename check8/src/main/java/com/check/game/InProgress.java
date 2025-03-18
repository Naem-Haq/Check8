package com.check.game;

public class InProgress implements GameState {

    @Override
    public String play(Game game) {
        return "Round " + game.getNumRounds() + " complete";
    }

    @Override
    public Type getType() {
        return Type.IN_PROGRESS;
    }
}

