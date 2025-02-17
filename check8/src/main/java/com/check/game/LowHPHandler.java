package com.check.game;

import com.check.characters.Character;

public class LowHPHandler extends Handler {
    @Override
    public void handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage >= 25 && healthPercentage < 50) {
            System.out.println("Character at low health, considering defense.");
            character.setAttackable(false);
            if (nextHandler != null) {
                nextHandler.handleCharacterDecision(character);
            }
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}