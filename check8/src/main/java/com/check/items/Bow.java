package com.check.items;

public class Bow extends Weapon{

    public Bow(){
        super(25, "A bow to be wielded by a skilled archer");
    }

    @Override
    public String attackText(){
        return "You draw your bow, aim and FIRE!";
    }
    
}
