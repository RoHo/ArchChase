package com.snigelentertainments.snigelgroup.archase.archenemy;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.snigelentertainments.snigelgroup.archase.MainActivity;
import com.snigelentertainments.snigelgroup.archase.PileActivity;
import com.snigelentertainments.snigelgroup.archase.R;
import com.snigelentertainments.snigelgroup.archase.RulesDialogFragment;

import misc.stack.CStack;
import misc.stack.HeapFactory;
import misc.stack.PileItem;

public class ArchenemyLaunch extends AppCompatActivity {

    public final static String TAG = MainActivity.TAG;

    private Spinner selectPredefinedDeck, selectRandomSize;
    private CheckBox allowRepetitions, allowPromos;
    private Intent lastRun;


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

        lastRun = null;

        allowRepetitions = (CheckBox) findViewById(R.id.cb_ar_allowrep);
        allowPromos = (CheckBox) findViewById(R.id.cb_ar_allowpromos);

        Log.v(TAG, "setting onItemClickListeener for predefined spinner");
        this.selectPredefinedDeck = (Spinner) findViewById(R.id.sp_ar_quickAccess);
        this.selectPredefinedDeck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        String spinnerItems[] = {"Assemble the Doomsday Machine", "Trample Civilization Underfoot", "Scorch the World with Dragonfire", "Bring About the Undead Apocalypse", "[Exit]"};
        //String spinnerItems[] = new String[3];
        //spinnerItems[0] = "Deck1";
        //spinnerItems[1] = "Deck2";
        //spinnerItems[2] = "Deck3";
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        //TODO find a prettier look for the spinner, i.e. increased text size
        this.selectPredefinedDeck.setAdapter(spinnerAdapter);



        Log.v(TAG, "setting onItemClickListeener for random size spinner");
        this.selectRandomSize = (Spinner) findViewById(R.id.spinner_random_number);
        this.selectRandomSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, String.format("onItemSelected random size: %d", position));
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
                    String text = "Size currently ignored!";
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
            String spinnerItems2[] = {"10", "15", "20", "25", "30", "All"};
        //String spinnerItems[] = new String[3];
        //spinnerItems[0] = "Deck1";
        //spinnerItems[1] = "Deck2";
        //spinnerItems[2] = "Deck3";
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems2);
        //TODO find a prettier look for the spinner, i.e. increased text size
        this.selectRandomSize.setAdapter(spinnerAdapter2);
        this.selectRandomSize.setSelection(3);


    }

    public void doesNothing(View view){
        String text = "This does currently nothing!";
        int duration = Toast.LENGTH_SHORT;
        Context context = ArchenemyLaunch.this.getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void showRules(View view){
        Log.d(TAG, "showRules started");

        String text = "This does currently nothing!";
        int duration = Toast.LENGTH_SHORT;
        Context context = ArchenemyLaunch.this.getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        DialogFragment rulesFragment = new RulesDialogFragment();
        //rulesFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");


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
        Log.v(TAG, "selected size" + selectRandomSize.getSelectedItem());

        CStack<PileItem> myPile = null;
        //get the real pile size
        String selctedSize = (String) selectRandomSize.getSelectedItem();
        if (selctedSize.equals("All")){
            //TODO create a pile with each scheme once

            Toast toast2 = Toast.makeText(context, "Currently not implemetned", duration);
            toast2.show();
            return;
        }
        else{
            try{
                int pileSize = Integer.parseInt(selectRandomSize.getSelectedItem().toString());
                int allowRepetions = allowRepetitions.isChecked()?2:1;
                boolean allowProm = allowPromos.isChecked();
                myPile = HeapFactory.getFactory().getRandomCardPile("schemes", pileSize,allowRepetions, allowProm, getAssets());
            }
            catch (Exception e){
                Toast toast2 = Toast.makeText(context, "Error while converting the size:\n"+e.getClass().toString(), duration);
                toast.show();
                e.printStackTrace();
                Log.e(TAG, "pile creation failed: "+ e.getStackTrace().toString());
                return;
            }
        }



        Log.v(TAG, String.format("myPile: %s", myPile));
        assert myPile != null;
        for (PileItem pi: myPile.get_items()){
            Log.v(TAG, String.format("pi: %s", pi));
        }

        Log.v(TAG, "creating pileactivity intent");


        Intent i = new Intent(ArchenemyLaunch.this, PileActivity.class);
        Log.v(TAG, String.format("putting pile into extras: %s", System.identityHashCode(myPile)));
        //i.putExtra("cstack", myPile);
        i.putExtra("type", "Random");
        //TODO allow real settings here
        i.putExtra("allowReps", true);
        i.putExtra("showScans", true);
        i.putExtra("allowPromos", true);
        i.putExtra("new", true);
        this.lastRun = i;
        startActivity(i);


    }

    public void loadLastRun(View view){
        Log.d(TAG, "attempting to load last archenemy run");
        if (this.lastRun == null){
            //TODO nothing to load
            Context context = ArchenemyLaunch.this.getApplicationContext();
            Toast toast = Toast.makeText(context, "Nothing to load", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            try{
                this.lastRun.putExtra("new", false);
                this.lastRun.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                this.lastRun.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.lastRun.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(this.lastRun);
            }
            catch (Exception e){
                Context context = ArchenemyLaunch.this.getApplicationContext();
                Toast toast = Toast.makeText(context, "could not restart latest game", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
