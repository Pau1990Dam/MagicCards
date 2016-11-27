package com.pau.a14270729b.magiccards;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.pau.a14270729b.magiccards.ShowDataFromDatabase.CardsCursorAdapter;
import com.pau.a14270729b.magiccards.databinding.FragmentMainBinding;

import java.util.HashMap;

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
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    public MainActivityFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMainBinding bindig = DataBindingUtil.inflate
                (inflater, R.layout.fragment_main, container, false);
        view = bindig.getRoot();

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

        mCallbacks = this;
        getLoaderManager().initLoader(0,null,mCallbacks);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                getLoaderManager().restartLoader(0, null, mCallbacks);
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(prefListener);


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

            HashMap<String, Card> cards;
            Cursor cursor = DataManager.getCursor(getContext());
            cards = MagiCardsApi.getAllCartas(cursor.getCount());
            if(cards!=null){
                DataManager.deleteCards(getContext());
                DataManager.saveCards(cards,getContext());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();
        }

    }

}
