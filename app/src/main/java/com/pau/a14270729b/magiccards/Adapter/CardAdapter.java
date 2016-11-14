package com.pau.a14270729b.magiccards.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.R;
import com.pau.a14270729b.magiccards.databinding.CartasFilaBinding;


import java.util.List;

import com.pau.a14270729b.magiccards.Pojos.Card;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 14270729b on 28/10/16.
 */

public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Card card = getItem(position);
        Log.i("", card.toString());

        CartasFilaBinding binding =null;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.cartas_fila, parent, false);
        }
        else
            binding = DataBindingUtil.getBinding(convertView);



        binding.carta.setText(card.getName());
        binding.type.setText(card.getType());
        binding.rarity.setText(card.getRarity());
        binding.text.setText(card.getText());
        Glide.with(getContext()).load(card.getImageUrl()).bitmapTransform(
                new RoundedCornersTransformation(getContext(),14,1)).into(binding.imgUrl);

        return binding.getRoot();
    }
}
