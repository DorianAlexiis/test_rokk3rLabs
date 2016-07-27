package com.example.momentum.myapplication.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.momentum.myapplication.R;
import com.example.momentum.myapplication.data.sugar.SugarManager;
import com.example.momentum.myapplication.data.sugar.controllers.BranchManager;
import com.example.momentum.myapplication.interfaces.MainActivityInterface;
import com.example.momentum.myapplication.ui.fragments.ResultsFragment;
import com.example.momentum.myapplication.ui.fragments.SearchFragment;
import com.example.momentum.myapplication.utils.Constants;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainActivityInterface{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<String> TagFragments;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        TagFragments = new ArrayList<>();

        if(SugarManager.isDBEmpty()){
            SugarManager.generateSeek();
        }
        BranchManager branchManager = new BranchManager();
        branchManager.showAll();
        //ADD FRAGMENT
        if(savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_TAG, SearchFragment.TAG);
            bundle.putInt(Constants.BUNDLE_ID, Constants.SEARCH_FRAMENT);
            replaceFragment(bundle);
        }
    }


    /*
        *
        *
        * Guarda la instancia de todos los tags y el ArrayList de Tags
        *
        *
        * */
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        for(int i=0; i<TagFragments.size();i++){
            if(fm.findFragmentByTag(TagFragments.get(i)) != null){
                fm.putFragment(outState,
                        TagFragments.get(i),
                        fm.findFragmentByTag(TagFragments.get(i))
                );
            }
        }
        outState.putStringArrayList(Constants.TAG_FRAGMENTS, TagFragments);
    }

    /*
    *
    * Se restablece todos los fragment con el ArrayList que contiene los tags
    *
    * */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TagFragments = savedInstanceState.getStringArrayList(Constants.TAG_FRAGMENTS);
        if(TagFragments == null){
            return;
        }
        for(int i=0; i<TagFragments.size();i++){
            Fragment fragment = fm.getFragment(savedInstanceState,TagFragments.get(i));
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            if((fragmentTransaction != null) && (fragment != null)){
                fragmentTransaction
                        .replace(android.R.id.content, fragment,TagFragments.get(i))
                        .commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    //MY METHODS

    private String getLastTAG() {
        if (fm.getBackStackEntryCount() < 1){
            return "";
         } else{
            return fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();
        }
    }


    //LISTENRS
    @Override
    public void replaceFragment(Bundle bundle) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        String tag = bundle.getString(Constants.BUNDLE_TAG);
        if(getLastTAG().equals(tag) || (tag == null)){
            return;
        }
        Fragment fragment = null;
        int id = bundle.getInt(Constants.BUNDLE_ID);
        switch (id){
            case Constants.SEARCH_FRAMENT:
                fragment = SearchFragment.newInstance(bundle);
                fragmentTransaction
                        .replace(android.R.id.content, fragment, tag)
                        .commit();
                TagFragments.add(tag);
                return;
            case Constants.RESULTS_FRAMENT:
                fragment = ResultsFragment.newInstance(bundle);
                break;
        }
        try{
            fragmentTransaction.replace(android.R.id.content, fragment,tag)
                    .addToBackStack(tag)
                    .commit();
            TagFragments.add(tag);
        }catch (Exception e){
            Log.w(TAG+" Dars","Can not perform this action after onSaveInstanceState");
        }
    }
}
