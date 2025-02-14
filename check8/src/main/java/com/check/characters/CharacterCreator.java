package com.check.characters;

import java.util.HashMap;
import java.util.function.Function;

public class CharacterCreator {
    public static class InvalidCharacterException extends Exception {
        public InvalidCharacterException(String message){
            super(message);
        }
    }

    // Hashmap to store the character creator functions
    static HashMap<String, Function<Boolean, Character>> hashMap = new HashMap<>();

    static {
        hashMap.put("archer", (cpu) -> new Archer(cpu));
        hashMap.put("mage", (cpu) -> new Mage(cpu));
        hashMap.put("brute", (cpu) -> new Brute(cpu));
        hashMap.put("knight", (cpu) -> new Knight(cpu));
    }

    public static Character createCharacter(String charType, boolean cpu) throws InvalidCharacterException {
        // Get the character creator function from the hashmap
        Function<Boolean, Character> charCreator = hashMap.get(charType.toLowerCase());
        if (charCreator == null) {
            throw new InvalidCharacterException("Invalid character type provided");
        }
        // Create the character using the creator function
        return charCreator.apply(cpu);
    }
    
}
