package com.pau.a14270729b.magiccards;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
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
import com.alexvasilkov.events.Events;
import com.pau.a14270729b.magiccards.AsyncTask.RefreshTask;
import com.pau.a14270729b.magiccards.CardsCursorAdapter.CardsCursorAdapter;
import com.pau.a14270729b.magiccards.databinding.FragmentMainBinding;
import com.pau.a14270729b.magiccards.DatabaseSuit.DataManager;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;


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

        adapter = new CardsCursorAdapter(getContext(),
                R.layout.activity_main,
                null,
                new String[]{
                        CardsColumns.NAME,
                        CardsColumns.TYPE,
                        CardsColumns.RARITY,
                        CardsColumns.COLORS,
                        CardsColumns.IMAGEURL
                },
                new int[]{
                        R.id.carta,
                        R.id.type,
                        R.id.rarity,
                        R.id.imgUrl
                },
                0);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");

        bindig.lvCartas.setAdapter(adapter);
        bindig.lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!esTablet()){
                    Intent intent = new Intent(getContext(),DetailActivity.class);
                    intent.putExtra("card_id",id);
                    startActivity(intent);
                }else
                    Events.create("card-selected").param(id).post();
            }
        });

        mCallbacks = this;

        getLoaderManager().initLoader(0,null,mCallbacks);

        return view;
    }

    @Events.Subscribe("start-downloading-data")
    void preRefresh() {
        dialog.show();
    }

    @Events.Subscribe("finish-downloading-data")
    void afterRefresh() {
        dialog.dismiss();
    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
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
        Events.register(this);
    }

    @Override
    public void onResume() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if(isAdded())
                    getLoaderManager().restartLoader(0, null, mCallbacks);
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(prefListener);

        super.onResume();
    }

    private void refresh() {
        RefreshTask task = new RefreshTask(getActivity().getApplicationContext());
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



}
