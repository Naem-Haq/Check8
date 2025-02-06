package com.check.items;

public class Sword extends Weapon{
    
    public Sword(){
        super(10, "A sharp sword to be wielded by a worthy warrior"); 
    }

    @Override
    public String attackText(){
        return "You swing your sharp edge at the enemy!";
    }
    
}
