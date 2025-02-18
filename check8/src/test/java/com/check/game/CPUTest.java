package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCommand;
import com.check.characters.CharacterCreator;
import com.check.characters.DodgeCommand;

public class CPUTest {
    @Test
    public void testGenerateMoveLowHealth() throws CharacterCreator.InvalidCharacterException {
        Character cpu = CharacterCreator.createCharacter("knight", true);
        Character target = CharacterCreator.createCharacter("brute", false);
        cpu.getHealthBar().decreaseHealth(80); // Set health to 20%
        
        CharacterCommand command = CPU.generateMove(cpu, target);
        
        assertTrue("CPU should dodge at low health", 
            command instanceof DodgeCommand);
    }

    @Test
    public void testGenerateMoveHighHealth() throws CharacterCreator.InvalidCharacterException {
        Character cpu = CharacterCreator.createCharacter("knight", true);
        Character target = CharacterCreator.createCharacter("brute", false);
        
        CharacterCommand command = CPU.generateMove(cpu, target);
        
        assertTrue("CPU should attack at high health", 
            cpu.isAttackable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateMoveNullCharacter() {
        CPU.generateMove(null, null);
    }

    @Test
    public void testLowHealthBehavior() throws CharacterCreator.InvalidCharacterException {
        Character cpu = CharacterCreator.createCharacter("knight", true);
        Character target = CharacterCreator.createCharacter("brute", false);
        cpu.getHealthBar().decreaseHealth(60); // Set to low health
        
        CharacterCommand command = CPU.generateMove(cpu, target);
        
        assertTrue("CPU should dodge at low health", 
            command instanceof DodgeCommand);
    }
} 