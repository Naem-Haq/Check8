package com.check.game;

import com.check.characters.Character;
// CHAIN OF RESPONSIBILITY PATTERN FOR CHARACTER DECISIONS
abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void handleCharacterDecision(com.check.characters.Character character);
}