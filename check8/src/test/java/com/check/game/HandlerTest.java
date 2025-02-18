package com.check.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCommand;
import com.check.characters.DodgeCommand;

public class HandlerTest {
    private Character testCharacter;
    private Character target;
    private CriticalHPHandler criticalHandler;

    @Before
    public void setUp() throws CharacterCreator.InvalidCharacterException {
        testCharacter = CharacterCreator.createCharacter("knight", true);
        target = CharacterCreator.createCharacter("brute", false);
        criticalHandler = new CriticalHPHandler();
    }

    @Test
    public void testCriticalHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(90); // Set health to 10%
        CharacterCommand command = criticalHandler.handleCharacterDecision(testCharacter, target);
        
        assertTrue("Should choose to dodge at critical health",
            command instanceof DodgeCommand);
        assertTrue("Health should be in critical range", 
            testCharacter.getHealthBar().getHealth() < 25);
    }

    @Test
    public void testLowHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(70); // Set health to 30%
        CharacterCommand command = criticalHandler.handleCharacterDecision(testCharacter, target);
        
        assertTrue("Health should be in low range", 
            testCharacter.getHealthBar().getHealth() >= 25 && 
            testCharacter.getHealthBar().getHealth() < 50);
    }

    @Test
    public void testMidHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(40); // Set health to 60%
        CharacterCommand command = criticalHandler.handleCharacterDecision(testCharacter, target);
        
        assertTrue("Health should be in mid range", 
            testCharacter.getHealthBar().getHealth() >= 50 && 
            testCharacter.getHealthBar().getHealth() < 75);
    }

    @Test
    public void testFullHealthHandling() {
        testCharacter.getHealthBar().decreaseHealth(10); // Set health to 90%
        CharacterCommand command = criticalHandler.handleCharacterDecision(testCharacter, target);
        
        assertTrue("Health should be in full range", 
            testCharacter.getHealthBar().getHealth() >= 75);
    }
} 