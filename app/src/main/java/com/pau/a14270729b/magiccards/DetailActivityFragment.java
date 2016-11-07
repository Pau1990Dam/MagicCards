package com.pau.a14270729b.magiccards;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import Pojos.Card;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private ImageView imageDetails;
    private TextView power;
    private TextView rarity;
    private TextView type;
    private TextView colors;
    private TextView text;
    private TextView cmc;
    private View view;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent dataCaught = getActivity().getIntent();

        if(dataCaught != null){
            Card card = (Card) dataCaught.getSerializableExtra("card");

            if(card != null){
                updateFrData(card);
            }
        }

        return view;
    }

    private void updateFrData(Card card) {
        Log.i("Carta ","   " +card.toString());

        getActivity().setTitle(card.getName().toUpperCase());

        imageDetails =  (ImageView) view.findViewById(R.id.imageDetail);
        power = (TextView) view.findViewById(R.id.power);
        type = (TextView) view.findViewById(R.id.type);
        rarity = (TextView) view.findViewById(R.id.rarity);
        colors = (TextView) view.findViewById(R.id.colors);
        text = (TextView) view.findViewById(R.id.text);
        cmc = (TextView) view.findViewById(R.id.cmc);

        Glide.with(getContext()).load(card.getImageUrl()).bitmapTransform(
                new RoundedCornersTransformation(getContext(),14,1)).into(imageDetails);
        power.setText(Html.fromHtml("<b>POWER : </b>"+card.getPower()));
        type.setText(Html.fromHtml("<b>TYPE : </b>"+card.getType()));
        rarity.setText(Html.fromHtml("<b>RARITY : </b>"+card.getRarity()));
        colors.setText(Html.fromHtml("<b>COLORS : </b>"+Arrays.toString(card.getColors())));
        cmc.setText(Html.fromHtml("<b>MANA COST : </b>"+card.getCmc()));
        text.setText(Html.fromHtml("<b>INFO : </b>"+card.getText()));



    }
}
