package com.check.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.check.characters.Character;

public class Inventory {
    private HashMap<String, ArrayList<Item>> items = new HashMap<>();

    public HashMap<String, ArrayList<Item>> getItems(){
        return this.items;
    }

    public void addItem(Item item){
        // If the item is already in the inventory, add it to the list of items of the same type
        if(this.items.containsKey(item.getClass().getSimpleName())){
            this.items.get(item.getClass().getSimpleName()).add(item);
        }
        // If the item is not in the inventory, create a new list of items of the same type
        else{
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);
            this.items.put(item.getClass().getSimpleName(), items);
        }
    }

    public void addItems(List<Item> items){
        for(Item item : items){
            this.addItem(item);
        }
    }

    public Item getItem(String itemType){
        // If the item is in the inventory, remove it from the list of items of the same type
        if(this.items.containsKey(itemType)){
            ArrayList<Item> items = this.items.get(itemType);
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
        if(this.items.containsKey(itemType)){
            ArrayList<Item> items = this.items.get(itemType);
            items.get(0).use(character);
            items.remove(0);
        }
    }
}
