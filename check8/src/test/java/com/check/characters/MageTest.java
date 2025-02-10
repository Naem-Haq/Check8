package com.check.characters;

import org.junit.Test;
import static org.junit.Assert.*;

public class MageTest {

    @Test
    public void testWeapon() {
        Mage mage = new Mage(true);
        assertEquals(com.check.items.Staff.class, mage.getWeapon().getClass());
    }

    @Test
    public void testCPUTrue() {
        Mage mage = new Mage(true);
        assertTrue(mage.isCPU());
    }

    @Test
    public void testCPUFalse() {
        Mage mage = new Mage(false);
        assertFalse(mage.isCPU());
    }

    @Test
    public void testDescription() {
        Mage mage = new Mage(true);
        assertEquals("""
        A powerful mage who uses the power of the elements to defeat his enemies.
        He is a master of the arcane arts and can cast powerful spells to destroy his foes.
        He is a wise and cunning warrior who uses his intelligence to outsmart his enemies.
        """, mage.getDescription());
    }

    @Test
    public void testCharacterName() {
        Mage mage = new Mage(true);
        assertEquals("Mage", mage.getName());
    }

    @Test
    public void testInventory() {
        Mage mage = new Mage(true);
        mage.populateInventory();
        assertEquals(5, mage.getInventory().getItems().size());
    }
    
}
