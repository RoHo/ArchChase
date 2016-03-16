package com.snigelentertainments.snigelgroup.archase.planechase;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.snigelentertainments.snigelgroup.archase.R;
import com.snigelentertainments.snigelgroup.archase.MainActivity;


public class PlanechaseLaunch extends AppCompatActivity {

    public static final String TAG = MainActivity.TAG;

    private Spinner selectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "planechaelauncher onCreate");
        setContentView(R.layout.activity_planechase_launch);
        ActionBar bar = getActionBar();
        if (bar != null) {
            getActionBar().setTitle("Planechase");
        }
        else{
            Log.w(TAG, "actionbar could not be accessed in planechaselaunchers");
        }
        setTitle("Planechase");

        Log.v(TAG, "setting onItemClickListeener for spinner");
        this.selectList = (Spinner) findViewById(R.id.sp_quickAccess);
        this.selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = PlanechaseLaunch.this.selectList.getSelectedItem();
                
            }
        });

    }


    /**
     * starts a game with a stack of 40 random cards, no card more than twice allowed
     * @param view
     */
    public void startRandom40(View view){

    }

    /**
     * starts a game with a stack which belongs to one preconstructed deck
     * @param view
     */
    public void startDeck(View view){


    }

    /**
     * offers options for advanced control and stack building
     * @param view
     */
    public void advancedPileBuilding(View view){


    }

    /**
     * shows the help messages
     * @param view
     */
    public void help(View view){


    }
}
