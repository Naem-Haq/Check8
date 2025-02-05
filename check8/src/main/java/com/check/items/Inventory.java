package com.check.items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();



    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }
}
