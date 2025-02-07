package com.check.characters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.check.characters.CharacterCreator.InvalidCharacterException;
import static org.junit.Assert.fail;

public class CharacterCreatorTest {
    
    @Test
    public void testCreateArcher() {
        try {
            Character archer = CharacterCreator.createCharacter("Archer", true);
            assertEquals(Archer.class, archer.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateMage() {
        try {
            Character mage = CharacterCreator.createCharacter("Mage", true);
            assertEquals(Mage.class, mage.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateBrute() {
        try {
            Character brute = CharacterCreator.createCharacter("Brute", true);
            assertEquals(Brute.class, brute.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testCreateKnight() {
        try {
            Character knight = CharacterCreator.createCharacter("Knight", true);
            assertEquals(Knight.class, knight.getClass());
        } catch (InvalidCharacterException e) {
            fail("Exception should not be thrown for valid character type");
        }
    }

    @Test
    public void testInvalidCharacter() {
        try {
            CharacterCreator.createCharacter("blah", true);
            fail("Exception should be thrown for invalid character type");
        } catch (InvalidCharacterException e) {
            assertEquals("Invalid character type provided", e.getMessage());
        }
    }
}
