package com.pau.a14270729b.magiccards;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alexvasilkov.events.Events;
import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.databinding.FragmentDetailBinding;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;
import com.pau.a14270729b.magiccards.provider.cards.CardsCursor;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        Events.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        View view = binding.getRoot();

        Intent dataCaught = getActivity().getIntent();

        if(dataCaught != null){
            Long card_id = dataCaught.getLongExtra("card_id",-1);

            if(card_id != -1){
                updateFrData(card_id);
            }
        }

        return view;
    }

    @Events.Subscribe("card-selected")
    private void onCardSelected(Long card_id){
        if(card_id!=null)
            updateFrData(card_id);
    }
    private void updateFrData(Long card_id) {

        Cursor cursor = getContext().getContentResolver().query(
                CardsColumns.CONTENT_URI,
                null,
                CardsColumns._ID + " = ?",
                new String[]{String.valueOf(card_id)},
                null
        );

        CardsCursor cardsCursor = new CardsCursor(cursor);
        cardsCursor.moveToNext();

        getActivity().setTitle(cardsCursor.getName().toUpperCase());

        if(!cardsCursor.getImageurl().equals(""))
            Glide.with(getContext()).load(cardsCursor.getImageurl()).bitmapTransform(
                    new RoundedCornersTransformation(getContext(),14,1)).into(binding.imageDetail);
        else
            Glide.with(getContext()).load(R.drawable.alt_cardback).bitmapTransform(
                new RoundedCornersTransformation(getContext(),14,1)).into(binding.imageDetail);

        binding.powerToughness.setText(Html.fromHtml("<b>"+cardsCursor.getPower()+" / "+
                cardsCursor.getToughness()+"</b>"));
        binding.type.setText(Html.fromHtml("<b>TYPE : </b>"+cardsCursor.getType()));
        binding.rarity.setText(Html.fromHtml("<b>RARITY : </b>"+cardsCursor.getRarity()));
        binding.cmc.setText(Html.fromHtml("<b>CONVERTED MANA COST : </b>"+cardsCursor.getCmc()));
        binding.text.setText(Html.fromHtml("<b>INFO : </b>"+cardsCursor.getText()));
        binding.flavor.setText(Html.fromHtml(cardsCursor.getFlavor()));
        ImageView [] img = {binding.color1,binding.color2,binding.color3,binding.color4,binding.color5};


        String arr [] = cardsCursor.getColors().split(" ");
        LinearLayout.LayoutParams params =  new LinearLayout
                .LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT);
        Context context = getContext();

        for(int i = 0; i < arr.length; i++){

            //ImageView c = new ImageView(context);
            //c.setLayoutParams(params);

            switch (arr[i]) {
                case "White":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_white).into(img[i]);
                    break;
                case "Black":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_black).into(img[i]);
                    break;
                case "Red":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_red).into(img[i]);
                    break;
                case "Green":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_green).into(img[i]);
                    break;
                case "Blue":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_blue).into(img[i]);
                    break;
                case "Colorless":
                    img[i].setLayoutParams(params);
                    Glide.with(context).load(R.drawable.ic_colorless).into(img[i]);
                    break;
            }
        }
    }
}



/*
String arr [] = cardsCursor.getColors().split(" ");
        LinearLayout.LayoutParams params =  new LinearLayout
                .LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT);
boolean activateJump;
        if(layout.getChildCount()==0) {
            jump:
            for (int i = 0; i < arr.length; i++) {
                ImageView color = new ImageView(context);
                color.setLayoutParams(params);
                activateJump = true;
                switch (arr[i]) {
                    case "White":
                        if (layout.findViewById(R.id.white) == null) {
                            color.setId(R.id.white);
                            Glide.with(context).load(R.drawable.ic_white).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                    case "Black":
                        if (layout.findViewById(R.id.black) == null) {
                            color.setId(R.id.black);
                            Glide.with(context).load(R.drawable.ic_black).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                    case "Red":
                        if (layout.findViewById(R.id.red) == null) {
                            color.setId(R.id.red);
                            Glide.with(context).load(R.drawable.ic_red).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                    case "Green":
                        if (layout.findViewById(R.id.green) == null) {
                            color.setId(R.id.green);
                            Glide.with(context).load(R.drawable.ic_green).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                    case "Blue":
                        if (layout.findViewById(R.id.blue) == null) {
                            color.setId(R.id.blue);
                            Glide.with(context).load(R.drawable.ic_blue).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                    case "Colorless":
                        if (layout.findViewById(R.id.colorless) == null) {
                            color.setId(R.id.colorless);
                            Glide.with(context).load(R.drawable.ic_colorless).into(color);
                            activateJump = false;
                        }
                        if (activateJump) break jump;
                        break;
                }

                layout.addView(color);
            }
        }
 */