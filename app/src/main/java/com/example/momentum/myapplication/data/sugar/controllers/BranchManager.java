package com.example.momentum.myapplication.data.sugar.controllers;

import android.util.Log;

import com.example.momentum.myapplication.data.sugar.models.Branch;
import com.example.momentum.myapplication.utils.AndroidUtilities;

import java.util.List;


/**
 * Created by Dorian on 27/07/2016.
 */

public class BranchManager {
    private static final String TAG = BranchManager.class.getSimpleName();


    public BranchManager(){
    }

    public Branch add(String name) {
        Branch branch = new Branch();
        branch.setName(name);
        branch.save();
        return branch;
    }

    public boolean add(String... branchs) {
        try{
            for (String name : branchs){
                add(name);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void showAll() {
        List<Branch> results = Branch.listAll(Branch.class);
        for (Branch branch : results){
            Log.d(TAG+" Dars",branch.getName());
        }
    }

    public List<Branch> searchContains(String search) {
        List<Branch> results = Branch.listAll(Branch.class);
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
