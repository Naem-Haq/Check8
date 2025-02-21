package com.check.game;

import com.check.characters.Character;
import com.check.characters.CharacterCommand;
import com.check.characters.Controls;

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
        Controls controls = new Controls(character);  // Create controls for CPU

        CriticalHPHandler criticalHandler = new CriticalHPHandler(controls);
        LowHPHandler lowHandler = new LowHPHandler(controls);
        MidHPHandler midHandler = new MidHPHandler(controls);
        FullHPHandler fullHandler = new FullHPHandler(controls);

        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);

        return criticalHandler.handleCharacterDecision(character, target);
    }
}