package com.check.game;

class FullHPHandler extends Handler {
    @Override
    void handleCharacterDecision(Character character) {
        if (character.getHealth() > 75) {
            System.out.println("Character at full health, attacking.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}