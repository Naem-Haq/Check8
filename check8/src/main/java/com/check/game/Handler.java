package com.check.game;

import com.check.characters.Character;

// CHAIN OF RESPONSIBILITY PATTERN FOR CHARACTER DECISIONS
public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public abstract void handleCharacterDecision(Character character);
}