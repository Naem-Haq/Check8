package com.check.game;

class FullHPHandler extends Handler {
    void handleCharacterDecision(com.check.characters.Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage >= 75) {
            System.out.println("Character at full health, attacking.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}