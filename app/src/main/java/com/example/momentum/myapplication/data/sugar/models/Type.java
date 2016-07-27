package com.example.momentum.myapplication.data.sugar.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;


/**
 * Created by Dorian on 27/07/2016.
 */

public class Type extends SugarRecord {
    @Ignore
    private static final String TAG = Type.class.getSimpleName();

    private String name;

    public Type (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
