package com.example.momentum.myapplication.ui;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Dorian on 27/07/2016.
 */

public class MyApplication extends Application{
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
