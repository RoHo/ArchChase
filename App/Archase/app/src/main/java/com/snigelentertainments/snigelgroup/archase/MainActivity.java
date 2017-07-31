package com.snigelentertainments.snigelgroup.archase;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snigelentertainments.snigelgroup.archase.archenemy.ArchenemyLaunch;
import com.snigelentertainments.snigelgroup.archase.dialogs.ImpressumDialogFragment;
import com.snigelentertainments.snigelgroup.archase.dialogs.KnownIssuesDialogFragment;
import com.snigelentertainments.snigelgroup.archase.planechase.PlanechaseLaunch;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Archase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("Mainactivity started, with %s", savedInstanceState));

        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        if (bar == null){
            Log.w(TAG, "could not access actionbar from main activity");
        }
        setTitle("Archase");
    }

    public void doesNothing(View view){
        Context context = this.getApplicationContext();
        String text = "Currently does nothing";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public void onClickPlanechase(View view){
        Log.d(TAG, "launching planechase laucnher");
        if (view.getId() == R.id.b_planechase){
            Intent i = new Intent(MainActivity.this, PlanechaseLaunch.class);
            startActivity(i);

        }
        else {
            String v = (String) (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried launching planechase with the wrong view: %s", v));
        }
    }

    public void onClickArchenemy(View view){
        Log.d(TAG, "launching archenemy launcher");
        if (view.getId() == R.id.b_archenemy){

            Intent i = new Intent(MainActivity.this, ArchenemyLaunch.class);
            startActivity(i);
        }
        else {
            String v = (String)(view == null?"null":""+view.getId());
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

    public void onClickShowKnownIssues(View view){
        Log.d(TAG, "showRules started");

        DialogFragment issuesFragment = new KnownIssuesDialogFragment();
        FragmentManager fm = getFragmentManager();

        issuesFragment.show(fm, "NoticeDialogFragment");
    }

    public void onClickShowLegalNotice(View view){
        Log.d(TAG, "showRules started");

//        String text = "This does currently nothing!";
//        int duration = Toast.LENGTH_SHORT;
//        Context context = ArchenemyLaunch.this.getApplicationContext();
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        DialogFragment legalFragment = new ImpressumDialogFragment();
        FragmentManager fm = getFragmentManager();

        legalFragment.show(fm, "NoticeDialogFragment");
    }
}
