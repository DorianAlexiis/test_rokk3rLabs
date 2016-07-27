package com.example.momentum.myapplication.data.sugar.controllers;

import com.example.momentum.myapplication.data.sugar.models.Branch;
import com.example.momentum.myapplication.data.sugar.models.ItemType;
import com.example.momentum.myapplication.data.sugar.models.Type;
import com.example.momentum.myapplication.utils.AndroidUtilities;

import java.util.List;


/**
 * Created by Dorian on 27/07/2016.
 */

public class ItemTypeManager {
    private static final String TAG = ItemTypeManager.class.getSimpleName();




    public ItemTypeManager() {
    }

    public ItemType add(Type type, String name) {
        ItemType itemType = new ItemType();
        itemType.setName(name);
        itemType.setType(type);
        itemType.save();
        return itemType;
    }


    public List<ItemType> searchContains(String search) {
        List<ItemType> results = ItemType.listAll(ItemType.class);
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
