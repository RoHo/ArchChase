package com.snigelentertainments.snigelgroup.archase;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import misc.stack.CStack;
import misc.stack.PileItem;

public class PileLandscapeActivity extends Activity {

    public static final String TAG = MainActivity.TAG;

    private CStack<PileItem> myPile;

    Button bOracle, bNext, bScry, bShuffle, bToggle;
    TextView tvTitle, tvOracle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "pileactivity oncreated");
        setContentView(R.layout.activity_landscape_pile);

        bOracle = (Button) findViewById(R.id.b_oracle);
        bNext = (Button) findViewById(R.id.b_next);
        bShuffle = (Button) findViewById(R.id.b_shuffle);
        bScry = (Button) findViewById(R.id.b_scry);
        bToggle = (Button) findViewById(R.id.b_hide);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvOracle = (TextView) findViewById(R.id.tv_oracle);

        Log.v(TAG, "trying to access bundle");
        Bundle b = this.getIntent().getExtras();
        Serializable s = b.getSerializable("cstack");
        this.myPile = (CStack<PileItem>) s;
        String type  = b.getString("type");

        Log.v(TAG, this.myPile==null?"pile is null":s.toString());

        Context context = this.getApplicationContext();
        String text = String.format("Heap\nType: \t%s\nPile size: \t%d", type, this.myPile.get_size());
        Log.v(TAG, text);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        PileItem firstEntry = this.myPile.get_first();
        String firstDrawableName = (String)firstEntry.getPicture();
        Log.v(TAG, String.format("firstpicAddess: %s", firstDrawableName));

        AssetManager assetManager = getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(firstDrawableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable firstDrawable = Drawable.createFromStream(istr, firstDrawableName);

        Log.v(TAG, String.format("FirstEntry: %s", firstEntry));
        Log.v(TAG, String.format("Firstdrawable: %s", firstDrawable));

        ImageView iview = (ImageView) findViewById(R.id.iv_background);
        iview.setImageDrawable(firstDrawable);
        iview.refreshDrawableState();

        tvTitle.setText(firstEntry.getName());
        tvTitle.setVisibility(View.INVISIBLE);
    }

    public void onClickNextCard(View view){
        Log.d(TAG, "onClickNextCard");

        if (view.getId() == R.id.b_next || view.getId() == R.id.iv_background){
            //flip the pile
            PileItem pi = this.myPile.turn_over();
            //get path to picture
            String firstDrawableName = (String)pi.getPicture();
            Log.v(TAG, String.format("new pi address: %s", firstDrawableName));

            AssetManager assetManager = getAssets();
            InputStream istr = null;
            try {
                istr = assetManager.open(firstDrawableName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable firstDrawable = Drawable.createFromStream(istr, firstDrawableName);

            Log.v(TAG, String.format("pi entry: %s", pi));
            Log.v(TAG, String.format("pi drawable: %s", firstDrawable));

            // update oracle and title
            String oracleText = this.myPile.get_first().getOracle();
            String titleText = this.myPile.get_first().getName();
            this.tvTitle.setText(titleText);
            this.tvOracle.setText(oracleText);

            ImageView iview = (ImageView) findViewById(R.id.iv_background);
            iview.setImageDrawable(firstDrawable);
            iview.refreshDrawableState();
        }
        else {
            String v = "" + (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried going to the next card with the wrong view: %s", v));
        }
    }


    public void toggleLegend(View view){
        Log.d(TAG, "Legendviewtoggled");
        if (view.getId() == R.id.b_hide){

            if (bOracle.getVisibility()==View.INVISIBLE || bScry.getVisibility()==View.INVISIBLE || bShuffle.getVisibility()==View.INVISIBLE){
                Log.v(TAG, "set all to VISIBLE");
                bOracle.setVisibility(View.VISIBLE);
                bScry.setVisibility(View.VISIBLE);
                bShuffle.setVisibility(View.VISIBLE);
            }
            else{
                Log.v(TAG, "set all to INIVISIBLE");
                bOracle.setVisibility(View.INVISIBLE);
                bScry.setVisibility(View.INVISIBLE);
                bShuffle.setVisibility(View.INVISIBLE);
            }

        }
        else {
            String v = (String) (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried toggling the buttons with the wrong view: %s", v));
        }

    }

    public void onClickOracle(View view){
        Log.d(TAG, "onClick Oracle");
        if (view.getId() == R.id.b_oracle){
            /*Context context = this.getApplicationContext();
            String text = "Oracling not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            */
            if (this.tvOracle.getVisibility()==View.INVISIBLE) {
                String oracleText = this.myPile.get_first().getOracle();
                String titleText = this.myPile.get_first().getName();
                Log.v(TAG, String.format("firstItem: %s", this.myPile.get_first()));
                this.tvTitle.setText(titleText);
                this.tvTitle.setVisibility(View.VISIBLE);
                this.tvOracle.setText(oracleText);
                this.tvOracle.setVisibility(View.VISIBLE);
            }
            else{
                this.tvOracle.setText("");
                this.tvOracle.setVisibility(View.INVISIBLE);
                this.tvTitle.setVisibility(View.INVISIBLE);
            }
        }
        else {
            String v = ""+(view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried oracling with the wrong view: %s", v));
        }
    }

    public void onClickShuffle(View view){
        Log.d(TAG, "onClick shuffle");
        if (view.getId() == R.id.b_shuffle){
            Context context = this.getApplicationContext();
            String text = "Shuffling not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            String v = (String) (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried shuffling with the wrong view: %s", v));
        }
    }

    public void onClickScry(View view){
        Log.d(TAG, "onClick Scry");
        if (view.getId() == R.id.b_scry){
            Context context = this.getApplicationContext();
            String text = "Scrying not yet implemented!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            String v = (String) (view == null?"null":view.getId());
            Log.w(TAG, String.format("Tried scrying with the wrong view: %s", v));
        }
    }
}
