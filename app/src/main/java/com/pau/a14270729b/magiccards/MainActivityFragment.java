package com.pau.a14270729b.magiccards;

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

import java.util.ArrayList;
import java.util.Arrays;

import MagicCardsApi.MagiCardsApi;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View view;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }


    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lista = (ListView) view.findViewById(R.id.lvCartas);

        String test [] = {
                "Blue Ward",
                "Bog Wraith",
                "Blue Ward",
                "Bog Wraith",
                "Blue Ward",
                "Bog Wraith"
        };

        items = new ArrayList<>(Arrays.asList(test));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.cartas_fila,
                R.id.carta,
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

    private void refresh() {
        MagiCardsApi api = new MagiCardsApi();
        String result = api.getCartas();

        Log.d("DEBUG", result);

    }

}
