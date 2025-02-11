package com.check.characters;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArcherTest {

    @Test
    public void testWeapon() {
        Archer archer = new Archer(true);
        assertEquals(com.check.items.Bow.class, archer.getWeapon().getClass());
    }

    @Test
    public void testCPUTrue() {
        Archer archer = new Archer(true);
        assertTrue(archer.isCPU());
    }

    @Test
    public void testCPUFalse() {
        Archer archer = new Archer(false);
        assertFalse(archer.isCPU());
    }

    @Test
    public void testDescription() {
        Archer archer = new Archer(true);
        assertEquals("""
        A skilled archer who uses his bow to defeat his enemies from a distance.
        He is a master of the bow and can hit any target with deadly accuracy.
        He is a quick and agile warrior who can move swiftly to avoid his enemies' attacks.
        """, archer.getDescription());
    }

    @Test
    public void testCharacterName() {
        Archer archer = new Archer(true);
        assertEquals("Archer", archer.getName());
    }
}
