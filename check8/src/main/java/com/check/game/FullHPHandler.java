package com.check.game;

import com.check.characters.Character;
class FullHPHandler extends Handler {
    void handleCharacterDecision(com.check.characters.Character character) {
        if (character.getHealthBar().getHealth() >= 75) {
            System.out.println("Character at full health, attacking.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}