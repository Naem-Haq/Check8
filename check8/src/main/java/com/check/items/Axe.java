package com.check.items;

public class Axe extends Weapon{

    public Axe(){
        super(35, "A heavy axe to be wielded by a strong warrior");
    }

    @Override
    public String attackText(){
        return "You swing your heavy axe at the enemy!";
    }
    
}
