package com.check.items;

public class Staff extends Weapon{
    
    public Staff(){
        super(15, "A staff to be wielded by a mage");
    }

    @Override
    public String attackText(){
        return "You cast a spell with your staff!";
    }
}
