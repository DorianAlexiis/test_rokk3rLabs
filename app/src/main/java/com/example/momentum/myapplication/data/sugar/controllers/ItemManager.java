package com.example.momentum.myapplication.data.sugar.controllers;

import com.example.momentum.myapplication.data.sugar.models.Branch;
import com.example.momentum.myapplication.data.sugar.models.Item;
import com.example.momentum.myapplication.data.sugar.models.ItemType;
import com.example.momentum.myapplication.data.sugar.models.Type;
import com.example.momentum.myapplication.utils.AndroidUtilities;

import java.util.List;


/**
 * Created by Dorian on 27/07/2016.
 */

public class ItemManager {
    private static final String TAG = ItemManager.class.getSimpleName();



    public ItemManager(){

    }


    public Item add(Type type, Branch branch, String code, String name, int total) {

        Item item = new Item();
        item.setType(type);
        item.setBranch(branch);
        item.setCode(code);
        item.setName(name);
        item.setTotal(total);
        item.setStatus(Item.STATUS_AVAILABLE);
        item.save();
        return item;
    }


    public List<Item> searchContains(String search) {
        List<Item> results = Item.listAll(Item.class);
        String items[] = AndroidUtilities.explode(search," ");
        for (int i=0;i<results.size();i++){
            boolean valido = false;
            for(String item : items){
                if(results.get(i).getName().toUpperCase().contains(item.toUpperCase())){
                    valido = true;
                }
            }
            if(!valido){
                results.remove(i--);
            }
        }
        return results;
    }
}
