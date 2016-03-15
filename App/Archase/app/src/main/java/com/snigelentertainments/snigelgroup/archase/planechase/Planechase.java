package com.snigelentertainments.snigelgroup.archase.planechase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.snigelentertainments.snigelgroup.archase.MainActivity;


/**
 * Created by rhofmann on 14.03.2016.
 */
public class Planechase extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.TAG, String.format("Planechase started with %s", savedInstanceState));
    }
}
