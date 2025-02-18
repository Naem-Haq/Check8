package com.check.game;

import com.check.characters.Character;
import com.check.characters.CharacterCommand;

// CHAIN OF RESPONSIBILITY PATTERN FOR CHARACTER DECISIONS
public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public abstract CharacterCommand handleCharacterDecision(Character character, Character target);
}