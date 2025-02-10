package com.check.game;

public class CPU {
    private final Handler handlerChain;
    
    public CPU() {
        Handler fullHP = new FullHPHandler();
        Handler lowHP = new LowHPHandler();
        
        fullHP.setNextHandler(lowHP);
        this.handlerChain = fullHP;
    }
    
    public void generateMove(Character character) {
        handlerChain.handleCharacterDecision(character);
    }
}
