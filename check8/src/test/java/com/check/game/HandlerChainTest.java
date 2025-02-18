package com.check.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCommand;
import com.check.characters.DodgeCommand;

public class HandlerChainTest {
    private Character character;
    private Character target;
    private CriticalHPHandler criticalHandler;

    @Before
    public void setUp() throws CharacterCreator.InvalidCharacterException {
        character = CharacterCreator.createCharacter("knight", true);
        target = CharacterCreator.createCharacter("brute", false);
        criticalHandler = new CriticalHPHandler();
        
        // Set up the chain
        LowHPHandler lowHandler = new LowHPHandler();
        MidHPHandler midHandler = new MidHPHandler();
        FullHPHandler fullHandler = new FullHPHandler();
        
        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);
    }

    @Test
    public void testHandlerChain() {
        // Test critical health
        character.getHealthBar().setHealth(20);
        CharacterCommand command = criticalHandler.handleCharacterDecision(character, target);
        assertTrue("Should dodge at critical health", command instanceof DodgeCommand);

        // Test low health
        character.getHealthBar().setHealth(40);
        command = criticalHandler.handleCharacterDecision(character, target);
        assertTrue("Should dodge at low health", command instanceof DodgeCommand);

        // Test mid health
        character.getHealthBar().setHealth(60);
        command = criticalHandler.handleCharacterDecision(character, target);
        assertTrue("Should be attackable at mid health", character.isAttackable());

        // Test full health
        character.getHealthBar().setHealth(90);
        command = criticalHandler.handleCharacterDecision(character, target);
        assertTrue("Should be attackable at full health", character.isAttackable());
    }
} 