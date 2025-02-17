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
     * Uses Chain of Responsibility to determine the appropriate action.
     * 
     * @param character The character for which to generate a move
     * @throws IllegalArgumentException if character is null
     */
    public static void generateMove(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null");
        }

        // Set up the chain of responsibility
        CriticalHPHandler criticalHandler = new CriticalHPHandler();
        LowHPHandler lowHandler = new LowHPHandler();
        MidHPHandler midHandler = new MidHPHandler();
        FullHPHandler fullHandler = new FullHPHandler();

        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);

        // Start the chain
        criticalHandler.handleCharacterDecision(character);
    }
}
