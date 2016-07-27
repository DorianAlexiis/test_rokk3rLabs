package com.example.momentum.myapplication.data.sugar.models;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * Created by Dorian on 27/07/2016.
 */

public class ItemType  extends SugarRecord {
    @Ignore
    private static final String TAG = ItemType.class.getSimpleName();

    private Type type;
    private String name;

    public ItemType(){

    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
