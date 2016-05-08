package misc.stack;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.snigelentertainments.snigelgroup.archase.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by rhofmann on 17.03.2016.
 */
public class HeapFactory implements Serializable{

    private static final String TAG = MainActivity.TAG;

    private static HeapFactory factory;

    private static HashMap<String, PileItem> allPlanes = null;

    private static HashMap<String, PileItem> allSchemes = null;

    private  HeapFactory(){
        if (allPlanes == null){

        }
    }

    public static HeapFactory getFactory(){
        if (factory == null){
            factory = new HeapFactory();
        }
        return factory;
    }

    public HashMap<String, PileItem> getAllPlanes(){
        return allPlanes;
    }

    public HashMap<String, PileItem> getAllSchemes(){
        return allSchemes;
    }

    /**
     * @Deprecated only returns values for the planes
     *
     * @return
     */

    public int getPlanePileSize(){
        //check if all sets exist
        if (allPlanes == null){
            Log.w(TAG, "allPlane is null");
            return 0;
        }
        else if (allPlanes.keySet() == null){
            Log.w(TAG, "keySet is null");
            return 0;
        }
        //check relationship betwen keys and values, should be one to one
        if (allPlanes.values() == null){
            Log.w(TAG, "values are null");
        }
        else{
            if (allPlanes.values().size() != allPlanes.keySet().size()){
                Log.w(TAG, "keys and values do not match!");
            }
        }

        return allPlanes.keySet().size();
    }

    public int getSchemePileSize(){
        //check if all sets exist
        if (allSchemes == null){
            Log.w(TAG, "allSchemes is null");
            return 0;
        }
        else if (allSchemes.keySet() == null){
            Log.w(TAG, "keySet is null");
            return 0;
        }
        //check relationship betwen keys and values, should be one to one
        if (allSchemes.values() == null){
            Log.w(TAG, "values are null");
        }
        else{
            if (allSchemes.values().size() != allSchemes.keySet().size()){
                Log.w(TAG, "keys and values do not match!");
            }
        }

        return allSchemes.keySet().size();
    }

    /**
     * Returns  a pile of (unsorted) cards beloning to the deck with the given name
     * @param deckName the decklist name
     * @return a CStack[PileItem] of the deck, null if the name is not known
     */
    public CStack<PileItem> getCardPile(String deckName){
        return null;
    }

    public CStack<PileItem> getRandomCardPile(String type, int number, int maxReps, AssetManager assets){
        CStack<PileItem> tempStack = new CStack<PileItem>();

        if (type.equals("planes")){
            Log.d(TAG, "creating planes pile");
            if (allPlanes == null) {
                createPlanes(assets);
            }

            int pilesize = Math.min(number, HeapFactory.getFactory().getPlanePileSize());
            Log.v(TAG, String.format("Available planes: %d, set size: %d",HeapFactory.getFactory().getPlanePileSize(), pilesize));

            //create pile
            CStack<PileItem> pile =  createPlanesPile(pilesize, maxReps);
            return pile;
        }
        else if (type.equals("schemes")) {
            Log.d(TAG, "creating scheme pile");
            createSchemes(assets);

            int pilesize = Math.min(number, HeapFactory.getFactory().getSchemePileSize());
            Log.v(TAG, String.format("Available schemes: %d, set size: %d, requested: %d",HeapFactory.getFactory().getSchemePileSize(), pilesize, number));

            //create pile
            CStack<PileItem> pile =  createSchemesPile(pilesize, maxReps);
            return pile;
        }
        else{
            Log.e(TAG, String.format("Unknown item pile requested: %s", type));
        }


        return tempStack;
    }

    private CStack<PileItem> createPlanesPile(int number, int maxReps){
        Log.d(TAG, String.format("createPlanesPile #:%d, reps:%d", number, maxReps));

        //TODO follow instruction is not correctly yet
        boolean followRestriction = (HeapFactory.allPlanes.size()) > number;
        Log.v(TAG, String.format("followRestricitnos: %s", followRestriction));
        List<PileItem> rawPile = new LinkedList<PileItem>();
        CStack<PileItem> tempSTack = new CStack<PileItem>();

        Random generator = new Random();
        Object[] values = HeapFactory.allPlanes.values().toArray();
        Log.v(TAG, String.format("#values %d", values.length));
        while(rawPile.size() < number) {
            int randInt = generator.nextInt(values.length);
            Log.v(TAG, String.format("value.size: %d, rand: %d, rawSize: %d, number: %d, fs: %s"
                    , values.length, randInt, rawPile.size(), number, followRestriction));
            PileItem randomValue = (PileItem) values[randInt];
            if (!followRestriction || !rawPile.contains(randomValue))
            {
                Log.v(TAG, String.format("Add %s to raw pile", randomValue));
                rawPile.add(randomValue);
            }
        }

        for (PileItem pi: rawPile){
            tempSTack.add_value_top(pi);
        }
        tempSTack.shuffle();
        return tempSTack;
    }


    private CStack<PileItem> createSchemesPile(int number, int maxReps){
        Log.d(TAG, String.format("createSchemesPile #:%d, reps:%d", number, maxReps));

        //TODO follow instruction is not correctly yet
        boolean followRestriction = (HeapFactory.allSchemes.size()) > number;
        Log.v(TAG, String.format("followRestricitnos: %s", followRestriction));
        List<PileItem> rawPile = new LinkedList<PileItem>();
        CStack<PileItem> tempSTack = new CStack<PileItem>();


        Random generator = new Random();
        Object[] values = HeapFactory.allSchemes.values().toArray();
        Log.v(TAG, String.format("#values %d", values.length));
        while(rawPile.size() < number) {
            int randInt = generator.nextInt(values.length);
            Log.v(TAG, String.format("value.size: %d, rand: %d, rawSize: %d, number: %d, fs: %s"
                    , values.length, randInt, rawPile.size(), number, followRestriction));
            PileItem randomValue = (PileItem) values[randInt];
            if (!followRestriction || !rawPile.contains(randomValue))
            {
                Log.v(TAG, String.format("Add %s to raw pile", randomValue));
                rawPile.add(randomValue);
            }
        }

        for (PileItem pi: rawPile){
            tempSTack.add_value_top(pi);
        }
        tempSTack.shuffle();

        return tempSTack;
    }

    private void createPlanes(AssetManager assets){
        Log.d(TAG, String.format("creating planes with asset %s", assets==null?"null":assets.toString()));

        if (HeapFactory.allPlanes != null){
            //return;
        }

        HashMap<String, PileItem> allPlanes = new HashMap<String, PileItem>();

        String[] files = null;
        try {
            files = assets.list("planes");
            Log.v(TAG, String.format("fils: %s", files));
            if (files != null){
                for (String s:files) {
                    Log.v(TAG, String.format("plane file %s", s));
                    //Drawable d = Drawable.createFromStream(assets.open("planes/" + s), null);
                    String address = "planes/" + s;
                    if (address == null){
                        Log.v(TAG, "drawable could not be created");
                    }
                    PileItem tempPlane = new PlanesItem(s,address,"N/A");
                    allPlanes.put(s, tempPlane);
                    }
                }

        } catch (IOException e) {
            Log.e(TAG, String.format("IO exceotion %s", e.getMessage()));
        }
        Log.v(TAG, String.format("%s", allPlanes));
        HeapFactory.allPlanes = allPlanes;

    }

    private void createSchemes(AssetManager assets){
        Log.d(TAG, String.format("creating schemes with asset %s", assets==null?"null":assets.toString()));

        if (HeapFactory.allSchemes != null){
            //return;
        }

        HashMap<String, PileItem> allSchemes = new HashMap<String, PileItem>();



        String[] files = null;
        try {
            files = assets.list("schemes");
            Log.v(TAG, String.format("fils: %s", files));
            if (files != null){
                for (String s:files) {
                    Log.v(TAG, String.format("scheme file %s", s));
                    //Drawable d = Drawable.createFromStream(assets.open("planes/" + s), null);
                    String address = "schemes/" + s;
                    if (address == null){
                        Log.v(TAG, "drawable could not be created");
                    }
                    PileItem tempScheme = new PlanesItem(s,address,"N/A");
                    allSchemes.put(s, tempScheme);
                }
            }

        } catch (IOException e) {
            Log.e(TAG, String.format("IO exceotion %s", e.getMessage()));
        }
        Log.v(TAG, String.format("allSchemes: %s", allSchemes));
        HeapFactory.allSchemes = allSchemes;

    }

    class PlanesItem implements PileItem{
        private String name;
        private String pic;
        private String oracle;

        public static final long serialVersionUID = 42L;

        @Override
        public String toString(){
            return this.getName();
        }

        PlanesItem(String name, String pic, String oracle) {
            this.name = name;
            this.pic = pic;
            this.oracle = oracle;
        }

        @Override
        public String getOracle() {
            return this.oracle;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getType() {
            return "Planes";
        }

        @Override
        public Object getPicture() {
            return this.pic;
        }
    }

}
