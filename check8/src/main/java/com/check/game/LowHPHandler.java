package com.check.game;

class LowHPHandler extends Handler {
    @Override
    void handleCharacterDecision(com.check.characters.Character character) {
        if (character.getHealthBar().getHealth() >= 25 && character.getHealthBar().getHealth() < 50) {
            System.out.println("Character at low health, considering defense.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}