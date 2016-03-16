package com.snigelentertainments.snigelgroup.archase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snigelentertainments.snigelgroup.archase.planechase.PlanechaseLaunch;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Archase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("Mainactivity started, with %s", savedInstanceState));

        setContentView(R.layout.activity_main);


    }


    public void onClickPlanechase(View view){
        Log.d(TAG, "launching planechase laucnher");
        if (view.getId() == R.id.b_planechase){
            Context context = this.getApplicationContext();
            String text = "Planechase not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Intent i = new Intent(MainActivity.this, PlanechaseLaunch.class);
            //startActivity(i);

        }
        else {
            String v = (String) (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried launching planechase with the wrong view: %s", v));
        }
    }

    public void onClickArchenemy(View view){
        Log.d(TAG, "launching archenemy launcher");
        if (view.getId() == R.id.b_archenemy){
            Context context = this.getApplicationContext();
            String text = "Archenemy not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            String v = (String)(view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried launching archenmy with the wrong view: %s", v));
        }

    }

    public void onClickImpressum(View view){
        Log.d(TAG, "launching Impressumg");
        if (view.getId() == R.id.b_impressum){
            Context context = this.getApplicationContext();
            String text = "Impressum not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            String v = (String)(view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried launching archenmy with the wrong view: %s", v));
        }
    }
}
