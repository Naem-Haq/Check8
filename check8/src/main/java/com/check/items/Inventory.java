package com.check.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.check.characters.Character;

public class Inventory {
    private static Logger logger = LoggerFactory.getLogger(Inventory.class.getName());

    private HashMap<String, ArrayList<Item>> items = new HashMap<>();

    public HashMap<String, ArrayList<Item>> getItems(){
        return this.items;
    }

    public void addItem(Item item){
        // If the item is already in the inventory, add it to the list of items of the same type
        if(this.items.containsKey(item.getClass().getSimpleName().toLowerCase())){
            this.items.get(item.getClass().getSimpleName().toLowerCase()).add(item);
        }
        // If the item is not in the inventory, create a new list of items of the same type
        else{
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);
            this.items.put(item.getClass().getSimpleName().toLowerCase(), items);
        }
        logger.debug("Added item to inventory: {}", item.getClass().getSimpleName());
    }

    public void addItems(List<Item> items){
        for(Item item : items){
            this.addItem(item);
        }
    }

    public Item getItem(String itemType){
        // If the item is in the inventory, remove it from the list of items of the same type
        if(this.items.containsKey(itemType.toLowerCase())){
            ArrayList<Item> items = this.items.get(itemType.toLowerCase());
            Item item = items.get(0);
            return item;
        }
        // If the item is not in the inventory, return null
        else{
            return null;
        }
    }

    public void useItem(String itemType, Character character){
        // If the item is in the inventory,use it and remove it from the list of items of the same type
        if(this.items.containsKey(itemType.toLowerCase())){
            ArrayList<Item> items = this.items.get(itemType.toLowerCase());
            items.get(0).use(character);
            logger.debug("Used item from inventory: {}", itemType);
            items.remove(0);
            logger.debug("Character {} health after using {}: {}", character.getName(), itemType, character.getHealthBar().getHealth());
        }
    }
}