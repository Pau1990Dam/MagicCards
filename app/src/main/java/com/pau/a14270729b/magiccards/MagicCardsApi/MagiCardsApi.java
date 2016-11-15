package com.pau.a14270729b.magiccards.MagicCardsApi;

import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.pau.a14270729b.magiccards.HttpPetition.HttpUtils;
import com.pau.a14270729b.magiccards.Pojos.Card;

/**
 * Created by 14270729b on 14/10/16.
 */

//https://docs.magicthegathering.io/

public class MagiCardsApi {
    private static final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public static ArrayList <Card> getCartas() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }

    public static ArrayList <Card> getCartasByRarity(String kind) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity", kind)
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }

    public static ArrayList <Card> getCartasByColor(String kind) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("colors", kind)
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }

    public static ArrayList <Card> getCartasByRaritiesAndColors(String kind, String Kind2) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity", kind)
                .appendQueryParameter("colors", Kind2)
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }


    @Nullable
    private static ArrayList <Card> getJson(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return jsonParser(JsonResponse);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Card>jsonParser(String jsonResponse) {
        ArrayList<Card> cartas = new ArrayList<>();
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

                cartas.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cartas;
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