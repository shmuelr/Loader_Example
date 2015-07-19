package com.tti.loaderexample;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by shmuel on 7/19/15.
 */
public class MyLoader extends AsyncTaskLoader<String> {
    private static final String TAG = "MyLoader";

    private static final Map<String,String> myCache = new HashMap<>();
    private String url = null;

    public MyLoader(Context context) {
        super(context);
        Log.d(TAG, "Loader constructor");
    }

    public void setURL(String url){
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, "onStartLoading");

        if (myCache.get(url) == null){
            forceLoad();
        }else{
            Log.d(TAG, "We have a cache hit!");
            deliverResult(myCache.get(url));
        }


    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
        Log.d(TAG, "deliverResult");
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
        Log.d(TAG, "Force Load");
    }

    @Override
    public String loadInBackground() {

        Log.d(TAG, "loadInBack");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        String result = null;
        try {
            response = client.newCall(request).execute();
            result =  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result != null)   {
            myCache.put(url, result);
        }


        return result;
    }
}
