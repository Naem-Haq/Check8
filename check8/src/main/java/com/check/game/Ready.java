package com.check.game;

public class Ready implements GameState {
    @Override
    public String play(Game game) {
        game.setState(new InProgress());
        return "The game is about to begin!\n| Player 1 (User) is " + game.getPlayer1().getName() + " || Player 2 is " + game.getPlayer2().getName() + " |\n";
    }

    @Override
    public Type getType() {
        return Type.READY;
    }
}
