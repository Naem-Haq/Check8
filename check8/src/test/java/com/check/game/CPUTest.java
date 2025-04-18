package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import com.check.characters.Character;
import com.check.items.Inventory;
import com.check.characters.Controls;
import java.util.List;
import com.check.items.HealPotion;
import com.check.items.DamagePotion;

public class CPUTest {

    @Test
    public void testGenerateMove() {
        Character character = new Character("Test", null, true, "Test Description") {
            // Implement abstract methods if any
        };
        Inventory inventory = new Inventory();
        character.getInventory().addItems(
            inventory.getItems().values().stream().flatMap(List::stream).toList()
        );

        int move = CPU.generateMove(character);
        assertTrue("Move should be a valid control", 
            move == Controls.getAttack() || 
            move == Controls.getDodge() || 
            move == Controls.getUseHealPotion() || 
            move == Controls.getUseDamagePotion());
    }

    @Test
    public void testGenerateMoveWithHealPotion() {
        Character character = new Character("Test", null, true, "Test Description") {
            // Implement abstract methods if any
        };
        Inventory inventory = new Inventory();
        inventory.addItem(new HealPotion());
        character.getInventory().addItems(
            inventory.getItems().values().stream().flatMap(List::stream).toList()
        );

        int move = CPU.generateMove(character);
        assertTrue("Move should be a valid control", 
            move == Controls.getAttack() || 
            move == Controls.getDodge() || 
            move == Controls.getUseHealPotion() || 
            move == Controls.getUseDamagePotion());
    }

    @Test
    public void testGenerateMoveWithDamagePotion() {
        Character character = new Character("Test", null, true, "Test Description") {
            // Implement abstract methods if any
        };
        Inventory inventory = new Inventory();
        inventory.addItem(new DamagePotion());
        character.getInventory().addItems(
            inventory.getItems().values().stream().flatMap(List::stream).toList()
        );

        int move = CPU.generateMove(character);
        assertTrue("Move should be a valid control", 
            move == Controls.getAttack() || 
            move == Controls.getDodge() || 
            move == Controls.getUseHealPotion() || 
            move == Controls.getUseDamagePotion());
    }
}