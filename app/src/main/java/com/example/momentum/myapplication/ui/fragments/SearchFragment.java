package com.example.momentum.myapplication.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.momentum.myapplication.R;
import com.example.momentum.myapplication.interfaces.MainActivityInterface;
import com.example.momentum.myapplication.ui.activities.MainActivity;
import com.example.momentum.myapplication.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainActivityInterface} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    public static final String TAG = SearchFragment.class.getSimpleName();

    private MainActivityInterface mListener;

    @Bind(R.id.etSearch)
    EditText etSearch;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param args Parameter 1.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(Bundle args) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        etSearch.setText("");
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

    //MY METHODS

    public boolean isValid() {
        if(etSearch.getText().toString().isEmpty() ||
                etSearch.getText().toString().trim().equals("")){
            return false;
        }
        return true;
    }

    //LISTENERS
    @OnClick(R.id.search_button_go)
    public void search(View v){
        if(!isValid()){
            return ;
        }

        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_TAG,ResultsFragment.TAG);
        bundle.putInt(Constants.BUNDLE_ID, Constants.RESULTS_FRAMENT);
        bundle.putString(Constants.BUNDLE_EXTRA, etSearch.getText().toString());
        mListener.replaceFragment(bundle);
    }


}
