package com.check.game;

import com.check.characters.Character;

public class FullHPHandler extends Handler {
    @Override
    public void handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage >= 75) {
            System.out.println("Character at full health, attacking.");
            character.setAttackable(true);
        }
        if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}