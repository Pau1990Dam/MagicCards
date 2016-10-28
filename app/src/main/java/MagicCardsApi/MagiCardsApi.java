package MagicCardsApi;

import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import HttpPetition.HttpUtils;
import Pojos.Card;

/**
 * Created by 14270729b on 14/10/16.
 */

public class MagiCardsApi {
    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public ArrayList <Card> getCartas() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }

    public ArrayList <Card> getCartasByRarity(String kind) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity", kind)
                .build();
        String url = builtUri.toString();

        return getJson(url);
    }

    @Nullable
    private ArrayList <Card> getJson(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return jsonParser(JsonResponse);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private ArrayList<Card>jsonParser(String jsonResponse) {
        ArrayList<Card> cartas = new ArrayList<>();
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
                    card.setText("");
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