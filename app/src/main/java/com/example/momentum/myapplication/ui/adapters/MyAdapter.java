package com.example.momentum.myapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momentum.myapplication.R;
import com.example.momentum.myapplication.data.sugar.models.Branch;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static String TAG = MyAdapter.class.getSimpleName();


    private Context mContext;
    private  String data[];


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvText)
        TextView tvText;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    public MyAdapter( Context mContext, String data[]) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       holder.tvText.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}