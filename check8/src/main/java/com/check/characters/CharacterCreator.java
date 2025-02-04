package com.check.characters;

public class CharacterCreator {

    public Character createCharacter(String char_type){
        return new ConcreteCharacter(); //TODO: Update to intelligently choose character
    }
    
}
