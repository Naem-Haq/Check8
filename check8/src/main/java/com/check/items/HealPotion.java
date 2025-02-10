package com.check.items;
import com.check.characters.Character;

public class HealPotion extends Potion{ 

    public HealPotion() {
        super(20);
    }

    @Override
    public String useText(){
        return "You drink a healing potion!";
    }

    @Override
    public void use(Character potionOwner) {
        potionOwner.getHealthBar().increaseHealth(this.getHealthPoints());
    }
    
}
