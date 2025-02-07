package com.check.characters;

import com.check.items.Staff;

public class Mage extends Character{

    public Mage(boolean cpu){
        super("Mage", new Staff(), cpu, """
        A powerful mage who uses the power of the elements to defeat his enemies.
        He is a master of the arcane arts and can cast powerful spells to destroy his foes.
        He is a wise and cunning warrior who uses his intelligence to outsmart his enemies.
        """);
    }
}
