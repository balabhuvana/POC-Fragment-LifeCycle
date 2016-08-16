package com.example.bala.poc_fragment_lifecycle;

import android.app.Application;
import android.content.res.Configuration;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by bala on 13/8/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
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
