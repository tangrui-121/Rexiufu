package com.text.rexiufu;

import android.app.Application;
import com.text.rexiufu.objectbox.MyObjectBox;
import io.objectbox.BoxStore;

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        initObjectBox();
    }

    public static MyApplication getInstance() {
        return application;
    }

    private static BoxStore boxStore;

    private void initObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}