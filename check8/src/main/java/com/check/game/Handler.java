package com.check.game;

public class Handler {
    protected Handler nextHandler;
    
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handleCharacterDecision(Character character);
}
