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
     * @param target The target character for the move
     * @throws IllegalArgumentException if character or target is null
     * @return CharacterCommand The command to execute
     */
    public static CharacterCommand generateMove(Character character, Character target) {
        if (character == null || target == null) {
            throw new IllegalArgumentException("Characters cannot be null");
        }

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