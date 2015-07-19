package com.tti.loaderexample;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(87, null, MainActivity.this).forceLoad();
    }



    @Override
    public Loader<String> onCreateLoader(int loaderId, Bundle bundle) {
        Log.d(TAG, "onCreateLoader");
        MyLoader loader = new MyLoader(this);
        loader.setURL("http://google.com");

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished");
        Log.d(TAG, "Data = "+data);

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(TAG, "loader reset");

    }

    public void onClickLoad(View view) {
        Log.d(TAG, "onClick");
        getSupportLoaderManager().restartLoader(87, null, this);
    }
}
