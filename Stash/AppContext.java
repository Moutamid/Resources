package com.moutamid.quranrepetitionalarm;

import android.app.Application;

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stash.init(this);
    }
}
