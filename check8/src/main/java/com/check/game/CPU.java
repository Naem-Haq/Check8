package com.check.game;

import com.check.characters.Character;
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
     * @param target The target character for the move
     * @return CharacterCommand The command to execute
     */
    public static CharacterCommand generateMove(Character character, Character target) {
        CriticalHPHandler criticalHandler = new CriticalHPHandler();
        LowHPHandler lowHandler = new LowHPHandler();
        MidHPHandler midHandler = new MidHPHandler();
        FullHPHandler fullHandler = new FullHPHandler();

        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);

        return criticalHandler.handleCharacterDecision(character, target);
    }
}