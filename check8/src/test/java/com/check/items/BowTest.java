package com.check.items;
import org.junit.Test;

public class BowTest{
    
    @Test
    public void testGetDamage(){
        Bow weapon = new Bow();
        assert(weapon.getDamage() == 25);
    }

    @Test
    public void testSetDamage(){
        Bow weapon = new Bow();
        weapon.setDamage(30);
        assert(weapon.getDamage() == 30);
    }

    @Test
    public void testGetDescription(){
        Bow weapon = new Bow();
        assert(weapon.getDescription().equals("A bow to be wielded by a skilled archer"));
    }

    @Test
    public void testAttackText(){
        Bow weapon = new Bow();
        assert(weapon.attackText().equals("You draw your bow, aim and FIRE!"));
    }
}
