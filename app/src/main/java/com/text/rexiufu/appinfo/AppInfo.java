package com.text.rexiufu.appinfo;

import android.graphics.drawable.Drawable;

import java.io.Serializable;


public class AppInfo implements Serializable {

    private static final long serialVersionUID = -4187894117665116364L;
    String appName;
    String packageName;
    Drawable drawable;

    public AppInfo(){}

    public AppInfo(String appName){
        this.appName = appName;
    }

    public AppInfo(String appName, String packageName){
        this.appName = appName;
        this.packageName = packageName;
    }

    public AppInfo(String appName,String packageName, Drawable drawable){
        this.appName = appName;
        this.packageName = packageName;
        this.drawable = drawable;
    }



    public String getAppName() {
        if(null == appName)
            return "";
        else
            return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        if(null == packageName)
            return "";
        else
            return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

}