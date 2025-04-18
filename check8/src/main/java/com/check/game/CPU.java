package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;

/**
 * CPU class responsible for controlling AI character behavior and decision making.
 */
public class CPU {

    private CPU() {
        // Private constructor to prevent instantiation
    }
    /**
     * Generates and executes an AI move based on the character's current state.
     * Uses Chain of Responsibility to determine the appropriate action.
     * 
     * @param character The character for which to generate a move
     * @return CharacterCommand The command to execute
     */
    public static int generateMove(Character character) {
        CriticalHPHandler criticalHandler = new CriticalHPHandler();
        LowHPHandler lowHandler = new LowHPHandler();
        MidHPHandler midHandler = new MidHPHandler();
        FullHPHandler fullHandler = new FullHPHandler();

        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);

        int move = criticalHandler.handleCharacterDecision(character);

        // Guard for item availability and dodge count
        if ((move == Controls.getUseHealPotion() && !character.getInventory().hasHealPotion()) ||
            (move == Controls.getUseDamagePotion() && !character.getInventory().hasDamagePotion()) ||
            (move == Controls.getDodge() && !character.canDodge())) {
            return Controls.getAttack();
        }

        return move;
    }
}