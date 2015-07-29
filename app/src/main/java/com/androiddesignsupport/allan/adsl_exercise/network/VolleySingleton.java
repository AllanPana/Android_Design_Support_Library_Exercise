package com.androiddesignsupport.allan.adsl_exercise.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.androiddesignsupport.allan.adsl_exercise.MyApplication;

/**
 * Created by allan on 24/07/15.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingletonInstance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            LruCache <String,Bitmap> lruCache = new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024)/8);

            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lruCache.put(s,bitmap);
            }
        });

    }



    public static VolleySingleton getVolleySingletonInstance(){
        if(volleySingletonInstance == null){
            volleySingletonInstance = new VolleySingleton();
        }
        return volleySingletonInstance;
    }



    public RequestQueue getRequestQueue() {
        return requestQueue;
    }



    public ImageLoader getImageLoader() {
        return imageLoader;
    }



    public <T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
