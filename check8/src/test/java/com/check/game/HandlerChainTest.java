package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;

public class HandlerChainTest {
    private CriticalHPHandler criticalHandler;
    private LowHPHandler lowHandler;
    private MidHPHandler midHandler;
    private FullHPHandler fullHandler;
    private Character character;

    @Before
    public void setUp() throws CharacterCreator.InvalidCharacterException {
        criticalHandler = new CriticalHPHandler();
        lowHandler = new LowHPHandler();
        midHandler = new MidHPHandler();
        fullHandler = new FullHPHandler();
        
        // Set up the chain
        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);
        
        character = CharacterCreator.createCharacter("knight", true);
    }

    @Test
    public void testHandlerChainOrder() {
        // Test critical health
        character.getHealthBar().setHealth(20);
        criticalHandler.handleCharacterDecision(character);
        assertFalse("Character should not be attackable at critical health", character.isAttackable());

        // Test low health
        character.getHealthBar().setHealth(40);
        criticalHandler.handleCharacterDecision(character);
        assertFalse("Character should not be attackable at low health", character.isAttackable());

        // Test mid health
        character.getHealthBar().setHealth(60);
        criticalHandler.handleCharacterDecision(character);
        assertTrue("Character should be attackable at mid health", character.isAttackable());

        // Test full health
        character.getHealthBar().setHealth(90);
        criticalHandler.handleCharacterDecision(character);
        assertTrue("Character should be attackable at full health", character.isAttackable());
    }

    @Test
    public void testHandlerResponsibility() {
        Handler handler = criticalHandler;
        assertNotNull("Handler should have next handler", handler.getNextHandler());
        
        handler = handler.getNextHandler();
        assertTrue("Second handler should be LowHPHandler", handler instanceof LowHPHandler);
        
        handler = handler.getNextHandler();
        assertTrue("Third handler should be MidHPHandler", handler instanceof MidHPHandler);
        
        handler = handler.getNextHandler();
        assertTrue("Fourth handler should be FullHPHandler", handler instanceof FullHPHandler);
        
        assertNull("Last handler should not have next handler", handler.getNextHandler());
    }
} 