package com.snigelentertainments.snigelgroup.archase.archenemy;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.snigelentertainments.snigelgroup.archase.MainActivity;
import com.snigelentertainments.snigelgroup.archase.PileActivity;
import com.snigelentertainments.snigelgroup.archase.PileLandscapeActivity;
import com.snigelentertainments.snigelgroup.archase.R;

import misc.stack.CStack;
import misc.stack.HeapFactory;
import misc.stack.PileItem;

public class ArchenemyLaunch extends AppCompatActivity {

    public final static String TAG = MainActivity.TAG;

    private Spinner selectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "archEnemyLaunch onCreate");
        setContentView(R.layout.activity_archenemy_launch);
        ActionBar bar = getActionBar();
        if (bar != null) {
            getActionBar().setTitle("Archenemy");
        } else {
            Log.w(TAG, "actionbar could not be accessed in planechaselaunchers");
        }
        setTitle("Archenemy");

        Log.v(TAG, "setting onItemClickListeener for spinner");
        this.selectList = (Spinner) findViewById(R.id.sp_ar_quickAccess);
        this.selectList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, String.format("onItemSelected pos: %d", position));
                Object selection = parent.getSelectedItem();
                Log.v(TAG, selection.toString());
                //PlanechaseLaunch.this.startDeck();

                if (position <= 0){
                    Log.v(TAG, String.format("spinner ignored with pos=%d", position));
                }
                else{
                    Log.v(TAG, String.format("Spinner selection accepted with %d:%s", position, selection.toString()));
                    //PlanechaseLaunch.this.startDeck(selection.toString());
                    Context context = ArchenemyLaunch.this.getApplicationContext();
                    String text = "Real deck lists not yet implemented!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.v(TAG, "nothing selected");
                //do nothing
            }

        });
        String spinnerItems[] = {"Preconstructed Deck", "Deck1", "Deck2", "Deck3"};
        //String spinnerItems[] = new String[3];
        //spinnerItems[0] = "Deck1";
        //spinnerItems[1] = "Deck2";
        //spinnerItems[2] = "Deck3";
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        //TODO find a prettier look for the spinner, i.e. increased text size
        this.selectList.setAdapter(spinnerAdapter);


    }




    /**
     * starts a game with a stack of 40 random cards, no card more than twice allowed
     * @param view
     */
    public void startRandom40(View view){

        Log.d(TAG, "startRandom40");

        Context context = this.getApplicationContext();
        String text = "Random not yet COMPLETLY implemented!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        //toast.show();

        CStack<PileItem> myPile = null;
        //TODO change 5 to 40

        myPile = HeapFactory.getFactory().getRandomCardPile("schemes", 40,2, getAssets());
        Log.v(TAG, String.format("myPile: %s", myPile));
        assert myPile != null;
        for (PileItem pi: myPile.get_items()){
            Log.v(TAG, String.format("pi: %s", pi));
        }

        Log.v(TAG, "creating pileactivity intent");


        Intent i = new Intent(ArchenemyLaunch.this, PileActivity.class);
        i.putExtra("cstack", myPile);
        i.putExtra("type", "Random");
        startActivity(i);


    }

}
