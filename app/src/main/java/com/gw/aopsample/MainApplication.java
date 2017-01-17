package com.gw.aopsample;

import android.app.Application;

import com.gw.aoplibrary.DebugLogAspect;

/**
 * Created by GongWen on 17/1/17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebugLogAspect.setEnabled(true);
    }
}
