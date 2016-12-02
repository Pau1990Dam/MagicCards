package com.pau.a14270729b.magiccards.AsyncTask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.alexvasilkov.events.Events;
import com.pau.a14270729b.magiccards.DatabaseSuit.DataManager;
import com.pau.a14270729b.magiccards.MagicCardsApi.MagiCardsApi;
import com.pau.a14270729b.magiccards.Pojos.Card;

import java.util.HashMap;

/**
 * Created by pau on 2/12/16.
 */

public class RefreshTask extends AsyncTask<Object, Object, Object> {
    private Context context;

    public RefreshTask(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("start-downloading-data");
    }

    @Override
    protected Object doInBackground(Object... params) {

        HashMap<String, Card> cards;
        Cursor cursor = DataManager.getCursor(context);
        cards = MagiCardsApi.getAllCartas(cursor.getCount());
        if(cards!=null){
            DataManager.deleteCards(context);
            DataManager.saveCards(cards,context);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object aVoid) {
        super.onPostExecute(aVoid);

        Events.post("finish-downloading-data");
    }

}