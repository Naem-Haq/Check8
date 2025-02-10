package com.check.game;

public class FullHPHandler extends Handler {
    public void handleCharacterDecision(Character character) {
        if (character.getHealth() > 80) {
            System.out.println("CPU chooses aggressive attack!");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}