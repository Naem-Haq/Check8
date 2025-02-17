package com.check.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;

public class HandlerTest {
    private CriticalHPHandler criticalHandler;
    private LowHPHandler lowHandler;
    private MidHPHandler midHandler;
    private FullHPHandler fullHandler;
    private Character testCharacter;

    @Before
    public void setUp() throws CharacterCreator.InvalidCharacterException {
        criticalHandler = new CriticalHPHandler();
        lowHandler = new LowHPHandler();
        midHandler = new MidHPHandler();
        fullHandler = new FullHPHandler();
        
        // Set up chain
        criticalHandler.setNextHandler(lowHandler);
        lowHandler.setNextHandler(midHandler);
        midHandler.setNextHandler(fullHandler);
        
        testCharacter = CharacterCreator.createCharacter("knight", true);
    }

    @Test
    public void testCriticalHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(90); // Set health to 10%
        criticalHandler.handleCharacterDecision(testCharacter);
        assertTrue("Health should be in critical range", 
            testCharacter.getHealthBar().getHealth() < 25);
    }

    @Test
    public void testLowHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(70); // Set health to 30%
        criticalHandler.handleCharacterDecision(testCharacter);
        assertTrue("Health should be in low range", 
            testCharacter.getHealthBar().getHealth() >= 25 && 
            testCharacter.getHealthBar().getHealth() < 50);
    }

    @Test
    public void testMidHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(40); // Set health to 60%
        criticalHandler.handleCharacterDecision(testCharacter);
        assertTrue("Health should be in mid range", 
            testCharacter.getHealthBar().getHealth() >= 50 && 
            testCharacter.getHealthBar().getHealth() < 75);
    }

    @Test
    public void testFullHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(10); // Set health to 90%
        criticalHandler.handleCharacterDecision(testCharacter);
        assertTrue("Health should be in full range", 
            testCharacter.getHealthBar().getHealth() >= 75);
    }
} 