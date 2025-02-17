package com.check.game;

import com.check.characters.Character;

public class MidHPHandler extends Handler {
    @Override
    public void handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage >= 50 && healthPercentage < 75) {
            System.out.println("Character at mid health, attacking.");
            character.setAttackable(true);
            return;
        }
        if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
