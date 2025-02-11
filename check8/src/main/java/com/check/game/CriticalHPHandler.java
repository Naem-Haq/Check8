package com.check.game;

class CriticalHPHandler extends Handler {
    void handleCharacterDecision(com.check.characters.Character character) {
        if (character.getHealthBar().getHealth() < 25) {
            System.out.println("Character at critical health, taking defensive action.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
