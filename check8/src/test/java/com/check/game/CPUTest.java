package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;

public class CPUTest {
    @Test
    public void testGenerateMoveLowHealth() throws CharacterCreator.InvalidCharacterException {
        Character cpu = CharacterCreator.createCharacter("knight", true);
        cpu.getHealthBar().decreaseHealth(80); // Set health to 20%
        
        boolean wasAttackable = cpu.isAttackable();
        CPU.generateMove(cpu);
        
        // CPU should dodge at low health
        assertNotEquals("CPU should change attackable status when low health", 
            wasAttackable, cpu.isAttackable());
    }

    @Test
    public void testGenerateMoveHighHealth() throws CharacterCreator.InvalidCharacterException {
        Character cpu = CharacterCreator.createCharacter("knight", true);
        boolean wasAttackable = cpu.isAttackable();
        CPU.generateMove(cpu);
        
        // CPU should attack at high health
        assertEquals("CPU should not change attackable status at high health", 
            wasAttackable, cpu.isAttackable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateMoveNullCharacter() {
        CPU.generateMove(null);
    }
} 