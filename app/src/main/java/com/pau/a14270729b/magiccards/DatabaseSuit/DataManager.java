package com.pau.a14270729b.magiccards.DatabaseSuit;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
                select += ") AND (_id || ' ' ||  IFNULL (cmc, ' ' )|| ' ' ||  IFNULL (colors, ' ' )" +
                        "||  ' ' ||   IFNULL (flavor, ' ') ||  ' '  || IFNULL ( id, ' ') || ' ' ||" +
                        "  IFNULL (imageUrl, ' ') ||  ' ' ||  IFNULL (name, ' ') || ' ' ||" +
                        "   IFNULL (power, ' ') ||  ' ' ||  IFNULL (rarity, ' ' )||  ' ' ||" +
                        "  IFNULL ( text, ' ' ) ||  ' ' ||  IFNULL (toughness, ' ') ||  ' ' ||" +
                        "  IFNULL (type, ' ') || ' ') LIKE ?";
                args[args.length - 1] = "%" + queryWords + "%";
            } else {
                select += "_id || ' ' ||  IFNULL (cmc, ' ' )|| ' ' ||  IFNULL (colors, ' ' )" +
                        "||  ' ' ||   IFNULL (flavor, ' ') ||  ' '  || IFNULL ( id, ' ') || ' ' ||" +
                        "  IFNULL (imageUrl, ' ') ||  ' ' ||  IFNULL (name, ' ') || ' ' ||" +
                        "   IFNULL (power, ' ') ||  ' ' ||  IFNULL (rarity, ' ' )||  ' ' ||" +
                        "  IFNULL ( text, ' ' ) ||  ' ' ||  IFNULL (toughness, ' ') ||  ' ' ||" +
                        "  IFNULL (type, ' ') || ' ') LIKE ?";
                args = new String[]{"%" + queryWords + "%"};
            }
        }else
            select+=")";


        System.out.println("Resultado select: "+select);
        System.out.println("Resultado: "+Arrays.toString(args));

        return new CursorLoader(context, CARD_URI, null, select, args, null);  //Activity,  Table, Columnas, clausulas (where if...), cierre de las clausulas, order (order by)
    }

}
