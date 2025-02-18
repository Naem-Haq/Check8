package com.check.game;

import com.check.characters.Character;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;

public class FullHPHandler extends Handler {
    @Override
    public CharacterCommand handleCharacterDecision(Character character, Character target) {
        System.out.println("Character at full health, attacking.");
        character.setAttackable(true);
        return new AttackCommand(character);
    }
}