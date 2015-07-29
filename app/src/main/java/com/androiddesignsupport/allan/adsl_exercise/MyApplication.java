package com.androiddesignsupport.allan.adsl_exercise;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by allan on 24/07/15.
 */
public class MyApplication extends Application {

    private static MyApplication myApplicationInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationInstance = this;
        //Toast.makeText(this.getApplicationContext(),"MyApplication onCreate called",Toast.LENGTH_LONG).show();
    }

    public static MyApplication getMyApplicationInstance() {
        return myApplicationInstance;
    }


    public static Context getAppContext(){
        return myApplicationInstance.getApplicationContext();
    }
}
