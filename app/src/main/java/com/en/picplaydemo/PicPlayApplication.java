package com.en.picplaydemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by en on 2016/9/23.
 */
public class PicPlayApplication extends Application {
    @Override
    public void onCreate() {
        Fresco.initialize(getApplicationContext());

        super.onCreate();

    }
}
