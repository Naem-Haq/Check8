package com.check.characters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.check.characters.CharacterCreator.InvalidCharacterException;
import static org.junit.Assert.fail;

public class CharacterCreatorTest {
    
    @Test
    public void testCreateArcher() {
        CharacterCreator characterCreator = new CharacterCreator();
        try {
            Character archer = characterCreator.createCharacter("Archer", true);
            assertEquals(Archer.class, archer.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateMage() {
        CharacterCreator characterCreator = new CharacterCreator();
        try {
            Character mage = characterCreator.createCharacter("Mage", true);
            assertEquals(Mage.class, mage.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateBrute() {
        CharacterCreator characterCreator = new CharacterCreator();
        try {
            Character brute = characterCreator.createCharacter("Brute", true);
            assertEquals(Brute.class, brute.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateKnight() {
        CharacterCreator characterCreator = new CharacterCreator();
        try {
            Character knight = characterCreator.createCharacter("Knight", true);
            assertEquals(Knight.class, knight.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testInvalidCharacter() {
        CharacterCreator characterCreator = new CharacterCreator();
        try {
            characterCreator.createCharacter("blah", true);
            fail("Exception should be thrown for invalid character type");
        } catch (InvalidCharacterException e) {
            assertEquals("Invalid character type provided", e.getMessage());
        }
    }
}
