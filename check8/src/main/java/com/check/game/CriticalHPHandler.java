package com.check.game;

import com.check.characters.Character;

public class CriticalHPHandler extends Handler {
    @Override
    public void handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage < 25) {
            System.out.println("Character at critical health, taking defensive action.");
            character.setAttackable(false);
        }
        if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
