package com.check.game;

import com.check.characters.Character;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;

public class MidHPHandler extends Handler {
    @Override
    public CharacterCommand handleCharacterDecision(Character character, Character target) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage >= 50 && healthPercentage < 75) {
            System.out.println("Character at mid health, attacking.");
            character.setAttackable(true);
            return new AttackCommand(character);
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character, target);
        }
        return new AttackCommand(character); // Default action
    }
}