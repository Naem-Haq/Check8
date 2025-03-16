package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.Knight;

public class CPUTest {
    @Test
    public void testCPUExists() {
        CPU cpu = new CPU();
        assertNotNull("CPU should be created", cpu);
    }

    @Test
    public void testGenerateMove() {
        Character character = new Knight(false);
        int move = CPU.generateMove(character);
        assertTrue("Move should be a valid command", move >= 0 && move <= 3);
    }
}