package com.snigelentertainments.snigelgroup.archase.planechase;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.snigelentertainments.snigelgroup.archase.PileLandscapeActivity;
import com.snigelentertainments.snigelgroup.archase.R;
import com.snigelentertainments.snigelgroup.archase.MainActivity;
import com.snigelentertainments.snigelgroup.archase.dialogs.RulesDialogFragmentArch;
import com.snigelentertainments.snigelgroup.archase.dialogs.RulesDialogFragmentPlane;

import misc.stack.CStack;
import misc.stack.HeapFactory;
import misc.stack.PileItem;


public class PlanechaseLaunch extends AppCompatActivity {

    public static final String TAG = MainActivity.TAG;

    private Spinner selectList;
    private Spinner selectRandomSize;
    private CheckBox allowRepetitions, allowPromos;

    private Intent lastRun;

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

        this.lastRun = null;
        ((Button) findViewById(R.id.b_pl_resume)).setEnabled(false);

        this.allowRepetitions = (CheckBox) findViewById(R.id.cb_pl_allowrep);
        this.allowPromos = (CheckBox) findViewById(R.id.cb_pl_allowpromos);

        Log.v(TAG, "setting onItemClickListeener for spinner");
        this.selectList = (Spinner) findViewById(R.id.sp_pl_quickAccess);
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
                    Context context = PlanechaseLaunch.this.getApplicationContext();
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
        String spinnerItems[] = {"Elemental Thunder (2009)", "Metallic Dreams (2009)", "Strike Force (2009)", "Zombie Empire (2009)",
                "Chaos Reigns (2012)", "Night of the Ninja (2012)", "Primordial Hunger (2012)", "Savage Auras (2012)", "[Exit]"};
        //String spinnerItems[] = new String[3];
        //spinnerItems[0] = "Deck1";
        //spinnerItems[1] = "Deck2";
        //spinnerItems[2] = "Deck3";
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        //TODO find a prettier look for the spinner, i.e. increased text size
        this.selectList.setAdapter(spinnerAdapter);


        String spinnerItems2[] = {"10", "15", "20", "25", "30", "All"};
        //String spinnerItems[] = new String[3];
        //spinnerItems[0] = "Deck1";
        //spinnerItems[1] = "Deck2";
        //spinnerItems[2] = "Deck3";
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems2);
        //TODO find a prettier look for the spinner, i.e. increased text size


        Log.v(TAG, "setting onItemClickListeener for random size spinner");
        this.selectRandomSize = (Spinner) findViewById(R.id.spinner_random_number_pl);
        this.selectRandomSize.setAdapter(spinnerAdapter2);
        this.selectRandomSize.setSelection(3);
    }

    /**
     * starts a game with a stack of 40 random cards, no card more than twice allowed
     * @param view
     */
    public void startRandomN(View view){

        Log.d(TAG, "startRandom40");

        Context context = this.getApplicationContext();
        String text = "Random not yet COMPLETLY implemented!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        //toast.show();

        CStack<PileItem> myPile = null;
        //TODO change 5 to 40
        if (this.selectRandomSize.equals("All")){
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
                myPile = HeapFactory.getFactory().getRandomCardPile("planes", pileSize,allowRepetions, allowProm, getAssets());
            }
            catch (Exception e){
                Toast toast2 = Toast.makeText(context, "Error while converting the size:\n"+e.getClass().toString(), duration);
                toast.show();
                e.printStackTrace();
                Log.e(TAG, "pile creation failed: "+ e.getStackTrace().toString());
                return;
            }
        }

        if (myPile != null) {
            Log.v(TAG, "creating pileactivity intent");

            Intent i = new Intent(PlanechaseLaunch.this, PileLandscapeActivity.class);
            i.putExtra("cstack", myPile);
            i.putExtra("allowReps", true);
            i.putExtra("showScans", true);
            i.putExtra("allowPromos", true);
            i.putExtra("new", true);
            i.putExtra("type", "Random");
            startActivity(i);
        }

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
        if (this.selectRandomSize.equals("All")){
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
                myPile = HeapFactory.getFactory().getRandomCardPile("planes", pileSize,allowRepetions, allowProm, getAssets());
            }
            catch (Exception e){
                Toast toast2 = Toast.makeText(context, "Error while converting the size:\n"+e.getClass().toString(), duration);
                toast.show();
                e.printStackTrace();
                Log.e(TAG, "pile creation failed: "+ e.getStackTrace().toString());
                return;
            }
        }

        if (myPile != null) {
            Log.v(TAG, "creating pileactivity intent");

            Intent i = new Intent(PlanechaseLaunch.this, PileLandscapeActivity.class);
            i.putExtra("cstack", myPile);
            i.putExtra("allowReps", true);
            i.putExtra("showScans", true);
            i.putExtra("allowPromos", true);
            i.putExtra("new", true);
            i.putExtra("type", "Random");
            ((Button) findViewById(R.id.b_pl_resume)).setEnabled(true);
            this.lastRun = i;
            startActivity(i);
        }

    }

    /**
     * starts a game with a stack which belongs to one preconstructed deck
     * @param deckname
     */
    public void startDeck(String deckname){
        Log.d(TAG, String.format("startDeck started with %s", deckname));

        Intent i = new Intent(PlanechaseLaunch.this, PileLandscapeActivity.class);
        startActivity(i);

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

    public void doesNothing(View view){

    }


    public void loadLastRun(View view){
        Log.d(TAG, "attempting to load last planechase run");
        if (this.lastRun == null){
            //TODO nothing to load
            Context context = PlanechaseLaunch.this.getApplicationContext();
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
                Context context = PlanechaseLaunch.this.getApplicationContext();
                Toast toast = Toast.makeText(context, "could not restart latest game", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void showRules(View view){
        Log.d(TAG, "showRules started");


        DialogFragment rulesFragment = new RulesDialogFragmentPlane();
        FragmentManager fm = getFragmentManager();

        rulesFragment.show(fm, "NoticeDialogFragment");
    }
}
