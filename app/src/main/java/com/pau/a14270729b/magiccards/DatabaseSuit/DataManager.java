package com.pau.a14270729b.magiccards.DatabaseSuit;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.CursorAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.pau.a14270729b.magiccards.Pojos.Card;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by 14270729b on 11/11/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    public static void saveCards(HashSet<Card> cards, Context context) {

        avoidDuplications(context, cards);

        Log.i("DEBUG" + " CARDS SIZE ", "eeeeiii : " + cards.size());
        if (cards.size() > 0)
            cupboard().withContext(context).put(CARD_URI, Card.class, cards);
    }

    public static void deleteCards(Context context) {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "0");
    }

    public static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARD_URI, null, null, null, null);
    }

    static void avoidDuplications(Context context, HashSet<Card> cards) {
        Iterator iterator = cards.iterator();
        while (iterator.hasNext()){
            if((cupboard().withContext(context).
                    query(CARD_URI, Card.class).withSelection("name = ?", ((Card)iterator.next()).getName()).get())
                    != null){
                Log.i("DEBUG"+" CARDS SIZE ", "eeeeiii : "+iterator.next().toString());
                iterator.remove();
            }
        }
    }

}
       /* QueryResultIterable<Card> itr = cupboard().withContext(context).query(CARD_URI, Card.class).
                query();
        for(Card exit: itr){
            Log.i("DEBUG"+" CARDS SIZE ", "eeeeiii : "+exit.toString());

            if( cards.contains(exit) ){ cards.remove(exit);}}
    }*/

/*
    static void avoidDuplications(Context context, HashSet<Card> cards) {

        ArrayList<Integer> arr = new ArrayList<>();
        int i = 0;
        for(Card card: cards){
            if((cupboard().withContext(context).
                    query(CARD_URI, Card.class).withSelection("name = ?", card.getName()).get())
                     != null){
                arr.add(i);

            }
            i++;
        }
        Log.i("DEBUG" + " REPETIDA", "SIZE 2 : "+arr.size());
        for(Integer j: arr){
            Log.i("DEBUG" + " REPETIDA", "REMOVE CARD : "+cards.get(j).toString());
            cards.remove((int)j);
        }

    }*/