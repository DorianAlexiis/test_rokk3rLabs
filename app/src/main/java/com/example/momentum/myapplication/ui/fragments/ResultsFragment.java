package com.example.momentum.myapplication.ui.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.momentum.myapplication.R;
import com.example.momentum.myapplication.data.sugar.controllers.BranchManager;
import com.example.momentum.myapplication.data.sugar.controllers.ItemManager;
import com.example.momentum.myapplication.data.sugar.controllers.ItemTypeManager;
import com.example.momentum.myapplication.data.sugar.models.Branch;
import com.example.momentum.myapplication.data.sugar.models.Item;
import com.example.momentum.myapplication.data.sugar.models.ItemType;
import com.example.momentum.myapplication.interfaces.MainActivityInterface;
import com.example.momentum.myapplication.ui.adapters.MyAdapter;
import com.example.momentum.myapplication.utils.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {
    public static final String  TAG = ResultsFragment.class.getSimpleName();

    private ProgressDialog progressDialog = null;
    private MainActivityInterface mListener;
    private String mSearch;
    private ItemManager mItemManager;
    private BranchManager mBranchManager;
    private ItemTypeManager mItemTypeManager;

    //data
    private List<Branch> branchs;
    private List<Item> items;
    private List<ItemType> itemTypes;


    //VIEWS
    @Bind(R.id.rvBranchs)
    RecyclerView rvBranchs;
    @Bind(R.id.rvClothingType)
    RecyclerView rvClothingType;
    @Bind(R.id.rvQuery)
    RecyclerView rvQuery;
    @Bind(R.id.llBranchContent)
    LinearLayout llBranchContent;
    @Bind(R.id.llTypeContent)
    LinearLayout llTypeContent;
    @Bind(R.id.tvMessageItemEmpty)
    TextView tvMessageItemEmpty;

    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param args Parameter 1.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(Bundle args) {
        ResultsFragment fragment = new ResultsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearch = getArguments().getString(Constants.BUNDLE_EXTRA);
        }
        mBranchManager = new BranchManager();
        mItemManager = new ItemManager();
        mItemTypeManager = new ItemTypeManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.bind(this,view);
        new AsynTaskFind().execute();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityInterface) {
            mListener = (MainActivityInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainActivityInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    //MY METHODS
    private void showLoading(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();
    }

    private void hideLoading(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void newBranchAdapter() {
        if(branchs.size() > 0){
            llBranchContent.setVisibility(View.VISIBLE);
            String data [] = new String[branchs.size()];
            for(int i=0; i< branchs.size();i++){
                data [i] = branchs.get(i).getName();
            }
            MyAdapter branchAdapter = new MyAdapter(getContext(), data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvBranchs.setLayoutManager(layoutManager);
            rvBranchs.setItemAnimator(new DefaultItemAnimator());
            rvBranchs.setAdapter(branchAdapter);
            rvBranchs.setLayoutManager(layoutManager);
        }else{
            llBranchContent.setVisibility(View.GONE);
        }

    }

    private void newTypeAdapter() {
        if(itemTypes.size() > 0){
            llTypeContent.setVisibility(View.VISIBLE);
            String data [] = new String[itemTypes.size()];
            for(int i=0; i< itemTypes.size();i++){
                data [i] = itemTypes.get(i).getName();
            }
            MyAdapter typeAdapter = new MyAdapter(getContext(), data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvClothingType.setLayoutManager(layoutManager);
            rvClothingType.setItemAnimator(new DefaultItemAnimator());
            rvClothingType.setAdapter(typeAdapter);
            rvClothingType.setLayoutManager(layoutManager);
        }else{
            llTypeContent.setVisibility(View.GONE);
        }
    }

    private void newItemAdapter() {
        if(items.size() > 0){
            tvMessageItemEmpty.setVisibility(View.GONE);
            rvQuery.setVisibility(View.VISIBLE);
            String data [] = new String[items.size()];
            for(int i=0; i< items.size();i++){
                data [i] = items.get(i).getName();
            }
            MyAdapter itemAdapter = new MyAdapter(getContext(), data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvQuery.setLayoutManager(layoutManager);
            rvQuery.setItemAnimator(new DefaultItemAnimator());
            rvQuery.setAdapter(itemAdapter);
            rvQuery.setLayoutManager(layoutManager);
        }else{
            tvMessageItemEmpty.setVisibility(View.VISIBLE);
            tvMessageItemEmpty.setText(getResources().getString(R.string.items_empty,mSearch));
            rvQuery.setVisibility(View.GONE);
        }
    }


    //LISTNERS




    //ASYNTAKS

    private class AsynTaskFind extends AsyncTask<Void,Void, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            //BRANCHS
            branchs = mBranchManager.searchContains(mSearch);

            //Clothing Type 
            itemTypes = mItemTypeManager.searchContains(mSearch);

            //OTHER
            items = mItemManager.searchContains(mSearch);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            //NEW ADAPTERS
            newBranchAdapter();
            newTypeAdapter();
            newItemAdapter();


            hideLoading();
        }
    }
}
