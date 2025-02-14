package com.check.game;

class CriticalHPHandler extends Handler {
    void handleCharacterDecision(com.check.characters.Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage < 25) {
            System.out.println("Character at critical health, taking defensive action.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
