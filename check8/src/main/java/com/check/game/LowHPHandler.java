package com.check.game;

class LowHPHandler extends Handler {
    @Override
    void handleCharacterDecision(Character character) {
        if (character.getHealth() > 25) {
            System.out.println("Character at low health, considering defense.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}