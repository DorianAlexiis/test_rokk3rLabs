package com.example.momentum.myapplication.data.sugar.models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;


/**
 * Created by Dorian on 27/07/2016.
 */

public class Item extends SugarRecord {
    @Ignore
    private static final String TAG = Item.class.getSimpleName();
    @Ignore
    public static final String STATUS_AVAILABLE = "available";

    private String code;

    private String name;
    private int total;
    private String status;
    private Branch branch;
    private Type type;

    public Item(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
