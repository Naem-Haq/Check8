package com.check.game;

public class LowHPHandler extends Handler {
    public void handleCharacterDecision(Character character) {
        if (character.getHealth() <= 40) {
            System.out.println("CPU chooses defensive maneuver!");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}