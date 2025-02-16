package com.check.characters;

import java.util.Arrays;

import com.check.items.DamagePotion;
import com.check.items.HealPotion;
import com.check.items.Item;
import com.check.items.Staff;

public class Mage extends Character{

    public Mage(boolean cpu){
        super("Mage", new Staff(), cpu, """
        A powerful mage who uses the power of the elements to defeat his enemies.
        He is a master of the arcane arts and can cast powerful spells to destroy his foes.
        He is a wise and cunning warrior who uses his intelligence to outsmart his enemies.
        """);
    }

    @Override
    public void populateInventory() {
        Item[] items = new Item[]{new HealPotion(), new HealPotion(), new HealPotion(), new DamagePotion(), new DamagePotion()};
        getInventory().addItems(Arrays.asList(items));
        super.populateInventory();
    }
}
