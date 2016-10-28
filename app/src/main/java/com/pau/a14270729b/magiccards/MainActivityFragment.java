package com.pau.a14270729b.magiccards;

import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Adapter.CardAdapter;
import MagicCardsApi.MagiCardsApi;
import Pojos.Card;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View view;
    private ArrayList<Card> items;
    private CardAdapter adapter;

    public MainActivityFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lista = (ListView) view.findViewById(R.id.lvCartas);

        items = new ArrayList<>();
        adapter = new CardAdapter(
                getContext(),
                R.layout.cartas_fila,
                items
        );
        lista.setAdapter(adapter);

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

    //
    private class RefreshTask extends AsyncTask<Object, Object, ArrayList<ArrayList<Card>>>{
        @Override
        protected ArrayList<ArrayList<Card>> doInBackground(Object... params) {
            //Queda millorar el codi.

            String [] selection ;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Set<String> activeRarities = new HashSet<String>();
            //Leemos El Set de String correspondiente al MultiSelectListPreference y lo metemos un array de Strings
            activeRarities = preferences.getStringSet("rarity", activeRarities);
            //Por alguna razón el getDefaultSharedPreferences no pilla el valor x defecto, metido en el xml, cuando la app se abre x primera vez
            //Por eso lo pongo yo a manubrio.
           /* if(activeRarities.size()==0)selection = new String[]{"0"};
            else ;*/

            selection = activeRarities.toArray(new String[]{});
            Log.i("DEBUG","Cheeeee  "+ String.valueOf(selection.length));

            ArrayList<ArrayList<Card>> nodes = new ArrayList<>();
            MagiCardsApi api = new MagiCardsApi();

            for(String str: selection){
                /*Como el array de Strings contiene las posiciones correspondientes a las entradas seleccionada del MultiSelectListPreference
                * hay que traduccir estas posiciones al valor que se refieren. */
                switch(str){
                    case "0":
                        nodes.add(api.getCartasByRarity("common"));
                        break;
                    case "1":
                        nodes.add(api.getCartasByRarity("uncommon"));
                        break;
                    case "2":
                        nodes.add(api.getCartasByRarity("mythic rare"));
                        break;
                    case "3":
                        nodes.add(api.getCartasByRarity("special"));
                        break;
                    case "4":
                        nodes.add(api.getCartasByRarity("basic land"));
                        break;
                }

            }

            return nodes;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<Card>> cardsArrays) {

            adapter.clear();
            for(ArrayList<Card> arr: cardsArrays){
                for(Card carta: arr){
                    adapter.add(carta);
                }
            }

        }
    }

}
