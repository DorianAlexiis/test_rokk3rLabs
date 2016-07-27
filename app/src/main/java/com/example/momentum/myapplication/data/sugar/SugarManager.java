package com.example.momentum.myapplication.data.sugar;

import android.content.Context;
import android.util.Log;


import com.example.momentum.myapplication.data.sugar.controllers.BranchManager;
import com.example.momentum.myapplication.data.sugar.controllers.ItemManager;
import com.example.momentum.myapplication.data.sugar.controllers.ItemTypeManager;
import com.example.momentum.myapplication.data.sugar.controllers.TypeManager;
import com.example.momentum.myapplication.data.sugar.models.Branch;
import com.example.momentum.myapplication.data.sugar.models.Type;


/**
 * Created by Dars on 27/07/2016.
 */
public class SugarManager {
    private static final String TAG = SugarManager.class.getSimpleName();
    public static final int VERSION_DB = 1;


    public static boolean isDBEmpty(){
        return Branch.listAll(Branch.class).size() < 1;
    }

    public static void generateSeek() {
        BranchManager branchManager = new BranchManager();
        TypeManager typeManager = new TypeManager();
        ItemTypeManager itemTypeManager = new ItemTypeManager();
        ItemManager itemManager = new ItemManager();

        //ADD BRANCH
        Branch gap = branchManager.add("Gap");
        Log.d(TAG+" Dars","name "+gap.getName());
        Branch banana_republic = branchManager.add("Banana Republic");
        Branch hugo_boss = branchManager.add("Hugo Boss");
        Branch taylor = branchManager.add("Taylor");
        Branch rebecca_taylor = branchManager.add("Rebecca Taylor");
        Branch  agatha = branchManager.add("Agatha Ruiz dela Prada");
        //-----------------------------------BRANCH

        //ADD TYPE
        Type clothing_type = typeManager.add("Clothing Type");
        Type moda = typeManager.add("Moda");
        //------------------------------------TYPE

        //ADD Item Type
        itemTypeManager.add(clothing_type, "Denim");
        itemTypeManager.add(clothing_type,"Pants");
        itemTypeManager.add(clothing_type,"Sweaters");
        itemTypeManager.add(clothing_type,"Skirts");
        itemTypeManager.add(clothing_type,"Dresses");
        //------------------------------------Item Type

        //ADD Item
        itemManager.add(
                clothing_type,
                gap,
                "555",
                "Wide-leg utility",
                10
        );
        itemManager.add(
                moda,
                agatha,
                "556",
                "Floral",
                11
        );
        //------------------------------------Item
    }

}
