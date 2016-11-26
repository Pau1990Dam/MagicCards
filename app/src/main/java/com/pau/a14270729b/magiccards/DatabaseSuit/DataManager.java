package com.pau.a14270729b.magiccards.DatabaseSuit;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;

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

    public static void saveCards(HashMap<String, Card> cards, Context context) {

        avoidDuplications(context, cards);
        Collection <Card> collection = cards.values();

        Log.i("DEBUG" + " CARDS SIZE ", "eeeeiii : " + cards.size());
        if (cards.size() > 0)
            cupboard().withContext(context).put(CARD_URI, Card.class, collection);
    }

    public static Cursor getCursor(Context context){
        return cupboard().withContext(context).query(CARD_URI, Card.class).getCursor();
    }

    public static void deleteCards(Context context) {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "0");
    }

    public static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARD_URI, null, null, null, null);  //Activity,  Table, Projection, Select, arguments (where if ...), order (order by)
    }

    private static void avoidDuplications(Context context, HashMap<String, Card> cards) {

        QueryResultIterable<Card> itr = cupboard().withContext(context).query(CARD_URI, Card.class).
                query();
        for(Card card: itr){
            Log.i("DEBUG"+" CARDS SIZE ", "eeeeiii : "+card.toString());
            cards.remove(card.toString());
        }
    }

}
