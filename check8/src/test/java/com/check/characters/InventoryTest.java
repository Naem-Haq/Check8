package com.check.characters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.items.HealPotion;
import com.check.items.Inventory;

public class InventoryTest {

    @Test
    public void testGetItem() throws InvalidCharacterException{
        Character character = CharacterCreator.createCharacter("Archer", true);
        character.populateInventory();
        Inventory inventory = character.getInventory();
        // Check if items are added
        assertNotNull(inventory.getItem("HealPotion"));
    }

    @Test
    public void testUseItem() throws InvalidCharacterException{
        Character character = CharacterCreator.createCharacter("Brute", false);
        character.populateInventory();
        Character enemy = CharacterCreator.createCharacter("Archer", true);
        Inventory inventory = character.getInventory();
        // Check if items are used
        inventory.useItem("DamagePotion", enemy);
        assertEquals(enemy.getHealthBar().getHealth(), 85);
    }

    @Test
    public void testAddItem(){
        Inventory inventory = new Inventory();
        // Check if items are added
        inventory.addItem(new HealPotion());
        assertNotNull(inventory.getItem("HealPotion"));
    }
    
}
