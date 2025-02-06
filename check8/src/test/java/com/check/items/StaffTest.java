package com.check.items;
import org.junit.Test;


public class StaffTest {
    
    @Test
    public void testGetDamage(){
        Staff weapon = new Staff();
        assert(weapon.getDamage() == 15);
    }

    @Test
    public void testSetDamage(){
        Staff weapon = new Staff();
        weapon.setDamage(30);
        assert(weapon.getDamage() == 30);
    }

    @Test
    public void testGetDescription(){
        Staff weapon = new Staff();
        assert(weapon.getDescription().equals("A staff to be wielded by a mage"));
    }

    @Test
    public void testAttackText(){
        Staff weapon = new Staff();
        assert(weapon.attackText().equals("You cast a spell with your staff!"));
    }
}
