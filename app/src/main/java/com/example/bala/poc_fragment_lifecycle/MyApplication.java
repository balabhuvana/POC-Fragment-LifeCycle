package com.example.bala.poc_fragment_lifecycle;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by bala on 13/8/16.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
