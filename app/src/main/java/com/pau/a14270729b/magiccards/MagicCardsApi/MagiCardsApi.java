package com.pau.a14270729b.magiccards.MagicCardsApi;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;


import com.pau.a14270729b.magiccards.HttpPetition.HttpUtils;
import com.pau.a14270729b.magiccards.Pojos.Card;

//https://docs.magicthegathering.io/

public class MagiCardsApi {
    private static final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public static HashMap<String, Card> getAllCartas(int dbCards) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("pageSize","1")
                .build();
        String url = builtUri.toString();

        return getAllPages(url, dbCards);
    }

    public static HashMap<String, Card> getAllPages(String url, int dbCards){
        try {
                int numCards = HttpUtils.getTotalCount(url);

                if(numCards==dbCards && numCards>0)return null;

                final int PAGES;

                if(numCards%100>0)
                    PAGES = (numCards/100)+1;
                else
                    PAGES = numCards/100;

            HashMap<String,Card> response = new HashMap<>();
            for(int i = 1; i<=PAGES;i++){
                Uri builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("page",String.valueOf(i))
                        .build();
                String jsonUrl = builtUri.toString();
                getJson(jsonUrl,response);
            }

            return response;

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private static void getJson(String url, HashMap<String, Card> cards) {
        try {
            String JsonResponse = HttpUtils.get(url);
            jsonParser(JsonResponse, cards);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void jsonParser(String jsonResponse, HashMap<String, Card> cards) {
        String [] cardColors;
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for(int i=0; i < jsonCards.length();i++){
                Card card = new Card();
                JSONObject object = jsonCards.getJSONObject(i);

                card.setName(object.getString("name"));
                card.setRarity(object.getString("rarity"));
                card.setType(object.getString("type"));

                if(object.has("imageUrl"))
                    card.setImageUrl(object.getString("imageUrl"));
                else
                    card.setImageUrl("");

                if(object.has("text"))
                    card.setText(object.getString("text"));
                else
                    card.setText("none");

                if(object.has("id"))
                    card.setId(object.getString("id"));
                else
                    card.setId(card.getName());

                if(object.has("power"))
                    card.setPower(object.getString("power"));
                else
                    card.setPower("nd");

                if(object.has("cmc"))
                    card.setCmc(object.getInt("cmc"));
                else
                    card.setCmc(0);

                if(object.has("colors")) {
                    JSONArray colors = object.getJSONArray("colors");
                    cardColors = new String[colors.length()];

                    for(int j = 0; j < colors.length(); j++){
                        cardColors[j] = colors.getString(j);
                    }
                    card.setColors(Arrays.toString(cardColors));
                }
                else
                    card.setColors("nd");

                cards.put(card.toString(),card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

/*
import android.net.Uri;

import java.io.IOException;

public class RottenTomatoesAPI {
    private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";

    String getPeliculesMesVistes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath("box_office.json")
                .appendQueryParameter("country", pais)
                .build();
        String url = builtUri.toString();

        try {
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
 */