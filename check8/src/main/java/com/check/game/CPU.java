package com.check.game;

import com.check.characters.Character;
import com.check.characters.AttackCommand;
import com.check.characters.DodgeCommand;
import com.check.characters.CharacterCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CPU class responsible for controlling AI character behavior and decision making.
 */
class CPU {
    private static Logger logger = LoggerFactory.getLogger(CPU.class.getName());

    /**
     * Generates and executes an AI move based on the character's current state.
     * Uses Chain of Responsibility to determine the appropriate action.
     * 
     * @param character The character for which to generate a move
     * @throws IllegalArgumentException if character is null
     */
    public static void generateMove(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }

        int health = character.getHealthBar().getHealth();
        
        if (health < 25) {
            logger.info("Character at critical health, taking defensive action.");
            character.setAttackable(false);
            new DodgeCommand(character).execute(character);
        } else if (health < 50) {
            logger.info("Character at low health, considering defense.");
            character.setAttackable(false);
            new DodgeCommand(character).execute(character);
        } else if (health < 75) {
            logger.info("Character at mid health, attacking.");
            character.setAttackable(true);
            new AttackCommand(character).execute(character);
        } else {
            logger.info("Character at full health, attacking.");
            character.setAttackable(true);
            new AttackCommand(character).execute(character);
        }
    }
}
