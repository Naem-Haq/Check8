package com.check.characters;

import java.util.HashMap;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterCreator {
    private static Logger logger = LoggerFactory.getLogger(CharacterCreator.class.getName());


    public static class InvalidCharacterException extends Exception {
        public InvalidCharacterException(String message){
            super(message);
        }
    }

    // Hashmap to store the character creator functions
    static HashMap<String, Function<Boolean, Character>> hashMap = new HashMap<>();

    static {
        hashMap.put("archer", Archer::new);
        hashMap.put("mage", Mage::new);
        hashMap.put("brute", Brute::new);
        hashMap.put("knight", Knight::new);
    }

    public static Character createCharacter(String charType, boolean cpu) throws InvalidCharacterException {
        // Get the character creator function from the hashmap
        Function<Boolean, Character> charCreator = hashMap.get(charType.toLowerCase());
        if (charCreator == null) {
            logger.error("Invalid character type {} provided", charType);
            throw new InvalidCharacterException("Invalid character type provided");
        }
        logger.debug("Creator Creating a {} character", charType);
        // Create the character using the creator function
        return charCreator.apply(cpu);
    }
    
}
