package com.check.characters;

import org.junit.Test;
import static org.junit.Assert.*;

public class BruteTest {

    @Test
    public void testCPUFalse() {
        Brute brute = new Brute(false);
        assertFalse(brute.isCPU());
    }

    @Test
    public void testCPUTrue() {
        Brute brute = new Brute(true);
        assertTrue(brute.isCPU());
    }

    @Test
    public void testDescription() {
        Brute brute = new Brute(true);
        assertEquals("""
        A brute character who uses his strength to defeat his enemies.
        He is a master of close combat and can overpower any enemy that comes his way.
        He is a fierce and powerful warrior who can crush his enemies with his bare hands and large axe.
        """, brute.getDescription());
    }

    @Test
    public void testCharacterName() {
        Brute brute = new Brute(true);
        assertEquals("Brute", brute.getName());
    }

    @Test
    public void testWeapon() {
        Brute brute = new Brute(true);
        assertEquals(com.check.items.Axe.class, brute.getWeapon().getClass());
    }
}
