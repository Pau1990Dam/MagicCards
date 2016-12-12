package com.pau.a14270729b.magiccards.CardsCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.R;
import com.pau.a14270729b.magiccards.provider.cards.CardsCursor;

import java.util.Arrays;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CardsCursorAdapter extends SimpleCursorAdapter {
    private final Context context;
    public CardsCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cursor cursor = getCursor();
        CardsCursor cardsCursor = new CardsCursor(cursor);
        cardsCursor.moveToPosition(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cartas_fila,parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.carta);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView rarity = (TextView) convertView.findViewById(R.id.rarity);
        TextView colors =(TextView) convertView.findViewById(R.id.colors);
        ImageView imageUrl = (ImageView) convertView.findViewById(R.id.imgUrl);

        name.setText(cardsCursor.getName());
        type.setText(cardsCursor.getType());
        rarity.setText(cardsCursor.getRarity());

        String arr [] = cardsCursor.getColors().split(" ");
        String colorFormater="";

        for(String color: arr){
            switch (color){
                case "White":
                    colorFormater+= "<font color=#ffffff>"+" "+color+"</font>";
                    break;
                case "Black":
                    colorFormater+= "<font color=#000000>"+" "+color+"</font>";
                    break;
                case "Blue":
                    colorFormater+= "<font color=#000080>"+" "+color+"</font>";
                    break;
                case "Red":
                    colorFormater+= "<font color=#ff0000>"+" "+color+"</font>";
                    break;
                case "Green":
                    colorFormater+= "<font color=#2eb82e>"+" "+color+"</font>";
                    break;
                case "Colorless":
                    colorFormater+= "<font color=#476b6b>"+" "+color+"</font>";
                    break;
            }
        }

        colors.setText(Html.fromHtml(colorFormater));

        if(!cardsCursor.getImageurl().equals(""))
            Glide.with(context).load(cardsCursor.getImageurl()).bitmapTransform(
                    new RoundedCornersTransformation(context,14,1)).into(imageUrl);
        else
            Glide.with(context).load(R.drawable.alt_cardback).bitmapTransform(
                    new RoundedCornersTransformation(context,14,1)).into(imageUrl);

        return convertView;
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