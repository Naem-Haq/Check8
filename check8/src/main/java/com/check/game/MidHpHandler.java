package com.check.game;

class MidHPHandler extends Handler {
    void handleCharacterDecision(com.check.characters.Character character) {
        if (character.getHealthBar().getHealth() >= 50 && character.getHealthBar().getHealth() < 75) {
            System.out.println("Character at mid health, attacking.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
