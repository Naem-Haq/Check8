package com.check.game;

import com.check.characters.Character;
import com.check.characters.DodgeCommand;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;

public class LowHPHandler extends Handler {
    @Override
    public CharacterCommand handleCharacterDecision(Character character, Character target) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * 100;
        if (healthPercentage < 50) {
            if (character.canDodge()) {
                System.out.println("Character at low health, dodging!");
                character.setAttackable(false);
                return new DodgeCommand(character);
            } else {
                System.out.println("No dodges left, attempting to attack!");
                character.setAttackable(true);
                return new AttackCommand(character);
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character, target);
        }
        return new AttackCommand(character); // Default action
    }
}