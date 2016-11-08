package com.pau.a14270729b.magiccards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pau.a14270729b.magiccards.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Adapter.CardAdapter;
import MagicCardsApi.MagiCardsApi;
import Pojos.Card;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View view;
    ArrayList<Card> items;
    private CardAdapter adapter;
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

        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.cartas_fila,
                items
        );
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

            refresh();
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

    private class RefreshTask extends AsyncTask<Object, Object, ArrayList<Card>>{
        @Override
        protected ArrayList<Card> doInBackground(Object... params) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Set<String> activeRarities = new HashSet<>();
            activeRarities = preferences.getStringSet("rarity", activeRarities);

            Log.i("DEBUG","Cheeeee  "+ String.valueOf(activeRarities.size()));

            ArrayList<Card> cards;
            MagiCardsApi api = new MagiCardsApi();
            petition.setLength(0);
            for(String str: activeRarities){
                petition.append(str).append(",");
            }
            cards = api.getCartasByRarity(petition.toString());
            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> arr) {

            adapter.clear();
            for(Card card: arr){
                adapter.add(card);
            }
        }
    }

}
