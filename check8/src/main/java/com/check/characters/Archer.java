package com.check.characters;

import java.util.Arrays;

import com.check.items.Bow;
import com.check.items.HealPotion;
import com.check.items.Item;

public class Archer extends Character{

    public Archer(boolean cpu){
        super("Archer", new Bow(), cpu, """
        A skilled archer who uses his bow to defeat his enemies from a distance.
        He is a master of the bow and can hit any target with deadly accuracy.
        He is a quick and agile warrior who can move swiftly to avoid his enemies' attacks.
        """);
        populateInventory();
    }

    @Override
    public void populateInventory() {
        Item[] items = new Item[]{new HealPotion(), new HealPotion(), new HealPotion(), new HealPotion()};
        getInventory().addItems(Arrays.asList(items));
        super.populateInventory();
    }
}
