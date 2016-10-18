package MagicCardsApi;

import android.net.Uri;

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

        try {
            String JsonResponse = HttpUtils.get(url);
            ArrayList<Card> cartas = new ArrayList<>();

            JSONObject data = new JSONObject(JsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for(int i=0; i < jsonCards.length();i++){
                Card card = new Card();
                JSONObject object = jsonCards.getJSONObject(i);

                card.setName(object.getString("name"));
                card.setImageUrl(object.getString("imageUrl"));
                card.setRarity(object.getString("rarity"));
                card.setType(object.getString("type"));

                if(object.has("text"))
                    card.setText(object.getString("text"));
                else
                    card.setText("");

                cartas.add(card);
            }

            return cartas;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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