package com.check.characters;

import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {

    @Test
    public void testWeapon() {
        Knight knight = new Knight(true);
        assertEquals(com.check.items.Sword.class, knight.getWeapon().getClass());
    }

    @Test
    public void testCPUTrue() {
        Knight knight = new Knight(true);
        assertTrue(knight.isCPU());
    }

    @Test
    public void testCPUFalse() {
        Knight knight = new Knight(false);
        assertFalse(knight.isCPU());
    }

    @Test
    public void testDescription() {
        Knight knight = new Knight(true);
        assertEquals("""
        A mighty knight who fights for justice for all who support the queen.
        He is a strong and brave warrior who is always ready to fight for his kingdom.
        He is a master of the sword and can defeat any enemy that comes his way.
        """, knight.getDescription());
    }

    @Test
    public void testCharacterName() {
        Knight knight = new Knight(true);
        assertEquals("Knight", knight.getName());
    }
    
}
