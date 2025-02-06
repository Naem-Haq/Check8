package com.check.items;
import org.junit.Test;


public class SwordTest {
    

    @Test
    public void testGetDamage(){
        Sword weapon = new Sword();
        assert(weapon.getDamage() == 10);
    }

    @Test
    public void testSetDamage(){
        Sword weapon = new Sword();
        weapon.setDamage(20);
        assert(weapon.getDamage() == 20);
    }
    
    @Test
    public void testGetDescription(){
        Sword weapon = new Sword();
        assert(weapon.getDescription().equals("A sharp sword to be wielded by a worthy warrior"));
    }

    @Test
    public void testAttackText(){
        Sword weapon = new Sword();
        assert(weapon.attackText().equals("You swing your sharp edge at the enemy!"));
    }

}
