package com.pau.a14270729b.magiccards;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.pau.a14270729b.magiccards.ShowDataFromDatabase.CardsCursorAdapter;
import com.pau.a14270729b.magiccards.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import com.pau.a14270729b.magiccards.DatabaseSuit.DataManager;
import com.pau.a14270729b.magiccards.MagicCardsApi.MagiCardsApi;
import com.pau.a14270729b.magiccards.Pojos.Card;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    View view;
    private ProgressDialog dialog;
    private CardsCursorAdapter adapter;
    private StringBuilder petition;

    public MainActivityFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentMainBinding bindig = DataBindingUtil.inflate
                (inflater, R.layout.fragment_main, container, false);
        view = bindig.getRoot();

        petition = new StringBuilder();

        adapter = new CardsCursorAdapter(getContext(), Card.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");

        bindig.lvCartas.setAdapter(adapter);
        bindig.lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card card = (Card) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(),DetailActivity.class);
                intent.putExtra("card",card);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(0,null,this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_refresh) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        RefreshTask task = new RefreshTask();
        task.execute();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    private class RefreshTask extends AsyncTask<Object, Object, Object>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.show();
        }

        @Override
        protected Object doInBackground(Object... params) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Set<String> activeRarities = new HashSet<>();
            Set<String> activeColors = new HashSet<>();
            boolean colorsAndOr = true;

            activeRarities = preferences.getStringSet("rarity", activeRarities);
            activeColors = preferences.getStringSet("color", activeColors);
            colorsAndOr = preferences.getBoolean("ColorsAndOr",colorsAndOr);

            Log.i("DEBUG","Cheeeee  "+ String.valueOf(activeRarities.size())+" Colors size: "+
                    String.valueOf(activeColors.size()));

            ArrayList<Card> cards;

            TreeMap <String,String> parameters = petitionParametersPreparation(activeRarities,activeColors,colorsAndOr);

            if(parameters.size() == 2){
                cards = MagiCardsApi.getCartasByRaritiesAndColors(parameters.get("rarity"),parameters.get("color"));
            }else if(parameters.size() == 1 ){

                if(parameters.containsKey("rarity"))
                    cards = MagiCardsApi.getCartasByRarity(parameters.get("rarity"));
                else
                    cards = MagiCardsApi.getCartasByColor(parameters.get("color"));
            }else{
                cards = MagiCardsApi.getCartas();
            }
            Log.i("DEBUG"+" INFOOOOOOOOO ", cards != null ? cards.toString() : null);
            DataManager.deleteCards(getContext());
            DataManager.saveCards(cards,getContext());

            //return cards;
            return null;
        }


        @Override
        protected void onPostExecute(Object aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();
        }

    }

    public TreeMap<String, String> petitionParametersPreparation(Set<String> rarities, Set<String> colors,
                                                                 Boolean switchColorsValue){
        TreeMap <String,String>parametersToUse = new TreeMap<>();

        if(rarities.size()>0) {
            petition.setLength(0);
            for (String str : rarities) {
                petition.append(str).append(",");
            }
            parametersToUse.put("rarity",petition.toString());
        }


        if(colors.size()>0) {

            String AndOroperator;
            if(switchColorsValue)
                AndOroperator = ",";
            else
                AndOroperator = "|";

            petition.setLength(0);
            for (String str : colors) {
                petition.append(str).append(AndOroperator);
            }
            parametersToUse.put("color",petition.toString());
        }
        Log.i("DEBUG","AAAAAAAAAAAAA  "+ parametersToUse.containsKey("rarity")+" "+parametersToUse.containsKey("color")+" "+parametersToUse.get("color")+" "+parametersToUse.get("rarity") );
        return parametersToUse;
    }

}
