package com.example.momentum.myapplication.data.sugar.controllers;

import com.example.momentum.myapplication.data.sugar.models.Type;


/**
 * Created by Dorian on 27/07/2016.
 */

public class TypeManager {
    private static final String TAG = TypeManager.class.getSimpleName();




    public TypeManager(){
    }

    public Type add(String name) {
        Type type = new Type();
        type.setName(name);
        type.save();
        return type;
    }
}
