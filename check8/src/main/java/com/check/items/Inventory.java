package com.check.items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public Item getItem(int index){
        return this.items.get(index);
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }
}
