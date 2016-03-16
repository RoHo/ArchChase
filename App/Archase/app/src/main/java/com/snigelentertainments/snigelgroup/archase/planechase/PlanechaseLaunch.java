package com.snigelentertainments.snigelgroup.archase.planechase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.snigelentertainments.snigelgroup.archase.R;

public class PlanechaseLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planechase_launch);
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
