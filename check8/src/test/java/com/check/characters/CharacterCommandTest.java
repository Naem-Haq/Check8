package com.check.characters;

import org.junit.Test;

import com.check.characters.CharacterCreator.InvalidCharacterException;

public class CharacterCommandTest {
    @Test
    public void testAttack() throws InvalidCharacterException {
        Character character = CharacterCreator.createCharacter("Knight", false);
        Controls characterControls = new Controls(character);

        Character enemy = CharacterCreator.createCharacter("Knight", true);

        characterControls.pressButton(Controls.getAttack(), enemy);
        assert(enemy.getHealthBar().getHealth() < 100);
    }

    @Test
    public void testDodge() throws InvalidCharacterException {
        Character character = CharacterCreator.createCharacter("Knight", false);
        Controls characterControls = new Controls(character);
    
        characterControls.pressButton(Controls.getDodge(), character);
        assert(!character.isAttackable());
    }

    @Test
    public void testUseHealPotion() throws InvalidCharacterException {
        Character character = CharacterCreator.createCharacter("Knight", false);
        Controls characterControls = new Controls(character);
        character.populateInventory();

        character.getHealthBar().decreaseHealth(50);
        characterControls.pressButton(Controls.getUseHealPotion(), character);
        assert(character.getHealthBar().getHealth() > 50);
    }

    @Test
    public void testUseDamagePotion() throws InvalidCharacterException {
        Character character = CharacterCreator.createCharacter("Brute", false);
        Controls characterControls = new Controls(character);
        character.populateInventory();

        characterControls.pressButton(Controls.getUseDamagePotion(), character);
        assert(character.getHealthBar().getHealth() < 100);
    }
}
