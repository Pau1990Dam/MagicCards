package com.pau.a14270729b.magiccards.DatabaseSuit;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import com.pau.a14270729b.magiccards.Pojos.Card;
import com.pau.a14270729b.magiccards.provider.CardsProvider;
import com.pau.a14270729b.magiccards.provider.CardsSQLiteOpenHelper;
import com.pau.a14270729b.magiccards.provider.CardsSQLiteOpenHelperCallbacks;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;
import com.pau.a14270729b.magiccards.provider.cards.CardsContentValues;
import com.pau.a14270729b.magiccards.provider.cards.CardsCursor;
import com.pau.a14270729b.magiccards.provider.cards.CardsSelection;


public class DataManager {

    public static void saveCards(ArrayList<Card> cards, Context context) {
        ArrayList <ContentValues> arr = new ArrayList<>();
        ContentValues [] bulkToInsert = new ContentValues[cards.size()];
        for(Card card: cards){
            CardsContentValues values = new CardsContentValues();
            values.putCardId(card.getId());
            values.putCmc(card.getCmc());
            values.putColors(card.getColors());
            values.putFlavor(card.getFlavor());
            values.putImageurl(card.getImageUrl());
            values.putName(card.getName());
            values.putPower(card.getPower());
            values.putRarity(card.getRarity());
            values.putText(card.getText());
            values.putToughness(card.getToughness());
            values.putType(card.getType());
            arr.add(values.values());
        }
        arr.toArray(bulkToInsert);
        context.getContentResolver().bulkInsert(CardsColumns.CONTENT_URI,bulkToInsert);
    }

    public static Cursor getCursor(Context context){
        return context.getContentResolver().query(CardsColumns.CONTENT_URI,null,null,null,null);
    }

    public static void deleteCards(Context context) {
        context.getContentResolver().delete(CardsColumns.CONTENT_URI,null,null);
    }
    public static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context,CardsColumns.CONTENT_URI,null,null,null,null);
    }
/*
    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    public static void saveCards(HashMap<String, Card> cards, Context context) {

      //  avoidDuplications(context, cards);
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> activeRarities = new HashSet<>();
        Set<String> activeColors = new HashSet<>();
        String queryWords;

        activeRarities = preferences.getStringSet("rarity", activeRarities);
        activeColors = preferences.getStringSet("color", activeColors);
        queryWords = preferences.getString("word","");
        int qWargsSizeCorrector = 0;
        if(!queryWords.equals(""))qWargsSizeCorrector=1;

        String select = "";
        String args [] = new String[0];
        if(activeColors.size()+activeRarities.size()==37)return new CursorLoader(context, CARD_URI,
                null, null, null, null);
        select+="(";
        int i = 0;
        if(!activeColors.isEmpty()&&!activeRarities.isEmpty()){
            args = new String[activeColors.size()+activeRarities.size()+qWargsSizeCorrector];
            for(String color: activeColors){
                select+="colors=? OR ";
                args[i]=color;
                i++;
            }
            select = select.substring(0,select.length()-3);
            select += ") AND (";
            for(String rarity: activeRarities){
                select+="rarity=? OR ";
                args[i]=rarity;
                i++;
            }
            select = select.substring(0,select.length()-3);

        }else if(!activeColors.isEmpty()){
            args = new String[activeColors.size()+qWargsSizeCorrector];
            for(String color: activeColors){
                select+="colors=? OR ";
                args[i]=color;
                i++;
            }
            select = select.substring(0,select.length()-3);
        }else if(!activeRarities.isEmpty()){
            args = new String[activeRarities.size()+qWargsSizeCorrector];
            for(String rarity: activeRarities){
                select+="rarity=? OR ";
                args[i]=rarity;
                i++;
            }
            select = select.substring(0,select.length()-3);
        }else if(queryWords.equals("")||queryWords==null)
            return new CursorLoader(context, CARD_URI, null, null, null, null);


        if(!queryWords.equals("")) {
            if (!activeColors.isEmpty() || !activeRarities.isEmpty()) {
                select += ") AND (IFNULL (cmc, ' ' )|| ' ' ||  IFNULL (colors, ' ' ) ||  ' ' || " +
                        "IFNULL (flavor, ' ') ||  ' ' || IFNULL (name, ' ') || ' ' || " +
                        "IFNULL (power, ' ') ||  ' ' ||  IFNULL (rarity, ' ' )||  ' ' || " +
                        "IFNULL (text, ' ' ) ||  ' ' ||  IFNULL (toughness, ' ') ||  ' ' || " +
                        "IFNULL (type, ' ') ) LIKE ?";
                args[args.length - 1] = "%" + queryWords + "%";
            } else {
                select += "IFNULL (cmc, ' ' )|| ' ' ||  IFNULL (colors, ' ' ) ||  ' ' || " +
                        "IFNULL (flavor, ' ') ||  ' ' || IFNULL (name, ' ') || ' ' || " +
                        "IFNULL (power, ' ') ||  ' ' ||  IFNULL (rarity, ' ' )||  ' ' || " +
                        "IFNULL (text, ' ' ) ||  ' ' ||  IFNULL (toughness, ' ') ||  ' ' || " +
                        "IFNULL (type, ' ') ) LIKE ?";
                args = new String[]{"%" + queryWords + "%"};
            }
        }else
            select+=")";


        System.out.println("Resultado select: "+select);
        System.out.println("Resultado: "+Arrays.toString(args));

        return new CursorLoader(context, CARD_URI, null, select, args, null);  //Activity,  Table, Columnas, clausulas (where if...), cierre de las clausulas, order (order by)
    }
*/
}
