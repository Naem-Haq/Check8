package com.check.game;

import com.check.characters.Character;
import com.check.characters.DodgeCommand;

public class CriticalHPHandler extends Handler {
    @Override
    public void handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage < 25) {
            System.out.println("Character at critical health, taking defensive action.");
            if (character.canDodge()) {
                character.setAttackable(false);
                new DodgeCommand(character).execute(character);
            } else {
                // If can't dodge, try next strategy
                if (nextHandler != null) {
                    nextHandler.handleCharacterDecision(character);
                }
            }
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
