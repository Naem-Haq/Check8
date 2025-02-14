package com.check.game;

import com.check.characters.Character;
import com.check.characters.AttackCommand;
import com.check.characters.DodgeCommand;
import com.check.characters.CharacterCommand;

/**
 * CPU class responsible for controlling AI character behavior and decision making.
 */
class CPU {
    /**
     * Generates and executes an AI move based on the character's current state.
     * The AI will dodge when health is low (< 50) and attack otherwise.
     * 
     * @param character The character for which to generate a move
     * @throws IllegalArgumentException if character is null
     */
    public static void generateMove(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }

        CharacterCommand action;
        if (character.getHealthBar().getHealth() < 50) {
            action = new DodgeCommand(character);
        } else {
            action = new AttackCommand(character);
        }
        action.execute(character); // Pass target character
    }
}
