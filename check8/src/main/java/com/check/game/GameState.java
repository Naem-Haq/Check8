package com.check.game;

public interface GameState {
    enum Type {
        READY,
        IN_PROGRESS,
        GAME_OVER
    }

    String play(Game game);
    Type getType();
}