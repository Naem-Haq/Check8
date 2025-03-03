package com.check.game;

public class Ready implements GameState {
    @Override
    public String play(Game game) {
        game.setState(new InProgress());
        return "The game is about to begin!";
    }

    @Override
    public Type getType() {
        return Type.READY;
    }
}
