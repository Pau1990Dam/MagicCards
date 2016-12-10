package com.pau.a14270729b.magiccards.CardsCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.R;
import com.pau.a14270729b.magiccards.provider.cards.CardsCursor;
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
        TextView text = (TextView) convertView.findViewById(R.id.text);
        ImageView imageUrl = (ImageView) convertView.findViewById(R.id.imgUrl);

        name.setText(cardsCursor.getName());
        type.setText(cardsCursor.getType());
        rarity.setText(cardsCursor.getRarity());
        text.setText(cardsCursor.getText());

        if(!cardsCursor.getImageurl().equals(""))
            Glide.with(context).load(cardsCursor.getImageurl()).bitmapTransform(
                    new RoundedCornersTransformation(context,14,1)).into(imageUrl);
        else
            Glide.with(context).load(R.drawable.alt_cardback).bitmapTransform(
                    new RoundedCornersTransformation(context,14,1)).into(imageUrl);
        return convertView;
    }
}
